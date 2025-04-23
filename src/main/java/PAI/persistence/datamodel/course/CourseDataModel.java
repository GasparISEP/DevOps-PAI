package PAI.persistence.datamodel.course;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSE")
public class CourseDataModel
{
    @EmbeddedId
    private CourseIDDataModel _courseID;

    private String _name;
    private String _acronym;

    public CourseDataModel() {}

    public CourseDataModel(CourseIDDataModel courseIDDataModel, String name, String acronym) {

        _courseID = courseIDDataModel;
        _name = name;
        _acronym = acronym;
    }

    public String get_name() {return _name; }

    public String get_acronym() {return _acronym;}


}
