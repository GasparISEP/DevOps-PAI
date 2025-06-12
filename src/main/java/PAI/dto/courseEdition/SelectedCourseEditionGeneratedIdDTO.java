package PAI.dto.courseEdition;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.UUID;

public record SelectedCourseEditionGeneratedIdDTO(
        @NotNull(message = "courseEditionID cannot be null.")
        UUID courseEditionID
)
{}
