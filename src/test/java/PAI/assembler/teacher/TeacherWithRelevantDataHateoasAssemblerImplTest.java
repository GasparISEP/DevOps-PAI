package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherWithRelevantDataDTO;
import org.springframework.hateoas.EntityModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherWithRelevantDataHateoasAssemblerImplTest {
    @Test
    void shouldCreateEntityModelWithTeacherWithRelevantDataDTO() {
        // arrange
        TeacherWithRelevantDataDTO dto = mock(TeacherWithRelevantDataDTO.class);
        when(dto.getAcronym()).thenReturn("ABC");
        TeacherWithRelevantDataHateoasAssemblerImpl assembler = new TeacherWithRelevantDataHateoasAssemblerImpl();

        // act
        EntityModel<TeacherWithRelevantDataDTO> result = assembler.toModel(dto);

        // assert
        assertEquals(dto, result.getContent());
        assertTrue(result.hasLink("get-teacher"));
    }

    @Test
    void createdEntityModelShouldNotBeNull() {
        // arrange
        TeacherWithRelevantDataDTO dto = mock(TeacherWithRelevantDataDTO.class);
        when(dto.getAcronym()).thenReturn("ABC");
        TeacherWithRelevantDataHateoasAssemblerImpl assembler = new TeacherWithRelevantDataHateoasAssemblerImpl();

        // act
        EntityModel<TeacherWithRelevantDataDTO> result = assembler.toModel(dto);

        // assert
        assertNotNull(result.getContent());
    }

    @Test
    void nullDTOShouldThrowIllegalArgumentException() {
        // arrange
        TeacherWithRelevantDataHateoasAssemblerImpl assembler = new TeacherWithRelevantDataHateoasAssemblerImpl();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toModel(null));
    }
}