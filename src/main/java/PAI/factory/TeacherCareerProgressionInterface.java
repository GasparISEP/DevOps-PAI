package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;

public interface TeacherCareerProgressionInterface {

    TeacherCareerProgression createTeacherCareerProgression (String date, TeacherCategory category, int workingPercentage);
}
