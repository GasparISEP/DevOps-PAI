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
        Programme programme = mock(Programme.class);
        ProgrammeEnrolment enrolment = mock(ProgrammeEnrolment.class);

        List<Programme> programmes = List.of(programme);
        List<ProgrammeEnrolment> enrolments = List.of(enrolment);

        // Act: create the record
        US34ListOfProgrammes listOfProgrammes = new US34ListOfProgrammes(programmes, enrolments);

        // Assert: verify the record's fields
        assertEquals(programmes, listOfProgrammes.programme());
        assertEquals(enrolments, listOfProgrammes.programmeEnrolment());
    }
}
