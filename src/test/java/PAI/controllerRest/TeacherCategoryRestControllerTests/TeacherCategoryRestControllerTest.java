package PAI.controllerRest.TeacherCategoryRestControllerTests;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.assembler.teacherCategory.*;
import PAI.controllerRest.TeacherCategoryRestController;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.dto.teacherCategory.TeacherCategoryRequestDTO;
import PAI.dto.teacherCategory.TeacherCategoryResponseDTO;

import PAI.exception.AlreadyRegisteredException;
import PAI.service.teacherCategory.ITeacherCategoryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TeacherCategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITeacherCategoryService serviceInterface;

    @MockBean
    private ITeacherCategoryExternalAssembler externalAssemblerInterface;

    @MockBean
    private ITeacherCategoryHateoasAssembler hateoasAssemblerInterface;


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

    @Test
    void shouldReturnAConflictStatusCodeTeacherCategoryAlreadyExists() throws Exception {
        // arrange
        String validRequestBody = "{\"name\": \"Assistant\"}";

        Name doubleName = mock(Name.class);
        when(doubleName.getName()).thenReturn("Assistant");

        when(externalAssemblerInterface.toNameVO(any(TeacherCategoryRequestDTO.class))).thenReturn(doubleName);

        when(serviceInterface.configureTeacherCategory(doubleName))
                .thenThrow(new AlreadyRegisteredException("Teacher Category already exists!"));

        MvcResult result = mockMvc.perform(post("/teacher-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestBody)
        ).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.CONFLICT.value(), statusCode);
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

    @Test
    void shouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        //arrange
        String id = "nonexistent-id";
        TeacherCategoryID mockId = mock(TeacherCategoryID.class);

        when(externalAssemblerInterface.toTeacherCategoryIDVO(id)).thenReturn(mockId);
        when(serviceInterface.getTeacherCategoryByID(mockId))
                .thenThrow(new PAI.exception.NotFoundException("Teacher Category ID was not found."));

        MvcResult result = mockMvc.perform(get("/teacher-categories/" + id)).andReturn();

        // act
        int statusCode = result.getResponse().getStatus();

        // assert
        assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    // testing getAllTeacherCategories method

    @Test
    void shouldReturnListOfCategoriesDTOs() {
        //arrange
        ITeacherCategoryExternalAssembler assembler = mock(ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService service = mock(ITeacherCategoryService.class);
        ITeacherCategoryHateoasAssembler hateoasAssembler = mock(ITeacherCategoryHateoasAssembler.class);

        TeacherCategoryRestController controller = new TeacherCategoryRestController(service, assembler, hateoasAssembler);

        TeacherCategory teacherCategory1 = mock(TeacherCategory.class);
        TeacherCategory teacherCategory2 = mock(TeacherCategory.class);
        TeacherCategory teacherCategory3 = mock(TeacherCategory.class);

        TeacherCategoryResponseDTO teacherCategoryResponseDTO1 = mock(TeacherCategoryResponseDTO.class);
        TeacherCategoryResponseDTO teacherCategoryResponseDTO2 = mock(TeacherCategoryResponseDTO.class);
        TeacherCategoryResponseDTO teacherCategoryResponseDTO3 = mock(TeacherCategoryResponseDTO.class);


        List<TeacherCategory> teacherCategoryList = List.of(teacherCategory1, teacherCategory2, teacherCategory3);
        List<TeacherCategoryResponseDTO> teacherCategoryResponseDTOList = List.of(teacherCategoryResponseDTO1, teacherCategoryResponseDTO2, teacherCategoryResponseDTO3);


        when(service.getAllTeacherCategories()).thenReturn(teacherCategoryList);
        when(assembler.toDTOs(teacherCategoryList)).thenReturn(teacherCategoryResponseDTOList);

        //act

        ResponseEntity<?> response = controller.getAllTeacherCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherCategoryResponseDTOList, response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionThrown() {
        // arrange
        ITeacherCategoryExternalAssembler assembler = mock(ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService service = mock(ITeacherCategoryService.class);
        ITeacherCategoryHateoasAssembler hateoasAssembler = mock(ITeacherCategoryHateoasAssembler.class);

        TeacherCategoryRestController controller = new TeacherCategoryRestController(service, assembler, hateoasAssembler);

        when(service.getAllTeacherCategories()).thenThrow(new IllegalArgumentException("Invalid input"));

        // act
        ResponseEntity<?> response = controller.getAllTeacherCategories();

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnhandledExceptionThrown() {
        // arrange
        ITeacherCategoryExternalAssembler assembler = mock(ITeacherCategoryExternalAssembler.class);
        ITeacherCategoryService service = mock(ITeacherCategoryService.class);
        ITeacherCategoryHateoasAssembler hateoasAssembler = mock(ITeacherCategoryHateoasAssembler.class);

        TeacherCategoryRestController controller = new TeacherCategoryRestController(service, assembler, hateoasAssembler);

        when(service.getAllTeacherCategories()).thenThrow(new RuntimeException("Something went wrong"));

        // act
        ResponseEntity<?> response = controller.getAllTeacherCategories();

        // assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }

}