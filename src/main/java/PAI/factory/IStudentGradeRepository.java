package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.repository.StudentGradeRepository;

public interface IStudentGradeRepository {
    boolean addGradeToStudent (Grade grade, Date date, StudentID student, CourseEdition courseEdition) throws Exception;
    Double KnowAverageGrade(CourseEdition courseEdition);
    double knowApprovalRate(CourseEdition courseEdition);
}

