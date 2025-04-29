package PAI.domain.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void testSameAsWithDifferentIDsReturnsFalse() {
        Name name = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        DegreeType d1 = new DegreeType(new DegreeTypeID(), name, ects);
        DegreeType d2 = new DegreeType(new DegreeTypeID(), name, ects);

        assertFalse(d1.sameAs(d2), "sameAs deve retornar false para objetos com IDs diferentes");
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
}