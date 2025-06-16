package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.dto.programmeEnrolment.*;

import java.util.List;

public interface IProgrammeEnrolmentAssembler {
    ProgrammeEnrolmentResponseDTO toProgrammeEnrolmentDTO (ProgrammeEnrolment programmeEnrolment);
    StudentID toStudentID (ProgrammeEnrolmentDTO programmeDTO);
    AccessMethodID toAccessMethodID (ProgrammeEnrolmentDTO programmeDTO);
    ProgrammeID toProgrammeID (ProgrammeEnrolmentDTO programmeDTO);
    Date toDateVO (ProgrammeEnrolmentDTO programmeDTO);
    ProgrammeEnrolmentGeneratedID toProgrammeEnrolmentGeneratedID (ProgrammeEnrolmentIdDTO programmeEnrolmentIdDTO);
    ProgrammeEnrolmentListIDDTO toDTO(ProgrammeEnrolment programmeEnrolment);
    List<ProgrammeEnrolmentListIDDTO> toListOfDTOs(List<ProgrammeEnrolment> listIDs);
    ProgrammeEnrolmentHateoasResponseDto toHateoasDto(ProgrammeEnrolment programmeEnrolment, Student student, Programme programme);
}
