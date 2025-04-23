
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentGradeRepository;

import java.util.Optional;


public class US22_IWantToGradeAStudentInACourseEditionController {
    StudentGradeRepository _StudentGradeRepository;
    ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    IStudentRepository _studentRepository;
    ICourseEditionRepository _courseEditionRepository;

    public US22_IWantToGradeAStudentInACourseEditionController(StudentGradeRepository studentGradeRepository, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, IStudentRepository studentRepository, ICourseEditionRepository courseEditionRepository ){
        if (studentGradeRepository == null || courseEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("Repository cannot be null");
        }
        _StudentGradeRepository = studentGradeRepository;
        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
        _studentRepository=studentRepository;
        _courseEditionRepository = courseEditionRepository;
    }

    public Optional <StudentID> findStudentIdByStudent(Student student){
        return  _studentRepository.findIdByStudent(student);
    }
//    public Optional <CourseEditionID> findCourseEditionIDByCourse(CourseEdition courseEdition_DDD){
//        return _courseEditionRepository.findIdByCourseEdition(courseEdition_DDD);
//    }

//    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEdition courseEdition_DDD){
//        Optional<StudentID> studentID = findStudentIdByStudent(student);
//        if (studentID.isPresent() && findCourseEditionIDByCourse(courseEdition_DDD).isPresent())  {
//            return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID.get(), findCourseEditionIDByCourse(courseEdition_DDD).get());
//        }
//
//        return false;
//    }

//    public boolean registerStudentGrade(Grade grade, Date date, Student student, CourseEdition courseEdition_DDD) throws Exception{
//        Optional<StudentID> studentID = findStudentIdByStudent(student);
//
//        if (isStudentEnrolledInCourseEdition(student, courseEdition_DDD)){
//            _StudentGradeRepository.addGradeToStudent(grade,date,studentID.get(),findCourseEditionIDByCourse(courseEdition_DDD).get());
//            return true;
//        }
//        return false;
//    }

}


