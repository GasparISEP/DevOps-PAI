package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface ITeacherHateoasAssembler extends RepresentationModelAssembler<TeacherDTO, EntityModel<TeacherDTO>> {
}
