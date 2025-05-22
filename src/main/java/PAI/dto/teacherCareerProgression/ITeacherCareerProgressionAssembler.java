package PAI.dto.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

public interface ITeacherCareerProgressionAssembler {
    TeacherCareerProgression toTeacherCareerProgression(TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    TeacherCareerProgressionResponseDTO toTeacherCareerProgressionDTO(TeacherCareerProgression teacherCareerProgression);
    TeacherCareerProgressionID toTeacherCareerProgressionID(TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    Date todate (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    TeacherCategoryID toTeacherCategoryID (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    WorkingPercentage toWorkingPercentage (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
    TeacherID toTeacherID (TeacherCareerProgressionDTO teacherCareerProgressionDTO);
}
