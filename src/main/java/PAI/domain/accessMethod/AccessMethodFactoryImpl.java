package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;

public class AccessMethodFactoryImpl implements IAccessMethodFactory {

    public AccessMethod createAccessMethod (AccessMethodID accessMethodID, NameWithNumbersAndSpecialChars accessMethodName) {
        return new AccessMethod(accessMethodID, accessMethodName);
    }

    public AccessMethod createAccessMethod (NameWithNumbersAndSpecialChars accessMethodName){
        return new AccessMethod(accessMethodName);
    }
}
