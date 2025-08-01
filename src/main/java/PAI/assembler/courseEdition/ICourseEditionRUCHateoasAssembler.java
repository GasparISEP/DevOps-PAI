package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseIDDTO;
import PAI.dto.courseEdition.DefineRucResponseDTO;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ICourseEditionRUCHateoasAssembler {
    EntityModel<DefineRucResponseDTO> toModel(DefineRucResponseDTO dto);
    CollectionModel<EntityModel<CourseEditionResponseIDDTO>> toCollectionModel(List<CourseEditionResponseIDDTO> dto);
}
