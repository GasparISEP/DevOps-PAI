package PAI.dto.programmeEnrolment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeSummaryDTOTest {

    @Test
    void testProgrammeSummaryDTOCreationAndFields() {
        //arrange
        String programmeID = "PROG123";
        String programmeName = "Engineering";
        String generatedID = "ENR456";

        //act
        ProgrammeSummaryDTO dto = new ProgrammeSummaryDTO(programmeID, programmeName, generatedID);

        //assert
        assertEquals(programmeID, dto.programmeID());
        assertEquals(programmeName, dto.programmeName());
        assertEquals(generatedID, dto.generatedID());
    }

}