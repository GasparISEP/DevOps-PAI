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
        this._acronym = acronym;
        this._name = name;
        this._email = email;
        this._nif = nif;
        this._phoneNumber = phoneNumber;
        this._address=address;
        this._teacherCategory= category;
        this._department = department;
    }
}
