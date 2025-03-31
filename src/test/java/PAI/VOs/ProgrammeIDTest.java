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

    @Test
    void shouldGetProgID() {
        //arrange
        ProgrammeID progID = new ProgrammeID();

        //act
        String res = progID.getProgID().toString();

        //assert
        assertEquals(res, progID.getProgID().toString());
    }

    @Test
    void shouldReturnTrueIfSameProgID(){
        //arrange
        ProgrammeID progID = new ProgrammeID();
        ProgrammeID progID1 = progID;
        //act
        boolean result = progID.equals(progID1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfFromDifferentInstances(){
        //arrange
        ProgrammeID progID = new ProgrammeID();
        Object o = new Object();
        //act
        boolean result = progID.equals(o);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDifferentContent(){
        //arrange
        ProgrammeID progID = new ProgrammeID();
        ProgrammeID progID1 = new ProgrammeID();
        //act
        boolean result = progID.equals(progID1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenIsContent(){
        //arrange
        ProgrammeID progID = new ProgrammeID();
        ProgrammeID progID1 = null;
        //act
        boolean result = progID.equals(progID1);
        //assert
        assertFalse(result);
    }
}