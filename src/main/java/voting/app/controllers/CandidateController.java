package voting.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import voting.app.enums.VotingResultStatus;
import voting.app.models.Candidate;
import voting.app.models.CandidateVoteCountDetails;
import voting.app.models.VoteCastingDetails;
import voting.app.service.ApplicationPropertyServiceImpl;
import voting.app.service.CandidateServiceImpl;
import voting.app.service.VoteCastingServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CandidateController {

    @Autowired
    private CandidateServiceImpl CandidateServiceImpl;

    @Autowired
    private VoteCastingServiceImpl voteCastingService;

    @Autowired
    private ApplicationPropertyServiceImpl applicationPropertyService;

    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    final SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @RequestMapping(value = "/candidate-index", method = RequestMethod.GET)
    public ModelAndView candidateIndexPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("candidateIndex");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("candidateIndex");
            } else {
                model.setViewName("redirect:/candidateIndex");
            }
        }
        return model;
    }

    @RequestMapping(value = "/candidate-login", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("candidateLogin");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("candidateLogin");
            } else {
                model.setViewName("redirect:/candidate-home");
            }
        }
        return model;
    }

    @RequestMapping(value = "/candidate-login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        if (CandidateServiceImpl.authentication(username, password)) {
            session.setAttribute("loginId", username);
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Login Successful");
            model.setViewName("redirect:/candidate-home");
        } else {
            model.addObject("errormessage", "username or password is invalid");
            model.addObject("errorcss", "alert alert-danger m-2");
            model.setViewName("candidateLogin");
        }
        return model;
    }

    @RequestMapping(value = "/candidate-registration", method = RequestMethod.GET)
    public ModelAndView candidateRegistrationPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("candidateRegistration");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("candidateRegistration");
            } else {
                model.setViewName("redirect:/candidate-home");
            }
        }
        return model;
    }

    @RequestMapping(value = "/candidate-registration", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute Candidate candidate, @RequestParam("dateOfBirth") String dob, HttpServletRequest request) throws ParseException {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        candidate.setDob(formDate(dob));
        candidate.setZipCode(75428);

        if (CandidateServiceImpl.checkValidCandidate(candidate)) {
            CandidateServiceImpl.saveCandidate(candidate);
            session.setAttribute("loginId", candidate.getLoginId());
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Registration Successful");
            session.setAttribute("schedulebuttoncss", "disabled");
            model.setViewName("redirect:/candidate-home");
        } else {
            model.addObject("errormessage", "username or Email Id is already exists");
            model.addObject("errorcss", "alert alert-danger m-2");
            model.setViewName("candidateRegistration");
        }

        return model;
    }

    @RequestMapping(value = "/candidate-home", method = RequestMethod.GET)
    public ModelAndView homePage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/candidate-login")) {
            model.addObject("modelscript", session.getAttribute("modelscript"));
            model.addObject("modelbodycontent", session.getAttribute("modelbodycontent"));
            session.setAttribute("modelscript", "hide");
            session.setAttribute("modelbodycontent", "modelbodycontent");
            model.setViewName("candidateHome");
        }
        return model;
    }

    @RequestMapping(value = "/candidate-view-result", method = RequestMethod.GET)
    public ModelAndView viewResult(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/candidate-login")) {
            if (applicationPropertyService.votingResultStatus() == VotingResultStatus.NOT_DECLARED) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, Results are not declared");
                model.setViewName("redirect:/candidate-home");
                return model;
            }
            List<CandidateVoteCountDetails> candidateVoteCountDetailsList = voteCastingService.findCandidateVoteCountDetailsList();
            model.addObject("candidateVoteCountDetailsList", candidateVoteCountDetailsList);

            List<VoteCastingDetails> voteCastingDetailsList = voteCastingService.findAllVoteCastingDetails();
            model.addObject("voteCastingDetailsList", voteCastingDetailsList);
            model.addObject("winner", voteCastingService.winner());
            model.setViewName("candidateViewResult");
        }
        return model;
    }

    @RequestMapping(value = "/candidate-logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session != null || !session.isNew()) {
            if (session.getAttribute("loginId") != null) {
                session.invalidate();
                model.setViewName("redirect:/candidate-login");
            }
        }
        return model;
    }

    @RequestMapping(value = "/edit-candidate-profile", method = RequestMethod.GET)
    public ModelAndView editCandidateProfilePage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/candidate-login")) {
            Candidate candidate = CandidateServiceImpl.findByLoginId((String) session.getAttribute("loginId"));
            model.addObject("candidate", candidate);
            model.addObject("dob", simpleDateFormat.format(candidate.getDob()));
            model.setViewName("candidateProfileEdit");
        }
        return model;
    }

    @RequestMapping(value = "/edit-candidate-profile", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute Candidate candidate, @RequestParam("dateOfBirth") String dateOfBirth, HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        candidate.setLoginId((String) session.getAttribute("loginId"));
        candidate.setDob(formDate(dateOfBirth));
        CandidateServiceImpl.updateCandidate(candidate);
        session.setAttribute("modelscript", "show");
        session.setAttribute("modelbodycontent", "Profile Update successful");
        session.setAttribute("schedulebuttoncss", "");
        model.setViewName("redirect:/candidate-home");
        return model;
    }

    public Date formDate(String dob) throws ParseException {
        Date dt = simpleDateFormat.parse(dob);
        return dt;
    }

    public ModelAndView checkSessionValidation(HttpSession session, ModelAndView model) {
        if (session.isNew() || session == null) {
            model.setViewName("redirect:/candidate-login");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("redirect:/candidate-login");
            } else {
                model.setViewName("");
            }
        }
        return model;
    }
}
