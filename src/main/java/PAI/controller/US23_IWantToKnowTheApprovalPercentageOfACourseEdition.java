package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEditionDDD;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepositoryDDD;

import java.util.Optional;

public class US23_IWantToKnowTheApprovalPercentageOfACourseEdition {

    IStudentGradeRepository _StudentGradeRepository;
    ICourseEditionRepositoryDDD _CourseEditionRepository;


    public US23_IWantToKnowTheApprovalPercentageOfACourseEdition(IStudentGradeRepository studentGradeRepository, ICourseEditionRepositoryDDD iCourseEditionRepository){
        _StudentGradeRepository = studentGradeRepository;
        _CourseEditionRepository = iCourseEditionRepository;
    }

    private Optional<CourseEditionID> findCourseEditionIdByCourseEdition(CourseEditionDDD courseEdition_DDD){
        return _CourseEditionRepository.findIdByCourseEdition(courseEdition_DDD);
    }



    public double CalculateApprovalPercentageOfACourseEdition(CourseEditionDDD courseEdition){
        return _StudentGradeRepository.knowApprovalRate(findCourseEditionIdByCourseEdition(courseEdition).get());
    }
}
