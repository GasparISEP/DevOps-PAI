package PAI.assembler.teacher;
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
    public EntityModel<TeacherDTO> toModel (TeacherDTO teacherDTO) {
        if(teacherDTO == null) {
            throw  new IllegalArgumentException("TeacherDTO must not be null");
        }
        return EntityModel.of(teacherDTO,
                linkTo(methodOn(TeacherRestController.class)
                        .getAllTeachers())
                        .withRel("teachers"));
    }
}
