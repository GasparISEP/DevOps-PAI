
package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.Student;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionEnrolmentRepository;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentRepository;

import java.util.Optional;


public class US22_IWantToGradeAStudentInACourseEdition {
    IStudentGradeRepository _StudentGradeRepository;
    ICourseEditionEnrolmentRepository _courseEditionEnrolmentRepository;
    IStudentRepository _studentRepository;

    public US22_IWantToGradeAStudentInACourseEdition(IStudentGradeRepository studentGradeRepository, ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository, IStudentRepository studentRepository ){
        if (studentGradeRepository == null || courseEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("Repository cannot be null");
        }
        _StudentGradeRepository = studentGradeRepository;
        _courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
        _studentRepository=studentRepository;
    }

    public Optional <StudentID> findStudentIdByStudent(Student student){
        return  _studentRepository.findIdByStudent(student);

    }

    public boolean isStudentEnrolledInCourseEdition (Student student, CourseEditionID courseEditionID){
        Optional<StudentID> studentID = findStudentIdByStudent(student);
        if (studentID.isPresent()) {
            return _courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID.get(), courseEditionID);
        }

        return false;
    }

    public boolean iWantToGradeAStudent (Grade grade, Date date, Student student, CourseEditionID courseEditionID) throws Exception{
        Optional<StudentID> studentID = findStudentIdByStudent(student);

        if (isStudentEnrolledInCourseEdition(student, courseEditionID)){
            _StudentGradeRepository.addGradeToStudent(grade,date,studentID.get(),courseEditionID);
            return true;
        }
        return false;
    }

}


