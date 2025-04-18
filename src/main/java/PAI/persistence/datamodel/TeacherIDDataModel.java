package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TeacherIDDataModel {

    private String _teacherAcronym;

    public TeacherIDDataModel() {}

    public TeacherIDDataModel(String teacherAcronym) {
        _teacherAcronym = teacherAcronym;
    }

    public String getTeacherAcronym() { return _teacherAcronym; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherIDDataModel otherID)) return false;
        return _teacherAcronym.equals(otherID._teacherAcronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_teacherAcronym);
    }
}

