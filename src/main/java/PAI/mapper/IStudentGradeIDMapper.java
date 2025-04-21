package PAI.mapper;

import PAI.VOs.StudentGradeID;
import PAI.persistence.datamodel.StudentGradeIDDataModel;

public interface IStudentGradeIDMapper {
    StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID) throws Exception;
    StudentGradeID toDomain (StudentGradeIDDataModel studentGradeIDDataModel) throws Exception;
}
