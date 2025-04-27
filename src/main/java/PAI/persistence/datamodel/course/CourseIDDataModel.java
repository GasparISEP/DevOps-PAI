package PAI.persistence.datamodel.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseIDDataModel implements Serializable {

    @Column(name = "courseID_acronym")
    private String _courseIDAcronym;

    @Column(name = "courseID_name")
    private String _courseIDName;

    public CourseIDDataModel() {
    }

    public CourseIDDataModel(String acronym, String name) {
        _courseIDAcronym = acronym;
        _courseIDName = name;
    }

    public String getId() {
        return _courseIDAcronym + "-" + _courseIDName;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseIDDataModel)) return false;
        CourseIDDataModel courseIDDataModel = (CourseIDDataModel) objectToCompare;
        return _courseIDAcronym.equals(courseIDDataModel._courseIDAcronym) &&
                _courseIDName.equals(courseIDDataModel._courseIDName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseIDAcronym, _courseIDName);
    }

    public String getAcronym() {
        return _courseIDAcronym;
    }

    public String getName() {
        return _courseIDName;
    }
}
