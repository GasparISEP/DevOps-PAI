package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.factory.StudentGradeFactory;
import PAI.factory.GradeStudentListFactory;
import PAI.domain.Student;

import java.util.List;
import java.util.Optional;

public class GradeStudentRepository {
    private final StudentGradeFactory _StudentGradeFactory;
    private List<StudentGrade> _StudentGradeList;

    public GradeStudentRepository (StudentGradeFactory studentGradeFactory, GradeStudentListFactory gradeStudentListFactory){
        if (studentGradeFactory == null){
            throw new IllegalArgumentException("Factory cannot be null!");
        }
        this._StudentGradeFactory = studentGradeFactory;

        if (gradeStudentListFactory == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _StudentGradeList = gradeStudentListFactory.newArrayList();
    }


    public Optional<StudentGrade> addGradeToStudent (double grade, String date, Student student, CourseEdition courseEdition){
        try {
                StudentGrade studentGrade = _StudentGradeFactory.newGradeStudent(grade,date,student,courseEdition);
                _StudentGradeList.add(studentGrade);
                return Optional.of(studentGrade);
        }
        catch (Exception ex) {
            return Optional.empty(); }
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
