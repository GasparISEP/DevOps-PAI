package PAI.assembler.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;

public class TeacherCategoryAssemblerImpl implements ITeacherCategoryAssembler {

    public TeacherCategoryResponseDTO toDTO (TeacherCategory teacherCategory) {

        String id = teacherCategory.getId().getValue().toString();
        String name = teacherCategory.getName().getName();

        return new TeacherCategoryResponseDTO(id, name);
    }
}
