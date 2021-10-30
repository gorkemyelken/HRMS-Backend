package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlamaio.hrms.business.abstracts.CvJobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvJobExperience;

@RestController
@RequestMapping("/api/cvjobexperiences")
@CrossOrigin
public class CvJobExperiencesController {

	private CvJobExperienceService cvJobExperienceService;

	@Autowired
	public CvJobExperiencesController(CvJobExperienceService cvJobExperienceService) {
		super();
		this.cvJobExperienceService = cvJobExperienceService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CvJobExperience>> getall(){
		return this.cvJobExperienceService.getall();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CvJobExperience cvJobExperience) {
		return this.cvJobExperienceService.add(cvJobExperience);
	}
}
