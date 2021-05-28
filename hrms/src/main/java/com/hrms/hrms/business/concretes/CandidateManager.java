package com.hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.CandidateService;
import com.hrms.hrms.business.abstracts.VerificationCodeService;
import com.hrms.hrms.core.adapters.MernisDemo;
import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.core.utilities.results.ErrorResult;
import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.hrms.core.utilities.results.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.hrms.dataAccess.abstracts.UserDao;
import com.hrms.hrms.entities.abstracts.User;
import com.hrms.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private MernisDemo mernisDemo;
	private VerificationCodeService verificationCodeService;
	private UserDao userDao;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, MernisDemo mernisDemo, VerificationCodeService verificationCodeService, UserDao userDao) {
		super();
		this.candidateDao = candidateDao;
		this.mernisDemo = mernisDemo;
		this.verificationCodeService = verificationCodeService;
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"Candidates listed.");
	}

	@Override
	public Result add(Candidate candidate) {
		if(candidate.getFirstName().length() < 2 || candidate.getLastName().length() < 2) {
			return new ErrorResult("Name or surname cannot be shorter than 2 characters.");
		}
		else if(getByEmail(candidate.getEmail()) != null) {
			return new ErrorResult("This email is already registered.");
		}
		else if(getByIdentityNumber(candidate.getIdentityNumber()) != null) {
			return new ErrorResult("This identity number is already registered.");
		}
		else if(!isEmailValid(candidate.getEmail())) {
			return new ErrorResult("Email is not in a valid format");
		}
		else if(mernisDemo.isValidIdentityNumber(candidate.getIdentityNumber())) {
			candidate.setMailVerified(false);
			
			User savedUser = this.userDao.save(candidate);
			
			String returnedCode = this.verificationCodeService.createVerificationCode(savedUser);
			
			this.candidateDao.save(candidate);
			
			return new SuccessResult("Verification code has been sent to your e-mail. : " + candidate.getEmail());
		}
		else {
			return new ErrorResult("Candidate information is incorrect or incomplete.");
		}
	}
	
	
	private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
	private boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(email).find();
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getByIdentityNumber(identityNumber),"Candidates listed by identity number.");
	}

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getByEmail(email),"Candidates listed by email.");
	}

}
