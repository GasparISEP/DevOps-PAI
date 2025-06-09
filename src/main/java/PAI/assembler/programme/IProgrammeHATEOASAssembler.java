package PAI.assembler.programme;

import PAI.dto.Programme.ProgrammeDTO;
import org.springframework.hateoas.EntityModel;

public interface IProgrammeHATEOASAssembler {

    EntityModel<ProgrammeDTO> toModel(ProgrammeDTO dto);
}
