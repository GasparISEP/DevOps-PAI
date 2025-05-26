package PAI.dto.Programme;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorResponseDTOTest {

    static class TestData {
        final String programmeName = "Data Science";
        final String programmeAcronym = "DSE";
        final String teacherAcronym = "TCH";

        ProgrammeDirectorResponseDTO.ProgrammeDTO programmeDTO() {
            return new ProgrammeDirectorResponseDTO.ProgrammeDTO(programmeName, programmeAcronym);
        }

        ProgrammeDirectorResponseDTO.TeacherDTO teacherDTO() {
            return new ProgrammeDirectorResponseDTO.TeacherDTO(teacherAcronym);
        }
    }

    @Test
    void shouldCreateEmptyProgrammeDirectorResponseDTO() {
        // act
        ProgrammeDirectorResponseDTO dto = new ProgrammeDirectorResponseDTO();

        // assert
        assertNotNull(dto);
    }

    @Test
    void shouldCreateProgrammeDirectorResponseDTOWithValues() {
        // arrange
        TestData data = new TestData();
        List<ProgrammeDirectorResponseDTO.ProgrammeDTO> programmes = List.of(data.programmeDTO());
        List<ProgrammeDirectorResponseDTO.TeacherDTO> teachers = List.of(data.teacherDTO());

        // act
        ProgrammeDirectorResponseDTO dto = new ProgrammeDirectorResponseDTO(programmes, teachers);

        // assert
        assertNotNull(dto);
        assertEquals(programmes, dto.getProgrammes());
        assertEquals(teachers, dto.getTeachers());
    }

    @Test
    void programmeDTOShouldReturnCorrectNameAndAcronym() {
        // arrange
        TestData data = new TestData();
        ProgrammeDirectorResponseDTO.ProgrammeDTO dto = new ProgrammeDirectorResponseDTO.ProgrammeDTO(
                data.programmeName, data.programmeAcronym
        );

        // act & assert
        assertEquals(data.programmeName, dto.getProgrammeName());
        assertEquals(data.programmeAcronym, dto.getProgrammeAcronym());
    }

    @Test
    void teacherDTOShouldReturnCorrectAcronym() {
        // arrange
        TestData data = new TestData();
        ProgrammeDirectorResponseDTO.TeacherDTO dto = new ProgrammeDirectorResponseDTO.TeacherDTO(
                data.teacherAcronym
        );

        // act & assert
        assertEquals(data.teacherAcronym, dto.getTeacherAcronym());
    }
}