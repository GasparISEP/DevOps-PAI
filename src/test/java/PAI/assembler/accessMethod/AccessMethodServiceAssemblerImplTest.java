package PAI.assembler.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import org.junit.jupiter.api.Test;

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

}