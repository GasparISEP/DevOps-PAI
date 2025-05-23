package PAI.service.programmeEditionEnrolment;

import PAI.VOs.StudentID;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;

import java.util.List;

public interface IStudentProgrammeEditionEnrolmentService {
    List<StudentProgrammeEditionEnrolmentDTO> findAvailableProgrammeEditionsForStudent(StudentID studentID);
}
