package PAI.mapper.teacherCareerProgression;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.persistence.datamodel.teacherCareer.TeacherCareerProgressionIDDataModel;
import org.springframework.stereotype.Component;

@Component
public interface ITeacherCareerProgressionIDMapper {

    TeacherCareerProgressionIDDataModel domainToDataModel(TeacherCareerProgressionID teacherCareerProgressionID);

    TeacherCareerProgressionID dataModelToDomain(TeacherCareerProgressionIDDataModel teacherCareerProgressionIDDataModel);
}
