package PAI.mapper;

import PAI.VOs.StudentAcademicEmail;
import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentAcademicEmailDataModel;

public class StudentAcademicEmailMapper implements IStudentAcademicEmailMapper {

    public StudentAcademicEmailDataModel domainToDataModel(StudentAcademicEmail studentAcademicEmail) {
        String fullEmail = studentAcademicEmail.getStudentEmail();

        String[] parts = fullEmail.split("@");
        String studentMail = parts[0];
        String emailDomain = parts[1];

        return new StudentAcademicEmailDataModel(studentMail, emailDomain);
    }

    public StudentAcademicEmail dataModelToDomain(StudentAcademicEmailDataModel studentAcademicEmailDataModel) {
        String fullEmail = studentAcademicEmailDataModel.getFullEmail();
        String uniqueNumberStr = fullEmail.split("@")[0];
        int uniqueNumber = Integer.parseInt(uniqueNumberStr);

        StudentID studentID = new StudentID(uniqueNumber);
        return new StudentAcademicEmail(studentID);
    }
}
