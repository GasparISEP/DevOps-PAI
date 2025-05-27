package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;

public interface ITeacherCategoryExternalAssembler {

    TeacherCategoryResponseDTO toResponseDTO(TeacherCategoryDTO teacherCategoryDTO);

    Name toVO (TeacherCategoryRequestDTO teacherCategoryRequestDTO);

    Iterable<TeacherCategoryResponseDTO> toDTOs(Iterable<TeacherCategory> teacherCategories);
}
