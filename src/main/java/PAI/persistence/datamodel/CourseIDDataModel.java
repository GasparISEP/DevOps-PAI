package PAI.persistence.datamodel;

import PAI.VOs.CourseID;
import jakarta.persistence.Embeddable;

@Embeddable
public class CourseIDDataModel {
    private String _id;


    public CourseIDDataModel() {
    }

    public CourseIDDataModel(CourseID courseID) {
        _id = courseID.toString();
    }

    public String getId() {
        return _id;
    }

}
