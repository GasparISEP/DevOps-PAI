package PAI.persistence.datamodel;

import PAI.VOs.CourseID;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CourseIDDataModel implements Serializable {

    private String _courseDomainID;

    public CourseIDDataModel() {
    }

    public CourseIDDataModel(CourseID courseID) {
        _courseDomainID = courseID.toString();
    }

    public String getId() {
        return _courseDomainID;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseIDDataModel)) return false;
        CourseIDDataModel courseIDDataModel = (CourseIDDataModel) objectToCompare;
        return _courseDomainID.equals(courseIDDataModel._courseDomainID);
    }

    @Override
    public int hashCode() {
        return _courseDomainID.hashCode();
    }
}
