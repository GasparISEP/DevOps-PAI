package PAI.VOs;

import PAI.ddd.ValueObject;

public class NameWithNumbersAndSpecialChars implements ValueObject {
    private final String _nameWithNumbersAndSpecialChars;

    public NameWithNumbersAndSpecialChars(String nameWithNumbersAndSpecialChars) {
     _nameWithNumbersAndSpecialChars = nameWithNumbersAndSpecialChars;
    }
}
