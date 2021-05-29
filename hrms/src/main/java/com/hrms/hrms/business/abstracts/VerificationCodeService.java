package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.entities.abstracts.User;
import com.hrms.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeService {
	
	DataResult<List<VerificationCode>> getAll();
	
	Result verificateUser(String code);
	
	String createVerificationCode(User user);
	
	DataResult<VerificationCode> getByCode(String code);
}
