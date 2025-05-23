package PAI.controllerRest;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DegreeTypeRestControllerTest {

    @Mock
    private IDegreeTypeRegistrationService degreeTypeService;

    @Mock
    private IDegreeTypeAssembler degreeTypeAssembler;

    @InjectMocks
    private DegreeTypeRestController controller;

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

        when(degreeTypeAssembler.toRegisterDegreeTypeCommand(request)).thenReturn(command);
        when(degreeTypeService.createAndSaveDegreeType(command)).thenReturn(degreeType);
        when(degreeTypeAssembler.toDTO(degreeType)).thenReturn(dto);

        // Act
        ResponseEntity<?> response = controller.registerDegreeType(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
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
        DegreeType type1 = mock(DegreeType.class);
        DegreeType type2 = mock(DegreeType.class);
        DegreeTypeDTO dto1 = mock(DegreeTypeDTO.class);
        DegreeTypeDTO dto2 = mock(DegreeTypeDTO.class);

        List<DegreeType> degreeTypes = List.of(type1, type2);
        List<DegreeTypeDTO> degreeTypeDTOs = List.of(dto1, dto2);

        when(degreeTypeService.getAllDegreeTypes()).thenReturn(degreeTypes);
        when(degreeTypeAssembler.toDTOs(degreeTypes)).thenReturn(degreeTypeDTOs);

        ResponseEntity<?> response = controller.getAllDegreeTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(degreeTypeDTOs, response.getBody());
    }


    @Test
    void shouldReturnEmptyListIfNoDegreeTypesExist() {
        when(degreeTypeService.getAllDegreeTypes()).thenReturn(List.of());
        when(degreeTypeAssembler.toDTOs(List.of())).thenReturn(List.of());

        ResponseEntity<?> response = controller.getAllDegreeTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((List<?>) response.getBody()).isEmpty());
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


}
