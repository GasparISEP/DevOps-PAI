package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeMapper {

    public StudentGradeDM toData(StudentGrade studentGrade) {
        StudentID studentID = studentGrade.get_studentID();
        StudentIDDataModel studentIdDM = new StudentIDDataModel(
                studentID.getUniqueNumber()
        );

        return new StudentGradeDM( studentGrade.identity().toString().hashCode(),studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate(), studentGrade.get_courseEditionID(),studentIdDM);
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.get_grade());
        Date date = new Date(studentGradeDM.get_date());

        StudentIDDataModel studentIdDM = studentGradeDM.getStudentId();

        int uniqueNumber = studentIdDM.getUniqueNumber();

        StudentID studentID = new StudentID(uniqueNumber);

        return new StudentGrade(grade,date,studentID,studentGradeDM.getCourseEditionID());


    }



}
