package voting.app.dao;

import voting.app.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

	public List<Admin> findByUserId(String userId);
	
}
