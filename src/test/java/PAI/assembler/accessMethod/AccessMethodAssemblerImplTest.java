package PAI.assembler.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
        AccessMethodRequestDTO dto = mock(AccessMethodRequestDTO.class);
        when(dto.name()).thenReturn("+23");

        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act
        RegisterAccessMethodCommand vo = assembler.toCommand(dto);

        // Assert
        assertNotNull(vo);
        assertEquals("+23", vo.name());
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
    void shouldConvertAccessMethodToResponseDTO() {
        // Arrange
        AccessMethod accessMethod = mock(AccessMethod.class);

        // Criar mock para o ID
        AccessMethodID mockId = mock(AccessMethodID.class);
        when(mockId.toString()).thenReturn("12345-uuid");

        // Quando chamar identity(), retorna o mock do ID
        when(accessMethod.identity()).thenReturn(mockId);

        // Criar o VO com o nome esperado
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars("+23");
        when(accessMethod.getAccessMethodName()).thenReturn(nameVO);

        AccessMethodAssemblerImpl assembler = new AccessMethodAssemblerImpl();

        // Act
        AccessMethodResponseDTO dto = assembler.toDto(accessMethod);

        // Assert
        assertNotNull(dto);
        assertEquals("12345-uuid", dto.id());
        assertEquals("+23", dto.name());
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



    
}
