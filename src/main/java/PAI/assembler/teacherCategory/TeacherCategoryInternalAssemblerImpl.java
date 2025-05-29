package PAI.assembler.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryInternalAssemblerImpl implements ITeacherCategoryInternalAssembler {

    public TeacherCategoryDTO toDTO(TeacherCategory teacherCategory) {

        if (teacherCategory == null) {
            throw new IllegalArgumentException("Teacher Category cannot be null");
        }

        String id = teacherCategory.identity().getValue().toString();
        String name = teacherCategory.getName().getName();

        return new TeacherCategoryDTO(id, name);
    }
}
