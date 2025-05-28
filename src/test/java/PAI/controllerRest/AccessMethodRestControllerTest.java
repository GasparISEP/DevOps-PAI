package PAI.controllerRest;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodControllerAssembler;
import PAI.assembler.accessMethod.IAccessMethodHateoasAssembler;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRestControllerTest {

    @Test
    void shouldCreateAccessMethodRestController() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodControllerAssembler assemblerMock = mock(IAccessMethodControllerAssembler.class);
        IAccessMethodHateoasAssembler hateoasAssembler = mock(IAccessMethodHateoasAssembler.class);

        // Act
        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock, hateoasAssembler);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnCreatedWhenAccessMethodConfiguredSuccessfully() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodControllerAssembler assemblerMock = mock(IAccessMethodControllerAssembler.class);
        IAccessMethodHateoasAssembler hateoasAssembler = mock(IAccessMethodHateoasAssembler.class);

        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock, hateoasAssembler);

        String name = "Test Access Method";
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("abc123", name);
        AccessMethodResponseDTO responseDTO = new AccessMethodResponseDTO("abc123", name);
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);
        EntityModel<AccessMethodResponseDTO> entityModel = EntityModel.of(responseDTO);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(serviceDTO);
        when(assemblerMock.toResponseDto(serviceDTO)).thenReturn(responseDTO);
        when(hateoasAssembler.toModel(responseDTO)).thenReturn(entityModel);

        // Act
        ResponseEntity<EntityModel<AccessMethodResponseDTO>> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    void shouldReturnBadRequestWhenAccessMethodConfigurationFails() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodControllerAssembler assemblerMock = mock(IAccessMethodControllerAssembler.class);
        IAccessMethodHateoasAssembler hateoasAssembler = mock(IAccessMethodHateoasAssembler.class);

        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock, hateoasAssembler);

        String name = "Test Access Method";
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("abc123", name);
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(serviceDTO);
        when(assemblerMock.toResponseDto(serviceDTO)).thenReturn(null); // Simula falha

        // Act
        ResponseEntity<EntityModel<AccessMethodResponseDTO>> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void shouldReturnAccessMethodWhenIdExists() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodControllerAssembler assemblerMock = mock(IAccessMethodControllerAssembler.class);
        IAccessMethodHateoasAssembler hateoasAssemblerMock = mock(IAccessMethodHateoasAssembler.class);

        String id = "abc123";
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO(id, "Test Name");
        AccessMethodResponseDTO responseDTO = new AccessMethodResponseDTO(id, "Test Name");
        EntityModel<AccessMethodResponseDTO> entityModel = EntityModel.of(responseDTO);

        when(serviceMock.getAccessMethodById(id)).thenReturn(serviceDTO);
        when(assemblerMock.toResponseDto(serviceDTO)).thenReturn(responseDTO);
        when(hateoasAssemblerMock.toModel(responseDTO)).thenReturn(entityModel);

        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock, hateoasAssemblerMock);

        // Act
        ResponseEntity<EntityModel<AccessMethodResponseDTO>> response = controller.getAccessMethodById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getContent().id());
    }

    @Test
    void shouldReturnNotFoundWhenIdDoesNotExist() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodControllerAssembler assemblerMock = mock(IAccessMethodControllerAssembler.class);
        IAccessMethodHateoasAssembler hateoasAssemblerMock = mock(IAccessMethodHateoasAssembler.class);

        String id = "nonexistent-id";

        when(serviceMock.getAccessMethodById(id)).thenReturn(null);

        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock, hateoasAssemblerMock);

        // Act
        ResponseEntity<EntityModel<AccessMethodResponseDTO>> response = controller.getAccessMethodById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
