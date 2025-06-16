package PAI.service.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEdition.ProgrammeEditionWithNameAndDescriptionResponseDTO;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentHateoasResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IStudentProgrammeEditionEnrolmentService {
    List<StudentProgrammeEditionEnrolmentDTO> findAvailableProgrammeEditionsForStudent(StudentID studentID);

    void enrolStudentInProgrammeEdition(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID);

    StudentID findStudentIDByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID);

    LocalDate findDateByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID);

    List<ProgrammeEditionID> getAvailableProgrammeEditions(ProgrammeID programmeID, LocalDate date);

    ProgrammeID findProgrammeIDByProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID);

    List<ProgrammeEditionID> getProgrammesEditionsIdWhereStudentIsEnrolled(StudentID studentID);

    List<ProgrammeEditionID> possibleProgrammeEditionsWhereStudentCanBeEnrolled(List<ProgrammeEditionID> ProgrammeEditionsIdsAvailable, List<ProgrammeEditionID> ProgrammeEditionsIdsAlreadyTaken);

    ProgrammeEditionWithNameAndDescriptionResponseDTO programmeEditionWithNameAndDescription(ProgrammeEditionID programmeEditionID);

    ProgrammeEnrolmentHateoasResponseDto getProgrammeEnrolmentHateoasInformationDto(ProgrammeEnrolment programmeEnrolment);
}