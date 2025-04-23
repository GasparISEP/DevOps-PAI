package PAI.mapper;

import PAI.VOs.StudentAcademicEmail;
import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentAcademicEmailDataModel;
import org.springframework.stereotype.Component;

@Component
public class StudentAcademicEmailMapper implements IStudentAcademicEmailMapper {

    public StudentAcademicEmailDataModel domainToDataModel(StudentAcademicEmail studentAcademicEmail) {
        String fullEmail = studentAcademicEmail.getStudentEmail();

        return new StudentAcademicEmailDataModel(fullEmail);
    }

    public StudentAcademicEmail dataModelToDomain(StudentAcademicEmailDataModel studentAcademicEmailDataModel) {
        String fullEmail = studentAcademicEmailDataModel.getFullEmail();
        String uniqueNumberStr = fullEmail.split("@")[0];
        int uniqueNumber = Integer.parseInt(uniqueNumberStr);

        return new StudentAcademicEmail(uniqueNumber);
    }
}
