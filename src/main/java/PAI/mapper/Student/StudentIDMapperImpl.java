package PAI.mapper.Student;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.Student.StudentIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class StudentIDMapperImpl implements IStudentIDMapper {

    public StudentIDDataModel domainToDataModel(StudentID studentID) {

        return new StudentIDDataModel(studentID.getUniqueNumber());
    }

    public StudentID dataModelToDomain(StudentIDDataModel studentIDDataModel) {

        return new StudentID(studentIDDataModel.getUniqueNumber());
    }
}
