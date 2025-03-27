package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class ProgrammeID implements ValueObject {

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
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeID that = (ProgrammeID) o;
        return Objects.equals(_progID, that._progID);
    }
}
