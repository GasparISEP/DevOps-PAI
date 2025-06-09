package PAI.assembler.teacherCareerProgression;

import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;

public interface ITeacherCareerProgressionInternalAssembler {

    UpdateTeacherCategoryDTO toDTO (TeacherCareerProgression teacherCareerProgression);
}
