package PAI.dto.degreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;

public record RegisterDegreeTypeCommand(
        Name name,
        MaxEcts maxEcts
) {
    public RegisterDegreeTypeCommand {
        if (name == null) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (maxEcts == null) {
            throw new IllegalArgumentException("Max ECTS is required.");
        }
    }
}

