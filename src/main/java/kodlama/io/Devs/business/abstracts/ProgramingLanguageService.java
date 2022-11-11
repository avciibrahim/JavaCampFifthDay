package kodlama.io.Devs.business.abstracts;

import java.util.List;

import kodlama.io.Devs.business.requests.CreateProgramingLanguageRequest;
import kodlama.io.Devs.business.requests.DeleteProgramingLanguageRequest;
import kodlama.io.Devs.business.requests.UpdateProgramingLanguageRequest;
import kodlama.io.Devs.business.responses.GetAllProgramingLanguageResponse;

public interface ProgramingLanguageService {

	List<GetAllProgramingLanguageResponse> getAll();
	void add(CreateProgramingLanguageRequest createProgramingLanguageRequest);
	void delete(DeleteProgramingLanguageRequest deleteProgramingLanguageRequest);
	void update(UpdateProgramingLanguageRequest updateProgramingLanguageRequest);
}
