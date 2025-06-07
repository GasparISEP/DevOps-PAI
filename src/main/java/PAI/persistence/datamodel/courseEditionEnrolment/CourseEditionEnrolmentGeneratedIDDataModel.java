package PAI.persistence.datamodel.courseEditionEnrolment;

import PAI.domain.schoolYear.SchoolYear;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CourseEditionEnrolmentGeneratedIDDataModel implements Serializable {

    @Column(name = "cee_generated_id")
    private UUID _generatedID;

    public CourseEditionEnrolmentGeneratedIDDataModel() {
    }

    public CourseEditionEnrolmentGeneratedIDDataModel(UUID id) {
        this._generatedID = id;
    }

    // Getters
    public UUID getGeneratedID() {
        return _generatedID;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CourseEditionEnrolmentGeneratedIDDataModel another = (CourseEditionEnrolmentGeneratedIDDataModel) o;
        return _generatedID.equals(another._generatedID);
    }

    //HashCode
    @Override
    public int hashCode() {
        return Objects.hash(_generatedID);
    }
}