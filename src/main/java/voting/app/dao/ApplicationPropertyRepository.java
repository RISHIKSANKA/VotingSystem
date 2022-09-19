package voting.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.app.models.Admin;
import voting.app.models.ApplicationProperty;

import java.util.List;

@Repository
public interface ApplicationPropertyRepository extends JpaRepository<ApplicationProperty, String> {

	public List<ApplicationProperty> findByName(String name);

}