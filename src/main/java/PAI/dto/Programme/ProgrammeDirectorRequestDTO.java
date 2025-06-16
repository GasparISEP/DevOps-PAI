package PAI.dto.Programme;

import PAI.dto.teacher.TeacherIdDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ProgrammeDirectorRequestDTO(
        @NotNull @Valid TeacherIdDTO teacher) {
}
