
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.domain.StudentGrade;
import PAI.service.IStudentGradeService;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class US22_IWantToGradeAStudentInACourseEditionController {
    IStudentGradeService studentGradeService;

    public US22_IWantToGradeAStudentInACourseEditionController(IStudentGradeService studentGradeService) {
        if (studentGradeService== null){
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.studentGradeService = studentGradeService;
    }


    public Optional <StudentGrade> registerStudentGrade(Grade grade, Date date, Student student, CourseEdition courseEdition_DDD) throws Exception{
        StudentID studentID = student.identity();
        CourseEditionID courseEditionID = courseEdition_DDD.identity();

        if (studentID != null && courseEditionID != null){
            return Optional.of(studentGradeService.newStudentGrade(grade,date,studentID,courseEditionID));
        }

        return Optional.empty();
    }

}


