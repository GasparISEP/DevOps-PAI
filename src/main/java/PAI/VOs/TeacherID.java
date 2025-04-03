package PAI.VOs;

import PAI.ddd.DomainId;

public class TeacherID implements DomainId {

    private final TeacherAcronym _teacherAcronym;

    public TeacherID(TeacherAcronym teacherAcronym) {
        if (teacherAcronym == null) {
            throw new IllegalArgumentException("Teacher acronym can not be null");
        }
        this._teacherAcronym = teacherAcronym;
    }

    public TeacherAcronym getTeacherAcronym() {
        return _teacherAcronym;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherID otherID)) return false;
        return _teacherAcronym.equals(otherID._teacherAcronym);
    }
}
