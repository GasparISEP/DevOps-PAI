
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
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

    public boolean isStudentEnrolledInCourseEdition (StudentID student, CourseEditionID courseEditionID){
       return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(student,courseEditionID);
    }

    public boolean iWantToGradeAStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception{
        if (isStudentEnrolledInCourseEdition(student, courseEditionID)){
            _StudentGradeRepository.addGradeToStudent(grade,date,student,courseEditionID);
            return true;
        }
        return false;
    }

}


