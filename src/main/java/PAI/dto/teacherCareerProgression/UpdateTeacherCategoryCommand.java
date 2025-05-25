package PAI.dto.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;

public record UpdateTeacherCategoryCommand(
    Date date,
        TeacherID teacherID,
        TeacherCategoryID teacherCategoryID
    ){
public UpdateTeacherCategoryCommand{
    if (date == null){
        throw new IllegalArgumentException("Date cannot be null");
    }
    if (teacherID == null){
        throw new IllegalArgumentException("TeacherID cannot be null");
    }
    if(teacherCategoryID == null){
        throw new IllegalArgumentException("CategoryID cannot be null");
    }
}
}
