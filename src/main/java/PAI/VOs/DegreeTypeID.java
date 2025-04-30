package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class DegreeTypeID implements DomainId {

    private final String _dtID;

    public DegreeTypeID() {
        this._dtID = UUID.randomUUID().toString();
    }

    public DegreeTypeID(String dtID) {
        if (dtID == null || dtID.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or blank.");
        }
        this._dtID = dtID;
    }

    public String getDTID() {
        return _dtID;
    }

    public boolean sameAs(DegreeTypeID other) {
        if (other == null) return false;
        return this._dtID.equals(other._dtID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DegreeTypeID that)) return false;
        return _dtID.equals(that._dtID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_dtID);
    }

    @Override
    public String toString() {
        return "DegreeTypeID{" + "_dtID='" + _dtID + '\'' + '}';
    }
}