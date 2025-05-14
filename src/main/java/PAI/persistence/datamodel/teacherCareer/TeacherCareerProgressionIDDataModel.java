package PAI.persistence.datamodel.teacherCareer;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TeacherCareerProgressionIDDataModel implements Serializable {

    private UUID ID;

    public TeacherCareerProgressionIDDataModel() {
    }

    public TeacherCareerProgressionIDDataModel(UUID ID) {
        this.ID = ID;
    }

    public UUID getIDValue() {
        return this.ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TeacherCareerProgressionIDDataModel)) return false;

        TeacherCareerProgressionIDDataModel other = (TeacherCareerProgressionIDDataModel) obj;

        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
