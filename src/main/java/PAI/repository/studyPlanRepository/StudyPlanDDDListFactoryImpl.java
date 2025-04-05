package PAI.repository.studyPlanRepository;

import PAI.domain.studyPlan.StudyPlan;

import java.util.ArrayList;
import java.util.List;


public class StudyPlanDDDListFactoryImpl implements IStudyPlanDDDListFactory {

    public List<StudyPlan> newArrayList() {
        return new ArrayList<>();
    }

    
}