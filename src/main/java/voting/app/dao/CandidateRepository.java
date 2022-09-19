package voting.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.app.models.Candidate;
import voting.app.models.Voter;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
	List<Candidate> findByLoginId(String loginId);
	void deleteByLoginId(String loginId);
	List<Candidate> findByEmailId(String emailId);
	List<Candidate> findAllByApprovalStatusIsTrue();
}