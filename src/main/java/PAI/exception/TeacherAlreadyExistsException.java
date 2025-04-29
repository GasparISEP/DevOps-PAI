package PAI.exception;

import PAI.VOs.NIF;
import PAI.VOs.TeacherAcronym;

public class TeacherAlreadyExistsException extends RuntimeException {

    public TeacherAlreadyExistsException (TeacherAcronym acronym, NIF nif) {
        super("Teacher already exists with the provided Acronym: " + acronym.toString() + " or NIF: " + nif.toString());
    }
}
