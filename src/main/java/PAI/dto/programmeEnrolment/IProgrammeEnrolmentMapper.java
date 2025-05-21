package PAI.dto.programmeEnrolment;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

public interface IProgrammeEnrolmentMapper {
    ProgrammeEnrolment toProgrammeEnrolment (ProgrammeEnrolmentDTO programmeDTO);
    ProgrammeEnrolmentResponseDTO toProgrammeEnrolmentDTO (ProgrammeEnrolment programmeEnrolment);
    StudentID toStudentID (ProgrammeEnrolmentDTO programmeDTO);
    AccessMethodID toAccessMethodID (ProgrammeEnrolmentDTO programmeDTO);
    ProgrammeID toProgrammeID (ProgrammeEnrolmentDTO programmeDTO);
    Date toDateVO (ProgrammeEnrolmentDTO programmeDTO);
}
