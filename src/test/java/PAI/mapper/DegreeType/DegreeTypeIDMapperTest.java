package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeIDMapperTest {

    private final IDegreeTypeIDMapper mapper = new DegreeTypeIDMapper();

    @Test
    void testToDataModelAndBack() {
        DegreeTypeID id = new DegreeTypeID("abc-123");

        DegreeTypeIDDataModel dataModel = mapper.toDataModel(id);
        assertEquals("abc-123", dataModel.getDegreeTypeID());

        DegreeTypeID idBack = mapper.toDomain(dataModel);
        assertEquals(id, idBack);
    }

    @Test
    void testToDataModel_NullInput_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapper.toDataModel(null));
        assertEquals("DegreeTypeID cannot be null", exception.getMessage());
    }

    @Test
    void testToDomain_NullInput_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapper.toDomain(null));
        assertEquals("DegreeTypeIDDataModel cannot be null", exception.getMessage());
    }
}