package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ICourseEditionHateoasAssembler {
    EntityModel<DefineRucResponseDTO> toModel(DefineRucResponseDTO dto);
    CollectionModel<EntityModel<CourseEditionResponseDTO>> toCollectionModel(List<CourseEditionResponseDTO> dto);
}
