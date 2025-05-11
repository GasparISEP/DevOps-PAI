package PAI.mapper;

import PAI.domain.Student;
import PAI.persistence.datamodel.Student.StudentDataModel;

public interface IStudentMapper {

    StudentDataModel domainToDataModel(Student student);

    Student dataModelToDomain(StudentDataModel studentDataModel) throws Exception;
}
