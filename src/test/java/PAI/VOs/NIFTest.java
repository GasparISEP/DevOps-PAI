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
    void belgiumNIFCanBeCreated_BE(){
        //arrange
        String stringNIF = "1234567890";
        //act
        NIF nif = new NIF(stringNIF);
        //assert
        assertNotNull(nif);
    }

    @Test
    void bulgariaNIFCanBeCreated_BG(){
        //arrange
        String stringNIF10digits = "1234567890";
        String stringNIF9digits = "123456789";
        //act
        NIF nif1 = new NIF(stringNIF10digits);
        NIF nif2 = new NIF(stringNIF9digits);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void croatiaNIFCanBeCreated_HR(){
        //arrange
        String stringNIF11digits = "12345678901";
        //act
        NIF nif = new NIF(stringNIF11digits);
        //assert
        assertNotNull(nif);
    }

    @Test
    void cyprusNIFCanBeCreated_CY(){
        //arrange
        String stringNIF8digits1letter = "12345678C";
        //act
        NIF nif1 = new NIF(stringNIF8digits1letter);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void czechRepublicNIFCanBeCreated_CZ(){
        //arrange
        String stringNIF8digits = "12345678";
        String stringNIF9digits = "123456789";
        String stringNIF10digits= "1234567890";
        //act
        NIF nif1 = new NIF(stringNIF8digits);
        NIF nif2 = new NIF(stringNIF9digits);
        NIF nif3 = new NIF(stringNIF10digits);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
        assertNotNull(nif3);
    }

    @Test
    void denmarkNIFCanBeCreated_DK(){
        //arrange
        String stringNIF8digits = "12345678";
        //act
        NIF nif1 = new NIF(stringNIF8digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void estoniaNIFCanBeCreated_EE(){
        //arrange
        String stringNIF9digits = "123456789";
        //act
        NIF nif1 = new NIF(stringNIF9digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void finlandNIFCanBeCreated_FI(){
        //arrange
        String stringNIF8digits = "12345678";
        //act
        NIF nif1 = new NIF(stringNIF8digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void franceNIFCanBeCreated_FR(){
        //arrange
        String stringNIF2letters9digits = "AB123456789";
        String stringNIF2digits9digits = "12123456789";
        //act
        NIF nif1 = new NIF(stringNIF2letters9digits);
        NIF nif2 = new NIF(stringNIF2digits9digits);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void germanyNIFCanBeCreated_DE(){
        //arrange
        String stringNIF9digits = "123456789";
        //act
        NIF nif1 = new NIF(stringNIF9digits);
        //assert
        assertNotNull(nif1);
    }

}