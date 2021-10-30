package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvJobExperience;

public interface CvJobExperienceService {

	DataResult<List<CvJobExperience>> getall();
	
	Result add(CvJobExperience cvJobExperience);
}
