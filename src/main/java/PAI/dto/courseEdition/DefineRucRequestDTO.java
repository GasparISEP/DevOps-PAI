package PAI.dto.courseEdition;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DefineRucRequestDTO(
    @NotBlank(message = "Teacher ID is required")
    String teacherID
) {}
