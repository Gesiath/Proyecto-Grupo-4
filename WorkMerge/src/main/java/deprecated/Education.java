package deprecated;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.WorkMerge.entities.Curriculum;

@Entity
public class Education {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	private String title;
	private String institute;
	@Enumerated(EnumType.STRING)
	private TypeEducation type;
	@Temporal(TemporalType.DATE)
	private Date beginDate;
	@Temporal(TemporalType.DATE)
	private Date finishDate;
	private String description;
	@ManyToOne
	private Curriculum curriculum;

	public Education() {	
	}


	public Education(String id, String title, String institute, TypeEducation type, Date beginDate, Date finishDate,
			String description) {
		super();
		this.id = id;
		this.title = title;
		this.institute = institute;
		this.type = type;
		this.beginDate = beginDate;
		this.finishDate = finishDate;
		this.description = description;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getInstitute() {
		return institute;
	}


	public void setInstitute(String institute) {
		this.institute = institute;
	}


	public TypeEducation getType() {
		return type;
	}


	public void setType(TypeEducation type) {
		this.type = type;
	}


	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	public Date getFinishDate() {
		return finishDate;
	}


	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}

