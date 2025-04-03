
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEditionDDD;
import PAI.domain.Student;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepositoryDDD;
import PAI.repository.IStudentRepository;

import java.util.Optional;


public class US22_IWantToGradeAStudentInACourseEdition {
    IStudentGradeRepository _StudentGradeRepository;
    ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    IStudentRepository _studentRepository;
    ICourseEditionRepositoryDDD _courseEditionRepository;

    public US22_IWantToGradeAStudentInACourseEdition(IStudentGradeRepository studentGradeRepository, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, IStudentRepository studentRepository, ICourseEditionRepositoryDDD courseEditionRepository ){
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
    public Optional <CourseEditionID> findCourseEditionIDByCourse(CourseEditionDDD courseEdition_DDD){
        return _courseEditionRepository.findIdByCourseEdition(courseEdition_DDD);
    }

    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEditionDDD courseEdition_DDD){
        Optional<StudentID> studentID = findStudentIdByStudent(student);
        if (studentID.isPresent() && findCourseEditionIDByCourse(courseEdition_DDD).isPresent())  {
            return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID.get(), findCourseEditionIDByCourse(courseEdition_DDD).get());
        }

        return false;
    }

    public boolean registerStudentGrade(Grade grade, Date date, Student student, CourseEditionDDD courseEdition_DDD) throws Exception{
        Optional<StudentID> studentID = findStudentIdByStudent(student);

        if (isStudentEnrolledInCourseEdition(student, courseEdition_DDD)){
            _StudentGradeRepository.addGradeToStudent(grade,date,studentID.get(),findCourseEditionIDByCourse(courseEdition_DDD).get());
            return true;
        }
        return false;
    }

}


