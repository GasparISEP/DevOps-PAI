package PAI.dto.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;

public record CourseDTOCommand (

    CourseID courseId,
    Name name,
    Acronym acronym
) {}