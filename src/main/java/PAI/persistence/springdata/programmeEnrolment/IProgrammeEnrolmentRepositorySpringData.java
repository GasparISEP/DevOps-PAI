package PAI.persistence.springdata.programmeEnrolment;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProgrammeEnrolmentRepositorySpringData extends JpaRepository <ProgrammeEnrolmentDataModel, ProgrammeEnrolmentIDDataModel> {

    boolean existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(StudentIDDataModel studentID, ProgrammeIDDataModel programmeID);
    List<ProgrammeEnrolmentDataModel> findByProgrammeEnrolmentID_PeStudentID(StudentIDDataModel studentId);
    @Query("SELECT p.programmeEnrolmentID.peProgrammeID FROM ProgrammeEnrolmentDataModel p WHERE p.programmeEnrolmentID.peStudentID = :studentID")
    List<ProgrammeIDDataModel> findProgrammeIDsByStudentID(@Param("studentID") StudentIDDataModel studentID);
    Optional<ProgrammeEnrolmentDataModel> findByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(StudentIDDataModel studentID, ProgrammeIDDataModel programmeID);
    Optional<ProgrammeEnrolmentDataModel> findByProgrammeEnrolmentGID(UUID programmeEnrolmentGID);
}
