package PAI.domain;

public class GradeStudentFactory {

    public GradeStudent newGradeStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception {
        return new GradeStudent(grade, date, student, courseEdition);
    }
}
