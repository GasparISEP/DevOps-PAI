package PAI.persistence.springdata.programmeEnrolment;

import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEnrolmentRepositorySpringData extends JpaRepository <ProgrammeEnrolmentDataModel, ProgrammeEnrolmentIDDataModel> {

    boolean existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(StudentIDDataModel studentID, ProgrammeIDDataModel programmeID);

    @Query("SELECT p.programmeEnrolmentID.peProgrammeID FROM ProgrammeEnrolmentDataModel p WHERE p.programmeEnrolmentID.peStudentID = :studentID")
    List<ProgrammeIDDataModel> findProgrammeIDsByStudentID(@Param("studentID") StudentIDDataModel studentID);
    Optional<ProgrammeEnrolmentDataModel> findByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(StudentIDDataModel studentID, ProgrammeIDDataModel programmeID);

}
