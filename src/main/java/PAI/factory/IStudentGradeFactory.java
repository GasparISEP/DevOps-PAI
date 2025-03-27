package PAI.factory;

import PAI.VOs.Grade;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public interface IStudentGradeFactory {
    StudentGrade newGradeStudent (Grade grade, String date, Student student, CourseEdition courseEdition) throws Exception;
}
