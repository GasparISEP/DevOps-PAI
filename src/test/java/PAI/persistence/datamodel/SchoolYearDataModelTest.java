package PAI.persistence.datamodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SchoolYearDataModelTest {

    @Test
    void testEmptyConstructor() {
        SchoolYearDataModel schoolYear = new SchoolYearDataModel();
        assertNotNull(schoolYear);
    }

    @Test
    void testConstrutorWithAllArguments() {
        String id = "uuid-123";
        String description = "2024/2025";
        String startDate = "2024-09-01";
        String endDate = "2025-06-30";

        SchoolYearDataModel schoolYear = new SchoolYearDataModel(id, description, startDate, endDate);

        assertNotNull(schoolYear);
    }

    @Test
    void testFullConstructorAndGetters() {
        String id = "uuid-123";
        String description = "2024/2025";
        String startDate = "2024-09-01";
        String endDate = "2025-06-30";

        SchoolYearDataModel schoolYear = new SchoolYearDataModel(id, description, startDate, endDate);

        assertEquals(id, schoolYear.getId());
        assertEquals(description, schoolYear.getDescription());
        assertEquals(startDate, schoolYear.getStartDate());
        assertEquals(endDate, schoolYear.getEndDate());
    }
}
