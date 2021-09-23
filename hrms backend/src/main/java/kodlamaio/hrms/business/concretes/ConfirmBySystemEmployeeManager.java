package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ConfirmBySystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ConfirmBySystemEmployeeDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.ConfirmBySystemEmployee;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class ConfirmBySystemEmployeeManager implements ConfirmBySystemEmployeeService{

	private ConfirmBySystemEmployeeDao confirmBySystemEmployeeDao;
	private EmployerDao employerDao;
	
	@Autowired
	public ConfirmBySystemEmployeeManager(ConfirmBySystemEmployeeDao confirmBySystemEmployeeDao,
			EmployerDao employerDao) {
		super();
		this.confirmBySystemEmployeeDao = confirmBySystemEmployeeDao;
		this.employerDao = employerDao;
	}

	@Override
	public void createActivationByEmployee(Employer employer) {
		ConfirmBySystemEmployee confirmBySystemEmployee = new ConfirmBySystemEmployee();
		confirmBySystemEmployee.setEmployerId(employer.getId());
		confirmBySystemEmployee.setConfirm(false);
		confirmBySystemEmployeeDao.save(confirmBySystemEmployee);
	}

	@Override
	public Result confirmEmployer(Employer employer) {
		employerDao.save(employer);
		return new SuccessResult("Employer is confirmed by system employee.");
	}
}
