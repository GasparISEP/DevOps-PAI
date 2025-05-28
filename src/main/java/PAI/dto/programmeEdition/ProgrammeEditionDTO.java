package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.schoolYear.SchoolYearIDDTO;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionDTO(
        @NotNull(message = "Programme is required")
        ProgrammeIDDTO programme,

        @NotNull(message = "School Year is required")
        SchoolYearIDDTO schoolYear) {
}
