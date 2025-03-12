package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.repository.GradeStudentRepository;

public class US23_IWantToKnowTheApprovalPercentageOfACourseEdition {

    GradeStudentRepository _gradeStudentRepository;

    public US23_IWantToKnowTheApprovalPercentageOfACourseEdition(GradeStudentRepository gradeStudentRepository){
        _gradeStudentRepository = gradeStudentRepository;
    }

    public double IWantToKnowTheApprovalPercentageOfACourseEdition(CourseEdition courseEdition){
        return _gradeStudentRepository.knowApprovalRate(courseEdition);
    }
}
