package PAI.factory;

import PAI.domain.StudyPlan_2;

import java.util.ArrayList;
import java.util.List;


public class StudyPlanListFactoryImpl_2 implements IStudyPlanListFactory_2 {

    public List<StudyPlan_2> newArrayList() {
        return new ArrayList<>();
    }
}