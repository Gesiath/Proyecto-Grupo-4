package deprecated;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.entities.Curriculum;

@Entity
public class Language {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	@Enumerated(EnumType.STRING)
	private EnumLanguage language ;
	@ManyToOne
	private Curriculum curriculum;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnumLanguage getName() {
		return language;
	}

	public void setName(EnumLanguage name) {
		this.language = name;
	}
}
