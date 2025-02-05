package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudentRepository;

public class US25_IWantToKnowTheAverageGradeOfACourseEdtion {

    GradeStudentRepository _gradeStudentRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEdtion(GradeStudentRepository gradeStudentList) throws Exception {

        if (gradeStudentList == null) {
            throw new Exception("GradeStudent Repository cannot be null");
        }
        _gradeStudentRepository = gradeStudentList;
    }

    public double IWantToKnowTheAvgGrade (CourseEdition courseEdition) {
        return _gradeStudentRepository.KnowAverageGrade(courseEdition);
    }
}

