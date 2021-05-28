package com.hrms.hrms.business.concretes;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.VerificationCodeService;
import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.hrms.core.utilities.results.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.UserDao;
import com.hrms.hrms.dataAccess.abstracts.VerificationCodeDao;
import com.hrms.hrms.entities.abstracts.User;
import com.hrms.hrms.entities.concretes.VerificationCode;

@Service
public class VerificationCodeManager implements VerificationCodeService{

	private VerificationCodeDao verificationCodeDao;
	private UserDao userDao;
	
	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao, UserDao userDao) {
		super();
		this.verificationCodeDao = verificationCodeDao;
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<VerificationCode>> getAll() {
		return new SuccessDataResult<List<VerificationCode>>(this.verificationCodeDao.findAll(),"Verification codes are listed.");
	}

	String generatedCode = "";
	@Override
	public String createVerificationCode(User user) {
		while(true) {
			generatedCode = randomCodeGenarator(20);
			if (getByCode(generatedCode) == null) {
				break;
			}
		}
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setId(user.getId());
		verificationCode.setCode(generatedCode);
		verificationCodeDao.save(verificationCode);
		return generatedCode;
	}

	private final String codeContent = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private SecureRandom mixer = new SecureRandom();
	
	private String randomCodeGenarator(int length) {
		StringBuilder randomValueConstructor = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			randomValueConstructor.append(codeContent.charAt(mixer.nextInt(codeContent.length())));
		}
		return randomValueConstructor.toString();
	}

	@Override
	public Result verificateUser(String activationCode) {
		User user = userDao.getOne(verificationCodeDao.getByCode(activationCode).getId());
		user.setMailVerified(true);
		VerificationCode verificationCode = verificationCodeDao.getByCode(activationCode);
		verificationCode.setVerified(true);
		userDao.save(user);
		verificationCodeDao.save(verificationCode);
		return new SuccessResult("User verified.");
	}

	@Override
	public DataResult<VerificationCode> getByCode(String code) {
		return new SuccessDataResult<VerificationCode>(this.verificationCodeDao.getByCode(code),"Verification codes are listed by code.");
	}

}
