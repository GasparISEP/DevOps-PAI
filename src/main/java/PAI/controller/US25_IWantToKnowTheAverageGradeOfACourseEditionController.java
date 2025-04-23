package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;
import PAI.service.IStudentGradeService;

import java.util.Optional;

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

