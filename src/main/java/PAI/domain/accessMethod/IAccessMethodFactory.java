package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;

import java.util.UUID;

public interface IAccessMethodFactory {
    AccessMethod createAccessMethod (NameWithNumbersAndSpecialChars accessMethodName);
    AccessMethod createAccessMethod (AccessMethodID accessMethodID, NameWithNumbersAndSpecialChars accessMethodName);
}
