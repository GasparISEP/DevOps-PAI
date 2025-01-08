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
        validateName(name);
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

    private void validateName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Teacher´s name must be a non-empty String.");
        }
        if (name.length() <2 || name.length() > 100){
            throw new IllegalArgumentException("Teacher´s name must be between 2 and 100 characters.");
        }
        for (int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            if (!Character.isLetter(c) && !Character.isSpaceChar(c) && c!= '-'){
                throw new IllegalArgumentException("Teacher´s name must contain only letters and spaces.");
            }
        }
        if(!name.matches("^[A-Z].*")){
            throw new IllegalArgumentException("Teacher´s name should start with a capital letter.");
        }
        this._name = name;
    }
}
