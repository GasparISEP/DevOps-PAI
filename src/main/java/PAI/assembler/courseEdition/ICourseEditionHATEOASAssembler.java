package PAI.assembler.courseEdition;

import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

public interface ICourseEditionHATEOASAssembler extends RepresentationModelAssembler<CourseEditionResponseDTO, EntityModel<CourseEditionResponseDTO>> {

}
