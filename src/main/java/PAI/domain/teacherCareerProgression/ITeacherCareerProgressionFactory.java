package PAI.domain.teacherCareerProgression;

import PAI.VOs.*;

public interface ITeacherCareerProgressionFactory {

    TeacherCareerProgression createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID);

    TeacherCareerProgression createTeacherCareerProgressionFromDataModel(TeacherCareerProgressionID teacherCareerProgressionId, Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID);
}
