package PAI.assembler.programmeEnrolment;

import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface IProgrammeEnrolmentHATEOASAssembler {
    EntityModel<ProgrammeEnrolmentResponseDTO> toModel(ProgrammeEnrolmentResponseDTO dto);
}
