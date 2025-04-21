package PAI.persistence.datamodel.schoolYear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearIDDataModelTest {

    @Test
    void testEmptyConstructor() {
        SchoolYearIDDataModel schoolYearID = new SchoolYearIDDataModel();
        assertNotNull(schoolYearID);
    }

    @Test
    void testConstructor() {
        String idValue = "2024-2025";
        SchoolYearIDDataModel schoolYearID = new SchoolYearIDDataModel(idValue);

        assertNotNull(schoolYearID);
        assertEquals(idValue, schoolYearID.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        String idValue1 = "2024-2025";
        String idValue2 = "2024-2025";
        String idValue3 = "2025-2026";

        SchoolYearIDDataModel schoolYearID1 = new SchoolYearIDDataModel(idValue1);
        SchoolYearIDDataModel schoolYearID2 = new SchoolYearIDDataModel(idValue2);
        SchoolYearIDDataModel schoolYearID3 = new SchoolYearIDDataModel(idValue3);

        assertEquals(schoolYearID1, schoolYearID2);
        assertEquals(schoolYearID1.hashCode(), schoolYearID2.hashCode());

        assertNotEquals(schoolYearID1, schoolYearID3);
        assertNotEquals(schoolYearID1.hashCode(), schoolYearID3.hashCode());
    }

    @Test
    void testEqualsWithNullAndDifferentClass() {
        SchoolYearIDDataModel schoolYearID = new SchoolYearIDDataModel("2024-2025");

        assertNotEquals(null, schoolYearID);

        assertNotEquals("some string", schoolYearID);

        assertEquals(schoolYearID, schoolYearID);
    }

    @Test
    void testIdUniqueness() {
        SchoolYearIDDataModel schoolYearID1 = new SchoolYearIDDataModel("2024-2025");
        SchoolYearIDDataModel schoolYearID2 = new SchoolYearIDDataModel("2024-2025");

        assertTrue(schoolYearID1.getId().equals(schoolYearID2.getId()));
    }
}