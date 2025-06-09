package PAI.assembler.teacherCareerProgression;

import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.*;

import java.util.List;

public interface ITeacherCareerProgressionAssembler {

    UpdateTeacherWorkingPercentageResponseDTO toUpdateWorkingPercentageDTO(TeacherCareerProgression teacherCareerProgression);

    UpdateTeacherWorkingPercentageCommand toUpdateTeacherWorkingPercentageCommand(String teacherIDStr, UpdateTeacherWorkingPercentageRequestDTO request);

    UpdateTeacherCategoryResponseDTO toUpdateTeacherCategoryResponseDTO(UpdateTeacherCategoryDTO updateTeacherCategoryDTO);

    UpdateTeacherCategoryCommand toUpdateTeacherCategoryCommand(String teacherId, UpdateTeacherCategoryRequestDTO request);

    List<UpdateTeacherCategoryResponseDTO> toResponseDTOs(List<UpdateTeacherCategoryDTO> teacherCareerProgressionDTOs);
}