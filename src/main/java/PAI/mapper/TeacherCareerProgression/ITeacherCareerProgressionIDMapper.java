package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;

public interface ITeacherCareerProgressionIDMapper {

    TeacherCareerProgressionIDDataModel domainToDataModel(TeacherCareerProgressionID teacherCareerProgressionID);

    TeacherCareerProgressionID dataModelToDomain(TeacherCareerProgressionIDDataModel teacherCareerProgressionIDDataModel);
}
