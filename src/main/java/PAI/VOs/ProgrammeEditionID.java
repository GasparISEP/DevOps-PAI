package PAI.VOs;

import PAI.ddd.DomainId;

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
        if (this == o)
            return true;
        if (o == null || (!(o instanceof UUID)))
            return false;
        if (_programmeEditionID.equals(o))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return _programmeEditionID.toString();
    }
}
