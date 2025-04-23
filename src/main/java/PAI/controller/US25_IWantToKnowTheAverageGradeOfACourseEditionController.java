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


//    public Optional<CourseEditionID> findCourseEditionIDByCourseEdition(CourseEdition courseEdition) {
//        return _courseEditionRepository.findIdByCourseEdition(courseEdition);
//    }

//    public Double IWantToKnowTheAvgGrade (CourseEdition courseEdition) {
//        Optional<CourseEditionID> courseEditionID = findCourseEditionIDByCourseEdition(courseEdition);
//        if (courseEditionID.isPresent()) {
//            return _studentGradeRepository.getAverageGrade(courseEditionID.get());
//        }
//        return null;
//    }
}

