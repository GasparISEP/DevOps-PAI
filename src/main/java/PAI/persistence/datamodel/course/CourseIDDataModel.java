package PAI.persistence.datamodel.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseIDDataModel implements Serializable {

    @Column(name = "course_acronym")
    private String _courseAcronym;

    @Column(name = "course_name")
    private String _courseName;

    public CourseIDDataModel() {
    }

    public CourseIDDataModel(String acronym, String name) {
        _courseAcronym = acronym;
        _courseName = name;
    }

    public String getId() {
        return _courseAcronym + "-" + _courseName;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseIDDataModel)) return false;
        CourseIDDataModel courseIDDataModel = (CourseIDDataModel) objectToCompare;
        return _courseAcronym.equals(courseIDDataModel._courseAcronym) &&
                _courseName.equals(courseIDDataModel._courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseAcronym, _courseName);
    }

    public String getAcronym() {
        return _courseAcronym;
    }

    public String getName() {
        return _courseName;
    }
}
