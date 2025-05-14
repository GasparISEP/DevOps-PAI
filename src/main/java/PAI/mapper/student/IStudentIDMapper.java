package PAI.mapper.student;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.student.StudentIDDataModel;

public interface IStudentIDMapper {

    StudentIDDataModel domainToDataModel(StudentID studentID);

    StudentID dataModelToDomain(StudentIDDataModel studentIDDataModel);
}
