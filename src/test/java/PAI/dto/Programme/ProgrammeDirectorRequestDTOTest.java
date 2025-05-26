package PAI.dto.Programme;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDirectorRequestDTOTest {

    static class ProgrammeDirectorTestData {
        final String programmeName = "Data Science";
        final String programmeAcronym = "DSE";
        final String teacherAcronym = "TCH";
    }

    @Test
    void shouldCreateProgrammeDirectorRequestDTO() {
        // arrange & act
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO();

        // assert
        assertNotNull(dto);
    }

    @Test
    void shouldCreateProgrammeDirectorRequestDTOWithGivenValues() {
        // arrange
        ProgrammeDirectorTestData data = new ProgrammeDirectorTestData();

        // act
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // assert
        assertNotNull(dto);
    }

    @Test
    void getProgrammeNameShouldReturnCorrectName() {
        // arrange
        ProgrammeDirectorTestData data = new ProgrammeDirectorTestData();
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // act
        String result = dto.getProgrammeName();

        // assert
        assertEquals(data.programmeName, result);
    }

    @Test
    void getProgrammeAcronymShouldReturnCorrectAcronym() {
        // arrange
        ProgrammeDirectorTestData data = new ProgrammeDirectorTestData();
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // act
        String result = dto.getProgrammeAcronym();

        // assert
        assertEquals(data.programmeAcronym, result);
    }

    @Test
    void getTeacherAcronymShouldReturnCorrectAcronym() {
        // arrange
        ProgrammeDirectorTestData data = new ProgrammeDirectorTestData();
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(
                data.programmeName,
                data.programmeAcronym,
                data.teacherAcronym
        );

        // act
        String result = dto.getTeacherAcronym();

        // assert
        assertEquals(data.teacherAcronym, result);
    }
}
