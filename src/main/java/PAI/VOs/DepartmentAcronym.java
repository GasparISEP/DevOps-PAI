package PAI.VOs;

import PAI.ddd.ValueObject;

public class DepartmentAcronym implements ValueObject {

    private final String _acronym;

    public DepartmentAcronym(String acronym) throws Exception {
        if (acronym == null || acronym.isBlank()) {
            throw new Exception("Acronym must be a non-empty string.");
        }
        if (acronym.length() < 3) {
            throw new Exception("Acronym must contain at least 3 characters.");
        }
        if (!acronym.matches("^[A-Z]+$")) {
            throw new Exception("Acronym must contain only capital letters.");
        }
        _acronym = acronym;
    }

    public String getAcronym() {
        return _acronym;
    }
}

