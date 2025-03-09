package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;

public class TeacherCareerProgressionFactory implements TeacherCareerProgressionFactoryInterface {

    public TeacherCareerProgression createTeacherCareerProgression(String date, TeacherCategory category, int workingPercentage) throws IllegalArgumentException {

        if (date == null || date.isBlank())
            throw new IllegalArgumentException("Date cannot be empty!");
        else if (category == null)
            throw new IllegalArgumentException("Teacher Category cannot be null");
        else if (workingPercentage < 0 || workingPercentage > 100)
            throw new IllegalArgumentException("Working Percentage must be a value between 0 and 100.");
        else
            return new TeacherCareerProgression(date, category, workingPercentage);
    }
}