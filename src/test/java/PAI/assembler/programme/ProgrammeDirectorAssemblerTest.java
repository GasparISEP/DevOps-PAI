package PAI.assembler.programme;

import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeDirectorResponseDTO;
import PAI.dto.Programme.ProgrammeDirectorVOsDTO;
import PAI.dto.teacher.TeacherIdDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeDirectorAssemblerTest {

    private final ProgrammeDirectorAssembler assembler = new ProgrammeDirectorAssembler();

    @Test
    void fromDomainToDTO_shouldConvertTeacherListToDTO() {
        // Mock Teachers
        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        // Stub getTeacherID to return TeacherID containing TeacherAcronym
        when(teacher1.getTeacherID()).thenReturn(new TeacherID(new TeacherAcronym("ABC")));
        when(teacher2.getTeacherID()).thenReturn(new TeacherID(new TeacherAcronym("XYZ")));

        ProgrammeDirectorAssembler assembler = new ProgrammeDirectorAssembler();

        // Call the method
        ProgrammeDirectorResponseDTO responseDTO = assembler.fromDomainToDTO(List.of(teacher1, teacher2));

        // Assert the result has expected acronyms
        List<TeacherIdDTO> teachersDTO = responseDTO.teachers();

        assertEquals(2, teachersDTO.size());
        assertEquals("ABC", teachersDTO.get(0).acronym());
        assertEquals("XYZ", teachersDTO.get(1).acronym());
    }

    @Test
    void testFromDTOToDomain() {
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO("ABC");

        ProgrammeDirectorVOsDTO result = assembler.fromDTOToDomain(dto);

        assertNotNull(result);
        assertEquals("ABC", result.teacherID());
    }
}