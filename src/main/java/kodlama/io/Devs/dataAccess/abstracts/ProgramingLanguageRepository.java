package kodlama.io.Devs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.Devs.enity.concretes.ProgramingLanguage;

public interface ProgramingLanguageRepository extends JpaRepository<ProgramingLanguage, Integer>{

}
