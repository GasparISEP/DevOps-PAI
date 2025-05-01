package PAI.persistence.datamodel.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseIDDataModel implements Serializable {

    @Column(name = "courseID_acronym")
    private String courseIDAcronym;

    @Column(name = "courseID_name")
    private String courseIDName;

    public CourseIDDataModel() {
    }

    public CourseIDDataModel(String acronym, String name) {
        courseIDAcronym = acronym;
        courseIDName = name;
    }

    public String getId() {
        return courseIDAcronym + "-" + courseIDName;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof CourseIDDataModel)) return false;
        CourseIDDataModel courseIDDataModel = (CourseIDDataModel) objectToCompare;
        return courseIDAcronym.equals(courseIDDataModel.courseIDAcronym) &&
                courseIDName.equals(courseIDDataModel.courseIDName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseIDAcronym, courseIDName);
    }

    public String getAcronym() {
        return courseIDAcronym;
    }

    public String getName() {
        return courseIDName;
    }
}
