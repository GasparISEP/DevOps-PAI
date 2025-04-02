
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition_2;
import PAI.domain.Student;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentRepository;

import java.util.Optional;


public class US22_IWantToGradeAStudentInACourseEdition {
    IStudentGradeRepository _StudentGradeRepository;
    ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    IStudentRepository _studentRepository;
    ICourseEditionRepository _courseEditionRepository;

    public US22_IWantToGradeAStudentInACourseEdition(IStudentGradeRepository studentGradeRepository, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, IStudentRepository studentRepository, ICourseEditionRepository courseEditionRepository ){
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
    public Optional <CourseEditionID> findCourseEditionIDByCourse(CourseEdition_2 courseEdition_2){
        return _courseEditionRepository.findIdByCourseEdition(courseEdition_2);
    }

    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEdition_2 courseEdition_2){
        Optional<StudentID> studentID = findStudentIdByStudent(student);
        if (studentID.isPresent() && findCourseEditionIDByCourse(courseEdition_2).isPresent())  {
            return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID.get(), findCourseEditionIDByCourse(courseEdition_2).get());
        }

        return false;
    }

    public boolean iWantToGradeAStudent (Grade grade, Date date, Student student, CourseEdition_2 courseEdition_2) throws Exception{
        Optional<StudentID> studentID = findStudentIdByStudent(student);

        if (isStudentEnrolledInCourseEdition(student, courseEdition_2)){
            _StudentGradeRepository.addGradeToStudent(grade,date,studentID.get(),findCourseEditionIDByCourse(courseEdition_2).get());
            return true;
        }
        return false;
    }

}


