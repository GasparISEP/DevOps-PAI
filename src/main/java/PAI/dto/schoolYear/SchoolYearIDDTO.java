package PAI.dto.schoolYear;

import jakarta.validation.constraints.NotBlank;


public record SchoolYearIDDTO(
        @NotBlank(message = "ID is required")
        String id) {
}
