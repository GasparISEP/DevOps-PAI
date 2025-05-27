package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;


class AccessMethodControllerAssemblerImplTest {

    @Test
    void shouldCreateAccessMethodAssembler() {
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();
        assertNotNull(assembler);
    }

    @Test
    void shouldConvertAccessMethodRequestDTOToCommand() {
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("+23");
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        RegisterAccessMethodCommand command = assembler.toCommand(dto);

        assertNotNull(command);
        assertEquals("+23", command.name().getnameWithNumbersAndSpecialChars());
    }

    @Test
    void shouldThrowExceptionWhenAccessMethodRequestDTOIsNull() {
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();
        assertThrows(IllegalArgumentException.class, () -> assembler.toCommand(null));
    }

    @Test
    void toResponseDto_shouldConvertServiceDtoToResponseDto() {
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("uuid-123", "AccessName");
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        AccessMethodResponseDTO responseDTO = assembler.toResponseDto(serviceDTO);

        assertNotNull(responseDTO);
        assertEquals("uuid-123", responseDTO.id());
        assertEquals("AccessName", responseDTO.name());
    }

    @Test
    void toResponseDto_shouldThrowException_whenServiceDtoIsNull() {
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> assembler.toResponseDto(null));
        assertEquals("AccessMethodServiceDTO cannot be null.", exception.getMessage());
    }

}
