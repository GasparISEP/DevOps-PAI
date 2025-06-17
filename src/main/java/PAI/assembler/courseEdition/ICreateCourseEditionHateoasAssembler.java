package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseIDDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface ICreateCourseEditionHateoasAssembler {

    EntityModel<CourseEditionResponseIDDTO> toModel(CourseEditionResponseIDDTO dto);
    CollectionModel<EntityModel<CourseEditionResponseIDDTO>> toCollectionModel(List<CourseEditionResponseIDDTO> dtos);

}
