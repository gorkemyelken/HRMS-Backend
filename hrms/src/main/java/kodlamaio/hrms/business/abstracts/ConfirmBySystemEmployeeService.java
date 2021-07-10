package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface ConfirmBySystemEmployeeService {

	void createActivationByEmployee(Employer employer);
	
	Result confirmEmployer(Employer employer);
}
