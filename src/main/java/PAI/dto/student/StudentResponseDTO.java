package PAI.dto.student;

public class StudentResponseDTO {

    private int studentID;
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
    private String _academicEmail;

    public StudentResponseDTO(int studentID, String name, String NIF, String NIFCountry, String street, String postalCode,
                              String location, String addressCountry, String phoneCountryCode, String phoneNumber, String email, String academicEmail) {

        this.studentID = studentID;
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
        this._academicEmail = academicEmail;
    }

    public int getStudentID() {
        return studentID;
    }
}