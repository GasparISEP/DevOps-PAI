package PAI.VOs;

public class EnrolmentStatus {

    private boolean _isActive;

    public EnrolmentStatus(boolean isActive){
        _isActive = isActive;
    }

    public boolean isEnrolmentActive(){
        return _isActive;
    }
}
