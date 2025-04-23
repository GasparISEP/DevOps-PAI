package PAI.persistence.datamodel;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Teacher")
public class TeacherDataModel {

    @Id
    private TeacherIDDataModel teacherIDDataModel;

    private String name;
    private String email;

    @Embedded
    private NIFDataModel nif;

    @Embedded
    private PhoneNumberDataModel phoneNumber;

    private String academicBackground;

    @Embedded
    private AddressDataModel address;

    private String dptAcronym;

    @Embedded
    private TeacherAcademicEmailDataModel teacherAcademicEmail;


    public TeacherDataModel () {}

    public TeacherDataModel (TeacherIDDataModel teacherIDDataModel, String name, String email, NIFDataModel nif, PhoneNumberDataModel phoneNumber, String academicBackground, AddressDataModel address, TeacherAcademicEmailDataModel teacherAcademicEmail, String dptAcronym) {

        this.teacherIDDataModel = teacherIDDataModel;
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.phoneNumber = phoneNumber;
        this.academicBackground = academicBackground;
        this.address = address;
        this.teacherAcademicEmail = teacherAcademicEmail;
        this.dptAcronym = dptAcronym;
    }

    public TeacherIDDataModel getTeacherIDDataModel() {
        return teacherIDDataModel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public NIFDataModel getNif() {
        return nif;
    }

    public PhoneNumberDataModel getPhoneNumber() {
        return phoneNumber;
    }

    public String getAcademicBackground() {
        return academicBackground;
    }

    public AddressDataModel getAddress() {
        return address;
    }

    public String getDptAcronym() {
        return dptAcronym;
    }

    public TeacherAcademicEmailDataModel getTeacherAcademicEmail() {
        return teacherAcademicEmail;
    }
}
