package PAI.mapper;

import PAI.VOs.StudentAcademicEmail;
import PAI.persistence.datamodel.StudentAcademicEmailDataModel;

public interface IStudentAcademicEmailMapper {

    StudentAcademicEmailDataModel domainToDataModel (StudentAcademicEmail studentAcademicEmail);
    StudentAcademicEmail dataModelToDomain (StudentAcademicEmailDataModel studentAcademicEmailDataModel);
}
