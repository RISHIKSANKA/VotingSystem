package voting.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import voting.app.enums.VotingResultStatus;
import voting.app.enums.VotingStatus;
import voting.app.models.ApplicationProperty;
import voting.app.models.Candidate;
import voting.app.models.CandidateVoteCountDetails;
import voting.app.models.VoteCastingDetails;
import voting.app.models.Voter;
import voting.app.service.AdminServiceImplementation;
import voting.app.service.ApplicationPropertyServiceImpl;
import voting.app.service.VoteCastingServiceImpl;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminServiceImplementation adminService;

    @Autowired
    private ApplicationPropertyServiceImpl applicationPropertyService;

    @Autowired
    private VoteCastingServiceImpl voteCastingService;

    @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
    public ModelAndView adminloginPage(HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        if (session.isNew() || session == null) {
            model.setViewName("adminlogin");
        } else {
            if (session.getAttribute("userid") == null) {
                model.setViewName("adminlogin");
            } else {
                model.setViewName("redirect:/adminhome");
            }
        }

        return model;
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public ModelAndView adminloginPage(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        if (adminService.authentication(username, password)) {
            session.setAttribute("userid", username);
            session.setAttribute("modelscript", "show");
            session.setAttribute("modelbodycontent", "Login Successful");
            model.setViewName("redirect:/adminhome");
        } else {
            model.addObject("errormessage", "username or password is invalid");
            model.addObject("errorcss", "alert alert-danger m-2");
            model.setViewName("adminlogin");
        }
        return model;
    }

    @RequestMapping(value = "/adminhome", method = RequestMethod.GET)
    public ModelAndView adminhomePage(HttpSession session) throws ParseException {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            model.addObject("modelscript", session.getAttribute("modelscript"));
            model.addObject("modelbodycontent", session.getAttribute("modelbodycontent"));
            session.setAttribute("modelscript", "hide");

            session.setAttribute("modelbodycontent", "modelbodycontent");
            model.setViewName("adminhome");
        }
        return model;
    }

    @RequestMapping(value = "/delete-voter", method = RequestMethod.POST)
    public ModelAndView deleteVoter(HttpSession session, @RequestParam("loginId") String loginId) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            adminService.deleteVoterByLoginId(loginId);
            model.setViewName("redirect:/view-all-voters");
        }
        return model;
    }

    @RequestMapping(value = "/approve-voter", method = RequestMethod.POST)
    public ModelAndView approveVoter(HttpSession session, @RequestParam("loginId") String loginId) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            adminService.approveVoterByLoginId(loginId);
            model.setViewName("redirect:/view-all-voters");
        }
        return model;
    }

    @RequestMapping(value = "/reject-voter", method = RequestMethod.POST)
    public ModelAndView rejectVoter(HttpSession session, @RequestParam("loginId") String loginId) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            adminService.rejectVoterByLoginId(loginId);
            model.setViewName("redirect:/view-all-voters");
        }
        return model;
    }

    @RequestMapping(value = "/view-all-voters", method = RequestMethod.GET)
    public ModelAndView viewAllVoters(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            List<Voter> voterList = adminService.getAllRegisteredVoters();
            model.addObject("voterList", voterList);
            model.setViewName("votersView");
        }
        return model;
    }

    @RequestMapping(value = "/delete-candidate", method = RequestMethod.POST)
    public ModelAndView deleteCandidate(HttpSession session, @RequestParam("loginId") String loginId) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            adminService.deleteCandidateByLoginId(loginId);
            model.setViewName("redirect:/view-all-candidates");
        }
        return model;
    }

    @RequestMapping(value = "/approve-candidate", method = RequestMethod.POST)
    public ModelAndView approveCandidate(HttpSession session, @RequestParam("loginId") String loginId) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            adminService.approveCandidateByLoginId(loginId);
            model.setViewName("redirect:/view-all-candidates");
        }
        return model;
    }

    @RequestMapping(value = "/reject-candidate", method = RequestMethod.POST)
    public ModelAndView rejectCandidate(HttpSession session, @RequestParam("loginId") String loginId) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            adminService.rejectCandidateByLoginId(loginId);
            model.setViewName("redirect:/view-all-candidates");
        }
        return model;
    }

    @RequestMapping(value = "/view-all-candidates", method = RequestMethod.GET)
    public ModelAndView viewAllCandidates(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            List<Candidate> candidateList = adminService.getAllRegisteredCandidates();
            model.addObject("candidateList", candidateList);
            model.setViewName("candidatesView");
        }
        return model;
    }

    @RequestMapping(value = "/election-status", method = RequestMethod.GET)
    public ModelAndView electionStatus(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            List<ApplicationProperty> applicationPropertiesList = applicationPropertyService.getAllApplicationProperties();
            model.addObject("applicationPropertiesList", applicationPropertiesList);
            model.addObject("VOTING_RESULT_STATUS_QUALIFIER", ApplicationPropertyServiceImpl.VOTING_RESULT_STATUS_QUALIFIER);
            model.addObject("VOTING_STATUS_QUALIFIER", ApplicationPropertyServiceImpl.VOTING_STATUS_QUALIFIER);
            model.setViewName("electionStatusView");
        }
        return model;
    }


    @RequestMapping(value = "/update-voting-result-status", method = RequestMethod.POST)
    public ModelAndView updateVotingResultStatus(HttpSession session, @RequestParam("votingResultStatus") String votingResultStatus) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            applicationPropertyService.updateVotingResultStatus(VotingResultStatus.valueOf(votingResultStatus));
            model.setViewName("redirect:/election-status");
        }
        return model;
    }

    @RequestMapping(value = "/update-voting-status", method = RequestMethod.POST)
    public ModelAndView updateVotingStatus(HttpSession session, @RequestParam("votingStatus") String votingStatus) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            applicationPropertyService.updateVotingStatus(VotingStatus.valueOf(votingStatus));
            model.setViewName("redirect:/election-status");
        }
        return model;
    }


    @RequestMapping(value = "/admin-view-result", method = RequestMethod.GET)
    public ModelAndView viewResult(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model = checkSessionValidation(session, model);
        if (!model.getViewName().equals("redirect:/adminlogin")) {
            List<CandidateVoteCountDetails> candidateVoteCountDetailsList = voteCastingService.findCandidateVoteCountDetailsList();
            model.addObject("candidateVoteCountDetailsList", candidateVoteCountDetailsList);

            List<VoteCastingDetails> voteCastingDetailsList = voteCastingService.findAllVoteCastingDetails();
            model.addObject("voteCastingDetailsList", voteCastingDetailsList);
            model.addObject("winner", voteCastingService.winner());
            model.setViewName("adminViewResult");
        }
        return model;
    }

    @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (session != null || !session.isNew()) {
            if (session.getAttribute("userid") != null) {
                session.invalidate();
                model.setViewName("redirect:/adminlogin");
            }
        }
        return model;
    }


    public ModelAndView checkSessionValidation(HttpSession session, ModelAndView model) {
        if (session.isNew() || session == null) {
            model.setViewName("redirect:/adminlogin");
        } else {
            if (session.getAttribute("userid") == null) {
                model.setViewName("redirect:/adminlogin");
            } else {
                model.setViewName("");
            }
        }
        return model;
    }
}
