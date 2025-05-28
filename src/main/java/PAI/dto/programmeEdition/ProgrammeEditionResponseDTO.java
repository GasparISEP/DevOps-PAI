package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDResponseDTO;
import PAI.dto.schoolYear.SchoolYearIDResponseDTO;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionResponseDTO(
        @NotNull(message = "Programme is required")
        ProgrammeIDResponseDTO programme,

        @NotNull(message = "School Year is required")
        SchoolYearIDResponseDTO schoolYear) {
}
