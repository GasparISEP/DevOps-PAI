package PAI.assembler.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;

public interface ITeacherCategoryAssembler {

    TeacherCategoryResponseDTO toDTO (TeacherCategory teacherCategory);
}
