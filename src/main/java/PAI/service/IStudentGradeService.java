package PAI.service;

import PAI.VOs.*;
import PAI.domain.StudentGrade;

import java.util.Optional;

public interface IStudentGradeService {
    StudentGrade newStudentGrade (Grade grade, Date date, StudentID studentID, CourseEditionID courseEditionID) throws Exception;
    double knowApprovalRate(CourseEditionID courseEditionID)throws Exception;
    Double getAverageGrade(CourseEditionID courseEditionID) throws Exception;

}
