package PAI.assembler.teacherCareerProgression;

import PAI.controllerRest.TeacherRestController;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UpdateTeacherWorkingPercentageHateoasAssembler implements
        RepresentationModelAssembler<UpdateTeacherWorkingPercentageResponseDTO,
                        EntityModel<UpdateTeacherWorkingPercentageResponseDTO>>, IUpdateTeacherWorkingPercentageHateoasAssembler {

    @Override
    public EntityModel<UpdateTeacherWorkingPercentageResponseDTO> toModel(UpdateTeacherWorkingPercentageResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(TeacherRestController.class)
                        .getTeacherById(dto.teacherID())).withRel("get-teacher"),

                linkTo(methodOn(TeacherRestController.class)
                        .getAllTeachers()).withRel("all-teachers"),

                linkTo(methodOn(TeacherRestController.class)
                        .updateTeacherWorkingPercentage(dto.teacherID(), null)).withSelfRel()
        );
    }
}
