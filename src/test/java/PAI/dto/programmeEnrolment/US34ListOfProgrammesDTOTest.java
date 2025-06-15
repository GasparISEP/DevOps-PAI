package PAI.dto.programmeEnrolment;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US34ListOfProgrammesDTOTest {

    @Test
    void testUS34ListOfProgrammesDTOCreationAndFields() {
        //arrange
        ProgrammeSummaryDTO summary1 = new ProgrammeSummaryDTO("PROG123", "Engineering", "ENR001");
        ProgrammeSummaryDTO summary2 = new ProgrammeSummaryDTO("PROG456", "Mathematics", "ENR002");

        List<ProgrammeSummaryDTO> programmeList = List.of(summary1, summary2);
        String studentName = "John Doe";

        //act
        US34ListOfProgrammesDTO dto = new US34ListOfProgrammesDTO(programmeList, studentName);

        //assert
        assertEquals(programmeList, dto.programmeInfo());
        assertEquals(studentName, dto.studentName());
    }
}