package PAI.assembler.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.DegreeTypeDTO;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.dto.degreeType.RegisterDegreeTypeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DegreeTypeAssemblerImplTest {

    private DegreeTypeAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new DegreeTypeAssemblerImpl();
    }

    @Test
    void toRegisterDegreeTypeCommand_ShouldConvertCorrectly() {
        // Arrange
        RegisterDegreeTypeRequest request = new RegisterDegreeTypeRequest("Mestrado", 120);

        // Act
        RegisterDegreeTypeCommand command = assembler.toRegisterDegreeTypeCommand(request);

        // Assert
        assertNotNull(command);
        assertEquals("Mestrado", command.name().getName());
        assertEquals(120, command.maxEcts().getMaxEcts());
    }

    @Test
    void toRegisterDegreeTypeCommand_ShouldThrow_WhenRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                assembler.toRegisterDegreeTypeCommand(null)
        );
    }

    @Test
    void toDTO_ShouldConvertCorrectly() {
        // Arrange
        String uuid = UUID.randomUUID().toString();

        DegreeTypeID id = mock(DegreeTypeID.class);
        when(id.getDTID()).thenReturn(uuid);

        Name name = mock(Name.class);
        when(name.toString()).thenReturn("Mestrado");

        MaxEcts maxEcts = mock(MaxEcts.class);
        when(maxEcts.getMaxEcts()).thenReturn(180);

        DegreeType mockDegreeType = mock(DegreeType.class);
        when(mockDegreeType.identity()).thenReturn(id);
        when(mockDegreeType.getName()).thenReturn(name);
        when(mockDegreeType.getMaxEcts()).thenReturn(maxEcts);

        DegreeTypeAssemblerImpl assembler = new DegreeTypeAssemblerImpl();

        // Act
        DegreeTypeDTO dto = assembler.toDTO(mockDegreeType);

        // Assert
        assertNotNull(dto);
        assertEquals(uuid, dto.id());
        assertEquals("Mestrado", dto.name());
        assertEquals(180, dto.maxEcts());
    }



    @Test
    void toDTO_ShouldThrow_WhenDegreeTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                assembler.toDTO(null)
        );
    }

}