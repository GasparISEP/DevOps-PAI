package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.domain.CourseEdition;
import PAI.repository.StudentGradeRepository;

public class US25_IWantToKnowTheAverageGradeOfACourseEdition {

    StudentGradeRepository _StudentGradeRepository;

    public US25_IWantToKnowTheAverageGradeOfACourseEdition(StudentGradeRepository gradeStudentList) throws Exception {

        if (gradeStudentList == null) {
            throw new Exception("GradeStudent Repository cannot be null");
        }
        _StudentGradeRepository = gradeStudentList;
    }

    public double IWantToKnowTheAvgGrade (CourseEditionID courseEditionID) {
        return _StudentGradeRepository.KnowAverageGrade(courseEditionID);
    }
}

