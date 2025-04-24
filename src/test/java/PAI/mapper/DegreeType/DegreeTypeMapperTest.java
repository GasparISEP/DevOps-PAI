package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.domain.degreeType.DegreeType;
import PAI.mapper.DegreeType.IDegreeTypeIDMapper;
import PAI.persistence.datamodel.DegreeTypeDM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeMapperTest {

    private DegreeTypeMapper mapper;
    private IDegreeTypeIDMapper idMapper;

    @BeforeEach
    void setUp() {
        idMapper = mock(IDegreeTypeIDMapper.class);  // mock do ID mapper
        mapper = new DegreeTypeMapper(idMapper);     // injetar no DegreeTypeMapper
    }

    @Test
    void testToDataModel_withMocks() {
        DegreeType degreeType = mock(DegreeType.class);
        DegreeTypeID id = mock(DegreeTypeID.class);

        when(degreeType.identity()).thenReturn(id);
        when(idMapper.toString(id)).thenReturn("mock-id-123");
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
        when(idMapper.toDomain("mock-id-456")).thenReturn(new DegreeTypeID("mock-id-456"));

        DegreeType result = mapper.toDomainModel(dm);

        assertNotNull(result);
        assertEquals("mock-id-456", result.identity().getDTID());
        assertEquals("AnotherMock", result.getName());
        assertEquals(90, result.getMaxEcts());
    }
}