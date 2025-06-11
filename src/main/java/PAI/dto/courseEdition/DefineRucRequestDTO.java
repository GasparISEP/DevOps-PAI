package PAI.dto.courseEdition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DefineRucRequestDTO(

    @NotBlank(message = "Teacher ID is required")
    String teacherID,

    @NotNull(message = "Course edition is required")
    @Valid
    UUID courseEditionID
) {}
