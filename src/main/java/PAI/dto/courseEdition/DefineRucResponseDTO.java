package PAI.dto.courseEdition;

import java.util.UUID;

public record DefineRucResponseDTO(
        String teacherID,
        UUID courseEditionGeneratedID) {}
