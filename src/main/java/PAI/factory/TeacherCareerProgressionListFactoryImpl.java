package PAI.factory;

import PAI.domain.TeacherCareerProgression;

import java.util.ArrayList;
import java.util.List;

public class TeacherCareerProgressionListFactoryImpl implements TeacherCareerProgressionListFactory {

    public List<TeacherCareerProgression> createTeacherCareerProgressionList() { return new ArrayList<>(); }
}
