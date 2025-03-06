package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudent;
import PAI.domain.Student;

public interface GradeStudentFactoryImpl {
    GradeStudent newGradeStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception;
}
