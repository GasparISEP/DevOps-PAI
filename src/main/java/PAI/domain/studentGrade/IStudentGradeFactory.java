package PAI.domain.studentGrade;

import PAI.VOs.*;

public interface IStudentGradeFactory {
    StudentGrade createGradeStudent(Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception;
    StudentGrade createGradeStudent(Grade grade, Date date, StudentID student, CourseEditionID courseEditionID, StudentGradeID studentGradeID, StudentGradeGeneratedID studentGradeGeneratedID) throws Exception;
}
