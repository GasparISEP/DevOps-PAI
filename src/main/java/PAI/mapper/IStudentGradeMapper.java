package PAI.mapper;

import PAI.domain.StudentGrade;
import PAI.persistence.datamodel.StudentGradeDM;

public interface IStudentGradeMapper {

   StudentGradeDM toData(StudentGrade studentGrade) throws Exception;
   StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception;

}
