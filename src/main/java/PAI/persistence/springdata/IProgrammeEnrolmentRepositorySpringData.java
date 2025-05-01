package PAI.persistence.springdata;

import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgrammeEnrolmentRepositorySpringData extends JpaRepository <ProgrammeEnrolmentDataModel, ProgrammeEnrolmentIDDataModel> {

    boolean existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(StudentIDDataModel studentID, ProgrammeIDDataModel programmeID);
}
