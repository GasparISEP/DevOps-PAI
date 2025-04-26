package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.springframework.stereotype.Component;

@Component
public interface ITeacherCareerProgressionIDMapper {

    TeacherCareerProgressionIDDataModel domainToDataModel(TeacherCareerProgressionID teacherCareerProgressionID);

    TeacherCareerProgressionID dataModelToDomain(TeacherCareerProgressionIDDataModel teacherCareerProgressionIDDataModel);
}
