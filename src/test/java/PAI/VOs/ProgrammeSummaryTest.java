package PAI.VOs;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ProgrammeSummaryTest {

    @Test
    public void testProgrammeSummaryRecord() {
        //arrange
        ProgrammeID pid = mock(ProgrammeID.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        ProgrammeEnrolmentGeneratedID gid = mock(ProgrammeEnrolmentGeneratedID.class);

        //act
        ProgrammeSummary summary = new ProgrammeSummary(pid, name, gid);

        //assert
        assertEquals(pid, summary.programmeID());
        assertEquals(name, summary.programmeName());
        assertEquals(gid, summary.generatedID());
    }
}