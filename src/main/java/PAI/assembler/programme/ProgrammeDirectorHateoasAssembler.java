package PAI.assembler.programme;

import PAI.controllerRest.ProgrammeRestController;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.teacher.TeacherIdDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProgrammeDirectorHateoasAssembler implements IProgrammeDirectorHateoasAssembler {

    public CollectionModel<TeacherIdDTO> toModel(String programmeAcronym, ProgrammeDirectorResponseDTO responseDTO) {
        CollectionModel<TeacherIdDTO> teacherModels = CollectionModel.of(responseDTO.teachers());

        teacherModels.add(linkTo(methodOn(ProgrammeRestController.class)
                .getProgrammeDirector(programmeAcronym))
                .withSelfRel());

        return teacherModels;
    }
}