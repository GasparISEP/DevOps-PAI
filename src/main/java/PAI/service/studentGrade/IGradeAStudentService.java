package PAI.service.studentGrade;

import PAI.VOs.CourseEditionID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;

public interface IGradeAStudentService {

    StudentGrade gradeAStudent (GradeAStudentCommand gradeAStudentCommand) throws Exception;

    Iterable<CourseEdition> findAllCourseEditions ();

    double knowApprovalRate (CourseEditionID courseEditionID) throws Exception;

    double getAverageGrade(CourseEditionID courseEditionID) throws Exception;
}
