package com.hrms.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.ConfirmByEmployeeService;
import com.hrms.hrms.core.utilities.results.ErrorResult;
import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.core.utilities.results.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.ConfirmByEmployeeDao;
import com.hrms.hrms.dataAccess.abstracts.EmployerDao;
import com.hrms.hrms.dataAccess.abstracts.UserDao;
import com.hrms.hrms.entities.concretes.ConfirmByEmployee;
import com.hrms.hrms.entities.concretes.Employer;

@Service
public class ConfirmByEmployeeManager implements ConfirmByEmployeeService{

	private EmployerDao employerDao;
	private ConfirmByEmployeeDao confirmByEmployeeDao;
	private UserDao userDao;
	
	@Autowired
	public ConfirmByEmployeeManager(EmployerDao employerDao, ConfirmByEmployeeDao confirmByEmployeeDao, UserDao userDao) {
		super();
		this.employerDao = employerDao;
		this.confirmByEmployeeDao = confirmByEmployeeDao;
		this.userDao = userDao;
	}

	@Override
	public void createActivationByEmployee(Employer employer) {
		ConfirmByEmployee confirmByEmployee = new ConfirmByEmployee();
		confirmByEmployee.setEmployerId(employer.getId());
		confirmByEmployee.setConfirm(false);
		confirmByEmployeeDao.save(confirmByEmployee);
		
	}
	
	@Override
	public Result confirmEmployer(Employer employer) {
		if(confirmByEmployeeDao.getByEmployerId(employer.getId()) != null) {
			this.userDao.save(employer);
			this.employerDao.save(employer);
			return new SuccessResult("Employer is confirmed by employer.");
		}
		return new ErrorResult("Employer is not confirmed by employer.");
	}

	

}
