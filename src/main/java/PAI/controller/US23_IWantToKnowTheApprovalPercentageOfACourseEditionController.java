package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;

import java.util.Optional;

public class US23_IWantToKnowTheApprovalPercentageOfACourseEditionController {

    StudentGradeRepository _StudentGradeRepository;
    ICourseEditionRepository _CourseEditionRepository;


    public US23_IWantToKnowTheApprovalPercentageOfACourseEditionController(StudentGradeRepository studentGradeRepository, ICourseEditionRepository iCourseEditionRepository){
        _StudentGradeRepository = studentGradeRepository;
        _CourseEditionRepository = iCourseEditionRepository;
    }

    private Optional<CourseEditionID> findCourseEditionIdByCourseEdition(CourseEdition courseEdition_DDD){
        return _CourseEditionRepository.findIdByCourseEdition(courseEdition_DDD);
    }



    public double CalculateApprovalPercentageOfACourseEdition(CourseEdition courseEdition){
        return _StudentGradeRepository.knowApprovalRate(findCourseEditionIdByCourseEdition(courseEdition).get());
    }
}
