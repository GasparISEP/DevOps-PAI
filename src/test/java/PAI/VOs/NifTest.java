package PAI.VOs;

import PAI.domain.AccessMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {

    @Test
    void validStringCreatesNif(){
        //arrange
        String stringNif = "123456789";
        //act
        Nif nif = new Nif(stringNif);
        //assert
        assertNotNull(nif);
    }

    @Test
    void emptyStringThrowsException(){
        //arrange
        String stringNif = "";
        //act + assert
        assertThrows(Exception.class, () -> new Nif(stringNif));
    }

    @Test
    void nullStringThrowsException(){
        //arrange
        String stringNif = null;
        //act + assert
        assertThrows(Exception.class, () -> new Nif(stringNif));
    }

}