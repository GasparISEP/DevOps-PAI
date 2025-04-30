package PAI.persistence.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")
public class TeacherDataModel {

    @EmbeddedId
    private TeacherIDDataModel teacherId;

    @Column (name = "Name", nullable = false)
    private String name;

    @Column (name = "Email", nullable = false)
    private String email;

    @Embedded
    private NIFDataModel nif;

    @Embedded
    private PhoneNumberDataModel phoneNumber;

    @Column (name = "AcademicBackground", nullable = false)
    private String academicBackground;

    @Embedded
    private AddressDataModel address;

    @Column (name = "DepartmentAcronym", nullable = false)
    private String departmentAcronym;

    @Embedded
    private TeacherAcademicEmailDataModel academicEmail;


    public TeacherDataModel () {}

    public TeacherDataModel (TeacherIDDataModel teacherId, String name, String email, NIFDataModel nif, PhoneNumberDataModel phoneNumber, String academicBackground, AddressDataModel address, TeacherAcademicEmailDataModel teacherAcademicEmail, String dptAcronym) {

        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.phoneNumber = phoneNumber;
        this.academicBackground = academicBackground;
        this.address = address;
        this.academicEmail = teacherAcademicEmail;
        this.departmentAcronym = dptAcronym;
    }

    public TeacherIDDataModel getTeacherIDDataModel() {
        return teacherId;
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
        return departmentAcronym;
    }

    public TeacherAcademicEmailDataModel getTeacherAcademicEmail() {
        return academicEmail;
    }
}
