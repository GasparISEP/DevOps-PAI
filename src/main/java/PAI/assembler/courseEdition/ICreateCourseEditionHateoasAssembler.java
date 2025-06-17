package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;


public interface ICreateCourseEditionHateoasAssembler
        extends RepresentationModelAssembler<CourseEditionResponseDTO, EntityModel<CourseEditionResponseDTO>> {
}
