package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class SchoolYearID implements DomainId {
    private final UUID _schoolYearID;

    public SchoolYearID() {
        this._schoolYearID = UUID.randomUUID();
    }

    public UUID getSchoolYearID() {
        return _schoolYearID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolYearID that = (SchoolYearID) o;
        return _schoolYearID.equals(that._schoolYearID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_schoolYearID);
    }

    @Override
    public String toString() {
        return _schoolYearID.toString();
    }
}
