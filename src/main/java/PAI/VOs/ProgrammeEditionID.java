package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeEditionID implements DomainId {

    private final UUID _programmeEditionID;

    ProgrammeEditionID() {
        _programmeEditionID = UUID.randomUUID();
    }

    public UUID getProgrammeEditionID() {
        return _programmeEditionID;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }
}
