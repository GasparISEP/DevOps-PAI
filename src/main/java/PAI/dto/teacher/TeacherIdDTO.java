package PAI.dto.teacher;

import jakarta.validation.constraints.NotNull;

public record TeacherIdDTO(
        @NotNull
        String id
) {}
