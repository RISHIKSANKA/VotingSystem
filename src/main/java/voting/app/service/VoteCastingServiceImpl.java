package voting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voting.app.dao.VoteCastingDetailsRepository;
import voting.app.models.Candidate;
import voting.app.models.CandidateVoteCountDetails;
import voting.app.models.VoteCastingDetails;
import voting.app.models.Voter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteCastingServiceImpl {
    @Autowired
    private VoteCastingDetailsRepository voteCastingRepository;

    @Autowired
    private CandidateServiceImpl candidateService;

    public List<VoteCastingDetails> findAllVoteCastingDetails() {
        List<VoteCastingDetails> voteCastingDetailsList = voteCastingRepository.findAll();
        return voteCastingDetailsList;
    }

    public String winner() {
        List<VoteCastingDetails> voteCastingDetailsList = findAllVoteCastingDetails();
        if (voteCastingDetailsList.size() == 0) {
            return "No winner yet, Zero votes casted!";
        }

        Map<String, Integer> candidateCountMap = new HashMap<String, Integer>();
        for (VoteCastingDetails voteCastingDetails : voteCastingDetailsList) {
            String candidateLoginId = voteCastingDetails.getCandidate().getLoginId();
            if (candidateCountMap.containsKey(candidateLoginId)) {
                int count = candidateCountMap.get(candidateLoginId);
                candidateCountMap.put(candidateLoginId, count + 1);
            } else {
                candidateCountMap.put(candidateLoginId, 1);
            }
        }

        int maxVotes = 0;
        List<String> winners = new ArrayList<>();
        for (String candidateLoginId : candidateCountMap.keySet()) {
            int value = candidateCountMap.get(candidateLoginId);
            if (value > maxVotes) {
                winners.clear();
                winners.add(findCandidateName(candidateLoginId));
                maxVotes = value;
            } else if (value == maxVotes) {
                //multiple winners
                winners.add(findCandidateName(candidateLoginId));
            }
        }
        return ("Election winner: " + Arrays.toString(winners.toArray()));
    }

    public String findCandidateName(String candidateLoginId) {
        Candidate candidate = candidateService.findByLoginId(candidateLoginId);
        if (candidate != null) {
            return candidate.getName() + " " + candidate.getSurname();
        }
        return "";
    }

    public List<CandidateVoteCountDetails> findCandidateVoteCountDetailsList() {
        List<VoteCastingDetails> voteCastingDetailsList = findAllVoteCastingDetails();
        if (voteCastingDetailsList.size() == 0) {
            return new ArrayList<CandidateVoteCountDetails>();
        }

        Map<String, Integer> candidateCountMap = new HashMap<String, Integer>();
        for (VoteCastingDetails voteCastingDetails : voteCastingDetailsList) {
            String candidateLoginId = voteCastingDetails.getCandidate().getLoginId();
            if (candidateCountMap.containsKey(candidateLoginId)) {
                int count = candidateCountMap.get(candidateLoginId);
                candidateCountMap.put(candidateLoginId, count + 1);
            } else {
                candidateCountMap.put(candidateLoginId, 1);
            }
        }
        List<CandidateVoteCountDetails> candidateVoteCountDetailsList = new ArrayList<CandidateVoteCountDetails>();
        for (Map.Entry<String, Integer> entry : candidateCountMap.entrySet()) {
            CandidateVoteCountDetails candidateVoteCountDetails = new CandidateVoteCountDetails();
            candidateVoteCountDetails.setCandidate(candidateService.findByLoginId(entry.getKey()));
            candidateVoteCountDetails.setCount(entry.getValue());
            candidateVoteCountDetailsList.add(candidateVoteCountDetails);
        }
        return candidateVoteCountDetailsList;

    }

    public List<VoteCastingDetails> getAllVoteCastingDetails() {
        return new ArrayList<VoteCastingDetails>();
    }

    public void castVote(Voter voter, Candidate candidate) {
        VoteCastingDetails voteCastingDetails = new VoteCastingDetails();
        voteCastingDetails.setVoteCastDate(new Date());
        voteCastingDetails.setCandidate(candidate);
        voteCastingDetails.setVoter(voter);
        voteCastingRepository.save(voteCastingDetails);
    }

    public VoteCastingDetails findByVoter(Voter voter) {
        return voteCastingRepository.findByVoter(voter);
    }


    @Transactional
    public void deleteByVoter(Voter voter) {
        VoteCastingDetails voteCastingDetails = findByVoter(voter);
        if (voteCastingDetails != null) {
//            voteCastingRepository.deleteVoteCastingDetailsByTransactionId(voteCastingDetails.getTransactionId());
            voteCastingRepository.delete(voteCastingDetails);
        }
    }

    @Transactional
    public void deleteByCandidate(Candidate candidate) {
        VoteCastingDetails voteCastingDetails = voteCastingRepository.findByCandidate(candidate);
        if (voteCastingDetails != null) {
            voteCastingRepository.delete(voteCastingDetails);
        }
    }
}
