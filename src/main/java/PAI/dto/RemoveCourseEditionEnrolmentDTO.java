package PAI.dto;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;

public record RemoveCourseEditionEnrolmentDTO(
        CourseEditionID courseEditionID,
        StudentID studentID
) {}
