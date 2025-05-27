package PAI.assembler.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;

public interface ITeacherCategoryInternalAssembler {

    TeacherCategoryDTO toDTO(TeacherCategory teacherCategory);
}
