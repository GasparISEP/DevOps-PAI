package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;

public interface IAccessMethodDDDFactory {
    AccessMethodDDD createAccessMethod (AccessMethodID accessMethodID, NameWithNumbersAndSpecialChars accessMethodName);

    AccessMethodDDD createAccessMethod (NameWithNumbersAndSpecialChars accessMethodName);
}
