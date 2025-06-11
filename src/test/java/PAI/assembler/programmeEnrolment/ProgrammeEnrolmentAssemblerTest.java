package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentListIDDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentAssemblerTest {

    private final ProgrammeEnrolmentAssembler assembler = new ProgrammeEnrolmentAssembler();

    @Test
    void toProgrammeEnrolmentGeneratedID_shouldReturnValidObject() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(uuid);

        // Act
        ProgrammeEnrolmentGeneratedID result = assembler.toProgrammeEnrolmentGeneratedID(dto);

        // Assert
        assertNotNull(result);
        assertEquals(uuid, result.getProgrammeEnrolmentGID());
    }

    @Test
    void toDTO_shouldReturnDTO_whenValidInput() {
        //arrange
        ProgrammeID programmeAcro = mock(ProgrammeID.class);
        ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID = mock(ProgrammeEnrolmentGeneratedID.class);
        ProgrammeEnrolment domainId = mock(ProgrammeEnrolment.class);

        when(domainId.getProgrammeEnrolmentGeneratedID()).thenReturn(programmeEnrolmentGeneratedID);
        when(programmeEnrolmentGeneratedID.getProgrammeEnrolmentGID()).thenReturn(UUID.randomUUID());
        when(domainId.getProgrammeID()).thenReturn(programmeAcro);

        //act
        ProgrammeEnrolmentListIDDTO dto = assembler.toDTO(domainId);

        // Assert
        assertNotNull(dto);
    }

    @Test
    void toDTO_shouldThrowException_whenNullInput() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> assembler.toDTO(null));
        assertEquals("Programme ID cannot be null", ex.getMessage());
    }

    @Test
    void toListOfDTOs_shouldReturnListOfDTOs_whenValidList() {
        //arrange
        ProgrammeID programmeAcro = mock(ProgrammeID.class);
        ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID = mock(ProgrammeEnrolmentGeneratedID.class);
        ProgrammeEnrolment domainId1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment domainId2 = mock(ProgrammeEnrolment.class);

        when(domainId1.getProgrammeEnrolmentGeneratedID()).thenReturn(programmeEnrolmentGeneratedID);
        when(programmeEnrolmentGeneratedID.getProgrammeEnrolmentGID()).thenReturn(UUID.randomUUID());
        when(domainId1.getProgrammeID()).thenReturn(programmeAcro);

        when(domainId2.getProgrammeEnrolmentGeneratedID()).thenReturn(programmeEnrolmentGeneratedID);
        when(programmeEnrolmentGeneratedID.getProgrammeEnrolmentGID()).thenReturn(UUID.randomUUID());
        when(domainId2.getProgrammeID()).thenReturn(programmeAcro);

        List<ProgrammeEnrolment> domainList = List.of(domainId1, domainId2);

        //act
        List<ProgrammeEnrolmentListIDDTO> dtoList = assembler.toListOfDTOs(domainList);

        //assert
        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
    }

    @Test
    void toListOfDTOs_shouldReturnEmptyList_whenEmptyList() {
        // Act
        List<ProgrammeEnrolmentListIDDTO> dtoList = assembler.toListOfDTOs(Collections.emptyList());

        // Assert
        assertNotNull(dtoList);
        assertTrue(dtoList.isEmpty());
    }

    @Test
    void testToProgrammeEnrolmentDTO() {
        // Arrange
        StudentID sid = new StudentID(1234567);
        AccessMethodID aid = new AccessMethodID(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        Acronym acronymVo = new Acronym("EG");
        ProgrammeID pid = new ProgrammeID(acronymVo);
        LocalDate localDate = LocalDate.of(2025, 5, 20);
        Date dateVo = new Date(localDate);
        ProgrammeEnrolmentGeneratedID enrolmentGID = new ProgrammeEnrolmentGeneratedID(UUID.randomUUID());

        ProgrammeEnrolment enrolment = new ProgrammeEnrolment(sid, aid, pid, dateVo, enrolmentGID);

        // Act
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();
        ProgrammeEnrolmentResponseDTO dto = mapper.toProgrammeEnrolmentDTO(enrolment);

        // Assert
        assertEquals(1234567, dto.getStudentID());

    }

    @Test
    void testReturnStudentID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();


        //act
        StudentID expectedStudentID = mapper.toStudentID(dto);

        //assert
        assertEquals(studentIDValue, expectedStudentID.getUniqueNumber());
    }

    @Test
    void testReturnAccessMethodID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();


        //act
        AccessMethodID expectedAccessMethodID = mapper.toAccessMethodID(dto);
        UUID expectedUuid = UUID.fromString(accessMethodUUID);

        //assert
        assertEquals(expectedUuid, expectedAccessMethodID.getAccessMethodID());    }

    @Test
    void testReturnProgrammeID() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);
        ProgrammeID programmeIdExpected = new ProgrammeID(new Acronym(programmeAcronymValue));

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();

        //act
        ProgrammeID programmeID = mapper.toProgrammeID(dto);

        //assert
        assertEquals(programmeIdExpected, programmeID);
    }

    @Test
    void testReturnDateVO() {

        // arrange
        int studentIDValue = 1234567;
        String accessMethodUUID = "550e8400-e29b-41d4-a716-446655440000";
        String programmeAcronymValue = "EI";
        LocalDate enrolmentDate = LocalDate.of(2025, 6, 1);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentIDValue, accessMethodUUID, programmeAcronymValue, enrolmentDate);
        ProgrammeEnrolmentAssembler mapper = new ProgrammeEnrolmentAssembler();

        //act
        Date date = mapper.toDateVO(dto);

        //assert
        assertEquals(enrolmentDate, date.getLocalDate());
    }
}


