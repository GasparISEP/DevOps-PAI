package PAI.VOs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NIFTest {

    @Test
    void validStringCreatesNIF(){
        //arrange
        String stringNIF = "123456789";
        //act
        NIF nif = new NIF(stringNIF);
        //assert
        assertNotNull(nif);
    }

    @Test
    void emptyStringThrowsException(){
        //arrange
        String stringNIF = "";
        //act + assert
        assertThrows(Exception.class, () -> new NIF(stringNIF));
    }

    @Test
    void nullStringThrowsException(){
        //arrange
        String stringNIF = null;
        //act + assert
        assertThrows(Exception.class, () -> new NIF(stringNIF));
    }

    @Test
    void invalidNIFThrowsException(){
        //arrange
        String stringNIF = "ABCDEFG";
        //act + assert
        assertThrows(Exception.class, () -> new NIF(stringNIF));
    }

    @Test
    void austriaNIFCanBeCreated_AT(){
        //arrange
        String stringNIF = "U12345678";
        //act
        NIF nif = new NIF(stringNIF);
        //assert
        assertNotNull(nif);
    }

    @Test
    void belgiumNIFCanBeCreated_AT(){
        //arrange
        String stringNIF = "1234567890";
        //act
        NIF nif = new NIF(stringNIF);
        //assert
        assertNotNull(nif);
    }

}