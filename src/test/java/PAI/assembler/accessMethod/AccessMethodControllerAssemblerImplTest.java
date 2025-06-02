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
        //Arrange

        //Act
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        //Assert
        assertNotNull(assembler);
    }

    @Test
    void shouldCreateCommandFromAccessMethodRequestDTO() {
        // Arrange
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("+23");
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act
        RegisterAccessMethodCommand command = assembler.toCommand(dto);

        // Assert
        assertNotNull(command);
    }

    @Test
    void shouldMapCorrectNameFromAccessMethodRequestDTOToCommand() {
        // Arrange
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("+23");
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act
        RegisterAccessMethodCommand command = assembler.toCommand(dto);

        // Assert
        assertEquals("+23", command.name().getNameWithNumbersAndSpecialChars());
    }


    @Test
    void shouldThrowExceptionWhenAccessMethodRequestDTOIsNull() {
        // Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toCommand(null));
    }

    @Test
    void whenDtoIsProvided_thenCommandShouldBeCreated() {
        // Arrange
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("+23");
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act
        RegisterAccessMethodCommand command = assembler.toCommand(dto);

        // Assert
        assertNotNull(command);
    }

    @Test
    void whenDtoHasName_thenCommandShouldContainSameName() {
        // Arrange
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("+23");
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act
        RegisterAccessMethodCommand command = assembler.toCommand(dto);

        // Assert
        assertEquals("+23", command.name().getNameWithNumbersAndSpecialChars());
    }

    @Test
    void toResponseDto_shouldThrowException_whenServiceDtoIsNull() {
        // Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> assembler.toResponseDto(null));
        assertEquals("AccessMethodServiceDTO cannot be null.", exception.getMessage());
    }

}
