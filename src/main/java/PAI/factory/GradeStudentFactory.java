package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudent;
import PAI.domain.Student;

public class GradeStudentFactory implements GradeStudentFactoryImpl {

    public GradeStudent newGradeStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception {
        return new GradeStudent(grade, date, student, courseEdition);
    }
}
