package PAI.VOs;

import PAI.ddd.ValueObject;

public class EnrolmentStatus implements ValueObject {

    private boolean _isActive;

    public EnrolmentStatus(boolean isActive){
        _isActive = isActive;
    }

    public boolean isEnrolmentActive(){
        return _isActive;
    }
}
