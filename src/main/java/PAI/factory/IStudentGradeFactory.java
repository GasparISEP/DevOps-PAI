package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEdition_2;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public interface IStudentGradeFactory {
    StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEdition_2 courseEdition) throws Exception;
}
