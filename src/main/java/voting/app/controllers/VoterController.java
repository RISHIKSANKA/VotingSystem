package voting.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import voting.app.enums.VotingResultStatus;
import voting.app.enums.VotingStatus;
import voting.app.models.Candidate;
import voting.app.models.CandidateVoteCountDetails;
import voting.app.models.VoteCastingDetails;
import voting.app.models.Voter;
import voting.app.service.ApplicationPropertyServiceImpl;
import voting.app.service.CandidateServiceImpl;
import voting.app.service.VoteCastingServiceImpl;
import voting.app.service.VoterServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class VoterController {

    @Autowired
    private VoterServiceImpl voterService;
    @Autowired
    private VoteCastingServiceImpl voteCastingService;
    @Autowired
    private ApplicationPropertyServiceImpl applicationPropertyService;
    @Autowired
    private CandidateServiceImpl candidateService;

    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    final SimpleDateFormat sdft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    LocalDate date = null;
    private static final String FILE_PATH = "classpath:passport-document-list.pdf";
    private static final String APPLICATION_PDF = "application/pdf";

    @RequestMapping(value = "/voter-index", method = RequestMethod.GET)
    public ModelAndView voterIndexPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("voterIndex");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("voterIndex");
            } else {
                model.setViewName("redirect:/voter-Index");
            }
        }
        return model;
    }

    @RequestMapping(value = "/voter-login", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("voterLogin");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("voterLogin");
            } else {
                model.setViewName("redirect:/voter-home");
            }
        }
        return model;
    }

    @RequestMapping(value = "/voter-login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        if (voterService.authentication(username, password)) {
            session.setAttribute("loginId", username);
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Login Successful");
            model.setViewName("redirect:/voter-home");
        } else {
            model.addObject("errormessage", "username or password is invalid");
            model.addObject("errorcss", "alert alert-danger m-2");
            model.setViewName("voterLogin");
        }
        return model;
    }

    @RequestMapping(value = "/voter-registration", method = RequestMethod.GET)
    public ModelAndView voterRegistrationPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("voterRegistration");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("voterRegistration");
            } else {
                model.setViewName("redirect:/voter-home");
            }
        }
        return model;
    }

    @RequestMapping(value = "/voter-registration", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute Voter voter, @RequestParam("dateOfBirth") String dob, HttpServletRequest request) throws ParseException {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        voter.setDob(formDate(dob));
        voter.setZipCode(75428);

        if (voterService.checkValidVoter(voter)) {
            voterService.saveVoter(voter);
            session.setAttribute("loginId", voter.getLoginId());
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Registration Successful");
            session.setAttribute("schedulebuttoncss", "disabled");
            model.setViewName("redirect:/voter-home");
        } else {
            model.addObject("errormessage", "username or Email Id is already exists");
            model.addObject("errorcss", "alert alert-danger m-2");
            model.setViewName("voterRegistration");
        }

        return model;
    }

    @RequestMapping(value = "/voter-home", method = RequestMethod.GET)
    public ModelAndView homePage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {
            model.addObject("modelscript", session.getAttribute("modelscript"));
            model.addObject("modelbodycontent", session.getAttribute("modelbodycontent"));
            session.setAttribute("modelscript", "hide");
            session.setAttribute("modelbodycontent", "modelbodycontent");
            model.setViewName("voterHome");
        }
        return model;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session != null || !session.isNew()) {
            if (session.getAttribute("loginId") != null) {
                session.invalidate();
                model.setViewName("redirect:/");
            }
        }
        return model;
    }

    @RequestMapping(value = "/edit-voter-profile", method = RequestMethod.GET)
    public ModelAndView editVoterProfilePage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {
            Voter voter = voterService.findByLoginId((String) session.getAttribute("loginId"));
            model.addObject("voter", voter);
            model.addObject("dob", sdf.format(voter.getDob()));
            model.setViewName("voterProfileEdit");
        }
        return model;
    }

    @RequestMapping(value = "/edit-voter-profile", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute Voter voter, @RequestParam("dateOfBirth") String dateOfBirth, HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        voter.setLoginId((String) session.getAttribute("loginId"));
        voter.setDob(formDate(dateOfBirth));
        voterService.updateVoter(voter);
        session.setAttribute("modelscript", "show");
        session.setAttribute("modelbodycontent", "Profile Update successful");
        session.setAttribute("schedulebuttoncss", "");
        model.setViewName("redirect:/voter-home");
        return model;
    }

    @RequestMapping(value = "/cast-vote", method = RequestMethod.POST)
    public ModelAndView castVote(@ModelAttribute Voter voter, @RequestParam("candidateId") String candidateId, HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {
            String loginId = (String) session.getAttribute("loginId");
            voteCastingService.castVote(voterService.findByLoginId(loginId), candidateService.findByLoginId(candidateId));
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Vote Casted Successfully!!");
            model.setViewName("redirect:/voter-home");
        }
        return model;
    }

    @RequestMapping(value = "/cast-vote", method = RequestMethod.GET)
    public ModelAndView fetchCastVote(@ModelAttribute Voter voter, HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {
            String loginId = (String) session.getAttribute("loginId");

            Voter voter1 = voterService.findByLoginId(loginId);
            if (voter1.getApprovalStatus() == false) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, you are not yet approved!!");
                model.setViewName("redirect:/voter-home");
                return model;
            }
            VoteCastingDetails voteCastingDetails = voteCastingService.findByVoter(voterService.findByLoginId(loginId));
            if (voteCastingDetails != null) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, you already voted!!");
                model.setViewName("redirect:/voter-home");
                return model;
            }
            List<Candidate> candidateList = candidateService.findAllByApprovalStatusIsTrue();
            if (candidateList.size() == 0) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, you cannot vote as there are Zero approved Candidates");
                model.setViewName("redirect:/voter-home");
                return model;
            }

            if (applicationPropertyService.votingStatus() == VotingStatus.IN_PROGRESS) {
                model.addObject("candidateList", candidateList);
                model.setViewName("vote");
            } else if (applicationPropertyService.votingStatus() == VotingStatus.NOT_STARTED) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, you can't vote as voting did not start yet");
                model.setViewName("redirect:/voter-home");
            } else {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, you can't vote as voting completed");
                model.setViewName("redirect:/voter-home");
            }
        }
        return model;
    }

    @RequestMapping(value = "/view-cast-vote", method = RequestMethod.GET)
    public ModelAndView viewVote(@ModelAttribute Voter voter, HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {
            String loginId = (String) session.getAttribute("loginId");

            Voter voter1 = voterService.findByLoginId(loginId);
            if (voter1.getApprovalStatus() == false) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, you are not yet approved!!");
                model.setViewName("redirect:/voter-home");
                return model;
            }
            VoteCastingDetails voteCastingDetails = voteCastingService.findByVoter(voterService.findByLoginId(loginId));
            if (voteCastingDetails == null) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Your Vote is not yet casted!");
                model.setViewName("redirect:/voter-home");
                return model;
            }
            String voteInfo = String.format("You voted %s %s!!", voteCastingDetails.getCandidate().getName(), voteCastingDetails.getCandidate().getSurname());
            model.addObject("voteInfo", voteInfo);
            model.setViewName("voteCastView");
        }
        return model;
    }

    @RequestMapping(value = "/voter-view-result", method = RequestMethod.GET)
    public ModelAndView viewResult(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {

            if (applicationPropertyService.votingResultStatus() == VotingResultStatus.NOT_DECLARED) {
                session.setAttribute("modelscript", "show");
                session.setAttribute("modelbodycontent", "Sorry, Results are not declared");
                model.setViewName("redirect:/voter-home");
                return model;
            }

            List<CandidateVoteCountDetails> candidateVoteCountDetailsList = voteCastingService.findCandidateVoteCountDetailsList();
            model.addObject("candidateVoteCountDetailsList", candidateVoteCountDetailsList);

            List<VoteCastingDetails> voteCastingDetailsList = voteCastingService.findAllVoteCastingDetails();
            model.addObject("voteCastingDetailsList", voteCastingDetailsList);

            model.addObject("winner", voteCastingService.winner());
            model.setViewName("voterViewResult");
        }
        return model;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ModelAndView cancel(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/voter-login")) {
            String loginId = (String) session.getAttribute("loginId");
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Already cancelled");
            model.setViewName("redirect:/voter-home");

        }
        return model;
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody
    Resource downloadC(HttpServletResponse response) throws FileNotFoundException {
        File file = fetchFile("classpath:DocumentList.pdf");
        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }

    @RequestMapping(value = "/declaration", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody
    Resource declaration(HttpServletResponse response) throws FileNotFoundException {
        File file = fetchFile("classpath:DeclarationStatement.pdf");
        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }

    private File fetchFile(String resourceLocation) throws FileNotFoundException {
        File file = ResourceUtils.getFile(resourceLocation);
        if (!file.exists()) {
            throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
        }
        return file;
    }

    public ModelAndView checkSessionValidation(HttpSession session, ModelAndView model) {
        if (session.isNew() || session == null) {
            model.setViewName("redirect:/voter-login");
        } else {
            if (session.getAttribute("loginId") == null) {
                model.setViewName("redirect:/voter-login");
            } else {
                model.setViewName("");
            }
        }
        return model;
    }

    public Date formDate(String dob) throws ParseException {
        Date dt = sdf.parse(dob);
        return dt;
    }
}