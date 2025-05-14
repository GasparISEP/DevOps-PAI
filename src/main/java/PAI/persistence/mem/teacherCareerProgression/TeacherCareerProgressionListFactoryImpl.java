package PAI.persistence.mem.teacherCareerProgression;

import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

import java.util.ArrayList;
import java.util.List;

public class TeacherCareerProgressionListFactoryImpl implements ITeacherCareerProgressionListFactory {

    public List<TeacherCareerProgression> createTeacherCareerProgressionList() { return new ArrayList<>(); }
}
