package PAI.dto.programmeEdition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ProgrammeEditionIdDtoTest {

    @Test
    void shouldCreateProgrammeEditionIdDtoAndGetValues() {
        //Arrange
        ProgrammeEditionIdDto programmeEditionIdDto = new ProgrammeEditionIdDto("programmeAcronym", "schoolYearId");
        //Act
        //Assert
        assertNotNull(programmeEditionIdDto);
        assertEquals("programmeAcronym", programmeEditionIdDto.programmeAcronym());
        assertEquals("schoolYearId", programmeEditionIdDto.schoolYearId());
    }
}
