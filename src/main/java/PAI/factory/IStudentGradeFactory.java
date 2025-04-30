package PAI.factory;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public interface IStudentGradeFactory {
    StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception;
    StudentGrade newGradeStudentFromDataModel (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID, StudentGradeID studentGradeID) throws Exception;
}
