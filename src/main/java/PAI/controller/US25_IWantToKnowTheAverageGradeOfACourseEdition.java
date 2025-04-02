package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEditionDDD;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepositoryDDD;

import java.util.Optional;

public class US25_IWantToKnowTheAverageGradeOfACourseEdition {

    IStudentGradeRepository _studentGradeRepository;
    ICourseEditionRepositoryDDD _courseEditionRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEdition(IStudentGradeRepository studentGradeRepo, ICourseEditionRepositoryDDD courseEditionRepo) throws Exception {

        if (studentGradeRepo == null || courseEditionRepo == null) {
            throw new Exception("GradeStudent Repository cannot be null");
        }
        _studentGradeRepository = studentGradeRepo;
        _courseEditionRepository = courseEditionRepo;
    }

    public Optional<CourseEditionID> findCourseEditionIDByCourseEdition(CourseEditionDDD courseEdition) {
        return _courseEditionRepository.findIdByCourseEdition(courseEdition);
    }

    public Double IWantToKnowTheAvgGrade (CourseEditionDDD courseEdition) {
        Optional<CourseEditionID> courseEditionID = findCourseEditionIDByCourseEdition(courseEdition);
        if (courseEditionID.isPresent()) {
            return _studentGradeRepository.getAverageGrade(courseEditionID.get());
        }
        return null;
    }
}

