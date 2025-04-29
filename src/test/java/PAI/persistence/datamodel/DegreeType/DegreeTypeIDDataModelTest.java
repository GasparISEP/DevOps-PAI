package PAI.persistence.datamodel.DegreeType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeIDDataModelTest {

    @Test
    void testConstructorAndGetter() {
        DegreeTypeIDDataModel dataModel = new DegreeTypeIDDataModel("abc-123");
        assertEquals("abc-123", dataModel.getDegreeTypeID());
    }

    @Test
    void testNoArgsConstructor() {
        DegreeTypeIDDataModel dataModel = new DegreeTypeIDDataModel();
        assertNotNull(dataModel);
    }

    @Test
    void testEqualsSameObject() {
        DegreeTypeIDDataModel dataModel = new DegreeTypeIDDataModel("abc-123");
        assertEquals(dataModel, dataModel);
    }

    @Test
    void testEqualsAndHashCodeWithEqualObjects() {
        DegreeTypeIDDataModel id1 = new DegreeTypeIDDataModel("abc-123");
        DegreeTypeIDDataModel id2 = new DegreeTypeIDDataModel("abc-123");

        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void testEqualsWithDifferentObjects() {
        DegreeTypeIDDataModel id1 = new DegreeTypeIDDataModel("abc-123");
        DegreeTypeIDDataModel id2 = new DegreeTypeIDDataModel("xyz-789");

        assertNotEquals(id1, id2);
    }

    @Test
    void testEqualsWithDifferentType() {
        DegreeTypeIDDataModel id = new DegreeTypeIDDataModel("abc-123");
        assertNotEquals(id, "abc-123");
    }

    @Test
    void testEqualsWithNull() {
        DegreeTypeIDDataModel id = new DegreeTypeIDDataModel("abc-123");
        assertNotEquals(id, null);
    }
}