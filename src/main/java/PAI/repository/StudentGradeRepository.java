package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.factory.StudentGradeFactory;
import PAI.factory.StudentGradeListFactory;
import PAI.domain.Student;

import java.util.List;
import java.util.Optional;

public class StudentGradeRepository {
    private final StudentGradeFactory _StudentGradeFactory;
    private List<StudentGrade> _StudentGradeList;

    public StudentGradeRepository(StudentGradeFactory studentGradeFactory, StudentGradeListFactory studentGradeListFactory){
        if (studentGradeFactory == null){
            throw new IllegalArgumentException("Factory cannot be null!");
        }
        this._StudentGradeFactory = studentGradeFactory;

        if (studentGradeListFactory == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _StudentGradeList = studentGradeListFactory.newArrayList();
    }


    public boolean addGradeToStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception{
        if (!hasStudentAlreadyGradeAtThisCourseEdition(student,courseEdition)){
            StudentGrade studentGrade = _StudentGradeFactory.newGradeStudent(grade,date,student,courseEdition);
            _StudentGradeList.add(studentGrade);
            return true;
        }
        return false;
    }

    private boolean hasStudentAlreadyGradeAtThisCourseEdition (Student student, CourseEdition courseEdition){
        for ( StudentGrade existingGradeStudent : _StudentGradeList){
            if ( existingGradeStudent.hasThisStudent(student) && existingGradeStudent.hasThisCourseEdition(courseEdition)) return true;
            }
        return false;
        }


    public Double KnowAverageGrade(CourseEdition courseEdition) {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (StudentGrade studentGrade : _StudentGradeList) {
            if (studentGrade.hasThisCourseEdition(courseEdition)) {
                double grade = studentGrade.knowGrade();
                sumGrade += grade;
                numOfStudent++;
            }
        }
        if (numOfStudent == 0) {
            return null;
        }
        return sumGrade/numOfStudent;
    }


    public double knowApprovalRate(CourseEdition courseEdition) {
        int totalApprovalStudents = 0;
        int totalOfStudents = 0;

        for (StudentGrade studentGrade : _StudentGradeList) {
            if (studentGrade.hasThisCourseEdition(courseEdition)) {
                totalOfStudents++;
                if (studentGrade.knowGrade() >= 10) {
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
