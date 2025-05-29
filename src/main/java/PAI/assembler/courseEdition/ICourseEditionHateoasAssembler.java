package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.DefineRucResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface ICourseEditionHateoasAssembler {
    EntityModel<DefineRucResponseDTO> toModel(DefineRucResponseDTO dto);
}
