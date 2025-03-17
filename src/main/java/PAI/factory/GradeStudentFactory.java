package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public interface GradeStudentFactory {
    StudentGrade newGradeStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception;
}
