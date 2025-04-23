package PAI.persistence.springdata.programmeEdition;

import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProgrammeEditionRepositorySpringData extends JpaRepository<ProgrammeEditionDataModel, ProgrammeEditionIdDataModel> {


    Optional<ProgrammeEditionIdDataModel> findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDatasModels (
            ProgrammeIDDataModel programmeIDDataModel, SchoolYearIDDataModel schoolYearIDDataModel);
}
