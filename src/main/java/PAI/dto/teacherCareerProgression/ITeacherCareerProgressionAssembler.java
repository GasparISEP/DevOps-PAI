package PAI.dto.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

public interface ITeacherCareerProgressionAssembler {
    TeacherWorkingPercentageUpdateDTO toTeacherWorkingPercentageUpdateDTO(TeacherCareerProgression teacherCareerProgression);
    TeacherCareerProgressionID toTeacherCareerProgressionID(TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    Date todate (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    TeacherCategoryID toTeacherCategoryID (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    WorkingPercentage toWorkingPercentage (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    TeacherID toTeacherID (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    TeacherCategoryUpdateResponseDTO UpdateCategoryToDTO (TeacherCareerProgression teacherCareerProgression);
}
