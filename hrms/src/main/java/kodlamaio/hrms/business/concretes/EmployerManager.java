package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.adapters.EmailService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private EmailService emailService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, EmailService emailService) {
		super();
		this.employerDao = employerDao;
		this.emailService = emailService;
	}

	@Override
	public DataResult<List<Employer>> getall() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Data listed.");
	}

	@Override
	public Result add(Employer employer) {
		if(employer.getCompanyName() == null || employer.getWebAddress() == null ||
				employer.getEmail() == null || employer.getPhoneNumber() == null ||
				employer.getPassword() == null) {
			return new ErrorResult("All fields are required.");	
		}
		else if(this.employerDao.findByEmail(employer.getEmail()) != null) {
			return new ErrorResult("This email has already been registered.");
		}
		emailService.activateEmail();
		this.employerDao.save(employer);
		employer.setConfirm(false);
		return new SuccessResult("Employer is waiting to be verified by the system employer.");
	}

}
