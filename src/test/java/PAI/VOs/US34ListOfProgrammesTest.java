package PAI.VOs;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class US34ListOfProgrammesTest {

    @Test
    public void testUS34ListOfProgrammesRecord() {
        //arrange
        Name name = mock(Name.class);
        ProgrammeSummary summary = mock(ProgrammeSummary.class);

        List<ProgrammeSummary> programmes = List.of(summary);

        //act
        US34ListOfProgrammes res = new US34ListOfProgrammes(programmes, name);

        //assert
        assertEquals(programmes, res.programmeInfo());
        assertEquals(name, res.studentName());
    }
}
