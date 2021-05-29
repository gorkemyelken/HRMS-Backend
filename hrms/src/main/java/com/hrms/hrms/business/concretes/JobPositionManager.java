package com.hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.JobPositionService;
import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.core.utilities.results.ErrorResult;
import com.hrms.hrms.core.utilities.results.Result;
import com.hrms.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.hrms.core.utilities.results.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.JobPositionDao;
import com.hrms.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(),"Job positions listed.");
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if(getByPosition(jobPosition.getPosition()).getData() != null) {
			return new ErrorResult("This position is already exists.");
		}
		else {
			jobPositionDao.save(jobPosition);
			return new SuccessResult("Job position added.");
		}	
	}

	@Override
	public DataResult<JobPosition> getByPosition(String position) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.getByPosition(position),
				"Job positions listed by position.");
	}
	
	
	
}
