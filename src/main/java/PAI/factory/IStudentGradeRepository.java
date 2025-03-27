package PAI.factory;

import PAI.VOs.Grade;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.repository.StudentGradeRepository;

public interface IStudentGradeRepository {
    boolean addGradeToStudent (Grade grade, String date, Student student, CourseEdition courseEdition) throws Exception;
    Double KnowAverageGrade(CourseEdition courseEdition);
    double knowApprovalRate(CourseEdition courseEdition);
}

