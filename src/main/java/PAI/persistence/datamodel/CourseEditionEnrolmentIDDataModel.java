package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseEditionEnrolmentIDDataModel implements Serializable {

    @Column(nullable = false)
    private String studentID;

    @Embedded
    private CourseEditionIDDataModel courseEditionID;


    public CourseEditionEnrolmentIDDataModel() {}

    public CourseEditionEnrolmentIDDataModel (String studentID, CourseEditionIDDataModel courseEditionID) {

        validateStudentID (studentID);
        validateCourseEditionID (courseEditionID);

        this.courseEditionID = courseEditionID;
    }

    private void validateStudentID (String studentID) {
        if (studentID == null || studentID.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty!");
        }
        this.studentID = studentID;
    }

    private void validateCourseEditionID (CourseEditionIDDataModel courseEditionID) {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("Course Edition ID cannot be null!");
        }
    }

    public String findStudentID() {
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
