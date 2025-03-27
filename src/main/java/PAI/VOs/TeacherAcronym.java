package PAI.VOs;

public class TeacherAcronym {

    private final String _acronym;

    public TeacherAcronym(String acronym) throws Exception {
        if (acronym == null || acronym.isBlank()) {
            throw new Exception("Acronym must be a 3 capital letter non-empty String.");
        }
        if (!acronym.matches("^[A-Z]{3}$")) {
            throw new Exception("Acronym must contain only three capital letters.");
        }
        _acronym = acronym;
    }

    public String getAcronym() {
        return _acronym;
    }
}


