package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentAcademicEmailDataModel {

    private String fullStudentEmail;

    public StudentAcademicEmailDataModel() {}

    public StudentAcademicEmailDataModel(String fullStudentEmail){
        this.fullStudentEmail = fullStudentEmail;
    }

    public String getFullEmail() {
        return fullStudentEmail;
    }
}
