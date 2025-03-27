package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeIDTest {

    @Test
    void shouldCreateProgrammeID() {
        //arrange
        ProgrammeID programmeID = new ProgrammeID();

        //act+assert
        assertNotNull(programmeID);
    }

}