package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvJobExperience;

public interface CvJobExperienceDao extends JpaRepository<CvJobExperience, Integer>{

}
