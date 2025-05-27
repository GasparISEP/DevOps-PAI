package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;

public interface ITeacherCategoryExternalAssembler {

    TeacherCategoryResponseDTO toResponseDTO(TeacherCategoryDTO teacherCategoryDTO);

    Name toNameVO(TeacherCategoryRequestDTO teacherCategoryRequestDTO);

    TeacherCategoryID toTeacherCategoryIDVO(String id);

    Iterable<TeacherCategoryResponseDTO> toDTOs(Iterable<TeacherCategory> teacherCategories);
}
