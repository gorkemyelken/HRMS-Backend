package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> getByCreateDate(String createDate);
	
	List<JobAdvertisement> getByEmployer_CompanyName(String companyName);
	
	List<JobAdvertisement> getByIsActive(Boolean isActive);
	
	List<JobAdvertisement> getByJobPosition_Position(String position);
	
	List<JobAdvertisement> findById(int id);
}
