package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherAcademicEmailDataModel {

    private String _emailDomain;
    private String _teacherAcademicEmail;

    public TeacherAcademicEmailDataModel () {}

    public TeacherAcademicEmailDataModel (String emailDomain, String teacherAcademicEmail) {

        _emailDomain = emailDomain;
        _teacherAcademicEmail = teacherAcademicEmail;
    }

    public String getEmailDomain () {
        return _emailDomain;
    }

    public String getTeacherAcademicEmail () {
        return _teacherAcademicEmail;
    }
}
