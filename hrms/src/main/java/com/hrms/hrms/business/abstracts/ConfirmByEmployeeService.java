package com.hrms.hrms.business.abstracts;

import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.entities.concretes.Employer;

public interface ConfirmByEmployeeService {

	void createActivationByEmployee(Employer employer);
	
	Result confirmEmployer(Employer employer);
}
