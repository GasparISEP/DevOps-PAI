package PAI.controllerRest;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("abc123", name);
        AccessMethodResponseDTO responseDTO = new AccessMethodResponseDTO("abc123", name);
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(serviceDTO);
        when(assemblerMock.toResponseDto(serviceDTO)).thenReturn(responseDTO);

        // Act
        ResponseEntity<AccessMethodResponseDTO> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    void shouldReturnBadRequestWhenAccessMethodConfigurationFails() {
        // Arrange
        IAccessMethodService serviceMock = mock(IAccessMethodService.class);
        IAccessMethodAssembler assemblerMock = mock(IAccessMethodAssembler.class);
        AccessMethodRestController controller = new AccessMethodRestController(serviceMock, assemblerMock);

        String name = "Test Access Method";
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("abc123", name);
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);

        when(assemblerMock.toCommand(requestDTO)).thenReturn(command);
        when(serviceMock.configureAccessMethod(command)).thenReturn(serviceDTO);
        when(assemblerMock.toResponseDto(serviceDTO)).thenReturn(null); // Simula falha

        // Act
        ResponseEntity<AccessMethodResponseDTO> response = controller.configureAccessMethod(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

}
