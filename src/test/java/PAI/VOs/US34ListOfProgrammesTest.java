package PAI.VOs;

import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class US34ListOfProgrammesTest {

    @Test
    public void testUS34ListOfProgrammesRecord() {
        //arrange
        Programme programme = mock(Programme.class);
        ProgrammeEnrolment enrolment = mock(ProgrammeEnrolment.class);
        Name name = mock(Name.class);

        List<Programme> programmes = List.of(programme);
        List<ProgrammeEnrolment> enrolments = List.of(enrolment);

        //act
        US34ListOfProgrammes res = new US34ListOfProgrammes(programmes, enrolments, name);

        //assert
        assertEquals(programmes, res.programme());
        assertEquals(enrolments, res.programmeEnrolment());
        assertEquals(name, res.name());
    }
}
