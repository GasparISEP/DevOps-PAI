package PAI.dto.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import static PAI.utils.ValidationUtils.validateNotNull;

public record UpdateTeacherCategoryCommand(
        Date date,
        TeacherID teacherID,
        TeacherCategoryID teacherCategoryID
    ){
public UpdateTeacherCategoryCommand{
    validateNotNull (date, "Date");
    validateNotNull (teacherID, "Teacher ID");
    validateNotNull (teacherCategoryID, "Teacher Category ID");
}
}
