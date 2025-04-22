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
    private double _quantityCreditsEcts;
    private int _duration;

    public CourseDataModel() {}

    public CourseDataModel(CourseIDDataModel courseIDDataModel, String name, String acronym, double quantityCreditsEcts, int duration) {

        _courseID = courseIDDataModel;
        _name = name;
        _acronym = acronym;
        _quantityCreditsEcts = quantityCreditsEcts;
        _duration = duration;
    }

    public String get_name() {return _name; }

    public String get_acronym() {return _acronym;}

    public double get_quantityCreditsEcts() {return _quantityCreditsEcts;}

    public int get_duration() {return _duration; }

}
