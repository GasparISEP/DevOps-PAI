package PAI.dto.Programme;


import jakarta.validation.constraints.NotBlank;

public record ProgrammeDirectorVOsDTO(
        @NotBlank(message = "Teacher ID is required")
        String teacherID
) {}
