package PAI.dto.programmeEdition;

import jakarta.validation.constraints.NotBlank;

public record RequestServiceDto(
        @NotBlank(message = "Programme acronym cannot be Blank") String programmeAcronym,

        @NotBlank(message = "schoolYearID cannot be Blank") String schoolYearID
) {}


