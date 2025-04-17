package PAI.persistence.datamodel;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Teacher")
public class TeacherDataModel {

    @Id
    private TeacherIDDataModel _teacherIDDataModel;

    private String _name;
    private String _email;

    @Embedded
    private NIFDataModel _nif;

    @Embedded
    private PhoneNumberDataModel _phoneNumber;

    private String _academicBackground;

    @Embedded
    private AddressDataModel _address;

    private String _dptAcronym;

    @Embedded
    private TeacherAcademicEmailDataModel _teacherAcademicEmail;


    public TeacherDataModel () {}

    public TeacherDataModel (TeacherIDDataModel teacherIDDataModel, String name, String email, NIFDataModel nif, PhoneNumberDataModel phoneNumber, String academicBackground, AddressDataModel address, TeacherAcademicEmailDataModel teacherAcademicEmail, String dptAcronym) {

        _teacherIDDataModel = teacherIDDataModel;
        _name = name;
        _email = email;
        _nif = nif;
        _phoneNumber = phoneNumber;
        _academicBackground = academicBackground;
        _address = address;
        _teacherAcademicEmail = teacherAcademicEmail;
        _dptAcronym = dptAcronym;
    }

    public TeacherIDDataModel getTeacherIDDataModel() {
        return _teacherIDDataModel;
    }

    public String getName() {
        return _name;
    }

    public String getEmail() {
        return _email;
    }

    public NIFDataModel getNif() {
        return _nif;
    }

    public PhoneNumberDataModel getPhoneNumber() {
        return _phoneNumber;
    }

    public String getAcademicBackground() {
        return _academicBackground;
    }

    public AddressDataModel getAddress() {
        return _address;
    }

    public String getDptAcronym() {
        return _dptAcronym;
    }

    public TeacherAcademicEmailDataModel getTeacherAcademicEmail() {
        return _teacherAcademicEmail;
    }
}
