package PAI.assembler.teacher;

import PAI.controllerRest.TeacherRestController;
import PAI.dto.teacher.TeacherWithRelevantDataDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeacherWithRelevantDataHateoasAssemblerImpl implements RepresentationModelAssembler<TeacherWithRelevantDataDTO, EntityModel<TeacherWithRelevantDataDTO>>, ITeacherWithRelevantDataHateoasAssembler {
    @Override
    public EntityModel<TeacherWithRelevantDataDTO> toModel(TeacherWithRelevantDataDTO teacherWithRelevantDataDTO) {
        if (teacherWithRelevantDataDTO == null) {
            throw  new IllegalArgumentException("TeacherWithRelevantDataDTO must not be null");
        }
        return EntityModel.of(teacherWithRelevantDataDTO,
                linkTo(methodOn(TeacherRestController.class)
                        .getTeacherById(teacherWithRelevantDataDTO.getAcronym())).withRel("get-teacher"));
    }
}
