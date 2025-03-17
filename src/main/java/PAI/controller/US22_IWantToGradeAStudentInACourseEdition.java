
package PAI.controller;

import PAI.domain.*;
import PAI.repository.GradeStudentRepository;
import PAI.repository.CourseEditionEnrolmentRepository;

import java.util.Optional;

public class US22_IWantToGradeAStudentInACourseEdition {
    GradeStudentRepository _gradeStudentRepository;
    CourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;

    public US22_IWantToGradeAStudentInACourseEdition(GradeStudentRepository gradeStudentRepository, CourseEditionEnrolmentRepository courseEditionEnrolmentRepository){
        if (gradeStudentRepository == null || courseEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("Repository cannot be null");
        }
        _gradeStudentRepository = gradeStudentRepository;
        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }

    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEdition courseEdition){
       return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student,courseEdition);
    }

    public Optional<GradeStudent> iWantToGradeAStudent (double grade, String date, Student student, CourseEdition courseEdition){
        if (isStudentEnrolledInCourseEdition(student, courseEdition)){
            Optional<GradeStudent> GradeStudent1 = _gradeStudentRepository.addGradeToStudent(grade,date,student,courseEdition);
            return GradeStudent1;
        }
        return Optional.empty();
    }

}


