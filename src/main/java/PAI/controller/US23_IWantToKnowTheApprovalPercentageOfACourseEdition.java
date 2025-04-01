package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEdition_2;
import PAI.repository.StudentGradeRepository;

public class US23_IWantToKnowTheApprovalPercentageOfACourseEdition {

    StudentGradeRepository _StudentGradeRepository;

    public US23_IWantToKnowTheApprovalPercentageOfACourseEdition(StudentGradeRepository studentGradeRepository){
        _StudentGradeRepository = studentGradeRepository;
    }

    public double IWantToKnowTheApprovalPercentageOfACourseEdition(CourseEdition_2 courseEdition){
        return _StudentGradeRepository.knowApprovalRate(courseEdition);
    }
}
