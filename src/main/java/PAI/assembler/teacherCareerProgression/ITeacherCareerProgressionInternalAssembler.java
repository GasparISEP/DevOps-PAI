package PAI.assembler.teacherCareerProgression;

import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;

import java.util.List;

public interface ITeacherCareerProgressionInternalAssembler {

    UpdateTeacherCategoryDTO toDTO (TeacherCareerProgression teacherCareerProgression);

    List<UpdateTeacherCategoryDTO> toDTOList (Iterable<TeacherCareerProgression> teacherCareerProgressions);
}
