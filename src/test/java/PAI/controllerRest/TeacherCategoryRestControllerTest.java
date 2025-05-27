package PAI.controllerRest;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.assembler.teacherCategory.ITeacherCategoryExternalAssembler;
import PAI.assembler.teacherCategory.ITeacherCategoryHateoasAssembler;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
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
        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryHateoasAssembler doubleTeacherCategoryHateoasAssemblerInterface = mock(ITeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(null, doubleTeacherCategoryAssemblerInterface,
                    doubleTeacherCategoryHateoasAssemblerInterface);
        });

        //assert
        assertEquals("Teacher Category Service Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryHateoasIsNull() {
        //arrange
        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface, null);
        });

        //assert
        assertEquals("Teacher Category Hateoas Assembler Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryAssemblerIsNull() {
        //arrange
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        ITeacherCategoryHateoasAssembler doubleTeacherCategoryHateoasAssemblerInterface = mock(ITeacherCategoryHateoasAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryRestController(doubleTeacherCategoryServiceInterface, null,
                    doubleTeacherCategoryHateoasAssemblerInterface);
        });

        //assert
        assertEquals("Teacher Category Assembler Interface cannot be null.", exception.getMessage());
    }

    //testing configureTeacherCategory method

    @Test
    void shouldReturnAnCreateStatusCodeWhenIsPossibleToConfigureATeacherCategory() throws Exception {
        //arrange
        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        ITeacherCategoryHateoasAssembler doubleTeacherCategoryHateoasAssemblerInterface = mock(ITeacherCategoryHateoasAssembler.class);
        TeacherCategoryRestController controller = new TeacherCategoryRestController
                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface, doubleTeacherCategoryHateoasAssemblerInterface);

        TeacherCategoryRequestDTO doubleTeacherCategoryRequestDTO = mock (TeacherCategoryRequestDTO.class);
        TeacherCategoryResponseDTO doubleTeacherCategoryResponseDTO = mock(TeacherCategoryResponseDTO.class);
        TeacherCategoryDTO doubleTeacherCategoryDTO = mock (TeacherCategoryDTO.class);

        Name doubleName = mock (Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        when(doubleTeacherCategoryAssemblerInterface.toNameVO(doubleTeacherCategoryRequestDTO)).thenReturn(doubleName);
        when (doubleTeacherCategoryServiceInterface.configureTeacherCategory(doubleName)).thenReturn(doubleTeacherCategoryDTO);
        when(doubleTeacherCategoryAssemblerInterface.toResponseDTO(doubleTeacherCategoryDTO)).thenReturn(doubleTeacherCategoryResponseDTO);

        //act
        ResponseEntity<Object> result = controller.configureTeacherCategory (doubleTeacherCategoryRequestDTO);

        //assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    // testing getTeacherCategoriesByID method

    @Test
    void shouldReturnAnTeacherCategoryResponseDTOByID () throws Exception {
        //arrange
        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
        ITeacherCategoryHateoasAssembler doubleTeacherCategoryHateoasAssemblerInterface = mock(ITeacherCategoryHateoasAssembler.class);
        TeacherCategoryRestController controller = new TeacherCategoryRestController
                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface, doubleTeacherCategoryHateoasAssemblerInterface);

        String id = "123e4567-e89b-12d3-a456-426614174000";
        TeacherCategoryID doubleTeacherCategoryID = mock (TeacherCategoryID.class);
        TeacherCategoryDTO doubleTeacherCategoryDTO = mock (TeacherCategoryDTO.class);

        when(doubleTeacherCategoryServiceInterface.getTeacherCategoryByID(doubleTeacherCategoryID)).thenReturn(doubleTeacherCategoryDTO);

        //act
        ResponseEntity<Object> result = controller.getTeacherCategoryById(id);

        //assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

//    @Test
//    void shouldReturnAnExceptionIfInputIsInvalid (){
//        //arrange
//        ITeacherCategoryExternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryExternalAssembler.class);
//        ITeacherCategoryService doubleTeacherCategoryServiceInterface = mock (ITeacherCategoryService.class);
//        ITeacherCategoryHateoasAssembler doubleTeacherCategoryHateoasAssemblerInterface = mock(ITeacherCategoryHateoasAssembler.class);
//        TeacherCategoryRestController controller = new TeacherCategoryRestController
//                (doubleTeacherCategoryServiceInterface, doubleTeacherCategoryAssemblerInterface, doubleTeacherCategoryHateoasAssemblerInterface);
//
//        //act
//        ResponseEntity<Object> result = controller.getTeacherCategoryById(null);
//
//        //assert
//        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
//    }

}