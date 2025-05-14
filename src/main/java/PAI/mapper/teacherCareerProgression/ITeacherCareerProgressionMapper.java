package PAI.mapper.teacherCareerProgression;

import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.persistence.datamodel.teacherCareer.TeacherCareerProgressionDataModel;

public interface ITeacherCareerProgressionMapper {

    TeacherCareerProgression toDomain(TeacherCareerProgressionDataModel tcpDataModel);

    TeacherCareerProgressionDataModel toDataModel(TeacherCareerProgression teacherCareerProgression);
}
