package voting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.app.dao.CandidateRepository;
import voting.app.dao.VoterRepository;
import voting.app.models.Candidate;
import voting.app.models.Voter;

import java.util.List;

@Service
public class CandidateServiceImpl {

    @Autowired
    private CandidateRepository candidateRepository;

    public void saveCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }


    public void deleteCandidate(Candidate candidate) {
        candidateRepository.deleteByLoginId(candidate.getLoginId());
    }

    public long getCount() {
        return candidateRepository.count();
    }

    public boolean checkValidCandidate(Candidate candidate) {

        List<Candidate> candidateList = candidateRepository.findByLoginId(candidate.getLoginId());
        if (candidateList.isEmpty()) {
            candidateList = candidateRepository.findByEmailId(candidate.getEmailId());
            if (candidateList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean authentication(String username, String password) {
        List<Candidate> candidateList = candidateRepository.findByLoginId(username);
        if (!candidateList.isEmpty()) {
            for (Candidate candidate : candidateList) {
                if (candidate.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    // set applicant id before and send it to update
    public void updateCandidate(Candidate candidate) {
        Candidate applicantold = null;
        List<Candidate> applicantList = candidateRepository.findByLoginId(candidate.getLoginId());
//		if(!applicantList.isEmpty())
//			applicantold = applicantList.get(applicantList.size()-1);
//		applicant.setPassword(applicantold.getPassword());
//		applicant.setHintQuestion(applicantold.getHintQuestion());
//		applicant.setHintAnswer(applicantold.getHintAnswer());
//		applicant.setZipCode(applicantold.getZipCode());
        candidateRepository.save(candidate);
    }

    public Candidate findByLoginId(String loginId) {
        Candidate candidate = null;
        List<Candidate> candidateList = candidateRepository.findByLoginId(loginId);
        if (!candidateList.isEmpty())
            candidate = candidateList.get(candidateList.size() - 1);
        return candidate;
    }


    public void updatePassword(Candidate candidate, String password) {
        candidate.setPassword(password);
        candidateRepository.save(candidate);
    }


    public List<Candidate> findAll() {
        List<Candidate> candidateList = candidateRepository.findAll();
        return candidateList;
    }


    public void deleteCandidateByLoginId(String loginId) {
        candidateRepository.deleteByLoginId(loginId);
    }

    public void approveByLoginId(String loginId) {
        List<Candidate> candidateList = candidateRepository.findByLoginId(loginId);
        if (!candidateList.isEmpty()) {
            Candidate candidate = candidateList.get(candidateList.size() - 1);
            candidate.setApprovalStatus(true);
            candidateRepository.save(candidate);
        }
    }

    public void rejectByLoginId(String loginId) {
        List<Candidate> candidateList = candidateRepository.findByLoginId(loginId);
        if (!candidateList.isEmpty()) {
            Candidate candidate = candidateList.get(candidateList.size() - 1);
            candidate.setApprovalStatus(false);
            candidateRepository.save(candidate);
        }
    }

    public List<Candidate> findAllByApprovalStatusIsTrue() {
        return candidateRepository.findAllByApprovalStatusIsTrue();
    }
}
