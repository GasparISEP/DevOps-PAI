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
        validateEmail(email);
        validateNif(nif);
        validatePhoneNumber(phoneNumber);
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

    private void validateEmail(String email) throws IllegalArgumentException {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Teacher´s email must be a non-empty String.");
        }
        if (!email.toLowerCase().matches(_acronym.toLowerCase() + "@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this._email = email;
    }

    private void validateNif(String nif) throws IllegalArgumentException{
        if (nif == null || nif.isBlank())
            throw new IllegalArgumentException("Teacher´s NIF must be a non-empty String.");

        if (!nif.matches("^[0-9]{9}$"))
            throw new IllegalArgumentException("Teacher´s NIF must contain only 9 characters.");
        this._nif = nif;
    }

    private void validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if(phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Teacher´s office phoneNumber must be a non-empty String.");

        if (!phoneNumber.matches("^[A-Z][0-9]{3}$") || phoneNumber.length() != 4)
            throw new IllegalArgumentException("Teacher´s office phoneNumber must have a letter followed by 3 digits.");

        this._phoneNumber = phoneNumber;
    }

    public boolean hasSameAcronym(Teacher teacher) {
        return this._acronym.equals(teacher._acronym);
    }
    public boolean hasSameNif(Teacher teacher) {
        return this._nif.equals(teacher._nif);
    }
    public boolean isInDepartment(Department department) {
        return _department == department;
    }
}
