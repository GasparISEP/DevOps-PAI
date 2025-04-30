package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.service.IStudentGradeService;
import org.springframework.stereotype.Component;


public class US23_IWantToKnowTheApprovalPercentageOfACourseEditionController {

    IStudentGradeService _iStudentGradeService;

    public US23_IWantToKnowTheApprovalPercentageOfACourseEditionController (IStudentGradeService iStudentGradeService){
        if (iStudentGradeService == null){
            throw new IllegalArgumentException("Service cannot be null");
        }
        _iStudentGradeService = iStudentGradeService;
    }
    public double CalculateApprovalPercentageOfACourseEdition (CourseEditionID courseEditionID){
        if (courseEditionID == null){
            throw new IllegalArgumentException("CourseEditionID cannot be null");
        }
        return _iStudentGradeService.knowApprovalRate(courseEditionID);
    }
}

