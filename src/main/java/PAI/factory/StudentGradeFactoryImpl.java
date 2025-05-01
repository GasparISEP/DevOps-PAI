package PAI.factory;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import org.springframework.stereotype.Service;


@Service
public class StudentGradeFactoryImpl implements IStudentGradeFactory {

    public StudentGrade newGradeStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception {
        StudentGradeID studentGradeID = new StudentGradeID(student,courseEditionID);
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

        return new StudentGrade(grade, date, student, courseEditionID,studentGradeID);
    }


    public StudentGrade newGradeStudentFromDataModel (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID, StudentGradeID studentGradeID) throws Exception {
        return new StudentGrade(grade, date, student, courseEditionID, studentGradeID);
    }
}
