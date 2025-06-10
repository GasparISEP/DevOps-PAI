package PAI.dto.programmeEdition;

import PAI.dto.Programme.ProgrammeIDDTO;
import jakarta.validation.constraints.NotNull;

public record ProgrammeEditionRequestServiceDTO (
        @NotNull(message = "Programme is required")
        ProgrammeIDDTO programme) {}
