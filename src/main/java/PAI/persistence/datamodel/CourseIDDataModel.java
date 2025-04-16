package PAI.persistence.datamodel;

import PAI.VOs.CourseID;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CourseIDDataModel implements Serializable {

    private String _acronym;
    private String _name;

    public CourseIDDataModel() {
    }

    public CourseIDDataModel(CourseID courseID) {
        _acronym = courseID.getAcronym().toString();
        _name = courseID.getName().toString();
    }

    public String getId() {
        return _acronym + "-" + _name;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseIDDataModel)) return false;
        CourseIDDataModel courseIDDataModel = (CourseIDDataModel) objectToCompare;
        return _acronym.equals(courseIDDataModel._acronym) ||
                _name.equals(courseIDDataModel._name);
    }

    @Override
    public int hashCode() {
        return _acronym.hashCode() + _name.hashCode();
    }
}
