package PAI.mapper.studentGrade;

import PAI.domain.studentGrade.StudentGrade;
import PAI.persistence.datamodel.studentGrade.StudentGradeDM;

public interface IStudentGradeMapper {

   StudentGradeDM toData(StudentGrade studentGrade) throws Exception;
   StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception;

}
