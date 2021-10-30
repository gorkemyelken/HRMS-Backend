package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="cvs")
public class Cv {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cv_id")
	private int id;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "github_address")
	private String githubAddress;
	
	@Column(name = "linkedin_address")
	private String linkedinAddress;
	
	@Column(name = "cover_letter")
	private String coverLetter;
	
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cv")
	private List<CvLanguage> languages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cv")
	private List<CvSkill> skills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cv")
	private List<CvEducation> educations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cv")
	private List<CvJobExperience> experiences;

}
