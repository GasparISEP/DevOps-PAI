package PAI.persistence.springdata;

import PAI.persistence.datamodel.ProgrammeDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgrammeRepositorySpringData extends JpaRepository<ProgrammeDataModel, String> {
}
