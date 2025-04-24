package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeIDMapperTest {

    @Test
    void testToStringAndBack() {
        DegreeTypeID id = new DegreeTypeID("abc-123");
        String idString = DegreeTypeIDMapper.toString(id);

        assertEquals("abc-123", idString);

        DegreeTypeID idBack = DegreeTypeIDMapper.toDomain(idString);
        assertEquals(id, idBack);
    }
}