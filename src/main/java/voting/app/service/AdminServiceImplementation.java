package voting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.app.dao.AdminRepository;
import voting.app.models.Admin;
import voting.app.models.Candidate;
import voting.app.models.VoteCastingDetails;
import voting.app.models.Voter;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AdminServiceImplementation {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VoterServiceImpl voterService;
    @Autowired
    private CandidateServiceImpl candidateService;
    @Autowired
    private VoteCastingServiceImpl voteCastingService;

    @PostConstruct
    public void initiateAdmin() {
        Admin admin = new Admin();
        admin.setUserId("admin");
        admin.setPassword("admin");
        saveAdmin(admin);
    }

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public boolean authentication(String username, String password) {
        List<Admin> adminList = adminRepository.findByUserId(username);
        if (!adminList.isEmpty()) {
            for (Admin value : adminList) {
                if (value.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Voter> getAllRegisteredVoters() {
        List<Voter> voterList = voterService.findAll();
        return voterList;
    }

    @Transactional
    public void deleteVoterByLoginId(String loginId) {
        voteCastingService.deleteByVoter(voterService.findByLoginId(loginId));

        voterService.deleteVoterByLoginId(loginId);
    }

    @Transactional
    public void approveVoterByLoginId(String loginId) {
        voterService.approveByLoginId(loginId);
    }

    @Transactional
    public void rejectVoterByLoginId(String loginId) {
        voterService.rejectByLoginId(loginId);
    }

    public List<Candidate> getAllRegisteredCandidates() {
        List<Candidate> candidateList = candidateService.findAll();
        return candidateList;
    }

    @Transactional
    public void deleteCandidateByLoginId(String loginId) {
        voteCastingService.deleteByCandidate(candidateService.findByLoginId(loginId));
        candidateService.deleteCandidateByLoginId(loginId);
    }

    @Transactional
    public void approveCandidateByLoginId(String loginId) {
        candidateService.approveByLoginId(loginId);
    }

    @Transactional
    public void rejectCandidateByLoginId(String loginId) {
        candidateService.rejectByLoginId(loginId);
    }

    public List<VoteCastingDetails> getAllVoteCastingDetails() {
        List<VoteCastingDetails> voteCastingDetailsList = voteCastingService.getAllVoteCastingDetails();
        return voteCastingDetailsList;
    }

}
