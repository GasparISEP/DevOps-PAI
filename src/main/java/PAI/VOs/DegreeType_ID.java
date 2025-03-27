package PAI.VOs;

import PAI.ddd.ValueObject;
import java.util.Objects;
import java.util.UUID;

public class DegreeType_ID implements ValueObject {

    private final String _dtID;

    public DegreeType_ID() {
        _dtID = UUID.randomUUID().toString();
    }

    public DegreeType_ID(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID must not be blank or null.");
        }
        _dtID = id;
    }

    public String getDTID() {
        return _dtID;
    }

    public boolean sameAs(DegreeType_ID other) {
        if (other == null) return false;
        return this._dtID.equals(other._dtID);
    }
}