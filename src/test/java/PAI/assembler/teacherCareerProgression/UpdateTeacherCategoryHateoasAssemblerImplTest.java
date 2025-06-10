package PAI.assembler.teacherCareerProgression;

import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UpdateTeacherCategoryHateoasAssemblerImplTest {

    @Test
    void shouldReturnAnEntityModelForUpdateTeacherCategoryResponseDTO () throws Exception {
        // arrange
        UpdateTeacherCategoryHateoasAssemblerImpl hateoasAssembler = new UpdateTeacherCategoryHateoasAssemblerImpl();

        UpdateTeacherCategoryResponseDTO doubleUpdateTeacherCategoryResponseDTO = mock(UpdateTeacherCategoryResponseDTO.class);

        // act
        EntityModel<UpdateTeacherCategoryResponseDTO> result = hateoasAssembler.toModel(doubleUpdateTeacherCategoryResponseDTO);

        // assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenUpdateTeacherCategoryResponseDTOIsNull () {
        // arrange
        UpdateTeacherCategoryHateoasAssemblerImpl hateoasAssembler = new UpdateTeacherCategoryHateoasAssemblerImpl();

        // act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hateoasAssembler.toModel(null);
        });

        // assert
        assertNotNull(exception.getMessage());
    }

}