package PAI.persistence.springdata.programmeEditionEnrolment;

import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionEnrolmentRepositorySpringData extends JpaRepository<ProgrammeEditionEnrolmentDataModel, ProgrammeEditionEnrolmentIDDataModel> {
    List<ProgrammeEditionEnrolmentDataModel> findAllById_ProgrammeEditionIdDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel);

    Optional<ProgrammeEditionEnrolmentDataModel> findById_StudentIdDataModelAndId_ProgrammeEditionIdDataModel(StudentIDDataModel studentId, ProgrammeEditionIdDataModel programmeEditionId);

    @Query(value = """
    SELECT edition_programme_acronym, edition_school_year, COUNT(*) as total
    FROM PROGRAMME_EDITION_ENROLMENTS
    WHERE edition_programme_acronym IN (:acronyms)
      AND edition_school_year IN (:schoolYears)
    GROUP BY edition_programme_acronym, edition_school_year
    """, nativeQuery = true)
    List<Object[]> countEnrolledByAcronymAndSchoolYear(
            @Param("acronyms") List<String> acronyms,
            @Param("schoolYears") List<String> schoolYears
    );

}
