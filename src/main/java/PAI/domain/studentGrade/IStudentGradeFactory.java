package PAI.domain.studentGrade;

import PAI.VOs.*;

public interface IStudentGradeFactory {
    StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception;
    StudentGrade newGradeStudentFromDataModel (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID, StudentGradeID studentGradeID) throws Exception;
}
