package PAI.factory;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.CourseEdition;
import PAI.domain.Student;
import PAI.domain.StudentGrade;
import PAI.repository.StudentGradeRepository;

public interface IStudentGradeRepository extends IRepository<StudentGradeID, StudentGrade> {
    boolean addGradeToStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception;
    Double KnowAverageGrade(CourseEditionID courseEditionID);
    double knowApprovalRate(CourseEditionID courseEditionID);
}

