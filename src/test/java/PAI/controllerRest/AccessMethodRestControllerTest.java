package PAI.controllerRest;

import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRestControllerTest {

    @Test
    void shouldCreateAccessMethodRestController() {
        // Arrange
        IAccessMethodService accessMethodServiceMock = mock(IAccessMethodService.class);
        IAccessMethodAssembler accessMethodAssemblerMock = mock(IAccessMethodAssembler.class);

        // Act
        AccessMethodRestController controller = new AccessMethodRestController(accessMethodServiceMock, accessMethodAssemblerMock);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnCreatedWhenAccessMethodConfiguredSuccessfully() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodAssembler assemblerMock = mock(IAccessMethodAssembler.class);
        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock);
        String name = "Test Access Method";
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(name);
        AccessMethodResponseDTO responseDTO = new AccessMethodResponseDTO("abc123", name);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(Optional.of(responseDTO));

        // Act
        ResponseEntity<AccessMethodResponseDTO> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getHeaders().getLocation()); // não tem Location
        assertEquals("abc123", response.getBody().id());
    }

    @Test
    void shouldReturnBadRequestWhenAccessMethodConfigurationFails() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodAssembler assemblerMock = mock(IAccessMethodAssembler.class);
        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock);

        String name = "Test Access Method";
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(name);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<AccessMethodResponseDTO> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

}
