package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDDTO;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionResponseServiceDTO(
        @NotNull(message = "Programme is required")
        ProgrammeIDDTO programme,
        @NotNull(message = "School Year ID is required")
        String schoolYearId) {}