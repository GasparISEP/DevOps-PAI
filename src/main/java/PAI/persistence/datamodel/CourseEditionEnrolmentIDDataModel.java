package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseEditionEnrolmentIDDataModel implements Serializable {

    @Embedded
    private StudentIDDataModel studentID;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeAcronym",
                    column = @Column(name = "cee_programme_acronym")
            ),
            @AttributeOverride(
                    name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeName",
                    column = @Column(name = "cee_programme_name")
            )
    })
    private CourseEditionIDDataModel courseEditionID;


    public CourseEditionEnrolmentIDDataModel() {}

    public CourseEditionEnrolmentIDDataModel (StudentIDDataModel studentID, CourseEditionIDDataModel courseEditionID) {

        validateStudentID (studentID);
        validateCourseEditionID (courseEditionID);

        this.courseEditionID = courseEditionID;
    }

    private void validateStudentID (StudentIDDataModel studentID) {
        if (studentID == null) {
            throw new IllegalArgumentException("Student ID cannot be null!");
        }
        this.studentID = studentID;
    }

    private void validateCourseEditionID (CourseEditionIDDataModel courseEditionID) {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("Course Edition ID cannot be null!");
        }
    }

    public StudentIDDataModel findStudentID() {
        return studentID;
    }

    public CourseEditionIDDataModel findCourseEditionID() {
        return courseEditionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEditionEnrolmentIDDataModel that = (CourseEditionEnrolmentIDDataModel) o;
        return studentID == that.studentID && Objects.equals(courseEditionID, that.courseEditionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, courseEditionID);
    }
}
