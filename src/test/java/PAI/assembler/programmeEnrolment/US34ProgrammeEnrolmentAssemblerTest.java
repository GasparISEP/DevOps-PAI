package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.dto.programmeEnrolment.US34ListOfProgrammesDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US34ProgrammeEnrolmentAssemblerTest {

    @Test
    void shouldTransformFromDomainToDTO () {
        //arrange
        US34ListOfProgrammes us34 = mock(US34ListOfProgrammes.class);
        Name name = mock(Name.class);
        ProgrammeSummary progSummary = mock(ProgrammeSummary.class);

        List<ProgrammeSummary> list = List.of(progSummary);

        ProgrammeID progID = mock(ProgrammeID.class);
        NameWithNumbersAndSpecialChars progName = mock(NameWithNumbersAndSpecialChars.class);
        ProgrammeEnrolmentGeneratedID enrolmentID = mock(ProgrammeEnrolmentGeneratedID.class);

        US34ProgrammeEnrolmentAssembler assembler = new US34ProgrammeEnrolmentAssembler();

        when(us34.studentName()).thenReturn(name);
        when(us34.programmeInfo()).thenReturn(list);
        when(progSummary.programmeID()).thenReturn(progID);
        when(progSummary.programmeName()).thenReturn(progName);
        when(progSummary.generatedID()).thenReturn(enrolmentID);
        when(enrolmentID.getProgrammeEnrolmentGID()).thenReturn(UUID.randomUUID());

        //act
        US34ListOfProgrammesDTO usDTO = assembler.toDto(us34);

        //assert
        assertNotNull(usDTO);
    }
}