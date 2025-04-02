package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEdition_2;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;

import java.util.Optional;

public class US25_IWantToKnowTheAverageGradeOfACourseEdition {

    IStudentGradeRepository _studentGradeRepository;
    ICourseEditionRepository _courseEditionRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEdition(IStudentGradeRepository studentGradeRepo, ICourseEditionRepository courseEditionRepo) throws Exception {

        if (studentGradeRepo == null || courseEditionRepo == null) {
            throw new Exception("GradeStudent Repository cannot be null");
        }
        _studentGradeRepository = studentGradeRepo;
        _courseEditionRepository = courseEditionRepo;
    }

    public Optional<CourseEditionID> findCourseEditionIDByCourseEdition(CourseEdition_2 courseEdition) {
        return _courseEditionRepository.findIdByCourseEdition(courseEdition);
    }

    public Double IWantToKnowTheAvgGrade (CourseEdition_2 courseEdition) {
        Optional<CourseEditionID> courseEditionID = findCourseEditionIDByCourseEdition(courseEdition);
        if (courseEditionID.isPresent()) {
            return _studentGradeRepository.getAverageGrade(courseEditionID.get());
        }
        return null;
    }
}

