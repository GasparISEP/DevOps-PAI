package PAI.dto.Programme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProgrammeIDResponseDTO(
        @NotNull(message = "Programme Acronym is required")
        @Size(min = 3, max = 3, message = "Acronym must have 3 characters")
        String acronym) {
}
