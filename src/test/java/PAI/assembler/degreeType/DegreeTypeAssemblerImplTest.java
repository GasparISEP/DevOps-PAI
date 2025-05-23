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

import java.util.List;
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

    @Test
    void toDTOs_ShouldConvertListCorrectly() {
        // Arrange
        DegreeTypeID id1 = mock(DegreeTypeID.class);
        when(id1.getDTID()).thenReturn(UUID.randomUUID().toString());
        Name name1 = mock(Name.class);
        when(name1.toString()).thenReturn("Licenciatura");
        MaxEcts maxEcts1 = mock(MaxEcts.class);
        when(maxEcts1.getMaxEcts()).thenReturn(180);
        DegreeType degreeType1 = mock(DegreeType.class);
        when(degreeType1.identity()).thenReturn(id1);
        when(degreeType1.getName()).thenReturn(name1);
        when(degreeType1.getMaxEcts()).thenReturn(maxEcts1);

        DegreeTypeID id2 = mock(DegreeTypeID.class);
        when(id2.getDTID()).thenReturn(UUID.randomUUID().toString());
        Name name2 = mock(Name.class);
        when(name2.toString()).thenReturn("Mestrado");
        MaxEcts maxEcts2 = mock(MaxEcts.class);
        when(maxEcts2.getMaxEcts()).thenReturn(120);
        DegreeType degreeType2 = mock(DegreeType.class);
        when(degreeType2.identity()).thenReturn(id2);
        when(degreeType2.getName()).thenReturn(name2);
        when(degreeType2.getMaxEcts()).thenReturn(maxEcts2);

        List<DegreeType> degreeTypes = List.of(degreeType1, degreeType2);

        // Act
        List<DegreeTypeDTO> dtos = assembler.toDTOs(degreeTypes);

        // Assert
        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        DegreeTypeDTO dto1 = dtos.get(0);
        assertEquals(id1.getDTID(), dto1.id());
        assertEquals("Licenciatura", dto1.name());
        assertEquals(180, dto1.maxEcts());

        DegreeTypeDTO dto2 = dtos.get(1);
        assertEquals(id2.getDTID(), dto2.id());
        assertEquals("Mestrado", dto2.name());
        assertEquals(120, dto2.maxEcts());
    }

    @Test
    void toDTOs_ShouldReturnEmptyList_WhenInputIsEmpty() {
        // Arrange
        List<DegreeType> emptyList = List.of();

        // Act
        List<DegreeTypeDTO> dtos = assembler.toDTOs(emptyList);

        // Assert
        assertNotNull(dtos);
        assertTrue(dtos.isEmpty());
    }

    @Test
    void toDTOs_ShouldThrow_WhenInputIsNull() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toDTOs(null));
    }

}