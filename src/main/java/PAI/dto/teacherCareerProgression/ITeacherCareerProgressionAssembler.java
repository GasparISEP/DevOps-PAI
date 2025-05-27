package PAI.dto.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import org.hibernate.sql.Update;

public interface ITeacherCareerProgressionAssembler {
    UpdateTeacherWorkingPercentageResponseDTO toUpdateWorkingPercentageDTO(TeacherCareerProgression teacherCareerProgression);
    UpdateTeacherWorkingPercentageCommand toUpdateTeacherWorkingPercentageCommand(UpdateTeacherWorkingPercentageRequestDTO request);
    UpdateTeacherCategoryResponseDTO toUpdateCategoryDTO(TeacherCareerProgression teacherCareerProgression);
    UpdateTeacherCategoryCommand toUpdateTeacherCategoryCommand(UpdateTeacherCategoryRequestDTO request) ;
}
