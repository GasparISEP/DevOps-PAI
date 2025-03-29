package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;

public interface ITeacherCareerProgressionFactory {

    TeacherCareerProgression createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID);

}
