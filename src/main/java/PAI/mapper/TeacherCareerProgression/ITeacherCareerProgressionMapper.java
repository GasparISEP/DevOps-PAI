package PAI.mapper.TeacherCareerProgression;

import PAI.domain.TeacherCareerProgression;
import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;

public interface ITeacherCareerProgressionMapper {

    TeacherCareerProgression toDomain(TeacherCareerProgressionDataModel tcpDataModel);

    TeacherCareerProgressionDataModel toDataModel(TeacherCareerProgression teacherCareerProgression);
}
