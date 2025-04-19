package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;

public class DepartmentAcronym implements ValueObject {

    private final String _acronym;

    public DepartmentAcronym(String acronym) {
        if (acronym == null || acronym.isBlank()) {
            throw new IllegalArgumentException("Acronym must be a non-empty string.");
        }
        if (acronym.length() < 3) {
            throw new IllegalArgumentException("Acronym must contain at least 3 characters.");
        }
        if (!acronym.matches("^[A-Z]+$")) {
            throw new IllegalArgumentException("Acronym must contain only capital letters.");
        }
        this._acronym = acronym;
    }

    public String getAcronym() {
        return _acronym;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DepartmentAcronym that = (DepartmentAcronym) obj;
        return Objects.equals(this._acronym, that._acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_acronym);
    }
}

