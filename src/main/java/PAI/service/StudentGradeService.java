package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeService {

    private final IStudentGradeFactory studentGradeFactory;
    private final IStudentGradeRepository studentGradeRepository;

    public StudentGradeService(IStudentGradeFactory studentGradeFactory, IStudentGradeRepository studentGradeRepository) {
        if (studentGradeFactory == null) {
            throw new IllegalArgumentException("Student Grade Factory cannot be null");
        }
        this.studentGradeFactory = studentGradeFactory;

        if (studentGradeRepository == null) {
            throw new IllegalArgumentException("Student Grade Repository cannot be null");
        }
        this.studentGradeRepository = studentGradeRepository;
    }

    public StudentGrade newStudentGrade (Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID) throws Exception {
        StudentGrade studentGrade =  studentGradeFactory.newGradeStudent(grade,date,studentID,courseEditionID);
        return studentGradeRepository.save(studentGrade);
    }


}
