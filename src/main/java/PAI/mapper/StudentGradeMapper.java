package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentGradeMapper {

    public StudentGradeDM toData(StudentGrade studentGrade) {
        StudentID studentID = studentGrade.get_studentID();
        StudentIDDataModel studentIdDM = new StudentIDDataModel(
                studentID.getUniqueNumber().getUniqueNumber(),
                studentID.getNIF().getNIF(),
                studentID.getNIF().getCountry().getCountryName()
        );

        return new StudentGradeDM( studentGrade.identity().toString().hashCode(),studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate(), studentGrade.get_courseEditionID(),studentIdDM);
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.get_grade());
        Date date = new Date(studentGradeDM.get_date());

        StudentIDDataModel studentIdDM = studentGradeDM.getStudentId();

        UniqueNumber uniqueNumber = new UniqueNumber(studentIdDM.getUniqueNumber());
        Country country = new Country(studentIdDM.getCountry());
        NIF nif = new NIF(studentIdDM.getNIF(), country);

        StudentID studentID = new StudentID(uniqueNumber, nif);

        return new StudentGrade(grade,date,studentID,studentGradeDM.getCourseEditionID());


    }



}
