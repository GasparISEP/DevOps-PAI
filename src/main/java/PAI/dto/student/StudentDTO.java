package PAI.dto.student;

public class StudentDTO {

    private int _studentID;
    private String _name;
    private String _NIF;
    private String _NIFCountry;
    private String _street;
    private String _postalCode;
    private String _location;
    private String _addressCountry;
    private String _phoneCountryCode;
    private String _phoneNumber;
    private String _email;

    public StudentDTO(int studentID, String name, String NIF, String NIFCountry, String street, String postalCode,
                      String location, String addressCountry, String phoneCountryCode, String phoneNumber, String email) {

        this._studentID = studentID;
        this._name = name;
        this._NIF = NIF;
        this._NIFCountry = NIFCountry;
        this._street = street;
        this._postalCode = postalCode;
        this._location = location;
        this._addressCountry = addressCountry;
        this._phoneCountryCode = phoneCountryCode;
        this._phoneNumber = phoneNumber;
        this._email = email;
    }

    public int getStudentID() {
        return _studentID;
    }

    public String getName() {
        return _name;
    }

    public String getNIF() {
        return _NIF;
    }

    public String getNIFCountry() {
        return _NIFCountry;
    }

    public String getStreet() {
        return _street;
    }

    public String getPostalCode() {
        return _postalCode;
    }

    public String getLocation() {
        return _location;
    }

    public String getAddressCountry() {
        return _addressCountry;
    }

    public String getPhoneCountryCode() {
        return _phoneCountryCode;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public String getEmail() {
        return _email;
    }
}