package PAI.VOs;

import PAI.ddd.DomainId;

public class TeacherID implements DomainId {

    private final TeacherAcronym _acronym;

    public TeacherID(TeacherAcronym acronym) {
        if (acronym == null) {
            throw new IllegalArgumentException("Teacher acronym can not be null");
        }
        this._acronym = acronym;
    }

    public TeacherAcronym getTeacherAcronym() {
        return _acronym;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherID otherID)) return false;
        return _acronym.equals(otherID._acronym);
    }
}
