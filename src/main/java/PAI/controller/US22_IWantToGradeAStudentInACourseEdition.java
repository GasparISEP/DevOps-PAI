
package PAI.controller;

import PAI.domain.*;
import PAI.repository.StudentGradeRepository;
import PAI.repository.CourseEditionEnrolmentRepository;

import java.util.Optional;

public class US22_IWantToGradeAStudentInACourseEdition {
    StudentGradeRepository _StudentGradeRepository;
    CourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;

    public US22_IWantToGradeAStudentInACourseEdition(StudentGradeRepository studentGradeRepository, CourseEditionEnrolmentRepository courseEditionEnrolmentRepository){
        if (studentGradeRepository == null || courseEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("Repository cannot be null");
        }
        _StudentGradeRepository = studentGradeRepository;
        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
    }

    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEdition courseEdition){
       return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student,courseEdition);
    }

    public boolean iWantToGradeAStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception{
        if (isStudentEnrolledInCourseEdition(student, courseEdition)){
            _StudentGradeRepository.addGradeToStudent(grade,date,student,courseEdition);
            return true;
        }
        return false;
    }

}


