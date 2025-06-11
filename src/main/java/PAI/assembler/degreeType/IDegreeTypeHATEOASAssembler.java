package PAI.assembler.degreeType;

import PAI.dto.degreeType.DegreeTypeDTO;
import org.springframework.hateoas.EntityModel;

public interface IDegreeTypeHATEOASAssembler {
    EntityModel<DegreeTypeDTO> toModel(DegreeTypeDTO dto);
}
