package PAI.service;

import PAI.VOs.*;
import PAI.domain.StudentGrade;

import java.util.Optional;

public interface IStudentGradeService {
    boolean addGradeToStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID);
    Optional<StudentGradeID> findIdByStudent (StudentGrade studentGrade);
    double knowApprovalRate(CourseEditionID courseEditionID);
    Double getAverageGrade(CourseEditionID courseEditionID);

}
