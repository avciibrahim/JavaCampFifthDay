package kodlama.io.Devs.business.abstracts;

import java.util.List;

import kodlama.io.Devs.business.requests.CreateSubTechnologyRequest;
import kodlama.io.Devs.business.requests.DeleteSubTechnologyRequest;
import kodlama.io.Devs.business.requests.UpdateSubTechnologyRequest;
import kodlama.io.Devs.business.responses.GetAllSubTechnologyResponse;

public interface SubTecnologyService {

	List<GetAllSubTechnologyResponse> getAll();
	void add(CreateSubTechnologyRequest createSubTechnologyRequest);
	void delete(DeleteSubTechnologyRequest deleteSubTechnologyRequest);
	void update(UpdateSubTechnologyRequest updateSubTechnologyRequest);
}
