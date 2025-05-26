package PAI.dto.schoolYear;

import jakarta.validation.constraints.NotBlank;


public record SchoolYearIDRequestDTO(
        @NotBlank(message = "ID is required")
        String id) {
}
