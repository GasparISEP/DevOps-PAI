package PAI.mapper;

import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentIDDataModel;

public class StudentIDMapper implements  IStudentIDMapper {

    public StudentIDDataModel domainToDataModel(StudentID studentID) {

        return new StudentIDDataModel(studentID.getUniqueNumber());
    }

    public StudentID dataModelToDomain(StudentIDDataModel studentIDDataModel) {

        return new StudentID(studentIDDataModel.getUniqueNumber());
    }
}
