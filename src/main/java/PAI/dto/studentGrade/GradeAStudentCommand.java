package PAI.dto.studentGrade;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentID;


public record GradeAStudentCommand(
    Grade grade,
    Date date,
    StudentID studentID,
    CourseEditionID courseEditionID
) {}
