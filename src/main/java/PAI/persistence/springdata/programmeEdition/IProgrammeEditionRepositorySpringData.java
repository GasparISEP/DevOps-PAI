package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionRepositorySpringData extends JpaRepository<ProgrammeEditionDataModel, ProgrammeEditionIdDataModel> {

    @Query("SELECT pe FROM ProgrammeEditionDataModel pe WHERE pe.programmeEditionIdDataModel.programmeIDDataModel = :programmeId AND pe.programmeEditionIdDataModel.schoolYearIDDataModel = :schoolYearId")
    Optional<ProgrammeEditionDataModel> findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDataModel(
            @Param("programmeId") ProgrammeIDDataModel programmeId,
            @Param("schoolYearId") SchoolYearIDDataModel schoolYearId);

    @Query("SELECT pe FROM ProgrammeEditionDataModel pe WHERE pe.programmeEditionIdDataModel.programmeIDDataModel = :programmeIDDataModel")
    List<ProgrammeEditionDataModel> findProgrammeEditionByProgrammeIDDataModel(
            @Param("programmeIDDataModel") ProgrammeIDDataModel programmeIDDataModel);


    @Query("""
    SELECT p.programmeEditionIdDataModel
    FROM ProgrammeEditionDataModel p
    JOIN SchoolYearDataModel s ON s.id = p.programmeEditionIdDataModel.schoolYearIDDataModel
    WHERE p.programmeEditionIdDataModel.programmeIDDataModel = :programmeID
    AND s.startDate > :startDate
    """)
        List<ProgrammeEditionIdDataModel> findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(
                @Param("programmeID") ProgrammeIDDataModel programmeID,
                @Param("startDate") LocalDate startDate
        );

    @Query("SELECT pe.programmeEditionGeneratedIDDataModel FROM ProgrammeEditionDataModel pe " +
            "WHERE pe.programmeEditionIdDataModel.schoolYearIDDataModel = :schoolYearId " +
            "AND pe.programmeEditionIdDataModel.schoolYearIDDataModel IN :programmeIds")
    List<ProgrammeEditionIdDataModel> findProgrammeEditionIDsBySchoolYearIdAndProgrammeIds(
            @Param("schoolYearId") SchoolYearIDDataModel schoolYearId,
            @Param("programmeIds") List<ProgrammeIDDataModel> programmeIds);
}