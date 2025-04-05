package PAI.domain.degreeType;

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

    @Test
    void testIdentityReturnsCorrectDegreeTypeID() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT100");
        Name nome = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        DegreeType degreeType = new DegreeType(id, nome, ects);
        assertEquals(id, degreeType.identity(), "O método identity deve retornar o DegreeTypeID correto");
    }

    @Test
    void testSameAsWithSameInstanceReturnsTrue() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT101");
        Name nome = new Name("Mestrado");
        MaxEcts ects = new MaxEcts(120);

        DegreeType degreeType = new DegreeType(id, nome, ects);
        // Comparação com a própria instância
        assertTrue(degreeType.sameAs(degreeType), "O método sameAs deve retornar true para a mesma instância");
    }

    @Test
    void testSameAsWithSameIDReturnsTrue() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT102");
        Name nome1 = new Name("Doutoramento");
        MaxEcts ects1 = new MaxEcts(240);

        DegreeType degreeType1 = new DegreeType(id, nome1, ects1);

        // Criar outra instância com o mesmo ID (mesmo se os outros atributos forem iguais ou diferentes)
        Name nome2 = new Name("Doutoramento");
        MaxEcts ects2 = new MaxEcts(240);
        DegreeType degreeType2 = new DegreeType(id, nome2, ects2);

        assertTrue(degreeType1.sameAs(degreeType2), "O método sameAs deve retornar true para objetos com o mesmo DegreeTypeID");
    }

    @Test
    void testSameAsWithDifferentIDsReturnsFalse() throws Exception {
        DegreeTypeID id1 = new DegreeTypeID("DT103");
        DegreeTypeID id2 = new DegreeTypeID("DT104");
        Name nome = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        DegreeType degreeType1 = new DegreeType(id1, nome, ects);
        DegreeType degreeType2 = new DegreeType(id2, nome, ects);

        assertFalse(degreeType1.sameAs(degreeType2), "O método sameAs deve retornar false para objetos com IDs diferentes");
    }

    @Test
    void testSameAsWithNullReturnsFalse() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT105");
        Name nome = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        DegreeType degreeType = new DegreeType(id, nome, ects);
        assertFalse(degreeType.sameAs(null), "O método sameAs deve retornar false quando comparado com null");
    }

    @Test
    void testSameAsWithDifferentTypeReturnsFalse() throws Exception {
        DegreeTypeID id = new DegreeTypeID("DT106");
        Name nome = new Name("Licenciatura");
        MaxEcts ects = new MaxEcts(180);

        DegreeType degreeType = new DegreeType(id, nome, ects);
        String outroObjeto = "Objeto diferente";
        assertFalse(degreeType.sameAs(outroObjeto), "O método sameAs deve retornar false quando comparado com um objeto de tipo diferente");
    }
}