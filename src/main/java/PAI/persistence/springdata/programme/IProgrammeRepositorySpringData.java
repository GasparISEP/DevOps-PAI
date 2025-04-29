package PAI.persistence.springdata.programme;

import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProgrammeRepositorySpringData extends JpaRepository<ProgrammeDataModel, String> {
    List<String> findAllProgrammeNames();
    Optional<ProgrammeIDDataModel> findProgrammeIDByName(String name);
}
