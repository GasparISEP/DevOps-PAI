package PAI.assembler.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodServiceAssemblerImplTest {

    @Test
    void toDto_shouldReturnNonNullDto() {
        // Arrange
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodID mockId = mock(AccessMethodID.class);
        when(mockId.toString()).thenReturn("uuid-123");
        when(accessMethod.identity()).thenReturn(mockId);

        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars("Access123");
        when(accessMethod.getAccessMethodName()).thenReturn(nameVO);

        AccessMethodServiceAssemblerImpl assembler = new AccessMethodServiceAssemblerImpl();

        // Act
        AccessMethodServiceDTO dto = assembler.toDTO(accessMethod);

        // Assert
        assertNotNull(dto);
    }

    @Test
    void toDto_shouldMapCorrectId() {
        // Arrange
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodID mockId = mock(AccessMethodID.class);
        when(mockId.toString()).thenReturn("uuid-123");
        when(accessMethod.identity()).thenReturn(mockId);

        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars("Access123");
        when(accessMethod.getAccessMethodName()).thenReturn(nameVO);

        AccessMethodServiceAssemblerImpl assembler = new AccessMethodServiceAssemblerImpl();

        // Act
        AccessMethodServiceDTO dto = assembler.toDTO(accessMethod);

        // Assert
        assertEquals("uuid-123", dto.id());
    }

    @Test
    void toDto_shouldMapCorrectName() {
        // Arrange
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodID mockId = mock(AccessMethodID.class);
        when(mockId.toString()).thenReturn("uuid-123");
        when(accessMethod.identity()).thenReturn(mockId);

        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars("Access123");
        when(accessMethod.getAccessMethodName()).thenReturn(nameVO);

        AccessMethodServiceAssemblerImpl assembler = new AccessMethodServiceAssemblerImpl();

        // Act
        AccessMethodServiceDTO dto = assembler.toDTO(accessMethod);

        // Assert
        assertEquals("Access123", dto.name());
    }

    @Test
    void toDto_shouldThrowException_whenAccessMethodIsNull() {
        // Arrange
        AccessMethodServiceAssemblerImpl assembler = new AccessMethodServiceAssemblerImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> assembler.toDTO(null));
        assertEquals("AccessMethod cannot be null.", exception.getMessage());
    }
    @Test
    void toResponseDtoList_shouldConvertListCorrectly() {
        // Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        List<AccessMethodServiceDTO> serviceDTOs = List.of(
                new AccessMethodServiceDTO("id1", "Name1"),
                new AccessMethodServiceDTO("id2", "Name2")
        );

        // Act
        List<AccessMethodResponseDTO> responseDTOs = assembler.toResponseDtoList(serviceDTOs);

        // Assert
        assertNotNull(responseDTOs);
        assertEquals(serviceDTOs.size(), responseDTOs.size());
        assertEquals("id1", responseDTOs.get(0).id());
        assertEquals("Name1", responseDTOs.get(0).name());
        assertEquals("id2", responseDTOs.get(1).id());
        assertEquals("Name2", responseDTOs.get(1).name());
    }

    @Test
    void toResponseDtoList_shouldThrowExceptionWhenListIsNull() {
        // Arrange
        AccessMethodControllerAssemblerImpl assembler = new AccessMethodControllerAssemblerImpl();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDtoList(null);
        });

        assertEquals("AccessMethodServiceDTO List cannot be null.", exception.getMessage());
    }

}