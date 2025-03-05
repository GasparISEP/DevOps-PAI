
package PAI.controller;

import PAI.domain.*;
import PAI.repository.GradeStudentRepository;
import PAI.repository.CourseEditionEnrollmentRepository;

import java.util.Optional;

public class US22_IWantToGradeAStudentInACourseEdition {
    GradeStudentRepository _gradeStudentRepository;
    CourseEditionEnrollmentRepository _courseEditionEnrollmentRepository;

    public US22_IWantToGradeAStudentInACourseEdition(GradeStudentRepository gradeStudentRepository, CourseEditionEnrollmentRepository courseEditionEnrollmentRepository){
        if (gradeStudentRepository == null || courseEditionEnrollmentRepository == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        _gradeStudentRepository = gradeStudentRepository;
        _courseEditionEnrollmentRepository = courseEditionEnrollmentRepository;
    }

    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEdition courseEdition){
       return _courseEditionEnrollmentRepository.isStudentEnrolledInCourseEdition(student,courseEdition);
    }

    public Optional<GradeStudent> iWantToGradeAStudent (double grade, String date, Student student, CourseEdition courseEdition){
        if (isStudentEnrolledInCourseEdition(student, courseEdition)){
            Optional<GradeStudent> GradeStudent1 = _gradeStudentRepository.addGradeToStudent(grade,date,student,courseEdition);
            return GradeStudent1;
        }
        return Optional.empty();
    }

}


