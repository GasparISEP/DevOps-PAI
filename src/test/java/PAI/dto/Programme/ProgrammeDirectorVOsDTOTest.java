package PAI.dto.Programme;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.TeacherAcronym;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorVOsDTOTest {

    static class TestData {
        final NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Data Science");
        final Acronym programmeAcronym = new Acronym("DSE");
        final TeacherAcronym teacherAcronym = new TeacherAcronym("TCH");
    }

    @Test
    void shouldCreateProgrammeDirectorVOsDTOWithGivenValues() {
        // arrange
        TestData data = new TestData();

        // act
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // assert
        assertNotNull(dto);
        assertEquals(data.programmeName, dto.getProgrammeName());
        assertEquals(data.programmeAcronym, dto.getProgrammeAcronym());
        assertEquals(data.teacherAcronym, dto.getTeacherAcronym());
    }

    @Test
    void getProgrammeNameShouldReturnCorrectValue() {
        // arrange
        TestData data = new TestData();
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // act & assert
        assertEquals(data.programmeName, dto.getProgrammeName());
    }

    @Test
    void getProgrammeAcronymShouldReturnCorrectValue() {
        // arrange
        TestData data = new TestData();
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // act & assert
        assertEquals(data.programmeAcronym, dto.getProgrammeAcronym());
    }

    @Test
    void getTeacherAcronymShouldReturnCorrectValue() {
        // arrange
        TestData data = new TestData();
        ProgrammeDirectorVOsDTO dto = new ProgrammeDirectorVOsDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // act & assert
        assertEquals(data.teacherAcronym, dto.getTeacherAcronym());
    }
}