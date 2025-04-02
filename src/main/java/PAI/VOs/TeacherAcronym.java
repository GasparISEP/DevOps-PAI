package PAI.VOs;

import PAI.ddd.ValueObject;

public class TeacherAcronym implements ValueObject {

    private final String _teacherAcronym;

    public TeacherAcronym(String acronym) {
        if (acronym == null || acronym.isBlank()) {
            throw new IllegalArgumentException("Acronym must be a 3 capital letter non-empty String.");
        }
        if (!acronym.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Acronym must contain only three capital letters.");
        }
        _teacherAcronym = acronym;
    }

    public String getAcronym() {
        return _teacherAcronym;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherAcronym otherID)) return false;
        return _teacherAcronym.equals(otherID._teacherAcronym);
    }
}


