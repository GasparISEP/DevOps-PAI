package PAI.persistence.datamodel.course;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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

    public CourseDataModel() {}

    public CourseDataModel(CourseIDDataModel courseIDDataModel, String name, String acronym) {

        _courseID = courseIDDataModel;
        _name = name;
        _acronym = acronym;
    }

    public String getName() {return _name; }

    public String getAcronym() {return _acronym;}

    public CourseIDDataModel getCourseID() {return _courseID; }
}
