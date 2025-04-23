package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherAcademicEmailDataModel {

    private String emailDomain;
    private String teacherAcademicEmail;

    public TeacherAcademicEmailDataModel () {}

    public TeacherAcademicEmailDataModel (String emailDomain, String teacherAcademicEmail) {

        this.emailDomain = emailDomain;
        this.teacherAcademicEmail = teacherAcademicEmail;
    }

    public String getEmailDomain () {
        return emailDomain;
    }

    public String getTeacherAcademicEmail () {
        return teacherAcademicEmail;
    }
}
