package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.GradeStudent;
import PAI.factory.GradeStudentListFactoryImpl;
import PAI.domain.Student;
import PAI.factory.GradeStudentFactoryImpl;

import java.util.List;
import java.util.Optional;

public class GradeStudentRepository {
    private final GradeStudentFactoryImpl _gradeStudentFactoryImpl;
    private List<GradeStudent> _gradeStudentList;

    public GradeStudentRepository (GradeStudentFactoryImpl gradeStudentFactoryImpl, GradeStudentListFactoryImpl gradeStudentListFactoryImpl){
        if (gradeStudentFactoryImpl == null){
            throw new IllegalArgumentException("Factory cannot be null!");
        }
        this._gradeStudentFactoryImpl = gradeStudentFactoryImpl;

        if (gradeStudentListFactoryImpl == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _gradeStudentList = gradeStudentListFactoryImpl.newArrayList();
    }


    public Optional<GradeStudent> addGradeToStudent (double grade, String date, Student student, CourseEdition courseEdition){
        try {
                GradeStudent gradeStudent = _gradeStudentFactoryImpl.newGradeStudent(grade,date,student,courseEdition);
                _gradeStudentList.add(gradeStudent);
                return Optional.of(gradeStudent);
        }
        catch (Exception ex) {
            return Optional.empty(); }
    }

    public Double KnowAverageGrade(CourseEdition courseEdition) {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (GradeStudent gradeStudent: _gradeStudentList) {
            if (gradeStudent.hasThisCourseEdition(courseEdition)) {
                double grade = gradeStudent.knowGrade();
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

        for (GradeStudent gradeStudent : _gradeStudentList) {
            if (gradeStudent.hasThisCourseEdition(courseEdition)) {
                totalOfStudents++;
                if (gradeStudent.knowGrade() >= 10) {
                    totalApprovalStudents++;
                }
            }
        }

        if (totalOfStudents == 0) {
            return 0;
        }

        double approvalRate = ((double) totalApprovalStudents / totalOfStudents) * 100;
        return approvalRate;
    }



}
