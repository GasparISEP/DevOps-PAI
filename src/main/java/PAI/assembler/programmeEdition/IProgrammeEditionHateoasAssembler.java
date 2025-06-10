package PAI.assembler.programmeEdition;

import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import java.util.List;

public interface IProgrammeEditionHateoasAssembler {

    EntityModel<ProgrammeEditionResponseDTO> toModel(ProgrammeEditionResponseDTO dto);
    CollectionModel<EntityModel<ProgrammeEditionResponseDTO>> toCollectionModel(List<ProgrammeEditionResponseDTO> dtos);

}
