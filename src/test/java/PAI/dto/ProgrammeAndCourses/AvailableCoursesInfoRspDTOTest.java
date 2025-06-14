package PAI.dto.ProgrammeAndCourses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableCoursesInfoRspDTOTest {
    @Test
    void testRecordStoresValuesCorrectly() {
        // Arrange
        String expectedAcronym = "CSD";
        String expectedName = "Computer Systems Design";
        double expectedQtyECTs = 30;
        int curricularYear = 1;
        int semester = 1;

        // Act
        AvailableCoursesInfoRspDTO dto = new AvailableCoursesInfoRspDTO(
                expectedAcronym,
                expectedName,
                expectedQtyECTs,
                curricularYear,
                semester
        );

        // Assert
        assertEquals(expectedAcronym, dto.acronym());
        assertEquals(expectedName, dto.name());
        assertEquals(expectedQtyECTs, dto.qtyECTs());
        assertEquals(curricularYear, dto.curricularYear());
        assertEquals(semester, dto.semester());
    }
}