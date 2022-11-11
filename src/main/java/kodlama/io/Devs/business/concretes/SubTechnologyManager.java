package kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Devs.business.abstracts.SubTecnologyService;
import kodlama.io.Devs.business.requests.CreateSubTechnologyRequest;
import kodlama.io.Devs.business.requests.DeleteSubTechnologyRequest;
import kodlama.io.Devs.business.requests.UpdateSubTechnologyRequest;
import kodlama.io.Devs.business.responses.GetAllSubTechnologyResponse;
import kodlama.io.Devs.dataAccess.abstracts.ProgramingLanguageRepository;
import kodlama.io.Devs.dataAccess.abstracts.SubTechnologyRepository;
import kodlama.io.Devs.enity.concretes.ProgramingLanguage;
import kodlama.io.Devs.enity.concretes.SubTechnology;

@Service
public class SubTechnologyManager implements SubTecnologyService {

	SubTechnologyRepository _subTechnologyRepository;
	ProgramingLanguageRepository _prProgramingLanguageRepository;

	@Autowired
	public SubTechnologyManager(SubTechnologyRepository subTechnologyRepository,
			ProgramingLanguageRepository programingLanguageRepository) {
		_subTechnologyRepository = subTechnologyRepository;
		_prProgramingLanguageRepository = programingLanguageRepository;
	}

	@Override
	public List<GetAllSubTechnologyResponse> getAll() {
		// TODO Auto-generated method stub
		List<SubTechnology> subTechnologies = _subTechnologyRepository.findAll();
		List<GetAllSubTechnologyResponse> getAllSubTechnologiesResponse = new ArrayList<GetAllSubTechnologyResponse>();

		for (SubTechnology subTechnology : subTechnologies) {
			GetAllSubTechnologyResponse item = new GetAllSubTechnologyResponse();

			item.setId(subTechnology.getId());
			item.setName(subTechnology.getName());

			getAllSubTechnologiesResponse.add(item);
		}

		return getAllSubTechnologiesResponse;
	}

	@Override
	public void add(CreateSubTechnologyRequest createSubTechnologyRequest) {
		// TODO Auto-generated method stub

		if (createSubTechnologyRequest.getName().isBlank()) {
			System.out.println("Bu Alt teknoloji alanı veya Programlama Dili alanı boş geçilemez.");
		} else {
			List<GetAllSubTechnologyResponse> subTecnologies = getAll();

			GetAllSubTechnologyResponse subTechnologyResponse = subTecnologies.stream()
					.filter(subTech -> createSubTechnologyRequest.getName().equals(subTech.getName())).findAny()
					.orElse(null);

			if (subTechnologyResponse != null) {
				System.out.println("Bu alt teknoloji daha önce eklenmiştir.");
			} else {
				SubTechnology subTechnology = new SubTechnology();
				subTechnology.setName(createSubTechnologyRequest.getName());

				Optional<ProgramingLanguage> programingLanguage = _prProgramingLanguageRepository
						.findById(createSubTechnologyRequest.getProgramingLanguageID());
				subTechnology.setProgramingLanguage(programingLanguage.get());

				_subTechnologyRepository.save(subTechnology);
			}
		}

	}

	@Override
	public void delete(DeleteSubTechnologyRequest deleteSubTechnologyRequest) {
		// TODO Auto-generated method stub
		
		if (deleteSubTechnologyRequest.getName().isBlank()) {
			System.out.println("Silmek istediğiniz programlama dilini seçiniz.");
		} else {
			List<GetAllSubTechnologyResponse> subTechnologies = getAll();

			GetAllSubTechnologyResponse getAllSubTechnologyResponse = subTechnologies.stream()
					.filter(progLang -> deleteSubTechnologyRequest.getName().equals(progLang.getName())).findAny()
					.orElse(null);

			if (getAllSubTechnologyResponse == null) {
				System.out.println(deleteSubTechnologyRequest.getName() + " programlama dili bulunamamıştır");
			} else {

				_subTechnologyRepository.deleteById(getAllSubTechnologyResponse.getId());

			}
		}
		
	}

	@Override
	public void update(UpdateSubTechnologyRequest updateSubTechnologyRequest) {
		// TODO Auto-generated method stub
		if (updateSubTechnologyRequest.getNewName().isBlank()
				|| updateSubTechnologyRequest.getOldName().isBlank()) {
			System.out.println("Değiştirmek istediğiniz veya yeni ismini giriniz.");
		} else {
			List<GetAllSubTechnologyResponse> subTechnologies = getAll();

			GetAllSubTechnologyResponse subTechnologyResponse = subTechnologies.stream()
					.filter(subTech -> updateSubTechnologyRequest.getOldName().equals(subTech.getName()))
					.findAny().orElse(null);

			if (subTechnologyResponse == null) {
				System.out.println(updateSubTechnologyRequest.getOldName() + " programlama dili bulunamamıştır");

			} else {

				GetAllSubTechnologyResponse subTechnologyResponseNew = subTechnologies.stream()
						.filter(subTech -> updateSubTechnologyRequest.getNewName().equals(subTech.getName()))
						.findAny().orElse(null);
				if (subTechnologyResponseNew != null) {
					System.out.println("Bu programlama dili daha önce eklenmiştir.");
				} else {
					Optional<SubTechnology> subOptional = _subTechnologyRepository
							.findById(subTechnologyResponse.getId());
					subOptional.get().setName(updateSubTechnologyRequest.getNewName());
					_subTechnologyRepository.save(subOptional.get());
				}
			}
		}
		
	}

}
