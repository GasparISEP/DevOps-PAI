package PAI.persistence.datamodel.course;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "COURSE")
public class CourseDataModel
{
    @EmbeddedId
    private CourseIDDataModel courseID;

    @Column(name = "course_generated_id")
    private UUID courseGeneratedID;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_acronym")
    private String acronym;

    @Column(name = "version")
    @Version
    private Long version;

    public CourseDataModel() {}

    public CourseDataModel(CourseIDDataModel courseIDDataModel, UUID courseGeneratedID, String name, String acronym) {

        courseID = courseIDDataModel;
        this.courseGeneratedID = courseGeneratedID;
        this.name = name;
        this.acronym = acronym;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (objectToCompare== null || !(objectToCompare instanceof CourseDataModel))
            return false;
        CourseDataModel courseDataModel = (CourseDataModel) objectToCompare;
        return Objects.equals(courseID, courseDataModel.courseID);
    }
    @Override
    public int hashCode() {
        return courseID.hashCode();
    }

    public String getName() {return name; }

    public String getAcronym() {return acronym;}

    public CourseIDDataModel getCourseID() {return courseID; }

    public UUID getCourseGeneratedID() {return courseGeneratedID; }

    public Long getVersion() { return version; }
}
