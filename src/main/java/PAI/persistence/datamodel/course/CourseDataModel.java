package PAI.persistence.datamodel.course;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "COURSE")
public class CourseDataModel
{
    @EmbeddedId
    private CourseIDDataModel _courseID;

    @Column(name = "course_name")
    private String _name;

    @Column(name = "course_acronym")
    private String _acronym;

    @Column(name = "version")
    @Version
    private Long _version;

    public CourseDataModel() {}

    public CourseDataModel(CourseIDDataModel courseIDDataModel, String name, String acronym) {

        _courseID = courseIDDataModel;
        _name = name;
        _acronym = acronym;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (objectToCompare== null || !(objectToCompare instanceof CourseDataModel))
            return false;
        CourseDataModel courseDataModel = (CourseDataModel) objectToCompare;
        return Objects.equals(_courseID, courseDataModel._courseID);
    }
    @Override
    public int hashCode() {
        return _courseID.hashCode();
    }

    public String getName() {return _name; }

    public String getAcronym() {return _acronym;}

    public CourseIDDataModel getCourseID() {return _courseID; }

    public Long getVersion() { return _version; }
}
