package voting.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.app.models.Candidate;
import voting.app.models.VoteCastingDetails;
import voting.app.models.Voter;

import java.util.List;

@Repository
public interface VoteCastingDetailsRepository extends JpaRepository<VoteCastingDetails, Integer> {
    public List<VoteCastingDetails> findByCandidateLoginId(String candidateId);

    public VoteCastingDetails findByVoter(Voter voter);

    public VoteCastingDetails findByCandidate(Candidate candidate);

    public void deleteVoteCastingDetailsByVoter(Voter voter);

    public void deleteVoteCastingDetailsByCandidate(Candidate candidate);

    public void deleteVoteCastingDetailsByTransactionId(int transactionId);
}