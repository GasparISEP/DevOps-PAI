package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;

import PAI.VOs.DegreeTypeID;
import PAI.factory.DegreeTypeFactory.DegreeTypeFactoryImpl;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DegreeTypeFactoryImplTest {

    private DegreeTypeFactoryImpl factory;

    @BeforeEach
    void setUp() {
        factory = new DegreeTypeFactoryImpl();
    }

    @Test
    void testCreate_WithValidValues() {
        Name name = new Name("Computer Science");
        MaxEcts maxEcts = new MaxEcts(180);

        DegreeType degreeType = factory.create(name, maxEcts);

        assertNotNull(degreeType);
        assertNotNull(degreeType.identity());
        assertEquals(name.getName(), degreeType.getName());
        assertEquals(maxEcts.getMaxEcts(), degreeType.getMaxEcts());
    }

    @Test
    void testCreate_WithNullValues() {
        assertThrows(NullPointerException.class, () -> {
            factory.create(null, null);
        });
    }

    @Test
    void testRecreate_WithValidValues() {
        DegreeTypeID id = new DegreeTypeID("mock-id-789");
        Name name = new Name("Engenharia");
        MaxEcts ects = new MaxEcts(180);

        DegreeType degreeType = factory.recreate(id, name, ects);

        assertNotNull(degreeType);
        assertEquals(id, degreeType.identity());
        assertEquals("Engenharia", degreeType.getName());
        assertEquals(180, degreeType.getMaxEcts());
    }
}