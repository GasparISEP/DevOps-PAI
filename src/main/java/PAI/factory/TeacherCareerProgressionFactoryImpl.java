package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;

public class TeacherCareerProgressionFactoryImpl implements ITeacherCareerProgressionFactory {

    public TeacherCareerProgression createTeacherCareerProgression(String date, TeacherCategory category, int workingPercentage) throws IllegalArgumentException {

        return new TeacherCareerProgression(date, category, workingPercentage);
    }
}