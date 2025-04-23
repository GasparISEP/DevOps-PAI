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

    public Double getAverageGrade(CourseEditionID courseEditionID) throws Exception {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (StudentGrade studentGrade : studentGradeRepository.findAll()) {
            if (studentGrade.hasThisCourseEditionID(courseEditionID)) {
                Grade grade1 = studentGrade.get_grade();
                double grade = grade1.knowGrade();
                sumGrade += grade;
                numOfStudent++;
            }
        }
        if (numOfStudent == 0) {
            return null;
        }
        return sumGrade / numOfStudent;
    }


    public double knowApprovalRate(CourseEditionID courseEditionID) throws Exception {
        int totalApprovalStudents = 0;
        int totalOfStudents = 0;

        for (StudentGrade studentGrade : studentGradeRepository.findAll()) {
            if (studentGrade.hasThisCourseEditionID(courseEditionID)) {
                totalOfStudents++;
                Grade grade1 = studentGrade.get_grade();
                if (grade1.knowGrade() >= 10) {
                    totalApprovalStudents++;
                }
            }
        }

        if (totalOfStudents == 0) {
            return 0.0;
        }

        double approvalRate = ((double) totalApprovalStudents / totalOfStudents) * 100;
        return approvalRate;
    }

}
