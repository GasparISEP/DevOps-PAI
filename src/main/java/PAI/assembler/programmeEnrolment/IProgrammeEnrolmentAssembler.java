package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentListIDDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;

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
}
