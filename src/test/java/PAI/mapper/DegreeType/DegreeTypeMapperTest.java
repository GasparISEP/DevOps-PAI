package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.DegreeTypeDM;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DegreeTypeMapperTest {

    private final DegreeTypeMapper mapper = new DegreeTypeMapper();

    @Test
    void testToDataModel_withMocks() {
        DegreeType degreeType = mock(DegreeType.class);
        DegreeTypeID id = mock(DegreeTypeID.class);

        when(degreeType.identity()).thenReturn(id);
        when(id.getDTID()).thenReturn("mock-id-123");
        when(degreeType.getName()).thenReturn("MockName");
        when(degreeType.getMaxEcts()).thenReturn(60);

        DegreeTypeDM result = mapper.toDataModel(degreeType);

        assertNotNull(result);
        assertEquals("mock-id-123", result.getId());
        assertEquals("MockName", result.getName());
        assertEquals(60, result.getMaxEcts());
    }

    @Test
    void testToDomainModel_withMocks() {
        DegreeTypeDM dm = mock(DegreeTypeDM.class);

        when(dm.getId()).thenReturn("mock-id-456");
        when(dm.getName()).thenReturn("AnotherMock");
        when(dm.getMaxEcts()).thenReturn(90);

        DegreeType result = mapper.toDomainModel(dm);

        assertNotNull(result);
        assertEquals("mock-id-456", result.identity().getDTID());
        assertEquals("AnotherMock", result.getName());
        assertEquals(90, result.getMaxEcts());
    }
}