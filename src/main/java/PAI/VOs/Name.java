package PAI.VOs;

import PAI.ddd.ValueObject;

public class Name implements ValueObject {
    private final String _name;

    public Name(String name) {
        if (!isNameValid(name)) {
            throw new IllegalArgumentException("Name does not meet the validation requirements.");
        }
        this._name = name.trim();
    }

    private boolean isNameValid(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        // Trim to ensure there are no extra spaces
        name = name.trim();

        // Check the length, first letter uppercase, and valid characters
        return name.length() >= 3 && name.length() <= 100 && // Length must be between 3 and 100 characters
                Character.isUpperCase(name.charAt(0)) && // The first character must be uppercase
                name.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ' -]+$"); // Allow letters, accents, spaces, hyphens, and apostrophes
    }

    public String getName() {
        return _name;
    }
}

