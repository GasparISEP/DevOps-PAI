package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherAcademicEmailDataModel {

    @Column (name = "TeacherEmailDomain", nullable = false)
    private String teacherEmailDomain;

    @Column (name = "TeacherAcademicEmail", nullable = false)
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
