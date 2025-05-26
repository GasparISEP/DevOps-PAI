package PAI.controllerRest;

import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.service.accessMethod.IAccessMethodService;
import PAI.utils.ServiceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRestControllerTest {

    @Test
    void shouldCreateAccessMethodRestController() {
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodAssembler assemblerMock = mock(IAccessMethodAssembler.class);

        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock);

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

        ServiceResponse<AccessMethodResponseDTO> serviceResponse =
                ServiceResponse.success(responseDTO);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(serviceResponse);

        // Act
        ResponseEntity<ServiceResponse<AccessMethodResponseDTO>> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("abc123", response.getBody().getObject().id());
        assertTrue(response.getBody().isSuccess());
        assertEquals(0, response.getBody().getMessages().size());
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

        ServiceResponse<AccessMethodResponseDTO> failedResponse =
                ServiceResponse.failure("Access method already exists");

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(failedResponse);

        // Act
        ResponseEntity<ServiceResponse<AccessMethodResponseDTO>> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getObject());
        assertFalse(response.getBody().isSuccess());
        assertEquals(1, response.getBody().getMessages().size());
    }

}
