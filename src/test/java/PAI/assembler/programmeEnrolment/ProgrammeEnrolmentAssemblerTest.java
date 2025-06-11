package PAI.assembler.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
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
        String uuidString = UUID.randomUUID().toString();
        ProgrammeEnrolmentID domainId = mock(ProgrammeEnrolmentID.class);

        when(domainId.getProgrammeEnrolmentId()).thenReturn(uuidString);

        //act
        ProgrammeEnrolmentIdDTO dto = assembler.toDTO(domainId);

        // Assert
        assertNotNull(dto);
        assertEquals(UUID.fromString(uuidString), dto.getProgrammeEnrolmentGID());
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
        String uuidString1 = UUID.randomUUID().toString();
        String uuidString2 = UUID.randomUUID().toString();
        ProgrammeEnrolmentID domainId1 = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentID domainId2 = mock(ProgrammeEnrolmentID.class);
        List<ProgrammeEnrolmentID> domainList = List.of(domainId1, domainId2);

        when(domainId1.getProgrammeEnrolmentId()).thenReturn(uuidString1);
        when(domainId2.getProgrammeEnrolmentId()).thenReturn(uuidString2);

        //act
        List<ProgrammeEnrolmentIdDTO> dtoList = assembler.toListOfDTOs(domainList);

        //assert
        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals(UUID.fromString(uuidString1), dtoList.get(0).getProgrammeEnrolmentGID());
        assertEquals(UUID.fromString(uuidString2), dtoList.get(1).getProgrammeEnrolmentGID());
    }

    @Test
    void toListOfDTOs_shouldReturnEmptyList_whenEmptyList() {
        // Act
        List<ProgrammeEnrolmentIdDTO> dtoList = assembler.toListOfDTOs(Collections.emptyList());

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


