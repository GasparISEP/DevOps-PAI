package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import org.springframework.stereotype.Component;

@Component
public class AccessMethodFactoryImpl implements IAccessMethodFactory {

    public AccessMethod createAccessMethod (NameWithNumbersAndSpecialChars accessMethodName){
        AccessMethodID accessMethodId = new AccessMethodID();
        return new AccessMethod(accessMethodId, accessMethodName);
    }

    @Override
    public AccessMethod createAccessMethod(AccessMethodID accessMethodID, NameWithNumbersAndSpecialChars accessMethodName) {
        if(accessMethodID == null){
            throw new IllegalArgumentException("AccessMethodID cannot be null");
        }
        if(accessMethodName == null){
            throw new IllegalArgumentException("AccessMethodName cannot be null");
        }
        return new AccessMethod(accessMethodID, accessMethodName);
    }
}
