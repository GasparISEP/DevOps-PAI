package PAI.assembler.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.service.accessMethod.AccessMethodServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodAssemblerImplTest {

    @Test
    void shouldCreateAccessMethodAssembler() {
        // Arrange
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act + Assert
        assertNotNull(assembler);
    }

    @Test
    void shouldConvertAccessMethodRequestDTOToCommand() {
        // Arrange
        AccessMethodRequestDTO dto = new AccessMethodRequestDTO("+23");
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act
        RegisterAccessMethodCommand command = assembler.toCommand(dto);

        // Assert
        assertNotNull(command);
        assertEquals("+23", command.name().getnameWithNumbersAndSpecialChars());
    }

    @Test
    void shouldThrowExceptionWhenAccessMethodRequestDTOIsNull() {
        // Arrange
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toCommand(null);
        });
    }

    @Test
    void toDto_shouldConvertAccessMethodToServiceDTO() {
        // Arrange
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodID mockId = mock(AccessMethodID.class);
        when(mockId.toString()).thenReturn("uuid-123");
        when(accessMethod.identity()).thenReturn(mockId);

        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars("Access123");
        when(accessMethod.getAccessMethodName()).thenReturn(nameVO);

        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act
        AccessMethodServiceDTO dto = assembler.toDto(accessMethod);

        // Assert
        assertNotNull(dto);
        assertEquals("uuid-123", dto.id());
        assertEquals("Access123", dto.name());
    }

    @Test
    void toDto_shouldThrowException_whenAccessMethodIsNull() {
        // Arrange
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toDto(null);
        });

        assertEquals("AccessMethod cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccessMethodIsNull() {
        // Arrange
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toDto(null);
        });
    }

    @Test
    void toResponseDto_shouldConvertServiceDtoToResponseDto() {
        // Arrange
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("uuid-123", "AccessName");
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act
        AccessMethodResponseDTO responseDTO = assembler.toResponseDto(serviceDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("uuid-123", responseDTO.id());
        assertEquals("AccessName", responseDTO.name());
    }

    @Test
    void toResponseDto_shouldThrowException_whenServiceDtoIsNull() {
        // Arrange
        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDto(null);
        });

        assertEquals("AccessMethodServiceDTO cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAccessMethodOptionalIfRegisterSuccessfully(){
        // arrange
        IAccessMethodFactory iAccessMethodFactory = mock(IAccessMethodFactory.class);
        IRepositoryAccessMethod iRepositoryAccessMethod = mock(IRepositoryAccessMethod.class);
        IAccessMethodAssembler iAccessMethodAssembler = mock(IAccessMethodAssembler.class);
        AccessMethodServiceImpl accessMethodServiceImpl = new AccessMethodServiceImpl(iAccessMethodFactory,
                iRepositoryAccessMethod, iAccessMethodAssembler);
        String accessMethodName = "M23";
        NameWithNumbersAndSpecialChars vo = new NameWithNumbersAndSpecialChars(accessMethodName);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(vo);
        AccessMethodResponseDTO responseDTO = mock(AccessMethodResponseDTO.class);
        AccessMethodServiceDTO expectedDto = mock(AccessMethodServiceDTO.class);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iRepositoryAccessMethod.getAccessMethodByName(eq(vo))).thenReturn(Optional.empty());
        when(iAccessMethodFactory.createAccessMethod(eq(vo))).thenReturn(accessMethod);
        when(iRepositoryAccessMethod.saveAccessMethod(eq(accessMethod))).thenReturn(Optional.of(accessMethod));
        when(iAccessMethodAssembler.toDto(eq(accessMethod))).thenReturn(expectedDto);

        // act
        AccessMethodServiceDTO result = accessMethodServiceImpl.configureAccessMethod(command);

        // assert
        assertEquals(expectedDto, result);
    }

}
