package PAI.controllerRest;

import PAI.VOs.DegreeTypeID;
import PAI.assembler.degreeType.DegreeTypeHATEOASAssembler;
import PAI.assembler.degreeType.IDegreeTypeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.DegreeTypeDTO;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.dto.degreeType.RegisterDegreeTypeRequest;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class DegreeTypeRestControllerTest {

    @Mock
    private IDegreeTypeRegistrationService degreeTypeService;

    @Mock
    private IDegreeTypeAssembler degreeTypeAssembler;

    @InjectMocks
    private DegreeTypeRestController controller;

    @Mock
    private DegreeTypeHATEOASAssembler hateoasAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCreatedDTOIfSuccessful() throws Exception {
        // Arrange
        RegisterDegreeTypeRequest request = mock(RegisterDegreeTypeRequest.class);
        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);
        DegreeType degreeType = mock(DegreeType.class);
        DegreeTypeDTO dto = mock(DegreeTypeDTO.class);

        EntityModel<DegreeTypeDTO> entityModel = EntityModel.of(dto,
                linkTo(methodOn(DegreeTypeRestController.class).getDegreeTypeById("some-id")).withSelfRel()
        );

        when(degreeTypeAssembler.toRegisterDegreeTypeCommand(request)).thenReturn(command);
        when(degreeTypeService.createAndSaveDegreeType(command)).thenReturn(degreeType);
        when(degreeTypeAssembler.toDTO(degreeType)).thenReturn(dto);

        when(hateoasAssembler.toModel(dto)).thenReturn(entityModel);

        // Act
        ResponseEntity<?> response = controller.registerDegreeType(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(entityModel, response.getBody());
    }


    @Test
    void shouldReturnConflictWhenBusinessRuleViolation() throws Exception {
        RegisterDegreeTypeRequest request = mock(RegisterDegreeTypeRequest.class);
        RegisterDegreeTypeCommand command = mock(RegisterDegreeTypeCommand.class);

        when(degreeTypeAssembler.toRegisterDegreeTypeCommand(request)).thenReturn(command);
        when(degreeTypeService.createAndSaveDegreeType(command))
                .thenThrow(new BusinessRuleViolationException("DegreeType already exists"));

        ResponseEntity<?> response = controller.registerDegreeType(request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("DegreeType already exists", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgument() {
        RegisterDegreeTypeRequest request = mock(RegisterDegreeTypeRequest.class);

        when(degreeTypeAssembler.toRegisterDegreeTypeCommand(request))
                .thenThrow(new IllegalArgumentException("Invalid Name"));

        ResponseEntity<?> response = controller.registerDegreeType(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Name", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedException() {
        RegisterDegreeTypeRequest request = mock(RegisterDegreeTypeRequest.class);

        when(degreeTypeAssembler.toRegisterDegreeTypeCommand(request))
                .thenThrow(new RuntimeException("Unexpected error occurred"));

        ResponseEntity<?> response = controller.registerDegreeType(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnListOfDegreeTypeDTOs() {
        // Arrange
        DegreeType type1 = mock(DegreeType.class);
        DegreeType type2 = mock(DegreeType.class);
        DegreeTypeDTO dto1 = mock(DegreeTypeDTO.class);
        DegreeTypeDTO dto2 = mock(DegreeTypeDTO.class);

        EntityModel<DegreeTypeDTO> model1 = EntityModel.of(dto1);
        EntityModel<DegreeTypeDTO> model2 = EntityModel.of(dto2);

        List<DegreeType> degreeTypes = List.of(type1, type2);

        when(degreeTypeService.getAllDegreeTypes()).thenReturn(degreeTypes);
        when(degreeTypeAssembler.toDTO(type1)).thenReturn(dto1);
        when(degreeTypeAssembler.toDTO(type2)).thenReturn(dto2);
        when(hateoasAssembler.toModel(dto1)).thenReturn(model1);
        when(hateoasAssembler.toModel(dto2)).thenReturn(model2);

        // Act
        ResponseEntity<?> response = controller.getAllDegreeTypes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CollectionModel<?> collectionModel = (CollectionModel<?>) response.getBody();
        assertNotNull(collectionModel);

        // Verifica se os modelos estão presentes
        assertTrue(collectionModel.getContent().containsAll(List.of(model1, model2)));
    }




    @Test
    void shouldReturnEmptyListIfNoDegreeTypesExist() {
        // Arrange
        when(degreeTypeService.getAllDegreeTypes()).thenReturn(List.of());

        // Act
        ResponseEntity<?> response = controller.getAllDegreeTypes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CollectionModel<?> body = (CollectionModel<?>) response.getBody();
        assertNotNull(body);
        assertTrue(body.getContent().isEmpty());
    }


    @Test
    void shouldReturnBadRequestIfIllegalArgumentThrownOnGetAll() {
        when(degreeTypeService.getAllDegreeTypes()).thenThrow(new IllegalArgumentException("Invalid request"));

        ResponseEntity<?> response = controller.getAllDegreeTypes();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorIfUnexpectedErrorOnGetAll() {
        when(degreeTypeService.getAllDegreeTypes()).thenThrow(new RuntimeException("Unexpected"));

        ResponseEntity<?> response = controller.getAllDegreeTypes();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnDegreeTypeDTOWhenFound() {
        // Arrange
        String id = "some-id";
        DegreeType degreeType = mock(DegreeType.class);
        DegreeTypeDTO dto = mock(DegreeTypeDTO.class);
        EntityModel<DegreeTypeDTO> entityModel = EntityModel.of(dto);

        when(degreeTypeService.getDegreeTypeById(any(DegreeTypeID.class))).thenReturn(Optional.of(degreeType));
        when(degreeTypeAssembler.toDTO(degreeType)).thenReturn(dto);
        when(dto.id()).thenReturn(id);
        when(hateoasAssembler.toModel(dto)).thenReturn(entityModel);

        // Act
        ResponseEntity<?> response = controller.getDegreeTypeById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, ((EntityModel<?>)response.getBody()).getContent());
    }


    @Test
    void shouldReturnNotFoundWhenDegreeTypeDoesNotExist() {
        // Arrange
        String id = "nonexistent-id";
        DegreeTypeID degreeTypeID = new DegreeTypeID(id);

        when(degreeTypeService.getDegreeTypeById(degreeTypeID)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = controller.getDegreeTypeById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("DegreeType not found", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentThrown() {
        // Arrange
        String id = "invalid-id";

        when(degreeTypeService.getDegreeTypeById(any())).thenThrow(new IllegalArgumentException("Invalid ID"));

        // Act
        ResponseEntity<?> response = controller.getDegreeTypeById(id);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid ID", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionThrown() {
        // Arrange
        String id = "any-id";

        when(degreeTypeService.getDegreeTypeById(any())).thenThrow(new RuntimeException("Unexpected error occurred"));

        // Act
        ResponseEntity<?> response = controller.getDegreeTypeById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }
}
