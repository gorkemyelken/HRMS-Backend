package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvSkill;

public interface CvSkillDao extends JpaRepository<CvSkill, Integer> {

}
