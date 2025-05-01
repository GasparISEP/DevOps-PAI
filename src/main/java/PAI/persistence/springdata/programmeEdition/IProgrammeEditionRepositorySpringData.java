package PAI.persistence.springdata.programmeEdition;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionRepositorySpringData extends JpaRepository<ProgrammeEditionDataModel, ProgrammeEditionIdDataModel> {

    @Query("SELECT pe FROM ProgrammeEditionDataModel pe WHERE pe.programmeEditionIdDataModel._programmeIDDataModel = :programmeId AND pe.programmeEditionIdDataModel._schoolYearIDDataModel = :schoolYearId")
    Optional<ProgrammeEditionDataModel> findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDataModel(
            @Param("programmeId") ProgrammeIDDataModel programmeId,
            @Param("schoolYearId") SchoolYearIDDataModel schoolYearId);

    @Query("SELECT pe FROM ProgrammeEditionDataModel pe WHERE pe.programmeEditionIdDataModel._programmeIDDataModel = :programmeIDDataModel")
    List<ProgrammeEditionDataModel> findProgrammeEditionByProgrammeIDDataModel(
            @Param("programmeIDDataModel") ProgrammeIDDataModel programmeIDDataModel);
}
