package PAI.mapper.studyPlanID;

import PAI.VOs.Date;
import PAI.VOs.StudyPlanID;
import PAI.mapper.ProgrammeIDMapper;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;

public class StudyPlanIDMapperImpl implements IStudyPlanIDMapper{

    public StudyPlanIDDataModel toDataModel(StudyPlanID studyPlanID) {
        ProgrammeIDMapper programmeIDMapper = new ProgrammeIDMapper();
        return new StudyPlanIDDataModel(programmeIDMapper.toData(studyPlanID.getProgrammeID()), studyPlanID.getLocalDate());
    }

    public StudyPlanID toDomain(StudyPlanIDDataModel studyPlanIDDataModel) {
        ProgrammeIDMapper programmeIDMapper = new ProgrammeIDMapper();
        return new StudyPlanID(programmeIDMapper.toDomain(studyPlanIDDataModel.getProgrammeID()), new Date(studyPlanIDDataModel.getImplementationDate()));
    }
}