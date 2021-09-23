package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.adapters.EmailService;
import kodlamaio.hrms.core.adapters.MernisService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private EmailService emailService;
	private MernisService mernisService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, EmailService emailService, MernisService mernisService) {
		super();
		this.candidateDao = candidateDao;
		this.emailService = emailService;
		this.mernisService = mernisService;
	}

	@Override
	public DataResult<List<Candidate>> getall() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"Data listed.");
	}

	@Override
	public Result add(Candidate candidate) {
		if(candidate.getFirstName() == null || candidate.getLastName() == null || 
				candidate.getIdentityNumber() == null ||
				candidate.getEmail() == null || candidate.getPassword() == null) {
			return new ErrorResult("All fields are required.");	
		}
		else if(this.candidateDao.findByIdentityNumber(candidate.getIdentityNumber()) != null) {
			return new ErrorResult("This idendity number has already been registered.");
		}
		else if(this.candidateDao.findByEmail(candidate.getEmail()) != null) {
			return new ErrorResult("This email has already been registered.");
		}
		emailService.activateEmail();
		mernisService.activateMernis();
		this.candidateDao.save(candidate);
		return new SuccessResult("Candidate added.");
	}

}
