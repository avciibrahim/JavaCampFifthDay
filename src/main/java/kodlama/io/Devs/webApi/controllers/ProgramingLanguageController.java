package kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Devs.business.abstracts.ProgramingLanguageService;
import kodlama.io.Devs.business.requests.CreateProgramingLanguageRequest;
import kodlama.io.Devs.business.requests.DeleteProgramingLanguageRequest;
import kodlama.io.Devs.business.requests.UpdateProgramingLanguageRequest;
import kodlama.io.Devs.business.responses.GetAllProgramingLanguageResponse;

@RestController
@RequestMapping("/api/programinglanguages")
public class ProgramingLanguageController {

	private ProgramingLanguageService _programingLanguageService;

	@Autowired
	public ProgramingLanguageController(ProgramingLanguageService programingLanguageService) {
		_programingLanguageService = programingLanguageService;
	}

	@GetMapping("/getall")
	public List<GetAllProgramingLanguageResponse> getAll(){
		return _programingLanguageService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateProgramingLanguageRequest createProgramingLanguageRequest) {
		_programingLanguageService.add(createProgramingLanguageRequest);
	}
	
	@PostMapping("/delete")
	public void delete(DeleteProgramingLanguageRequest deleteProgramingLanguageRequest) {
		_programingLanguageService.delete(deleteProgramingLanguageRequest);
	}
	
	@PostMapping("/update")
	public void update(UpdateProgramingLanguageRequest updateProgramingLanguageRequest) {
		_programingLanguageService.update(updateProgramingLanguageRequest);
	}
}
