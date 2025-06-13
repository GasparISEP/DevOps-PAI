package PAI.assembler.programme;

import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import org.springframework.hateoas.EntityModel;

public interface IProgrammeHATEOASAssembler {

    EntityModel<ProgrammeIDDTO> toModel(ProgrammeIDDTO dto);
}
