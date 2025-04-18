package PAI.persistence.datamodel;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "courseEditionEnrolments")
public class CourseEditionEnrolmentDataModel {

    @EmbeddedId
    private CourseEditionEnrolmentIDDataModel id;

    @Column(nullable = false)
    private String enrolmentDate;

    @Column (name= "status", nullable = false)
    private boolean isActive;

    @Version
    private Long version;


    public CourseEditionEnrolmentDataModel() {
    }

    public CourseEditionEnrolmentDataModel(CourseEditionEnrolmentIDDataModel id, String enrolmentDate, boolean isActive) {
        this.id = id;
        this.enrolmentDate = enrolmentDate;
        this.isActive = isActive;
    }

    public CourseEditionEnrolmentIDDataModel findId() {
        return id;
    }

    public String findEnrolmentDate() {
        return enrolmentDate;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEditionEnrolmentDataModel that = (CourseEditionEnrolmentDataModel) o;
        return isActive == that.isActive && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
