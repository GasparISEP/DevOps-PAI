package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;

public interface IAccessMethodFactory {

    AccessMethod createAccessMethod (NameWithNumbersAndSpecialChars accessMethodName);
}
