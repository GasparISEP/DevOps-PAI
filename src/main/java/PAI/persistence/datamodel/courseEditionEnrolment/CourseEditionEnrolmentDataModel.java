package PAI.persistence.datamodel.courseEditionEnrolment;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (name = "Course_Edition_Enrolments")
public class CourseEditionEnrolmentDataModel {

    @Column(name = "uuid", nullable = false)
    private CourseEditionEnrolmentGeneratedIDDataModel generatedID;

    @EmbeddedId
    private CourseEditionEnrolmentIDDataModel id;

    @Column(name = "enrolment_date", nullable = false)
    private LocalDate enrolmentDate;

    @Column (name= "status", nullable = false)
    private boolean active;

    @Version
    private Long version;


    public CourseEditionEnrolmentDataModel() {
    }

    public CourseEditionEnrolmentDataModel(CourseEditionEnrolmentGeneratedIDDataModel generatedID, CourseEditionEnrolmentIDDataModel id, LocalDate enrolmentDate, boolean isActive) {
        if (generatedID == null || id == null || enrolmentDate == null) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }

        this.generatedID = generatedID;
        this.id = id;
        this.enrolmentDate = enrolmentDate;
        this.active = isActive;
    }

    public CourseEditionEnrolmentGeneratedIDDataModel findGeneratedID(){
        return generatedID;
    }

    public CourseEditionEnrolmentIDDataModel findId() {
        return id;
    }

    public LocalDate findEnrolmentDate() {
        return enrolmentDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEditionEnrolmentDataModel that = (CourseEditionEnrolmentDataModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
