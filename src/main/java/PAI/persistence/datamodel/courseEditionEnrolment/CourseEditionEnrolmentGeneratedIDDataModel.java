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
    private UUID generatedID;

    public CourseEditionEnrolmentGeneratedIDDataModel() {
    }

    public CourseEditionEnrolmentGeneratedIDDataModel(UUID id) {
        this.generatedID = id;
    }

    // Getters
    public UUID getGeneratedID() {
        return generatedID;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CourseEditionEnrolmentGeneratedIDDataModel another = (CourseEditionEnrolmentGeneratedIDDataModel) o;
        return generatedID.equals(another.generatedID);
    }

    //HashCode
    @Override
    public int hashCode() {
        return Objects.hash(generatedID);
    }
}