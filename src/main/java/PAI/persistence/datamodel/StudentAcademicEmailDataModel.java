package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class StudentAcademicEmailDataModel {

    private String studentEmail;
    private String emailDomain;
    private String fullStudentEmail;

    public StudentAcademicEmailDataModel() {}

    public StudentAcademicEmailDataModel(String studentMail, String emailDomain) {
        this.studentEmail = studentMail;
        this.emailDomain = emailDomain;
        this.fullStudentEmail = studentMail + "@" + emailDomain;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public String getFullEmail() {
        return fullStudentEmail;
    }
}
