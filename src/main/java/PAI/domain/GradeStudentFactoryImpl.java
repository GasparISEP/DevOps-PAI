package PAI.domain;

public interface GradeStudentFactoryImpl {
    GradeStudent newGradeStudent (double grade, String date, Student student, CourseEdition courseEdition) throws Exception;
}
