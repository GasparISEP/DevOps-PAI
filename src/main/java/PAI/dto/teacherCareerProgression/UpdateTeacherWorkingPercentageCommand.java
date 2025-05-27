package PAI.dto.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;

public record UpdateTeacherWorkingPercentageCommand(
        Date date,
        TeacherID teacherID,
        WorkingPercentage workingPercentage
){
    public UpdateTeacherWorkingPercentageCommand{
        if (date == null){
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (teacherID == null){
            throw new IllegalArgumentException("TeacherID cannot be null");
        }
        if(workingPercentage == null){
            throw new IllegalArgumentException("CategoryID cannot be null");
        }
    }
}
