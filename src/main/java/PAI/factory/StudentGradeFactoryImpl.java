package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.domain.Student;

public class StudentGradeFactoryImpl implements IStudentGradeFactory {

    public StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception {
        if (grade == null){
            throw new IllegalArgumentException("Grade cannot be null.");
        }
        if (date == null){
            throw new IllegalArgumentException ("Date cannot be null or empty!");
        }
        if (student == null){
            throw  new IllegalArgumentException("Student cannot be null");
        }
        if (courseEditionID == null){
            throw  new IllegalArgumentException("Course Edition cannot be null");
        }
        return new StudentGrade(grade, date, student, courseEditionID);
    }


}
