package PAI.VOs;

public class Acronym {

    private final String _acronym;

    public Acronym(String acronym) {
        if (isAcronymInvalid(acronym)) {
            throw new IllegalArgumentException("Acronym must not be empty");
        }
        this._acronym = acronym;
    }

    private boolean isAcronymInvalid(String acronym) {
        return acronym == null || acronym.isBlank() || !acronym.matches("^[A-Z]+[0-9]*$");
    }
}
