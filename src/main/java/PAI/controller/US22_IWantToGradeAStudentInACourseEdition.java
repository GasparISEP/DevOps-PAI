
package PAI.controller;

import PAI.domain.*;

import java.util.Optional;

public class US22_IWantToGradeAStudentInACourseEdition {
    GradeStudentRepository _gradeStudentRepository;

    public US22_IWantToGradeAStudentInACourseEdition(GradeStudentRepository gradeStudentRepository){
        _gradeStudentRepository = gradeStudentRepository;
    }

    public Optional<GradeStudent> iWantToGradeAStudent (double grade, String date, Student student, CourseEdition courseEdition, CourseEditionEnrollmentRepository courseEditionEnrollmentRepository){
        Optional<GradeStudent> GradeStudent1 = _gradeStudentRepository.addGradeToStudent(grade,date,student,courseEdition,courseEditionEnrollmentRepository);
        return GradeStudent1;
    }

}


