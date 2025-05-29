package PAI.dto.programmeEdition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CountStudentsDto(
        @NotBlank(message = "Programme name cannot be Blank") String programmeName,

        @NotBlank(message = "Programme acronym cannot be Blank") String programmeAcronym,

        @NotBlank(message = "schoolYearID cannot be Blank") String schoolYearID
) {}


