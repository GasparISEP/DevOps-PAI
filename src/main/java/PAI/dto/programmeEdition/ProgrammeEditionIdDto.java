package PAI.dto.programmeEdition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionIdDto(
    @NotBlank(message = "Programme acronym cannot be Blank")
    String programmeAcronym,

    @NotNull(message = "School year ID cannot be Blank")
    String schoolYearId
) {}
