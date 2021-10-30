package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {

	DataResult<List<JobAdvertisement>> getall();
	
	Result add(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> getByCreateDate(String createDate);
	
	DataResult<List<JobAdvertisement>> getByEmployer_CompanyName(String companyName);
	
	DataResult<List<JobAdvertisement>> getByIsActive(Boolean isActive);
	
	DataResult<List<JobAdvertisement>> getByJobPosition_Position(String position);
	
	DataResult<List<JobAdvertisement>> findById(int id);

}
