package PAI.dto.schoolYear;

import jakarta.validation.constraints.NotBlank;


public record SchoolYearIDResponseDTO(
        @NotBlank(message = "ID is required")
        String id) {
}
