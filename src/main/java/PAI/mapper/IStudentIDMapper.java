package PAI.mapper;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentIDDataModel;

public interface IStudentIDMapper {

    StudentIDDataModel domainToDataModel(StudentID studentID);

    StudentID dataModelToDomain(StudentIDDataModel studentIDDataModel);
}
