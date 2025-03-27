package PAI.factory;

import PAI.VOs.Grade;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public class StudentGradeFactoryImpl implements IStudentGradeFactory {

    public StudentGrade newGradeStudent (Grade grade, String date, Student student, CourseEdition courseEdition) throws Exception {
        if (grade == null){
            throw new IllegalArgumentException("Grade cannot be null.");
        }
        if (date == null || date.isBlank()){
            throw new IllegalArgumentException ("Date cannot be null or empty!");
        }
        if (student == null){
            throw  new IllegalArgumentException("Student cannot be null");
        }
        if (courseEdition == null){
            throw  new IllegalArgumentException("Course Edition cannot be null");
        }
        return new StudentGrade(grade, date, student, courseEdition);
    }


}
