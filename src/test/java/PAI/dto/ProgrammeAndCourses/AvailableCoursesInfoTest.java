package PAI.dto.ProgrammeAndCourses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableCoursesInfoTest {
    @Test
    void testRecordStoresValuesCorrectly() {
        // Arrange
        String expectedAcronym = "CSD";
        String expectedName = "Computer Systems Design";
        int expectedQtyECTs = 30;

        // Act
        AvailableCoursesInfo dto = new AvailableCoursesInfo(
                expectedAcronym,
                expectedName,
                expectedQtyECTs
        );

        // Assert
        assertEquals(expectedAcronym, dto.acronym());
        assertEquals(expectedName, dto.name());
        assertEquals(expectedQtyECTs, dto.qtyECTs());
    }

}