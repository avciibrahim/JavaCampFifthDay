package kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Devs.business.abstracts.SubTecnologyService;
import kodlama.io.Devs.business.requests.CreateSubTechnologyRequest;
import kodlama.io.Devs.business.requests.DeleteSubTechnologyRequest;
import kodlama.io.Devs.business.requests.UpdateSubTechnologyRequest;
import kodlama.io.Devs.business.responses.GetAllSubTechnologyResponse;

@RestController
@RequestMapping("/api/subtechnologies")
public class SubTechnologyController {

	SubTecnologyService _subSubTecnologyService;
	
	@Autowired
	public SubTechnologyController(SubTecnologyService subTecnologyService) {
		_subSubTecnologyService = subTecnologyService;
	}
	
	@GetMapping("/getall")
	public List<GetAllSubTechnologyResponse> getAll(){
		return _subSubTecnologyService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateSubTechnologyRequest createSubTechnologyRequest) {
		
		_subSubTecnologyService.add(createSubTechnologyRequest);
	}
	
	@PostMapping("/delete")
	public void delete(DeleteSubTechnologyRequest deleteSubTechnologyRequest) {
		
		_subSubTecnologyService.delete(deleteSubTechnologyRequest);
	}
	
	@PostMapping("/update")
	public void update(UpdateSubTechnologyRequest updateSubTechnologyRequest) {
		
		_subSubTecnologyService.update(updateSubTechnologyRequest);
	}
}
