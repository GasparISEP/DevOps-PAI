package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class AccessMethodId implements DomainId {
    private final UUID _accessMethodId;

    public AccessMethodId () {
        this._accessMethodId = UUID.randomUUID();
    }


}
