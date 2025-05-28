package PAI.persistence.springdata.programme;

import PAI.VOs.Acronym;
import PAI.VOs.Name;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProgrammeRepositorySpringData extends JpaRepository<ProgrammeDataModel, ProgrammeIDDataModel> {

    boolean existsByName (String name);

    boolean existsByAcronym (String acronym);
}
