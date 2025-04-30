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
    void testToDataModel_withRealObjects() {
        DegreeTypeID id = new DegreeTypeID("mock-id-123");
        DegreeType degreeType = new DegreeType(id, new Name("MockName"), new MaxEcts(60));

        when(idMapper.toDataModel(id)).thenReturn(new DegreeTypeIDDataModel("mock-id-123"));

        DegreeTypeDataModel result = mapper.toDataModel(degreeType);

        assertNotNull(result);
        assertEquals("mock-id-123", result.getId().getDegreeTypeID());
        assertEquals("MockName", result.getName());
        assertEquals(60, result.getMaxEcts());
    }

    @Test
    void testToDomainModel_withRealObjects() {
        // Arrange
        DegreeTypeIDDataModel idDataModel = new DegreeTypeIDDataModel("mock-id-456");
        DegreeTypeDataModel dm = new DegreeTypeDataModel(idDataModel, "AnotherMock", 90);

        DegreeTypeID domainID = new DegreeTypeID("mock-id-456");
        Name name = new Name("AnotherMock");
        MaxEcts ects = new MaxEcts(90);
        DegreeType expected = new DegreeType(domainID, name, ects);

        IDegreeTypeIDMapper realIdMapper = new DegreeTypeIDMapper();
        IDegreeTypeFactory realFactory = new IDegreeTypeFactory() {
            @Override
            public DegreeType create(Name name, MaxEcts maxEcts) {
                return new DegreeType(new DegreeTypeID(), name, maxEcts);
            }

            @Override
            public DegreeType recreate(DegreeTypeID id, Name name, MaxEcts maxEcts) {
                return new DegreeType(id, name, maxEcts);
            }
        };

        DegreeTypeMapper mapper = new DegreeTypeMapper(realIdMapper, realFactory);

        // Act
        DegreeType result = mapper.toDomainModel(dm);

        // Assert
        assertNotNull(result);
        assertEquals("mock-id-456", result.identity().getDTID());
        assertEquals("AnotherMock", result.getName());
        assertEquals(90, result.getMaxEcts());
    }
}