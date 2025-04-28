package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;

public class StudyPlanMapperImpl implements IStudyPlanMapper {

    private final IStudyPlanIDMapper _studyPlanIDMapper;
    private final IProgrammeIDMapper _programmeIDMapper;
    private final IStudyPlanFactory _studyPlanFactory;

    public StudyPlanMapperImpl(IStudyPlanIDMapper studyPlanIDMapper, IProgrammeIDMapper programmeIDMapper, IStudyPlanFactory studyPlanFactory) throws IllegalArgumentException {
        if (studyPlanIDMapper == null)
            throw new IllegalArgumentException("StudyPlanIDMapper cannot be null");
        _studyPlanIDMapper = studyPlanIDMapper;

        if (programmeIDMapper == null)
            throw new IllegalArgumentException("StudyPlanFactory cannot be null");
        _programmeIDMapper = programmeIDMapper;

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

    public StudyPlan toDomain(StudyPlanDataModel studyPlanDataModel) throws Exception {

        ProgrammeID programmeID = _programmeIDMapper.toDomain(studyPlanDataModel.getStudyPlanIDDataModel().getProgrammeID());

        Date implementationDate = new Date(studyPlanDataModel.getStudyPlanIDDataModel().getImplementationDate());
        DurationInYears durationInYears = new DurationInYears(studyPlanDataModel.getDurationInYears());
        MaxEcts maxEcts = new MaxEcts(studyPlanDataModel.getMaxECTS());

        return _studyPlanFactory.newStudyPlan_2(programmeID, implementationDate, durationInYears, maxEcts);
    }

}
