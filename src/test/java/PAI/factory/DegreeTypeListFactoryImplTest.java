/*
package PAI.factory;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.DegreeTypeListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeListFactoryImplTest {

    private final DegreeTypeListFactoryImpl factory = new DegreeTypeListFactoryImpl();

    @Test
    void testCreateEmptyList() {
        List<DegreeType> result = factory.createEmptyList();

        assertNotNull(result, "List should not be null");
        assertTrue(result.isEmpty(), "List should be empty");
    }

    @Test
    void testCreateFromExistingList() {
        DegreeType d1 = DegreeType.create(new Name("Engenharia Informática"), new MaxEcts(180));
        DegreeType d2 = DegreeType.create(new Name("Ciências Biomédicas"), new MaxEcts(180));

        List<DegreeType> existing = List.of(d1, d2);
        List<DegreeType> result = factory.createFromExisting(existing);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(existing));
    }
}*/
