package PAI.persistence.datamodel.schoolYear;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SchoolYearDataModelTest {

    @Test
    void testEmptyConstructor() {
        SchoolYearDataModel schoolYear = new SchoolYearDataModel();
        assertNotNull(schoolYear);
    }

    @Test
    void testConstrutorWithAllArguments() {
        SchoolYearIDDataModel id = new SchoolYearIDDataModel("2024-2025");
        String description = "2024/2025";
        LocalDate startDate = LocalDate.of(2024, 9, 1);
        LocalDate endDate = LocalDate.of(2025, 6, 30);

        SchoolYearDataModel schoolYear = new SchoolYearDataModel(id, description, startDate, endDate);

        assertNotNull(schoolYear);
    }

    @Test
    void testFullConstructorAndGetters() {
        SchoolYearIDDataModel id = new SchoolYearIDDataModel("2024-2025");
        String description = "2024/2025";
        LocalDate startDate = LocalDate.of(2024, 9, 1);
        LocalDate endDate = LocalDate.of(2025, 6, 30);

        SchoolYearDataModel schoolYear = new SchoolYearDataModel(id, description, startDate, endDate);

        assertEquals(id, schoolYear.getId());
        assertEquals(description, schoolYear.getDescription());
        assertEquals(startDate, schoolYear.getStartDate());
        assertEquals(endDate, schoolYear.getEndDate());
    }

    @Test
    void testEqualsAndHashCode() {
        SchoolYearDataModel schoolYear1 = new SchoolYearDataModel(new SchoolYearIDDataModel("2024-2025"), "2024/2025", LocalDate.of(2024, 9, 1), LocalDate.of(2025, 6, 30));
        SchoolYearDataModel schoolYear2 = new SchoolYearDataModel(new SchoolYearIDDataModel("2024-2025"), "2024/2025", LocalDate.of(2024, 9, 1), LocalDate.of(2025, 6, 30));
        SchoolYearDataModel schoolYear3 = new SchoolYearDataModel(new SchoolYearIDDataModel("2025-2026"), "2025/2026", LocalDate.of(2024, 9, 1), LocalDate.of(2025, 6, 30));

        assertEquals(schoolYear1, schoolYear2);
        assertEquals(schoolYear1.hashCode(), schoolYear2.hashCode());

        assertNotEquals(schoolYear1, schoolYear3);
        assertNotEquals(schoolYear1.hashCode(), schoolYear3.hashCode());
    }

    @Test
    void testEqualsWithNullAndDifferentClass() {
        SchoolYearDataModel schoolYear = new SchoolYearDataModel(new SchoolYearIDDataModel("2024-2025"), "2024/2025", LocalDate.of(2024, 9, 1), LocalDate.of(2025, 6, 30));

        assertNotEquals(null, schoolYear);
        assertNotEquals("some string", schoolYear);
        assertEquals(schoolYear, schoolYear);
    }
}
