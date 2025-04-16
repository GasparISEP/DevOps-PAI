package PAI.domain.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.ddd.AggregateRoot;

public class AccessMethod implements AggregateRoot<AccessMethodID> {

    private final AccessMethodID _accessMethodId;
    private final NameWithNumbersAndSpecialChars _accessMethodName;

    protected AccessMethod(AccessMethodID accessMethodID, NameWithNumbersAndSpecialChars accessMethodName){
        if(accessMethodID == null) throw new IllegalArgumentException("Access Method ID cannot be null");

        this._accessMethodId = accessMethodID;

        if(accessMethodName == null) throw new IllegalArgumentException("Access Method name cannot be null");

        this._accessMethodName = accessMethodName;
    }

    @Override
    public AccessMethodID identity() {
        return this._accessMethodId;
    }

    public NameWithNumbersAndSpecialChars getAccessMethodName(){
        return this._accessMethodName;
    }

    @Override
    public boolean equals (Object object){

        if(this == object) return true;

        if(object instanceof AccessMethod oAccessMethod){
            return this._accessMethodId.equals(oAccessMethod._accessMethodId);
        }

        return false;

    }

    @Override
    public boolean sameAs (Object object) {

        if (object instanceof AccessMethod oAccessMethod){
            return this._accessMethodName.equals(oAccessMethod._accessMethodName);
        }

        return false;
    }

    public boolean hasThisAccessMethodName (NameWithNumbersAndSpecialChars accessMethodName) {
        return _accessMethodName.equals(accessMethodName);
    }
}
