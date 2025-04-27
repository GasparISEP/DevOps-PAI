
package PAI.controller;

import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentGradeRepository;
import PAI.service.IStudentGradeService;


public class US22_IWantToGradeAStudentInACourseEditionController {
    IStudentGradeService studentGradeService;

    public US22_IWantToGradeAStudentInACourseEditionController(IStudentGradeService studentGradeService) {
        if (studentGradeService== null){
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.studentGradeService = studentGradeService;
    }

    //public Optional <StudentID> findStudentIdByStudent(Student student){
        //return  _studentRepository.findIdByStudent(student);
   // }
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


