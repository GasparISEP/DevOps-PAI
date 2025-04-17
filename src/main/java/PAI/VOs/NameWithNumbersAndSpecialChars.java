package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;

public class NameWithNumbersAndSpecialChars implements ValueObject {
    private final String _nameWithNumbersAndSpecialChars;

    public NameWithNumbersAndSpecialChars(String nameWithNumbersAndSpecialChars) {
     if (isNameInvalid(nameWithNumbersAndSpecialChars)){
         throw new IllegalArgumentException("Name cannot be null or empty");
     }
        _nameWithNumbersAndSpecialChars = nameWithNumbersAndSpecialChars;
    }

    public boolean isNameInvalid (String name){
       return name==null || name.isBlank() || name.isEmpty();
    }

    public boolean equalsIgnoreCase(Object o) {
        if (this == o) return true;
        if (!(o instanceof NameWithNumbersAndSpecialChars that)) return false;
        return _nameWithNumbersAndSpecialChars != null
                && _nameWithNumbersAndSpecialChars.equalsIgnoreCase(that._nameWithNumbersAndSpecialChars);
    }

    public String getnameWithNumbersAndSpecialChars() {
        return _nameWithNumbersAndSpecialChars;
    }
}
