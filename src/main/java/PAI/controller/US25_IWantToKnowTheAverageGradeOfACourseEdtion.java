package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudentRepository;

public class US25_IWantToKnowTheAverageGradeOfACourseEdtion {

    GradeStudentRepository _gradeStudentRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEdtion(GradeStudentRepository gradeStudentList) {
        _gradeStudentRepository = gradeStudentList;
    }

    public double IWantToKnowTheAvgGrade (CourseEdition courseEdition) {
        return _gradeStudentRepository.KnowAverageGrade(courseEdition);
    }
}

