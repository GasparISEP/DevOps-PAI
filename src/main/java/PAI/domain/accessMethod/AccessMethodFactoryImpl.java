package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;

public class AccessMethodFactoryImpl implements IAccessMethodFactory {

    public AccessMethod createAccessMethod (NameWithNumbersAndSpecialChars accessMethodName){
        AccessMethodID accessMethodId = new AccessMethodID();
        return new AccessMethod(accessMethodId, accessMethodName);
    }
}
