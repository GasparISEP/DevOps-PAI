package PAI.service.studentGrade;

import PAI.VOs.*;
import PAI.domain.StudentGrade;

public interface IStudentGradeService {
    StudentGrade newStudentGrade (Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID) throws Exception;
    double knowApprovalRate(CourseEditionID courseEditionID)throws Exception;
    Double getAverageGrade(CourseEditionID courseEditionID) throws Exception;
    boolean isDateGradeInRangeWithSchoolYear (CourseEditionID courseEditionID, Date dates) throws Exception;
    boolean hasStudentAlreadyGradeAtThisCourseEdition (StudentID student, CourseEditionID courseEditionID) throws Exception;
}
