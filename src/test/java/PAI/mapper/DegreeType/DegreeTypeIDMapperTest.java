package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeIDMapperTest {

    private final IDegreeTypeIDMapper mapper = new DegreeTypeIDMapper(); // ← Instância

    @Test
    void testToStringAndBack() {
        DegreeTypeID id = new DegreeTypeID("abc-123");
        String idString = mapper.toString(id);  // ← Usa instância

        assertEquals("abc-123", idString);

        DegreeTypeID idBack = mapper.toDomain(idString);  // ← Usa instância
        assertEquals(id, idBack);
    }
}