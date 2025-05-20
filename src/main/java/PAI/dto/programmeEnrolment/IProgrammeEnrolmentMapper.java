package PAI.dto.programmeEnrolment;

import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

public interface IProgrammeEnrolmentMapper {
    ProgrammeEnrolment toProgrammeEnrolment (ProgrammeEnrolmentDTO programmeDTO);
    ProgrammeEnrolmentResponseDTO toProgrammeEnrolmentDTO (ProgrammeEnrolment programmeEnrolment);
}
