package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TeacherIDDataModel {

    @Column (name = "TeacherAcronym") // Does not need "nullable = false" because @EmbeddedId implies it cannot be null
    private String teacherAcronym;

    public TeacherIDDataModel() {}

    public TeacherIDDataModel(String teacherAcronym) {
        this.teacherAcronym = teacherAcronym;
    }

    public String getTeacherAcronym() { return teacherAcronym; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherIDDataModel otherID)) return false;
        return teacherAcronym.equals(otherID.teacherAcronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherAcronym);
    }
}

