package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisements")
@CrossOrigin
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getall(){
		return this.jobAdvertisementService.getall();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement){
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("/getbycreatedate")
	public DataResult<List<JobAdvertisement>> getByCreateDate(@RequestParam String createDate){
		return this.jobAdvertisementService.getByCreateDate(createDate);
	}
	
	@GetMapping("/getbyemployer")
	public DataResult<List<JobAdvertisement>> getByEmployer_CompanyName(@RequestParam String companyName){
		return this.jobAdvertisementService.getByEmployer_CompanyName(companyName);
	}
	
	@GetMapping("/getbyisactive")
	public DataResult<List<JobAdvertisement>> getByIsActive(Boolean isActive){
		return this.jobAdvertisementService.getByIsActive(isActive);
	}

	@GetMapping("/getbyjobposition")
	public DataResult<List<JobAdvertisement>> getByJobPosition_Position(@RequestParam String position){
		return this.jobAdvertisementService.getByJobPosition_Position(position);
	}
	
	@GetMapping("/findbyid")
	public DataResult<List<JobAdvertisement>> getById(@RequestParam int id){
		return this.jobAdvertisementService.findById(id);
	}
}
