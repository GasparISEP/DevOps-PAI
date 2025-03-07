package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudent;
import PAI.domain.Student;

public class GradeStudentFactory implements GradeStudentFactoryImpl {

    public GradeStudent newGradeStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception {
        if (grade < 0 ||  grade > 20){
            throw new IllegalArgumentException("Grade cannot be less than 0 or higher than 20.");
        }
        if (date == null || date.isBlank()){
            throw new IllegalArgumentException ("Date cannot be null or empty!");
        }
        return new GradeStudent(grade, date, student, courseEdition);
    }
}
