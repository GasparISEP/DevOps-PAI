package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.service.IStudentGradeService;
import org.springframework.stereotype.Component;


public class US25_IWantToKnowTheAverageGradeOfACourseEditionController {

    IStudentGradeService studentGradeService;

    public US25_IWantToKnowTheAverageGradeOfACourseEditionController(IStudentGradeService studentGradeService) throws Exception {

        if (studentGradeService == null) {
            throw new Exception("GradeStudent Service cannot be null");
        }
        this.studentGradeService=studentGradeService;
    }

    public Double IWantToKnowTheAvgGrade (CourseEditionID courseEdition) throws Exception{
        if (courseEdition == null){
            throw new Exception("Course Edition cannot be null");
        }
        return studentGradeService.getAverageGrade(courseEdition);

    }
}

