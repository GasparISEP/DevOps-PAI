package PAI.assembler.teacher;
import PAI.controllerRest.TeacherRestController;
import PAI.dto.teacher.TeacherDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeacherHateoasAssemblerImpl implements ITeacherHateoasAssembler {

    @Override
    public EntityModel<TeacherDTO> toModel (TeacherDTO teacherDTO) {
        if (teacherDTO == null) {
            throw new IllegalArgumentException("TeacherDTO must not be null");
        }
        return EntityModel.of(teacherDTO,
                linkTo(methodOn(TeacherRestController.class)
                        .getTeacherById(teacherDTO.id()))
                        .withSelfRel());
    }

    @Override
    public CollectionModel<EntityModel<TeacherDTO>> toCollectionModel (Iterable<? extends TeacherDTO> teacherDTOs) {
        if (teacherDTOs == null) {
            throw new IllegalArgumentException("TeacherDTOs must not be null");
        }

        List<EntityModel<TeacherDTO>> models = new ArrayList<>();

        for (TeacherDTO teacherDTO : teacherDTOs) {
            models.add(toModel(teacherDTO));
        }

        return  CollectionModel.of(
                models,
                linkTo(methodOn(TeacherRestController.class).getAllTeachers()).withRel("teachers")
                );
    }
}
