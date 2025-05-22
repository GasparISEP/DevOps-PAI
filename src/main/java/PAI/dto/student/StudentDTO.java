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

    public StudentDTO() {}

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

    public void setStudentID(int _studentID) {
        this._studentID = _studentID;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void setNIF(String _NIF) {
        this._NIF = _NIF;
    }

    public void setNIFCountry(String _NIFCountry) {
        this._NIFCountry = _NIFCountry;
    }

    public void setStreet(String _street) {
        this._street = _street;
    }

    public void setPostalCode(String _postalCode) {
        this._postalCode = _postalCode;
    }

    public void setLocation(String _location) {
        this._location = _location;
    }

    public void setAddressCountry(String _addressCountry) {
        this._addressCountry = _addressCountry;
    }

    public void setPhoneCountryCode(String _phoneCountryCode) {
        this._phoneCountryCode = _phoneCountryCode;
    }

    public void setPhoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }
}