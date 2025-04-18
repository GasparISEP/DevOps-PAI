package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;

public class Acronym implements ValueObject {

    private final String _acronym;

    public Acronym(String acronym) {
        isAcronymInvalid(acronym);
        this._acronym = acronym;
    }

    private void isAcronymInvalid(String acronym) {
        if (acronym == null) {
            throw new IllegalArgumentException("Acronym must not be null");
        }
        if (acronym.isBlank()) {
            throw new IllegalArgumentException("Acronym must not be blank");
        }
        if (!acronym.matches("^[A-Z]+[0-9]*$")) {
            throw new IllegalArgumentException("Acronym must contain only uppercase letters, followed by optional digits");
        }
    }

    public String getAcronym() {
        return _acronym;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof Acronym other)) return false;
        return _acronym.equals(other._acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_acronym);
    }
}
