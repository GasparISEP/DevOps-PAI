package PAI.persistence.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class StudentDataModel {

    @EmbeddedId
    private StudentIDDataModel studentID;

    private String name;

    @Embedded
    private NIFDataModel NIF;

    @Embedded
    private PhoneNumberDataModel phone;

    private String email;

    @Embedded
    private AddressDataModel address;

    @Embedded
    private StudentAcademicEmailDataModel academicEmail;

    public StudentDataModel() {}

    public StudentDataModel(StudentIDDataModel studentID, String name, NIFDataModel nifDataModel, PhoneNumberDataModel phoneNumberDataModel, String email,
                            AddressDataModel addressDataModel, StudentAcademicEmailDataModel studentAcademicEmailDataModel) {
        this.studentID = studentID;
        this.name = name;
        this.NIF = nifDataModel;
        this.phone = phoneNumberDataModel;
        this.email = email;
        this.address = addressDataModel;
        this.academicEmail = studentAcademicEmailDataModel;
    }

    public StudentIDDataModel getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public NIFDataModel getNIF() {
        return NIF;
    }

    public PhoneNumberDataModel getPhoneNumber() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public AddressDataModel getAddress() {
        return address;
    }

    public StudentAcademicEmailDataModel getAcademicEmail() {
        return academicEmail;
    }
}
