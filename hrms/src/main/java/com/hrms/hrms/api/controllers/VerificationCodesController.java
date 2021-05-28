package com.hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.hrms.business.abstracts.VerificationCodeService;
import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.entities.concretes.VerificationCode;

@RestController
@RequestMapping("/api/verificationcodes")
public class VerificationCodesController {

	private VerificationCodeService verificationCodeService;

	@Autowired
	public VerificationCodesController(VerificationCodeService verificationCodeService) {
		super();
		this.verificationCodeService = verificationCodeService;
	}
	
	@GetMapping("/getall")
	DataResult<List<VerificationCode>> getAll(){
		return this.verificationCodeService.getAll();
	}
	
	@GetMapping("getByCode")
	DataResult<VerificationCode> getByCode(String code){
		return this.verificationCodeService.getByCode(code);
	}
}
