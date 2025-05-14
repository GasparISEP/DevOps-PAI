package PAI.mapper.student;

import PAI.domain.student.Student;
import PAI.persistence.datamodel.student.StudentDataModel;

public interface IStudentMapper {

    StudentDataModel domainToDataModel(Student student);

    Student dataModelToDomain(StudentDataModel studentDataModel) throws Exception;
}
