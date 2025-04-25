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

    private String teacherName;
    private String teacherEmail;

    @Embedded
    private NIFDataModel teacherNIF;

    @Embedded
    private PhoneNumberDataModel teacherPhoneNumber;

    private String teacherAcademicBackground;

    @Embedded
    private AddressDataModel teacherAddress;

    private String teacherDepartmentAcronym;

    @Embedded
    private TeacherAcademicEmailDataModel teacherAcademicEmail;


    public TeacherDataModel () {}

    public TeacherDataModel (TeacherIDDataModel teacherIDDataModel, String name, String email, NIFDataModel nif, PhoneNumberDataModel phoneNumber, String academicBackground, AddressDataModel address, TeacherAcademicEmailDataModel teacherAcademicEmail, String dptAcronym) {

        this.teacherIDDataModel = teacherIDDataModel;
        this.teacherName = name;
        this.teacherEmail = email;
        this.teacherNIF = nif;
        this.teacherPhoneNumber = phoneNumber;
        this.teacherAcademicBackground = academicBackground;
        this.teacherAddress = address;
        this.teacherAcademicEmail = teacherAcademicEmail;
        this.teacherDepartmentAcronym = dptAcronym;
    }

    public TeacherIDDataModel getTeacherIDDataModel() {
        return teacherIDDataModel;
    }

    public String getName() {
        return teacherName;
    }

    public String getEmail() {
        return teacherEmail;
    }

    public NIFDataModel getNif() {
        return teacherNIF;
    }

    public PhoneNumberDataModel getPhoneNumber() {
        return teacherPhoneNumber;
    }

    public String getAcademicBackground() {
        return teacherAcademicBackground;
    }

    public AddressDataModel getAddress() {
        return teacherAddress;
    }

    public String getDptAcronym() {
        return teacherDepartmentAcronym;
    }

    public TeacherAcademicEmailDataModel getTeacherAcademicEmail() {
        return teacherAcademicEmail;
    }
}
