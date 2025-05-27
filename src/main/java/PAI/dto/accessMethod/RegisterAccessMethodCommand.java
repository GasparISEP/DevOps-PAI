package PAI.dto.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import static PAI.utils.ValidationUtils.validateNotNull;


public record RegisterAccessMethodCommand(

        NameWithNumbersAndSpecialChars name
) {
    public RegisterAccessMethodCommand {
        validateNotNull(name, "Name");
        }
    }


