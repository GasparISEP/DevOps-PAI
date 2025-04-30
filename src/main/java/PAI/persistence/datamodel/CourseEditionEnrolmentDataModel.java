package PAI.persistence.datamodel;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (name = "course_Edition_Enrolments")
public class CourseEditionEnrolmentDataModel {

    @EmbeddedId
    private CourseEditionEnrolmentIDDataModel id;

    @Column(name = "enrolment_date", nullable = false)
    private LocalDate _enrolmentDate;

    @Column (name= "status", nullable = false)
    private boolean isActive;

    @Version
    private Long version;


    public CourseEditionEnrolmentDataModel() {
    }

    public CourseEditionEnrolmentDataModel(CourseEditionEnrolmentIDDataModel id, LocalDate enrolmentDate, boolean isActive) {
        this.id = id;
        this._enrolmentDate = enrolmentDate;
        this.isActive = isActive;
    }

    public CourseEditionEnrolmentIDDataModel findId() {
        return id;
    }

    public LocalDate findEnrolmentDate() {
        return _enrolmentDate;
    }

    public boolean isActive() {
        return isActive;
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
