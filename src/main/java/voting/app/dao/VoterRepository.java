package voting.app.dao;

import voting.app.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter, String> {
	List<Voter> findByLoginId(String loginId);
	void deleteByLoginId(String loginId);
	List<Voter> findByEmailId(String emailId);
}