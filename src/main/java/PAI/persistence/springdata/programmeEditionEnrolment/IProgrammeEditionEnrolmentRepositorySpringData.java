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
    SELECT COUNT(*)
    FROM programme_edition_enrolment
    WHERE (edition_programme_acronym, edition_school_year) IN (:pairs)
    """, nativeQuery = true)
    int countEnrolledStudentsByProgrammeEditionIds(@Param("pairs") List<Object[]> pairs);
}
