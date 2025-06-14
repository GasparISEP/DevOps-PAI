package PAI.dto.programmeEdition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionWithNameAndDescriptionResponseDTO(
        @NotBlank(message = "Programme acronym cannot be Blank")
        String programmeAcronym,

        @NotNull(message = "School year ID cannot be Blank")
        String schoolYearId,

        String programmeName,
        String description

) {}
