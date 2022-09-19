package voting.app.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATION_PROPERTY")
@Component
public class ApplicationProperty {
	@Id
    @GeneratedValue
	int id;

	@Column(unique = true)
	String name;

	@Column
    String value;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
