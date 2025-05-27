package PAI.assembler.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryExternalAssemblerImplTest {

    //testing method toResponseDTO

    @Test
    void shouldReturnATeacherCategoryResponseDTO() {
        //arrange
        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant Professor");

        TeacherCategoryID doubleID = mock(TeacherCategoryID.class);
        when (doubleID.getValue()).thenReturn(UUID.randomUUID());

        TeacherCategoryDTO doubleTeacherCategoryDTO = mock (TeacherCategoryDTO.class);

        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        //act
        TeacherCategoryResponseDTO result = teacherCategoryAssembler.toResponseDTO(doubleTeacherCategoryDTO);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryIsNull() {
        // arrange
        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toResponseDTO(null);
        });

        // assert
        assertEquals("Teacher Category DTO cannot be null.", exception.getMessage());
    }

    //testing method toNameVO

    @Test
    void shouldReturnANameVO() {
        //arrange
        TeacherCategoryRequestDTO doublleTeacherCategoryRequestDTO = mock(TeacherCategoryRequestDTO.class);
        when (doublleTeacherCategoryRequestDTO.name()).thenReturn("Assistant Professor");

        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        //act
        Name result = teacherCategoryAssembler.toNameVO(doublleTeacherCategoryRequestDTO);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAnExceptionWhenTeacherCategoryRequestDTOIsNull() {
        // arrange
        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toNameVO(null);
        });

        // assert
        assertEquals("Teacher Category Request DTO cannot be null.", exception.getMessage());
    }

    // testing toTeacherCategoryIDVO method

    @Test
    void shouldReturnATeacherCategoryID (){
        //arrange
        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();
        String id = "123e4567-e89b-12d3-a456-426614174000";
        UUID uuid = UUID.fromString(id);

        TeacherCategoryID doubleTeacherCategoryID = mock(TeacherCategoryID.class);
        when(doubleTeacherCategoryID.getValue()).thenReturn(uuid);

        //act
        TeacherCategoryID result = teacherCategoryAssembler.toTeacherCategoryIDVO(id);

        //assert
        assertEquals(result.getValue().toString(), id);
    }

    @Test
    void shouldReturnAnExceptionIfInputIsNull (){
        // arrange
        TeacherCategoryExternalAssemblerImpl teacherCategoryAssembler = new TeacherCategoryExternalAssemblerImpl();

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teacherCategoryAssembler.toTeacherCategoryIDVO(null);
        });

        // assert
        assertEquals("Teacher Category ID cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnATeacherCareerResponseDTO(){
        //arrange
        TeacherCategoryExternalAssemblerImpl assembler = new TeacherCategoryExternalAssemblerImpl();
        TeacherCategory teacherCategory = mock(TeacherCategory.class);
        Name teacherCategoryName = mock(Name.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);

        when(teacherCategory.getName()).thenReturn(teacherCategoryName);
        when(teacherCategoryName.getName()).thenReturn("Assistant Professor");

        when(teacherCategory.identity()).thenReturn(teacherCategoryID);
        when(teacherCategoryID.toString()).thenReturn("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85");

        //act
        TeacherCategoryResponseDTO result = assembler.fromDomainToDTO(teacherCategory);

        //assert
        assertNotNull(result);
        assertEquals("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85", result.name());
        assertEquals("Assistant Professor", result.id());
    }

    @Test
    void shouldReturnExceptionWhenTeacherCategoryIsNull(){
        //arrange
        TeacherCategoryExternalAssemblerImpl assembler = new TeacherCategoryExternalAssemblerImpl();
        //assert
        assertThrows(IllegalArgumentException.class, () -> assembler.fromDomainToDTO(null));
    }

    @Test
    void shouldConvertListOfTeachersToDTOs(){
        //arrange
        TeacherCategoryExternalAssemblerImpl assembler = new TeacherCategoryExternalAssemblerImpl();
        TeacherCategory teacherCategory = mock(TeacherCategory.class);
        Name teacherCategoryName = mock(Name.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);

        when(teacherCategory.getName()).thenReturn(teacherCategoryName);
        when(teacherCategoryName.getName()).thenReturn("Assistant Professor");

        when(teacherCategory.identity()).thenReturn(teacherCategoryID);
        when(teacherCategoryID.toString()).thenReturn("3f7bfe9a-d0e7-4b18-9b42-4b0a3f3e0c85");
        Iterable<TeacherCategory> teacherCategories = List.of(teacherCategory);
        //act
        Iterable<TeacherCategoryResponseDTO> teacherCategoryDTOs = assembler.toDTOs(teacherCategories);
        //assert
        assertNotNull(teacherCategoryDTOs);
        assertTrue(teacherCategoryDTOs.iterator().hasNext());
    }
    @Test
    void shouldReturnAnEmptyListWhenTeacherCategoryIsNull(){
        TeacherCategoryExternalAssemblerImpl assembler = new TeacherCategoryExternalAssemblerImpl();
        //act
        Iterable<TeacherCategoryResponseDTO> teacherCategoryResponseDTOS = assembler.toDTOs(null);
        assertFalse(teacherCategoryResponseDTOS.iterator().hasNext());
    }
}