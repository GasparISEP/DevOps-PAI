package PAI.VOs;

import PAI.ddd.ValueObject;

public class DegreeTypeID implements ValueObject {

    private final String _dtID;

    public DegreeTypeID(String dtID) throws Exception {
        if (!isDegreeTypeIDValid(dtID)) throw new  Exception("Id cannot be null or empty");
        this._dtID = dtID;
    }

    private boolean isDegreeTypeIDValid(String id) {
        if (id == null || id.isBlank()) return false;
        return true;
    }

    public String getDTID() {
        return _dtID;
    }

    public boolean sameAs(DegreeTypeID other) {
        if (other == null) return false;
        return this._dtID.equals(other._dtID);
    }
}