package PAI.persistence.datamodel.DegreeType;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class DegreeTypeIDDataModel {

    private String degreeTypeID;

    protected DegreeTypeIDDataModel() {}

    public DegreeTypeIDDataModel(String degreeTypeID) {
        this.degreeTypeID = degreeTypeID;
    }

    public String getDegreeTypeID() {
        return degreeTypeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DegreeTypeIDDataModel)) return false;
        DegreeTypeIDDataModel that = (DegreeTypeIDDataModel) o;
        return Objects.equals(degreeTypeID, that.degreeTypeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degreeTypeID);
    }
}