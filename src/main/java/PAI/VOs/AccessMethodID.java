package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class AccessMethodID implements DomainId {
    private final UUID _accessMethodId;

    public AccessMethodID() {
        this._accessMethodId = UUID.randomUUID();
    }

    @Override
    public boolean equals (Object objectToCompare){
        if (this == objectToCompare) return true;

        if(objectToCompare instanceof AccessMethodID that){
            return this._accessMethodId.equals(that._accessMethodId);
        }

        return false;
    }


}
