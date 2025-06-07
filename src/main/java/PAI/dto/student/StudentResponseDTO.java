package PAI.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class StudentResponseDTO extends RepresentationModel<StudentResponseDTO> {

    @JsonProperty("studentID")
    private final int studentID;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("nif")
    private final String nif;

    @JsonProperty("nifCountry") // Corrigido
    private final String nifCountry;

    @JsonProperty("street")
    private final String street;

    @JsonProperty("postalCode")
    private final String postalCode;

    @JsonProperty("location")
    private final String location;

    @JsonProperty("addressCountry") // Corrigido
    private final String addressCountry;

    @JsonProperty("countryCode") // Corrigido (em vez de phoneCountryCode)
    private final String countryCode;

    @JsonProperty("phoneNumber")
    private final String phoneNumber;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("academicEmail")
    private final String academicEmail;

    public StudentResponseDTO(int studentID, String name, String nif, String nifCountry, String street, String postalCode,
                              String location, String addressCountry, String countryCode, String phoneNumber,
                              String email, String academicEmail) {
        this.studentID = studentID;
        this.name = name;
        this.nif = nif;
        this.nifCountry = nifCountry;
        this.street = street;
        this.postalCode = postalCode;
        this.location = location;
        this.addressCountry = addressCountry;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.academicEmail = academicEmail;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getNif() {
        return nif;
    }

    public String getNifCountry() {
        return nifCountry;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getLocation() {
        return location;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAcademicEmail() {
        return academicEmail;
    }
}