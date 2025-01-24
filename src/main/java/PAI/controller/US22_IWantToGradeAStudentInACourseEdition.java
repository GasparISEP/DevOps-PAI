package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudent;
import PAI.domain.GradeStudentRepository;
import PAI.domain.Student;

import java.util.Optional;

public class US22_IWantToGradeAStudentInACourseEdition {
    GradeStudentRepository _gradeStudentRepository;

    public US22_IWantToGradeAStudentInACourseEdition(GradeStudentRepository gradeStudentRepository){
        _gradeStudentRepository = gradeStudentRepository;
    }

    public Optional<GradeStudent> iWantToGradeAStudent (double grade, String date, Student student, CourseEdition courseEdition){
        Optional<GradeStudent> GradeStudent1 = _gradeStudentRepository.addGradeToStudent(grade,date,student,courseEdition);
        return GradeStudent1;
    }

}
