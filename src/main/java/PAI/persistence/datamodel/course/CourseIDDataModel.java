package PAI.persistence.datamodel.course;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CourseIDDataModel implements Serializable {

    private String _acronym;
    private String _name;

    public CourseIDDataModel() {
    }

    public CourseIDDataModel(String acronym, String name) {
        _acronym = acronym;
        _name = name;
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

    public String getAcronym() {
        return _acronym;
    }

    public String getName() {
        return _name;
    }
}
