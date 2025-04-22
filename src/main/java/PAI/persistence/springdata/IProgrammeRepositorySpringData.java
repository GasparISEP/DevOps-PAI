package PAI.persistence.springdata;

import PAI.persistence.datamodel.ProgrammeDataModel;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProgrammeRepositorySpringData extends JpaRepository<ProgrammeDataModel, String> {
    List<String> findAllProgrammeNames();
    Optional<ProgrammeIDDataModel> findProgrammeIDByName(String name);
}
