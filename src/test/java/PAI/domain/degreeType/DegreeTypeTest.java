package PAI.domain.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DegreeTypeTest {

    @Test
    void shouldCreateValidDegreeType() {
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);
        DegreeType degreeType = new DegreeType(new DegreeTypeID(), name, maxEcts);

        assertNotNull(degreeType);
        assertNotNull(degreeType.getId());
        assertEquals("Bachelor", degreeType.getName());
        assertEquals(180, degreeType.getMaxEcts());
    }

    @Test
    void shouldGenerateUniqueIDsForEachDegreeType() {
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        DegreeType degreeType1 = new DegreeType(new DegreeTypeID(), name, maxEcts);
        DegreeType degreeType2 = new DegreeType(new DegreeTypeID(), name, maxEcts);

        assertNotEquals(degreeType1.getId(), degreeType2.getId(), "IDs devem ser únicos mesmo com os mesmos valores");
    }

    @Test
    void shouldThrowExceptionWhenCreatingWithNullValues() {
        Name validName = new Name("Bachelor");
        MaxEcts validMaxEcts = new MaxEcts(180);

        assertThrows(NullPointerException.class, () -> new DegreeType(null, validName, validMaxEcts));
        assertThrows(NullPointerException.class, () -> new DegreeType(new DegreeTypeID(), null, validMaxEcts));
        assertThrows(NullPointerException.class, () -> new DegreeType(new DegreeTypeID(), validName, null));
    }

    @Test
    void testIdentityReturnsCorrectDegreeTypeID() {
        Name name = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);
        DegreeTypeID id = new DegreeTypeID();

        DegreeType degreeType = new DegreeType(id, name, ects);
        assertEquals(id.getDTID(), degreeType.identity().getDTID());
    }

    @Test
    void testSameAsWithSameInstanceReturnsTrue() {
        Name nome = new Name("Mestrado");
        MaxEcts ects = new MaxEcts(120);
        DegreeType degreeType = new DegreeType(new DegreeTypeID(), nome, ects);

        assertTrue(degreeType.sameAs(degreeType), "sameAs deve retornar true para a mesma instância");
    }

    @Test
    void testSameAsWithEqualIDsButDifferentObjects() {
        DegreeTypeID id = new DegreeTypeID("same-id");
        DegreeType d1 = new DegreeType(id, new Name("Engenharia"), new MaxEcts(180));
        DegreeType d2 = new DegreeType(id, new Name("Arquitetura"), new MaxEcts(120));

        assertTrue(d1.sameAs(d2));
    }

    @Test
    void testSameAsWithDifferentIDsReturnsFalse() {
        Name name = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        DegreeType d1 = new DegreeType(new DegreeTypeID("id1"), name, ects);
        DegreeType d2 = new DegreeType(new DegreeTypeID("id2"), name, ects);

        assertFalse(d1.sameAs(d2));
    }

    @Test
    void testSameAsWithNullReturnsFalse() {
        DegreeType degreeType = new DegreeType(new DegreeTypeID(), new Name("Licenciatura"), new MaxEcts(180));
        assertFalse(degreeType.sameAs(null));
    }

    @Test
    void testSameAsWithDifferentTypeReturnsFalse() {
        DegreeType degreeType = new DegreeType(new DegreeTypeID(), new Name("Licenciatura"), new MaxEcts(180));
        String outroObjeto = "Objeto qualquer";

        assertFalse(degreeType.sameAs(outroObjeto));
    }

    @Test
    void testEqualsAndHashCodeWithSameID() {
        DegreeTypeID sharedID = new DegreeTypeID();
        Name name = new Name("Bachelor");
        MaxEcts maxEcts = new MaxEcts(180);

        DegreeType d1 = new DegreeType(sharedID, name, maxEcts);
        DegreeType d2 = new DegreeType(sharedID, name, maxEcts);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testEqualsWithDifferentIDShouldReturnFalse() {
        DegreeType d1 = new DegreeType(new DegreeTypeID("id1"), new Name("Engenharia"), new MaxEcts(180));
        DegreeType d2 = new DegreeType(new DegreeTypeID("id2"), new Name("Engenharia"), new MaxEcts(180));

        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullShouldReturnFalse() {
        DegreeType degreeType = new DegreeType(new DegreeTypeID(), new Name("Engenharia"), new MaxEcts(180));
        assertNotEquals(degreeType, null);
    }

    @Test
    void testEqualsWithDifferentTypeShouldReturnFalse() {
        DegreeType degreeType = new DegreeType(new DegreeTypeID(), new Name("Engenharia"), new MaxEcts(180));
        assertNotEquals(degreeType, "Not a DegreeType");
    }

    @Test
    void testHashCodeWithDifferentAttributes() {
        DegreeTypeID id = new DegreeTypeID("same-id");

        DegreeType d1 = new DegreeType(id, new Name("Engenharia"), new MaxEcts(180));
        DegreeType d2 = new DegreeType(id, new Name("Arquitetura"), new MaxEcts(120));

        assertNotEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeConsistencyForSameAttributes() {
        DegreeTypeID id = new DegreeTypeID("hash-test-id");
        Name name = new Name("Consistent Name");
        MaxEcts ects = new MaxEcts(180);

        DegreeType d1 = new DegreeType(id, name, ects);
        DegreeType d2 = new DegreeType(id, name, ects);

        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeDifferentWhenAttributesDiffer() {
        DegreeTypeID id1 = new DegreeTypeID("id-1");
        DegreeTypeID id2 = new DegreeTypeID("id-2");
        Name name = new Name("Nome");
        MaxEcts ects = new MaxEcts(180);

        DegreeType d1 = new DegreeType(id1, name, ects);
        DegreeType d2 = new DegreeType(id2, name, ects);

        assertNotEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeExactValue() {
        DegreeTypeID id = new DegreeTypeID("hash-1");
        Name name = new Name("Test Degree");
        MaxEcts ects = new MaxEcts(120);

        DegreeType degreeType = new DegreeType(id, name, ects);

        int expected = 31 * id.hashCode() + name.hashCode() + ects.hashCode();
        assertEquals(expected, degreeType.hashCode());
    }

    @Test
    void testHashCodeCoversAllMathMutations() {

        DegreeTypeID id = new DegreeTypeID("id-test") {
            @Override
            public int hashCode() {
                return 2;
            }
        };
        Name name = new Name("Test") {
            @Override
            public int hashCode() {
                return 3;
            }
        };
        MaxEcts ects = new MaxEcts(60) {
            @Override
            public int hashCode() {
                return 5;
            }
        };

        DegreeType degreeType = new DegreeType(id, name, ects);
        int expected = 31 * 2 + 3 + 5;

        assertEquals(expected, degreeType.hashCode());
    }

    @Test
    void testHashCodeAffectsHashSetBehavior() {
        DegreeTypeID id = new DegreeTypeID("id-abc");
        Name name = new Name("Test");
        MaxEcts ects = new MaxEcts(90);

        DegreeType degreeType1 = new DegreeType(id, name, ects);
        DegreeType degreeType2 = new DegreeType(id, name, ects);

        Set<DegreeType> set = new HashSet<>();
        set.add(degreeType1);

        assertTrue(set.contains(degreeType2), "HashSet should recognize equal object with same hashCode");
    }
}