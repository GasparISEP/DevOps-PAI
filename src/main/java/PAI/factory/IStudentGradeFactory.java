package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public interface IStudentGradeFactory {
    StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEdition courseEdition) throws Exception;
}
