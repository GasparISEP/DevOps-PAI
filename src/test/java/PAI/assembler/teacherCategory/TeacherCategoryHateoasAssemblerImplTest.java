package PAI.assembler.teacherCategory;

import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCategoryHateoasAssemblerImplTest {

    @Test
    void shouldReturnAnEntityModel () {
        //arrange
        TeacherCategoryHateoasAssemblerImpl hateoas = new TeacherCategoryHateoasAssemblerImpl();

        TeacherCategoryResponseDTO doubleResponseDTO = mock (TeacherCategoryResponseDTO.class);

        //act
        EntityModel<TeacherCategoryResponseDTO> result = hateoas.toModel(doubleResponseDTO);

        //assert
        assertNotNull(result);
    }

}