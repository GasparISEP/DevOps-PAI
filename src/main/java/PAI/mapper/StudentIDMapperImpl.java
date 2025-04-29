package PAI.mapper;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.springframework.stereotype.Service;

@Service
public class StudentIDMapperImpl implements  IStudentIDMapper {

    public StudentIDDataModel domainToDataModel(StudentID studentID) {

        return new StudentIDDataModel(studentID.getUniqueNumber());
    }

    public StudentID dataModelToDomain(StudentIDDataModel studentIDDataModel) {

        return new StudentID(studentIDDataModel.getUniqueNumber());
    }
}
