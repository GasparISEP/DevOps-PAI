package PAI.assembler.programme;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeDirectorRequestDTO;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.Programme.ProgrammeDirectorVOsDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeDirectorAssemblerTest {

    @Test
    void shouldCreateProgrammeDirectorVOsDTOFromProgrammeDirectorRequestDTO() {
        // Arrange
        ProgrammeDirectorAssembler assembler = new ProgrammeDirectorAssembler();

        ProgrammeDirectorRequestDTO dtoMock = mock(ProgrammeDirectorRequestDTO.class);

        String programmeName = "Computer Science";
        String programmeAcronym = "CS";
        String teacherAcronym = "ABC";

        when(dtoMock.getProgrammeName()).thenReturn(programmeName);
        when(dtoMock.getProgrammeAcronym()).thenReturn(programmeAcronym);
        when(dtoMock.getTeacherAcronym()).thenReturn(teacherAcronym);

        // Act
        ProgrammeDirectorVOsDTO result = assembler.fromDTOToDomain(dtoMock);

        // Assert
        assertAll(
                () -> assertEquals(programmeName, result.getProgrammeName().getnameWithNumbersAndSpecialChars()),
                () -> assertEquals(programmeAcronym, result.getProgrammeAcronym().getAcronym()),
                () -> assertEquals(teacherAcronym, result.getTeacherAcronym().getAcronym())
        );
    }

    @Test
    void shouldCreateProgrammeDirectorResponseDTOFromSingleProgrammeAndTeacher() {
        // Arrange
        ProgrammeDirectorAssembler assembler = new ProgrammeDirectorAssembler();

        // Mock single Programme and VOs
        Programme programmeMock = mock(Programme.class);
        NameWithNumbersAndSpecialChars programmeNameMock = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronymMock = mock(Acronym.class);

        when(programmeMock.getProgrammeName()).thenReturn(programmeNameMock);
        when(programmeNameMock.getnameWithNumbersAndSpecialChars()).thenReturn("Computer Science");
        when(programmeMock.getAcronym()).thenReturn(acronymMock);
        when(acronymMock.getAcronym()).thenReturn("CS");

        List<Programme> programmes = List.of(programmeMock);

        // Mock single Teacher and VOs
        Teacher teacherMock = mock(Teacher.class);
        TeacherID teacherIDMock = mock(TeacherID.class);
        TeacherAcronym teacherAcronymMock = mock(TeacherAcronym.class);

        when(teacherMock.getTeacherID()).thenReturn(teacherIDMock);
        when(teacherIDMock.getTeacherAcronym()).thenReturn(teacherAcronymMock);
        when(teacherAcronymMock.getAcronym()).thenReturn("ABC");

        List<Teacher> teachers = List.of(teacherMock);

        // Act
        ProgrammeDirectorResponseDTO responseDTO = assembler.fromDomainToDTO(programmes, teachers);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.getProgrammes().size());
        assertEquals(1, responseDTO.getTeachers().size());

        assertEquals("Computer Science", responseDTO.getProgrammes().get(0).getProgrammeName());
        assertEquals("CS", responseDTO.getProgrammes().get(0).getProgrammeAcronym());

        assertEquals("ABC", responseDTO.getTeachers().get(0).getTeacherAcronym());
    }
}
