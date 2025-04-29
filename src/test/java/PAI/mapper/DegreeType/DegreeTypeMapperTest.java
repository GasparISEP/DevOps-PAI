package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeMapperTest {

    private DegreeTypeMapper mapper;
    private IDegreeTypeIDMapper idMapper;
    private IDegreeTypeFactory factory;

    @BeforeEach
    void setUp() {
        idMapper = mock(IDegreeTypeIDMapper.class);
        factory = mock(IDegreeTypeFactory.class);
        mapper = new DegreeTypeMapper(idMapper, factory);
    }

    @Test
    void testToDataModel_withMocks() {
        DegreeType degreeType = mock(DegreeType.class);
        DegreeTypeID id = mock(DegreeTypeID.class);

        when(degreeType.identity()).thenReturn(id);
        when(idMapper.toDataModel(id)).thenReturn(new DegreeTypeIDDataModel("mock-id-123"));
        when(degreeType.getName()).thenReturn("MockName");
        when(degreeType.getMaxEcts()).thenReturn(60);

        DegreeTypeDataModel result = mapper.toDataModel(degreeType);

        assertNotNull(result);
        assertEquals("mock-id-123", result.getId().getDegreeTypeID());
        assertEquals("MockName", result.getName());
        assertEquals(60, result.getMaxEcts());
    }

    @Test
    void testToDomainModel_withMocks() {
        DegreeTypeDataModel dm = mock(DegreeTypeDataModel.class);

        DegreeTypeIDDataModel idDataModel = new DegreeTypeIDDataModel("mock-id-456");
        DegreeTypeID domainID = new DegreeTypeID("mock-id-456");
        DegreeType domain = mock(DegreeType.class);

        when(dm.getId()).thenReturn(idDataModel);
        when(dm.getName()).thenReturn("AnotherMock");
        when(dm.getMaxEcts()).thenReturn(90);
        when(idMapper.toDomain(idDataModel)).thenReturn(domainID);
        when(factory.recreate(eq(domainID), any(Name.class), any(MaxEcts.class))).thenReturn(domain);

        DegreeType result = mapper.toDomainModel(dm);

        assertNotNull(result);
        assertEquals(domain, result);
    }
}