package PAI.persistence.datamodel;

import PAI.VOs.CourseID;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CourseIDDataModel implements Serializable {
    private String _id;


    public CourseIDDataModel() {
    }

    public CourseIDDataModel(CourseID courseID) {
        _id = courseID.toString();
    }

    public String getId() {
        return _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseIDDataModel)) return false;
        CourseIDDataModel that = (CourseIDDataModel) o;
        return _id.equals(that._id);
    }



}
