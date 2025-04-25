package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherAcademicEmailDataModel {

    private String teacherEmailDomain;
    private String teacherAcademicEmail;

    public TeacherAcademicEmailDataModel () {}

    public TeacherAcademicEmailDataModel (String emailDomain, String teacherAcademicEmail) {

        this.teacherEmailDomain = emailDomain;
        this.teacherAcademicEmail = teacherAcademicEmail;
    }

    public String getEmailDomain () {
        return teacherEmailDomain;
    }

    public String getTeacherAcademicEmail () {
        return teacherAcademicEmail;
    }
}
