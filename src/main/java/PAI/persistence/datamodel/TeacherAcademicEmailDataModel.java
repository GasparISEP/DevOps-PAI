package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherAcademicEmailDataModel {

    @Column (name = "TeacherAcademicEmail", nullable = false)
    private String teacherAcademicEmail;

    public TeacherAcademicEmailDataModel () {}

    public TeacherAcademicEmailDataModel (String teacherAcademicEmail) {

        this.teacherAcademicEmail = teacherAcademicEmail;
    }

    public String getTeacherAcademicEmail () {
        return teacherAcademicEmail;
    }
}
