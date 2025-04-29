package PAI.mapper.studyPlanID;

import PAI.VOs.Date;
import PAI.VOs.StudyPlanID;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;

public class StudyPlanIDMapperImpl implements IStudyPlanIDMapper{

    private final IProgrammeIDMapper _programmeIDMapper;

    public StudyPlanIDMapperImpl(IProgrammeIDMapper programmeIDMapper) {
        if (programmeIDMapper == null)
                throw new IllegalArgumentException("ProgrammeIDMapper cannot be null");
        _programmeIDMapper = programmeIDMapper;
    }

    public StudyPlanIDDataModel toDataModel(StudyPlanID studyPlanID) {
            return new StudyPlanIDDataModel(_programmeIDMapper.toData(studyPlanID.getProgrammeID()), studyPlanID.getLocalDate());
    }

    public StudyPlanID toDomain(StudyPlanIDDataModel studyPlanIDDataModel) {
        return new StudyPlanID(_programmeIDMapper.toDomain(studyPlanIDDataModel.getProgrammeID()), new Date(studyPlanIDDataModel.getImplementationDate()));
    }
}