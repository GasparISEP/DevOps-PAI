package PAI.assembler.accessMethod;

import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;

import org.junit.jupiter.api.Test;


import java.util.List;

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


    @Test
    void toResponseDtoList_shouldConvertListCorrectly() {
        //Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        List<AccessMethodServiceDTO> serviceDTOs = List.of(
                new AccessMethodServiceDTO("id1", "Name1"),
                new AccessMethodServiceDTO("id2", "Name2")
        );

        //Act
        List<AccessMethodResponseDTO> responseDTOs = assembler.toResponseDtoList(serviceDTOs);

        //Assert
        assertEquals(serviceDTOs.size(), responseDTOs.size());
        assertEquals("id1", responseDTOs.get(0).id());
        assertEquals("Name1", responseDTOs.get(0).name());
        assertEquals("id2", responseDTOs.get(1).id());
        assertEquals("Name2", responseDTOs.get(1).name());
    }

    @Test
    void toResponseDtoList_shouldThrowExceptionWhenNull() {
        //Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        //Act e Assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toResponseDtoList(null));
    }

    @Test
    void toResponseDtoList_shouldNotReturnNull() {
        //Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        List<AccessMethodServiceDTO> serviceDTOs = List.of(
                new AccessMethodServiceDTO("id1", "Name1"),
                new AccessMethodServiceDTO("id2", "Name2")
        );

        //Act
        List<AccessMethodResponseDTO> responseDTOs = assembler.toResponseDtoList(serviceDTOs);

        //Assert
        assertNotNull(responseDTOs);
    }

}
