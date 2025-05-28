package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDRequestDTO;
import PAI.dto.schoolYear.SchoolYearIDRequestDTO;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionRequestDTO(
        @NotNull(message = "Programme is required")
        ProgrammeIDRequestDTO programme,

        @NotNull(message = "School Year is required")
        SchoolYearIDRequestDTO schoolYear)  {
}
