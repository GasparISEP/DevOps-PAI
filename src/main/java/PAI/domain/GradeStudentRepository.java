package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeStudentRepository {
    private List<GradeStudent> gradeStudentList = new ArrayList<>();


    public Optional<GradeStudent> addGradeToStudent (double grade, String date, Student student, CourseEdition courseEdition){
        try {
                GradeStudent gradeStudent = new GradeStudent(grade, date, student,courseEdition);
                gradeStudentList.add(gradeStudent);
                return Optional.of(gradeStudent);
        }
        catch (Exception ex) {
            return Optional.empty(); }
    }

    public Double KnowAverageGrade(CourseEdition courseEdition) {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (GradeStudent gradeStudent: gradeStudentList) {
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

        for (GradeStudent gradeStudent : gradeStudentList) {
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
