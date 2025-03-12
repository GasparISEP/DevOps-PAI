package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.repository.GradeStudentRepository;

public class US25_IWantToKnowTheAverageGradeOfACourseEdition {

    GradeStudentRepository _gradeStudentRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEdition(GradeStudentRepository gradeStudentList) throws Exception {

        if (gradeStudentList == null) {
            throw new Exception("GradeStudent Repository cannot be null");
        }
        _gradeStudentRepository = gradeStudentList;
    }

    public double IWantToKnowTheAvgGrade (CourseEdition courseEdition) {
        return _gradeStudentRepository.KnowAverageGrade(courseEdition);
    }
}

