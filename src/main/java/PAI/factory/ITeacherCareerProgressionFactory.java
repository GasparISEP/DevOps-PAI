package PAI.factory;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import org.springframework.stereotype.Component;

@Component
public interface ITeacherCareerProgressionFactory {

    TeacherCareerProgression createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID);

    TeacherCareerProgression createTeacherCareerProgressionFromDataModel(TeacherCareerProgressionID teacherCareerProgressionId, Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID);
}
