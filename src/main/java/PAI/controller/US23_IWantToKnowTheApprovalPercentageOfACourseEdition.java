package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEdition_2;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;

import java.util.Optional;

public class US23_IWantToKnowTheApprovalPercentageOfACourseEdition {

    IStudentGradeRepository _StudentGradeRepository;
    ICourseEditionRepository _CourseEditionRepository;


    public US23_IWantToKnowTheApprovalPercentageOfACourseEdition(IStudentGradeRepository studentGradeRepository, ICourseEditionRepository iCourseEditionRepository){
        _StudentGradeRepository = studentGradeRepository;
        _CourseEditionRepository = iCourseEditionRepository;
    }

    private Optional<CourseEditionID> findCourseEditionIdByCourseEdition(CourseEdition_2 courseEdition_2){
        return _CourseEditionRepository.findIdByCourseEdition(courseEdition_2);
    }



    public double IWantToKnowTheApprovalPercentageOfACourseEdition(CourseEdition_2 courseEdition){
        return _StudentGradeRepository.knowApprovalRate(findCourseEditionIdByCourseEdition(courseEdition).get());
    }
}
