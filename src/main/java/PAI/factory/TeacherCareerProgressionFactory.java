package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;

public interface TeacherCareerProgressionFactory {

    TeacherCareerProgression createTeacherCareerProgression (String date, TeacherCategory category, int workingPercentage);
}
