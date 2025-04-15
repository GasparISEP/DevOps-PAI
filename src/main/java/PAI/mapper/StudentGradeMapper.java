/*package PAI.mapper;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
import PAI.persistence.datamodel.StudentGradeDM;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentGradeMapper {

    public StudentGradeDM toData(StudentGrade studentGrade) {
        return new StudentGradeDM( studentGrade.get_studentID().getUniqueNumber(),studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate(), studentGrade.get_courseEditionID(),studentGrade.get_studentID().getUniqueNumber());
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.get_grade());
        Date date = new Date(studentGradeDM.get_date());
        StudentID studentID = new StudentID(studentGradeDM.getStudentId());


        return new StudentGrade(grade,date,studentID,studentGradeDM.getCourseEditionID());


    }


}*/
