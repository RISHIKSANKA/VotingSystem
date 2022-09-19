package voting.app.service;

import voting.app.dao.VoterRepository;
import voting.app.models.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterServiceImpl {

    @Autowired
    private VoterRepository voterRepository;

    public void saveVoter(Voter voter) {
        voterRepository.save(voter);
    }


    public void deleteVoter(Voter voter) {
        voterRepository.deleteByLoginId(voter.getLoginId());
    }

    public long getCount() {
        return voterRepository.count();
    }

    public boolean checkValidVoter(Voter voter) {

        List<Voter> voterList = voterRepository.findByLoginId(voter.getLoginId());
        if (voterList.isEmpty()) {
            voterList = voterRepository.findByEmailId(voter.getEmailId());
            if (voterList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean authentication(String username, String password) {
        List<Voter> voterList = voterRepository.findByLoginId(username);
        if (!voterList.isEmpty()) {
            for (Voter voter : voterList) {
                if (voter.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    // set applicant id before and send it to update
    public void updateVoter(Voter voter) {
        Voter applicantold = null;
        List<Voter> applicantList = voterRepository.findByLoginId(voter.getLoginId());
//		if(!applicantList.isEmpty())
//			applicantold = applicantList.get(applicantList.size()-1);
//		applicant.setPassword(applicantold.getPassword());
//		applicant.setHintQuestion(applicantold.getHintQuestion());
//		applicant.setHintAnswer(applicantold.getHintAnswer());
//		applicant.setZipCode(applicantold.getZipCode());
        voterRepository.save(voter);
    }

    public Voter findByLoginId(String loginId) {
        Voter voter = null;
        List<Voter> voterList = voterRepository.findByLoginId(loginId);
        if (!voterList.isEmpty())
            voter = voterList.get(voterList.size() - 1);
        return voter;
    }


    public void updatePassword(Voter voter, String password) {
        voter.setPassword(password);
        voterRepository.save(voter);
    }


    public List<Voter> findAll() {
        List<Voter> voterList = voterRepository.findAll();
        return voterList;
    }

    public void deleteVoterByLoginId(String loginId) {
        voterRepository.deleteByLoginId(loginId);
    }

    public void approveByLoginId(String loginId) {
        List<Voter> voterList = voterRepository.findByLoginId(loginId);
        if (!voterList.isEmpty()) {
            Voter voter = voterList.get(voterList.size() - 1);
            voter.setApprovalStatus(true);
            voterRepository.save(voter);
        }
    }

    public void rejectByLoginId(String loginId) {
        List<Voter> voterList = voterRepository.findByLoginId(loginId);
        if (!voterList.isEmpty()) {
            Voter voter = voterList.get(voterList.size() - 1);
            voter.setApprovalStatus(false);
            voterRepository.save(voter);
        }
    }
}
