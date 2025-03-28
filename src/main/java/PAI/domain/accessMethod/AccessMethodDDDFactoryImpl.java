package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;

public class AccessMethodDDDFactoryImpl implements IAccessMethodDDDFactory{

    public AccessMethodDDD createAccessMethod (AccessMethodID accessMethodID, NameWithNumbersAndSpecialChars accessMethodName) {
        return new AccessMethodDDD(accessMethodID, accessMethodName);
    }
}
