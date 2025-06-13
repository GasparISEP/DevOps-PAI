package PAI.dto.ProgrammeAndCourses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableCoursesInfoRspDTOTest {
    @Test
    void testRecordStoresValuesCorrectly() {
        // Arrange
        String expectedAcronym = "CSD";
        String expectedName = "Computer Systems Design";
        int expectedQtyECTs = 30;

        // Act
        AvailableCoursesInfoRspDTO dto = new AvailableCoursesInfoRspDTO(
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