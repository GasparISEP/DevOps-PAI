package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeID implements DomainId {

    private final String _progID;

    public ProgrammeID() {
        _progID = UUID.randomUUID().toString();
    }

    public String getProgID() {
        return _progID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammeID that)) return false;
        return Objects.equals(_progID, that._progID);
    }
}
