package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class TeacherCareerProgressionID implements DomainId {

    private final UUID _ID;

    public TeacherCareerProgressionID() {

        _ID = UUID.randomUUID();
    }

    public TeacherCareerProgressionID(UUID id) {
        _ID = Objects.requireNonNull(id, "Id can not be null!");
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;

        if(!(obj instanceof TeacherCareerProgressionID)) return false;

        TeacherCareerProgressionID other = (TeacherCareerProgressionID) obj;
        return _ID.equals(other._ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_ID);
    }

    public UUID getIDValue() {
        return this._ID;
    }
}
