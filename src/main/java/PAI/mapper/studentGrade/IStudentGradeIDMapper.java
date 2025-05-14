package PAI.mapper.studentGrade;

import PAI.VOs.StudentGradeID;
import PAI.persistence.datamodel.studentGrade.StudentGradeIDDataModel;

public interface IStudentGradeIDMapper {
    StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID) throws Exception;
    StudentGradeID toDomain (StudentGradeIDDataModel studentGradeIDDataModel) throws Exception;
}
