package PAI.assembler.teacher;

import PAI.controllerRest.DepartmentRestController;
import PAI.controllerRest.TeacherRestController;
import PAI.dto.teacher.TeacherDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeacherHateoasAssemblerImpl implements RepresentationModelAssembler<TeacherDTO, EntityModel<TeacherDTO>>, ITeacherHateoasAssembler {
    @Override
    public EntityModel<TeacherDTO> toModel (TeacherDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(TeacherRestController.class)
                        .getAllTeachers())
                        .withRel("teachers"));
    }
}
