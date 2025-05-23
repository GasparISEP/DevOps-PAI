package PAI.service.programmeEditionEnrolment;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.VOs.StudentID;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;

import java.util.List;

public interface IStudentProgrammeEditionEnrolmentService {
    List<StudentProgrammeEditionEnrolmentDTO> findAvailableProgrammeEditionsForStudent(StudentID studentID);

    void enrolStudentInProgrammeEdition(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID);

}
