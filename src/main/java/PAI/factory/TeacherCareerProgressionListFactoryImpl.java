package PAI.factory;

import PAI.domain.TeacherCareerProgression;

import java.util.ArrayList;
import java.util.List;

public class TeacherCareerProgressionListFactoryImpl implements ITeacherCareerProgressionListFactory {

    public List<TeacherCareerProgression> createTeacherCareerProgressionList() { return new ArrayList<>(); }
}
