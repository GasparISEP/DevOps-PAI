package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class StudentAcademicEmailDataModel {

    @Column(name = "StudentAcademicEmail")
    private String fullStudentEmail;

    public StudentAcademicEmailDataModel() {}

    public StudentAcademicEmailDataModel(String fullStudentEmail){
        this.fullStudentEmail = fullStudentEmail;
    }

    public String getFullEmail() {
        return fullStudentEmail;
    }
}
