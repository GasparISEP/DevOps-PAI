package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.StudentGradeRepository;

import java.util.Optional;

public class US25_IWantToKnowTheAverageGradeOfACourseEditionController {

    StudentGradeRepository _studentGradeRepository;
    ICourseEditionRepository _courseEditionRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEditionController(StudentGradeRepository studentGradeRepo, ICourseEditionRepository courseEditionRepo) throws Exception {

        if (studentGradeRepo == null || courseEditionRepo == null) {
            throw new Exception("GradeStudent Repository cannot be null");
        }
        _studentGradeRepository = studentGradeRepo;
        _courseEditionRepository = courseEditionRepo;
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

