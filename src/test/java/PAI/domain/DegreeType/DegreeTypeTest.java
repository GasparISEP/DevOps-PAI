package PAI.domain.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeTest {

    @Test
    void shouldCreateValidDegreeType() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        DegreeType degreeType = new DegreeType(id, name, maxEcts);

        assertNotNull(degreeType);
        assertEquals("DT1", degreeType.getId());
        assertEquals("Bachelor", degreeType.getName());
        assertEquals(180, degreeType.getMaxEcts());
    }

    @Test
    void shouldThrowExceptionWhenCreatingWithNullValues() throws Exception {
        Name validName = new Name("Bachelor");
        MaxEcts validMaxEcts = new MaxEcts(180);
        DegreeTypeID validID = new DegreeTypeID("DT1");

        assertThrows(NullPointerException.class, () -> new DegreeType(null, validName, validMaxEcts));
        assertThrows(NullPointerException.class, () -> new DegreeType(validID, null, validMaxEcts));
        assertThrows(NullPointerException.class, () -> new DegreeType(validID, validName, null));
    }

    @Test
    void shouldReturnTrueForEqualObjects() throws Exception {
        DegreeTypeID id1 = new DegreeTypeID("DT1");
        Name name1 = new Name("Bachelor");
        MaxEcts maxEcts1 = new MaxEcts(180);

        DegreeType degreeType1 = new DegreeType(id1, name1, maxEcts1);
        DegreeType degreeType2 = new DegreeType(id1, name1, maxEcts1);

        assertEquals(degreeType1, degreeType2);
        assertEquals(degreeType1.hashCode(), degreeType2.hashCode());
    }

    @Test
    void shouldReturnFalseForDifferentObjects() throws Exception {
        DegreeType degreeType1 = new DegreeType(new DegreeTypeID("DT1"), new Name("Bachelor"), new MaxEcts(180));
        DegreeType degreeType2 = new DegreeType(new DegreeTypeID("DT2"), new Name("Master"), new MaxEcts(120));

        assertNotEquals(degreeType1, degreeType2);
        assertNotEquals(degreeType1.hashCode(), degreeType2.hashCode());
    }

    @Test
    void shouldReturnCorrectValuesFromGetters() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT1");
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);
        DegreeType degreeType = new DegreeType(id, name, maxEcts);

        assertEquals("DT1", degreeType.getId());
        assertEquals("Bachelor", degreeType.getName());
        assertEquals(180, degreeType.getMaxEcts());
    }
}