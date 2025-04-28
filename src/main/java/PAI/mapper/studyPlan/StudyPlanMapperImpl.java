package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;

public class StudyPlanMapperImpl {

    private final IStudyPlanIDMapper _studyPlanIDMapper;
    private final IStudyPlanFactory _studyPlanFactory;

    public StudyPlanMapperImpl(IStudyPlanIDMapper studyPlanIDMapper,IStudyPlanFactory studyPlanFactory) throws IllegalArgumentException {
        if (studyPlanIDMapper == null)
            throw new IllegalArgumentException("StudyPlanIDMapper cannot be null");
        _studyPlanIDMapper = studyPlanIDMapper;

        if (studyPlanFactory == null)
            throw new IllegalArgumentException("StudyPlanFactory cannot be null");
        _studyPlanFactory = studyPlanFactory;
    }

    public StudyPlanDataModel toDataModel(StudyPlan studyPlan) {

        StudyPlanIDDataModel studyPlanIDDataModel = _studyPlanIDMapper.toDataModel(studyPlan.identity());

        MaxEcts maxECTS = studyPlan.getQuantityOfEcts();
        DurationInYears durationInYears = studyPlan.getDurationInYears();

        return new StudyPlanDataModel(studyPlanIDDataModel, maxECTS, durationInYears);
    }



}
