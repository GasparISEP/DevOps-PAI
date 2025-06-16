package PAI.assembler.programme;

import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.teacher.TeacherIdDTO;
import org.springframework.hateoas.CollectionModel;

public interface IProgrammeDirectorHateoasAssembler {
    CollectionModel<TeacherIdDTO> toModel(String programmeAcronym, ProgrammeDirectorResponseDTO responseDTO);
}
