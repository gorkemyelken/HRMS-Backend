package com.hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.ConfirmByEmployeeService;
import com.hrms.hrms.business.abstracts.EmployerService;
import com.hrms.hrms.business.abstracts.VerificationCodeService;
import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.core.utilities.results.ErrorResult;
import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.hrms.core.utilities.results.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.EmployerDao;
import com.hrms.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private VerificationCodeService verificationCodeService;
	private ConfirmByEmployeeService confirmByEmployeeService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, VerificationCodeService verificationCodeService, ConfirmByEmployeeService confirmByEmployeeService) {
		super();
		this.employerDao = employerDao;
		this.verificationCodeService = verificationCodeService;
		this.confirmByEmployeeService = confirmByEmployeeService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Employers listed.");
	}

	@Override
	public Result add(Employer employer) {
		if(employer.getCompanyName().isEmpty() || employer.getEmail().isEmpty() || employer.getPassword().isEmpty()
	    		|| employer.getPhoneNumber().isEmpty() || employer.getWebAddress().isEmpty()) {
	    	return new ErrorResult("All fields must be filled.");
	    }
		else if(getByEmail(employer.getEmail()).getData() != null) {
			return new ErrorResult("This email is already registered.");
		}
		else if(!employer.getEmail().endsWith(employer.getWebAddress())) {
			return new ErrorResult("Website and email domain must be the same");
		}
		else if(!isEmailValid(employer.getEmail())) {
			return new ErrorResult("Email is not in a valid format");
		}
		else{
			employer.setMailVerified(false);
			
			employer.setActive(false);
			
			String returnedCode = this.verificationCodeService.createVerificationCode(employer);
			
			// returnedCode mail adresine gönderilecek
			
			confirmByEmployeeService.createActivationByEmployee(employer);
			
			return new SuccessResult("Verification code has been sent to your e-mail. : " + employer.getEmail());	
		} 
	}

	private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
	private boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(email).find();
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {
		return new SuccessDataResult<Employer>(this.employerDao.getByEmail(email));
	}
	
	

}
