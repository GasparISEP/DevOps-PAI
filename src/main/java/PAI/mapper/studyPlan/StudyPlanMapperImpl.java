package PAI.mapper.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudyPlanMapperImpl implements IStudyPlanMapper {

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

        MaxEcts maxECTS = studyPlan.getMaxEcts();
        DurationInYears durationInYears = studyPlan.getDurationInYears();
        UUID uuid = studyPlan.getGeneratedID().getUUID();

        return new StudyPlanDataModel(studyPlanIDDataModel, uuid, maxECTS, durationInYears);
    }

    public StudyPlan toDomain(StudyPlanDataModel studyPlanDataModel) throws Exception {

        StudyPlanID studyPlanID = _studyPlanIDMapper.toDomain(studyPlanDataModel.getStudyPlanIDDataModel());

        Acronym progAcronym = new Acronym(studyPlanDataModel.getStudyPlanIDDataModel().getProgrammeID().getAcronym());

        ProgrammeID programmeID = new ProgrammeID(progAcronym);

        Date implementationDate = new Date(studyPlanDataModel.getStudyPlanIDDataModel().getImplementationDate());

        DurationInYears durationInYears = new DurationInYears(studyPlanDataModel.getDurationInYears());

        MaxEcts maxEcts = new MaxEcts(studyPlanDataModel.getMaxECTS());

        UUID uuid = studyPlanDataModel.getUUID();
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(uuid);

        return _studyPlanFactory.createStudyPlanFromDataModel(programmeID, implementationDate, durationInYears, maxEcts, studyPlanID, generatedID);
    }
}