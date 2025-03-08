package PAI.factory;

import PAI.domain.TeacherCareerProgression;

import java.util.ArrayList;
import java.util.List;

public class TeacherCareerProgressionListFactory implements TeacherCareerProgressionListFactoryInterface {

    public List<TeacherCareerProgression> createTeacherCareerProgressionList() { return new ArrayList<>(); }
}
