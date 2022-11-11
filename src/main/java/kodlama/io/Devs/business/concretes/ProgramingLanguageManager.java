package kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Devs.business.abstracts.ProgramingLanguageService;
import kodlama.io.Devs.business.requests.CreateProgramingLanguageRequest;
import kodlama.io.Devs.business.requests.DeleteProgramingLanguageRequest;
import kodlama.io.Devs.business.requests.UpdateProgramingLanguageRequest;
import kodlama.io.Devs.business.responses.GetAllProgramingLanguageResponse;
import kodlama.io.Devs.dataAccess.abstracts.ProgramingLanguageRepository;
import kodlama.io.Devs.enity.concretes.ProgramingLanguage;

@Service
public class ProgramingLanguageManager implements ProgramingLanguageService {

	ProgramingLanguageRepository _programingLanguageRepository;

	@Autowired
	public ProgramingLanguageManager(ProgramingLanguageRepository programingLanguageRepository) {
		_programingLanguageRepository = programingLanguageRepository;
	}

	@Override
	public List<GetAllProgramingLanguageResponse> getAll() {
		// TODO Auto-generated method stub

		List<ProgramingLanguage> programingLanguages = _programingLanguageRepository.findAll();
		List<GetAllProgramingLanguageResponse> getAllProgramingLanguagesResponse = new ArrayList<GetAllProgramingLanguageResponse>();

		for (ProgramingLanguage programingLanguage : programingLanguages) {
			GetAllProgramingLanguageResponse item = new GetAllProgramingLanguageResponse();
			item.setId(programingLanguage.getId());
			item.setName(programingLanguage.getName());

			getAllProgramingLanguagesResponse.add(item);
		}
		return getAllProgramingLanguagesResponse;
	}

	@Override
	public void add(CreateProgramingLanguageRequest createProgramingLanguageRequest) {
		// TODO Auto-generated method stub

		if (createProgramingLanguageRequest.getName().isBlank()) {
			System.out.println("Bu programlama dili alanı boş geçilemez.");
		} else {
			List<GetAllProgramingLanguageResponse> programingLanguages = getAll();

			GetAllProgramingLanguageResponse programingLanguageResponse = programingLanguages.stream()
					.filter(progLang -> createProgramingLanguageRequest.getName().equals(progLang.getName())).findAny()
					.orElse(null);

			if (programingLanguageResponse != null) {
				System.out.println("Bu programlama dili daha önce eklenmiştir.");
			} else {
				ProgramingLanguage programingLanguage = new ProgramingLanguage();
				programingLanguage.setName(createProgramingLanguageRequest.getName());

				_programingLanguageRepository.save(programingLanguage);

			}
		}

	}

	@Override
	public void delete(DeleteProgramingLanguageRequest deleteProgramingLanguageRequest) {
		// TODO Auto-generated method stub

		if (deleteProgramingLanguageRequest.getName().isBlank()) {
			System.out.println("Silmek istediğiniz programlama dilini seçiniz.");
		} else {
			List<GetAllProgramingLanguageResponse> programingLanguages = getAll();

			GetAllProgramingLanguageResponse programingLanguageResponse = programingLanguages.stream()
					.filter(progLang -> deleteProgramingLanguageRequest.getName().equals(progLang.getName())).findAny()
					.orElse(null);

			if (programingLanguageResponse == null) {
				System.out.println(deleteProgramingLanguageRequest.getName() + " programlama dili bulunamamıştır");
			} else {

				_programingLanguageRepository.deleteById(programingLanguageResponse.getId());

			}
		}

	}

	@Override
	public void update(UpdateProgramingLanguageRequest updateProgramingLanguageRequest) {
		// TODO Auto-generated method stub
		if (updateProgramingLanguageRequest.getNewName().isBlank()
				|| updateProgramingLanguageRequest.getOldName().isBlank()) {
			System.out.println("Değiştirmek istediğiniz veya yeni ismini giriniz.");
		} else {
			List<GetAllProgramingLanguageResponse> programingLanguages = getAll();

			GetAllProgramingLanguageResponse programingLanguageResponse = programingLanguages.stream()
					.filter(progLang -> updateProgramingLanguageRequest.getOldName().equals(progLang.getName()))
					.findAny().orElse(null);

			if (programingLanguageResponse == null) {
				System.out.println(updateProgramingLanguageRequest.getOldName() + " programlama dili bulunamamıştır");

			} else {

				GetAllProgramingLanguageResponse programingLanguageResponseNew = programingLanguages.stream()
						.filter(progLang -> updateProgramingLanguageRequest.getNewName().equals(progLang.getName()))
						.findAny().orElse(null);
				if (programingLanguageResponseNew != null) {
					System.out.println("Bu programlama dili daha önce eklenmiştir.");
				} else {
					Optional<ProgramingLanguage> programingLanguage = _programingLanguageRepository
							.findById(programingLanguageResponse.getId());
					programingLanguage.get().setName(updateProgramingLanguageRequest.getNewName());
					_programingLanguageRepository.save(programingLanguage.get());
				}
			}
		}
	}

}
