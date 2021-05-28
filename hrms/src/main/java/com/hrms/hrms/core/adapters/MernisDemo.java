package com.hrms.hrms.core.adapters;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class MernisDemo {

	private final String identityNumberPattern = "[0-9]";
	
	public boolean isValidIdentityNumber(String identityNumber) {
		if(identityNumber.length() != 11) {
			return false;
		}
		Pattern pattern = Pattern.compile(identityNumberPattern);
		return pattern.matcher(identityNumber).find();
	}
}
