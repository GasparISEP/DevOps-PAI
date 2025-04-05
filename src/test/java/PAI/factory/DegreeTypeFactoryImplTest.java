package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;

import PAI.domain.degreeType.DegreeTypeFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;

class DegreeTypeFactoryImplTest {

    private DegreeTypeFactoryImpl factory;

    @BeforeEach
    void setUp() {
        factory = new DegreeTypeFactoryImpl();
    }

    @Test
    void testAddNewDegreeType_2() throws Exception {
        DegreeTypeID degreeTypeID = new DegreeTypeID("DT001");
        Name name = new Name("Computer Science");
        MaxEcts maxEcts = new MaxEcts(180);

        DegreeType degreeType = factory.addNewDegreeType_2(degreeTypeID, name, maxEcts);

        assertNotNull(degreeType);
        assertEquals(degreeTypeID.getDTID(), degreeType.getId());
        assertEquals(name.getName(), degreeType.getName());
        assertEquals(maxEcts.getMaxEcts(), degreeType.getMaxEcts());
    }

    @Test
    void testAddNewDegreeType_2_WithNullValues() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            factory.addNewDegreeType_2(null, null, null);
        });

        assertTrue(exception.getMessage().contains("cannot be null"));
    }
}
