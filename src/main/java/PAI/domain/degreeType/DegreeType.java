package PAI.domain.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.ddd.AggregateRoot;

import java.util.Objects;

public class DegreeType implements AggregateRoot<DegreeTypeID> {

    private final DegreeTypeID _degreeTypeID;
    private final Name _name;
    private final MaxEcts _maxEcts;

    public DegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) {
        this._degreeTypeID = Objects.requireNonNull(degreeTypeID, "DegreeTypeID cannot be null");
        this._name = Objects.requireNonNull(name, "Name cannot be null");
        this._maxEcts = Objects.requireNonNull(maxEcts, "MaxEcts cannot be null");
    }


    public static DegreeType create(Name name, MaxEcts maxEcts) {
        return new DegreeType(new DegreeTypeID(), name, maxEcts);
    }

    @Override
    public DegreeTypeID identity() {
        return this._degreeTypeID;
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

    @Override
    public boolean sameAs(Object object) {
        if (object instanceof DegreeType degreeType) {
            return this._degreeTypeID.equals(degreeType._degreeTypeID);
        }
        return false;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) return false;
        DegreeType degreeType = (DegreeType) objectToCompare;
        return this._degreeTypeID.equals(degreeType._degreeTypeID);
    }

    @Override
    public int hashCode() {
        return 31 * _degreeTypeID.hashCode() + _name.hashCode() + _maxEcts.hashCode();
    }
}