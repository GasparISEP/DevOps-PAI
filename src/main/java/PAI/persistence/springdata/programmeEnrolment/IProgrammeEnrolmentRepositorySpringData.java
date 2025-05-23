package PAI.persistence.springdata.programmeEnrolment;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProgrammeEnrolmentRepositorySpringData extends JpaRepository <ProgrammeEnrolmentDataModel, ProgrammeEnrolmentIDDataModel> {

    boolean existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(StudentIDDataModel studentID, ProgrammeIDDataModel programmeID);

    @Query("SELECT p.programmeEnrolmentID.peProgrammeID FROM ProgrammeEnrolmentDataModel p WHERE p.programmeEnrolmentID.peStudentID = :studentID")
    List<ProgrammeIDDataModel> findProgrammeIDsByStudentID(@Param("studentID") StudentIDDataModel studentID);

}
