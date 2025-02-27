package PAI.domain;

public class TeacherCareerProgressionFactory {

    public TeacherCareerProgression createTeacherCareerProgression (String date, TeacherCategory category, int workingPercentage) throws IllegalArgumentException {
        return new TeacherCareerProgression(date,category,workingPercentage);
    }
}
