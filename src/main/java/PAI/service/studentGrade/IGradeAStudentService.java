package PAI.service.studentGrade;

import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;

public interface IGradeAStudentService {

    StudentGrade gradeAStudent (GradeAStudentCommand gradeAStudentCommand) throws Exception;

    Iterable<CourseEdition> findAllCourseEditions ();
}
