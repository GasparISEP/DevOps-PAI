package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEdition_2;
import PAI.domain.Student;
import PAI.repository.StudentGradeRepository;

public interface IStudentGradeRepository {
    boolean addGradeToStudent (Grade grade, Date date, StudentID student, CourseEdition_2 courseEdition) throws Exception;
    Double KnowAverageGrade(CourseEdition_2 courseEdition);
    double knowApprovalRate(CourseEdition_2 courseEdition);
}

