package PAI.domain;

public class Teacher {

    private String _acronym;

    private String _name;

    private String _email;

    private String _nif;

    private String _phoneNumber;

    private Address _address;

    private TeacherCategory _teacherCategory;

    private Department _department;

    //constructor
    public Teacher(String acronym, String name, String email, String nif, String phoneNumber, Address address, TeacherCategory category,
                   Department department) throws IllegalArgumentException {
        validateAcronym(acronym);
        this._name = name;
        this._email = email;
        this._nif = nif;
        this._phoneNumber = phoneNumber;
        this._address=address;
        this._teacherCategory= category;
        this._department = department;
    }

    private void validateAcronym(String teacherAcronym) throws IllegalArgumentException {
        if (teacherAcronym == null || teacherAcronym.isBlank()) {
            throw new IllegalArgumentException("Teacher´s acronym must be a 3 capital letter non-empty String.");
        }
        if(!teacherAcronym.matches("^[A-Z]{3}$")){
            throw new IllegalArgumentException("Teacher´s acronym must contain only three capital letters.");
        }
        this._acronym = teacherAcronym;
    }
}
