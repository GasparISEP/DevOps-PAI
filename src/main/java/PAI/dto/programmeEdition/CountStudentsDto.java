package PAI.dto.programmeEdition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CountStudentsDto(
        @NotBlank(message = "Programme name cannot be Blank") String programmeName,

        @NotBlank(message = "Programme acronym cannot be Blank") String programmeAcronym,

        @NotNull(message = "schoolYearID cannot be Blank") UUID schoolYearID
) {}


