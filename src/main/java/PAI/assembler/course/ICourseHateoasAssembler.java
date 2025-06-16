package PAI.assembler.course;

import PAI.dto.course.CourseResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ICourseHateoasAssembler  extends RepresentationModelAssembler<CourseResponseDTO, EntityModel<CourseResponseDTO>> {


}
