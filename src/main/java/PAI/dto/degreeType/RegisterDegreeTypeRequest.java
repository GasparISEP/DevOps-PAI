package PAI.dto.degreeType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RegisterDegreeTypeRequest(
        @NotBlank(message = "Name is required.")
        String name,

        @Positive(message = "Max ECTS must be greater than 0.")
        int maxEcts
) {
}
