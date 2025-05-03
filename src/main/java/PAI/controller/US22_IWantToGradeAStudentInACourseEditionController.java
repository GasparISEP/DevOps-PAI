
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
import PAI.service.studentGrade.IStudentGradeService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class US22_IWantToGradeAStudentInACourseEditionController {
    IStudentGradeService studentGradeService;

    public US22_IWantToGradeAStudentInACourseEditionController(IStudentGradeService studentGradeService) {
        if (studentGradeService== null){
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.studentGradeService = studentGradeService;
    }


    public Optional <StudentGrade> registerStudentGrade(Grade grade, Date date, StudentID student, CourseEditionID courseEdition_DDD) throws Exception{

        if (student != null && courseEdition_DDD != null){
            return Optional.of(studentGradeService.newStudentGrade(grade,date,student,courseEdition_DDD));
        }

        return Optional.empty();
    }

}


