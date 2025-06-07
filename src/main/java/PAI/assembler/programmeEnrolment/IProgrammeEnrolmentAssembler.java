package PAI.assembler.programmeEnrolment;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;

public interface IProgrammeEnrolmentAssembler {
    ProgrammeEnrolmentResponseDTO toProgrammeEnrolmentDTO (ProgrammeEnrolment programmeEnrolment);
    StudentID toStudentID (ProgrammeEnrolmentDTO programmeDTO);
    AccessMethodID toAccessMethodID (ProgrammeEnrolmentDTO programmeDTO);
    ProgrammeID toProgrammeID (ProgrammeEnrolmentDTO programmeDTO);
    Date toDateVO (ProgrammeEnrolmentDTO programmeDTO);
}
