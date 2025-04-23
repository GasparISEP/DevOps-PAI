package PAI.service;

import PAI.VOs.*;
import PAI.domain.StudentGrade;

import java.util.Optional;

public interface IStudentGradeService {
    StudentGrade newStudentGrade (Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID);
    double knowApprovalRate(CourseEditionID courseEditionID);
    Double getAverageGrade(CourseEditionID courseEditionID);

}
