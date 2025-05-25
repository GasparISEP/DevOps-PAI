package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.assembler.teacherCategory.ITeacherCategoryAssembler;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;
import PAI.service.teacherCategory.ITeacherCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCategoryRestControllerTest {

    // testing constructor method

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryServiceIsNull() {
        //arrange
        ITeacherCategoryAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(null, doubleTeacherCategoryAssemblerInterface);
        });

        //assert
        assertEquals("Teacher Category Service Interface cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryAssemblerIsNull() {
        //arrange
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(doubleTeacherCategoryServiceInterface, null);
        });

        //assert
        assertEquals("Teacher Category Assembler Interface cannot be null!", exception.getMessage());
    }

    //testing configureTeacherCategory method

    @Test
    void shouldReturnAnCreateStatusCodeWhenIsPossibleToConfigureATeacherCategory() throws Exception {
        //arrange
        ITeacherCategoryAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        TeacherCategoryRestController controller = new TeacherCategoryRestController
                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface);

        TeacherCategoryRequestDTO doubleTeacherCategoryRequestDTO = mock (TeacherCategoryRequestDTO.class);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);
        TeacherCategoryResponseDTO doubleTeacherCategoryResponseDTO = mock(TeacherCategoryResponseDTO.class);

        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        when(doubleTeacherCategoryAssemblerInterface.toVO(doubleTeacherCategoryRequestDTO)).thenReturn(doubleName);
        when (doubleTeacherCategoryServiceInterface.configureTeacherCategory(doubleName)).thenReturn(doubleTeacherCategory);
        when(doubleTeacherCategoryAssemblerInterface.toDTO(doubleTeacherCategory)).thenReturn(doubleTeacherCategoryResponseDTO);

        //act
        ResponseEntity<Object> result = controller.configureTeacherCategory (doubleTeacherCategoryRequestDTO);

        //assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void shouldReturnAnBadStatusCodeWhenIsNotPossibleToConfigureATeacherCategory() throws Exception {
        //arrange
        ITeacherCategoryAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        TeacherCategoryRestController controller = new TeacherCategoryRestController
                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface);

        TeacherCategoryRequestDTO doubleTeacherCategoryRequestDTO = mock (TeacherCategoryRequestDTO.class);

        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("");

        when(doubleTeacherCategoryAssemblerInterface.toVO(doubleTeacherCategoryRequestDTO)).thenReturn(doubleName);
        when (doubleTeacherCategoryServiceInterface.configureTeacherCategory(doubleName)).thenThrow();

        //act
        ResponseEntity<Object> result = controller.configureTeacherCategory (doubleTeacherCategoryRequestDTO);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void shouldReturnAnBadStatusCodeWhenRequestDTOIsNotValid() {
        //arrange
        ITeacherCategoryAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        TeacherCategoryRestController controller = new TeacherCategoryRestController
                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface);

        TeacherCategoryRequestDTO doubleTeacherCategoryRequestDTO = mock (TeacherCategoryRequestDTO.class);

        when(doubleTeacherCategoryAssemblerInterface.toVO(doubleTeacherCategoryRequestDTO)).thenThrow();

        //act
        ResponseEntity<Object> result = controller.configureTeacherCategory (doubleTeacherCategoryRequestDTO);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}