package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;

public class TeacherAcronym implements ValueObject {

    private final String _teacherAcronymString;

    public TeacherAcronym(String acronym) {
        if (acronym == null || acronym.isBlank()) {
            throw new IllegalArgumentException("Acronym must be a 3 capital letter non-empty String.");
        }
        if (!acronym.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Acronym must contain only three capital letters.");
        }
        this._teacherAcronymString = acronym;
    }

    public String getAcronym() {
        return _teacherAcronymString;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherAcronym otherID)) return false;
        return _teacherAcronymString.equals(otherID._teacherAcronymString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_teacherAcronymString);
    }

    @Override
    public String toString () {
        return _teacherAcronymString;
    }
}


