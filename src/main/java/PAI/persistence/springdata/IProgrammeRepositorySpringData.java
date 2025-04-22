package PAI.persistence.springdata;

import PAI.persistence.datamodel.ProgrammeDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProgrammeRepositorySpringData extends JpaRepository<ProgrammeDataModel, String> {
    public List<String> findAllProgrammeNames();
}
