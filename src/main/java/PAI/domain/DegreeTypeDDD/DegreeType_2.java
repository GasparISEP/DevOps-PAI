package PAI.domain.DegreeTypeDDD;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;

import java.util.Objects;

public class DegreeType_2 {

    private final DegreeTypeID _degreeTypeID;
    private final Name _name;
    private final MaxEcts _maxEcts;

    public DegreeType_2(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) {
        this._degreeTypeID = Objects.requireNonNull(degreeTypeID, "DegreeTypeID cannot be null");
        this._name = Objects.requireNonNull(name, "Name cannot be null");
        this._maxEcts = Objects.requireNonNull(maxEcts, "MaxEcts cannot be null");
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof DegreeType_2)) return false;
        DegreeType_2 degreeType_2 = (DegreeType_2) objectToCompare;
        return _degreeTypeID.sameAs(degreeType_2._degreeTypeID) &&
                _name.getName().equals(degreeType_2._name.getName()) &&
                _maxEcts.getMaxEcts() == degreeType_2._maxEcts.getMaxEcts();
    }

    @Override
    public int hashCode() {
        return 31 * _degreeTypeID.getDTID().hashCode() + _name.getName().hashCode() + _maxEcts.getMaxEcts();
    }

    public String getId() {
        return _degreeTypeID.getDTID();
    }

    public String getName() {
        return _name.getName();
    }

    public int getMaxEcts() {
        return _maxEcts.getMaxEcts();
    }
}
