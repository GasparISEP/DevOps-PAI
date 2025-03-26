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

    @Test
    void greeceNIFCanBeCreated_EL(){
        //arrange
        String stringNIF9digits = "123456789";
        //act
        NIF nif1 = new NIF(stringNIF9digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void hungaryNIFCanBeCreated_HU(){
        //arrange
        String stringNIF8digits = "12345678";
        //act
        NIF nif1 = new NIF(stringNIF8digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void irelandNIFCanBeCreated_IE(){
        //arrange
        String stringNIF7digits1letter = "1234567A";
        String stringNIF7digits2letters = "1234567AB";
        //act
        NIF nif1 = new NIF(stringNIF7digits1letter);
        NIF nif2 = new NIF(stringNIF7digits2letters);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void italyNIFCanBeCreated_IT(){
        //arrange
        String stringNIF11digits = "12345678901";
        //act
        NIF nif1 = new NIF(stringNIF11digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void latviaNIFCanBeCreated_LV(){
        //arrange
        String stringNIF11digits = "12345678901";
        //act
        NIF nif1 = new NIF(stringNIF11digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void lithuaniaNIFCanBeCreated_LT(){
        //arrange
        String stringNIF9digits = "123456789";
        String stringNIF12digits = "123456789012";
        //act
        NIF nif1 = new NIF(stringNIF9digits);
        NIF nif2 = new NIF(stringNIF12digits);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void luxembourgNIFCanBeCreated_LU(){
        //arrange
        String stringNIF8digits = "12345678";
        //act
        NIF nif1 = new NIF(stringNIF8digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void maltaNIFCanBeCreated_MT(){
        //arrange
        String stringNIF8digits = "12345678";
        //act
        NIF nif1 = new NIF(stringNIF8digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void netherlandsNIFCanBeCreated_NL(){
        //arrange
        String stringNIF9digitsB2digits = "123456789B12";
        //act
        NIF nif1 = new NIF(stringNIF9digitsB2digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void polandNIFCanBeCreated_PL(){
        //arrange
        String stringNIF10digits = "1234567890";
        //act
        NIF nif1 = new NIF(stringNIF10digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void portugalNIFCanBeCreated_PT(){
        //arrange
        String stringNIF9digits = "123456789";
        //act
        NIF nif1 = new NIF(stringNIF9digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void romaniaNIFCanBeCreated_RO(){
        //arrange
        String stringNIF2digits = "12";
        String stringNIF3digits = "123";
        String stringNIF4digits = "1234";
        String stringNIF5digits = "12345";
        String stringNIF6digits = "123456";
        String stringNIF7digits = "1234567";
        String stringNIF8digits = "12345678";
        String stringNIF9digits = "123456789";
        String stringNIF10digits = "1234567890";
        //act
        NIF nif1 = new NIF(stringNIF2digits);
        NIF nif2 = new NIF(stringNIF3digits);
        NIF nif3 = new NIF(stringNIF4digits);
        NIF nif4 = new NIF(stringNIF5digits);
        NIF nif5 = new NIF(stringNIF6digits);
        NIF nif6 = new NIF(stringNIF7digits);
        NIF nif7 = new NIF(stringNIF8digits);
        NIF nif8 = new NIF(stringNIF9digits);
        NIF nif9 = new NIF(stringNIF10digits);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
        assertNotNull(nif3);
        assertNotNull(nif4);
        assertNotNull(nif5);
        assertNotNull(nif6);
        assertNotNull(nif7);
        assertNotNull(nif8);
        assertNotNull(nif9);
    }

    @Test
    void slovakiaNIFCanBeCreated_SK(){
        //arrange
        String string10digits = "1234567890";
        //act
        NIF nif1 = new NIF(string10digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void sloveniaNIFCanBeCreated_SI(){
        //arrange
        String string8digits = "12345678";
        //act
        NIF nif1 = new NIF(string8digits);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void spainNIFCanBeCreated_ES(){
        //arrange
        String string1letter7digits1letter = "A1234567A";
        String string1letter8digits = "A12345678";
        //act
        NIF nif1 = new NIF(string1letter7digits1letter);
        NIF nif2 = new NIF(string1letter8digits);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void swedenNIFCanBeCreated_SE(){
        //arrange
        String string12digits01 = "12345678901201";
        //act
        NIF nif1 = new NIF(string12digits01);
        //assert
        assertNotNull(nif1);
    }


}