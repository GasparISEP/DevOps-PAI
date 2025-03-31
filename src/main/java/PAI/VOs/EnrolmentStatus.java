package PAI.VOs;

import PAI.ddd.DomainId;

public class EnrolmentStatus implements DomainId {

    private boolean _isActive;

    public EnrolmentStatus(boolean isActive){
        _isActive = isActive;
    }

    public boolean isEnrolmentActive(){
        return _isActive;
    }
}
