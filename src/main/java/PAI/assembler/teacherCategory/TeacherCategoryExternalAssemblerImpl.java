package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class TeacherCategoryExternalAssemblerImpl implements ITeacherCategoryExternalAssembler {

    public TeacherCategoryResponseDTO toResponseDTO(TeacherCategoryDTO teacherCategoryDTO) {

        if (teacherCategoryDTO == null) {
            throw new IllegalArgumentException("Teacher Category DTO cannot be null.");
        }

        String id = teacherCategoryDTO.id();
        String name = teacherCategoryDTO.name();

        return new TeacherCategoryResponseDTO(id, name);
    }

    @Override
    public Name toNameVO(TeacherCategoryRequestDTO teacherCategoryRequestDTO) {

        if (teacherCategoryRequestDTO == null) {
            throw new IllegalArgumentException("Teacher Category Request DTO cannot be null.");
        }

        return new Name (teacherCategoryRequestDTO.name());
    }

    @Override
    public TeacherCategoryID toTeacherCategoryIDVO(String id) {
        if (id == null){
            throw new IllegalArgumentException("Teacher Category ID cannot be null.");
        }

        return new TeacherCategoryID(UUID.fromString(id));
    }

    @Override
    public TeacherCategoryResponseDTO fromDomainToDTO(TeacherCategory teacherCategory){
        if(teacherCategory == null) throw new IllegalArgumentException("TeacherCategory cannot be null");
        return new TeacherCategoryResponseDTO(teacherCategory.identity().toString(), teacherCategory.getName().getName());

    }

    @Override
    public Iterable<TeacherCategoryResponseDTO> toDTOs(Iterable<TeacherCategory> teacherCategories){
        if (teacherCategories == null) {
            return Collections.emptyList();}

            List<TeacherCategoryResponseDTO> listDto = new ArrayList<>();
            for (TeacherCategory existingTeacherCategory : teacherCategories){
                TeacherCategoryResponseDTO responseDto = fromDomainToDTO(existingTeacherCategory);
                listDto.add(responseDto);
            }
            return listDto;
        }
}

