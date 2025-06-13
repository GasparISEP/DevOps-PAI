package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;

import java.time.LocalDate;
import java.util.List;

public interface IStudentProgrammeEditionEnrolmentService {
    List<StudentProgrammeEditionEnrolmentDTO> findAvailableProgrammeEditionsForStudent(StudentID studentID);

    void enrolStudentInProgrammeEdition(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID);

    StudentID findStudentIDByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID);

    LocalDate findDateByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID);

    List<ProgrammeEditionID> getAvailableProgrammeEditions(ProgrammeID programmeID, LocalDate date);

    ProgrammeID findProgrammeIDByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID);
}
