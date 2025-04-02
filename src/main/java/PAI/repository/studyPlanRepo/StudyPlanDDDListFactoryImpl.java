package PAI.repository.studyPlanRepo;

import PAI.domain.studyPlan.StudyPlanDDD;

import java.util.ArrayList;
import java.util.List;


public class StudyPlanDDDListFactoryImpl implements IStudyPlanDDDListFactory {

    public List<StudyPlanDDD> newArrayList() {
        return new ArrayList<>();
    }

    
}