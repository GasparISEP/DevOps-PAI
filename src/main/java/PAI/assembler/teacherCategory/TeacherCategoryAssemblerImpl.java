package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryAssemblerImpl implements ITeacherCategoryAssembler {

    public TeacherCategoryResponseDTO toDTO (TeacherCategory teacherCategory) {

        if (teacherCategory == null) {
            throw new IllegalArgumentException("Teacher Category cannot be null");
        }

        String id = teacherCategory.getId().getValue().toString();
        String name = teacherCategory.getName().getName();

        return new TeacherCategoryResponseDTO(id, name);
    }

    @Override
    public Name toVO(TeacherCategoryRequestDTO teacherCategoryRequestDTO) {

        if (teacherCategoryRequestDTO == null) {
            throw new IllegalArgumentException("Teacher Category Request DTO cannot be null");
        }

        return new Name (teacherCategoryRequestDTO.name());
    }
}

