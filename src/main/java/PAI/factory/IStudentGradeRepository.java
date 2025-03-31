package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.repository.StudentGradeRepository;

public interface IStudentGradeRepository {
    boolean addGradeToStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception;
    Double KnowAverageGrade(CourseEditionID courseEditionID);
    double knowApprovalRate(CourseEditionID courseEditionID);
}

