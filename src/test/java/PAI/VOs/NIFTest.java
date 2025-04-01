package PAI.VOs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NIFTest {

    @Test
    void validStringCreatesNIF(){
        //arrange
        String stringNIF = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif = new NIF(stringNIF, country);
        //assert
        assertNotNull(nif);
    }

    @Test
    void emptyStringThrowsException(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF = "";
        //act + assert
        assertThrows(Exception.class, () -> new NIF(stringNIF,country));
    }

    @Test
    void nullStringThrowsException(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF = null;
        //act + assert
        assertThrows(Exception.class, () -> new NIF(stringNIF, country));
    }

    @Test
    void invalidNIFThrowsException(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF = "ABCDEFG";
        //act + assert
        assertThrows(Exception.class, () -> new NIF(stringNIF, country));
    }

    @Test
    void austriaNIFCanBeCreated_AT(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF = "U12345678";
        //act
        NIF nif = new NIF(stringNIF, country);
        //assert
        assertNotNull(nif);
    }

    @Test
    void belgiumNIFCanBeCreated_BE(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF = "1234567890";
        //act
        NIF nif = new NIF(stringNIF, country);
        //assert
        assertNotNull(nif);
    }

    @Test
    void bulgariaNIFCanBeCreated_BG(){
        //arrange
        String stringNIF10digits = "1234567890";
        String stringNIF9digits = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF10digits, country);
        NIF nif2 = new NIF(stringNIF9digits, country);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void croatiaNIFCanBeCreated_HR(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF11digits = "12345678901";
        //act
        NIF nif = new NIF(stringNIF11digits, country);
        //assert
        assertNotNull(nif);
    }

    @Test
    void cyprusNIFCanBeCreated_CY(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF8digits1letter = "12345678C";
        //act
        NIF nif1 = new NIF(stringNIF8digits1letter, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void czechRepublicNIFCanBeCreated_CZ(){
        //arrange
        String stringNIF8digits = "12345678";
        String stringNIF9digits = "123456789";
        String stringNIF10digits= "1234567890";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF8digits,country);
        NIF nif2 = new NIF(stringNIF9digits, country);
        NIF nif3 = new NIF(stringNIF10digits, country);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
        assertNotNull(nif3);
    }

    @Test
    void denmarkNIFCanBeCreated_DK(){
        //arrange
        String stringNIF8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF8digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void estoniaNIFCanBeCreated_EE(){
        //arrange
        String stringNIF9digits = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF9digits, country );
        //assert
        assertNotNull(nif1);
    }

    @Test
    void finlandNIFCanBeCreated_FI(){
        //arrange
        String stringNIF8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF8digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void franceNIFCanBeCreated_FR(){
        //arrange
        String stringNIF2letters9digits = "AB123456789";
        String stringNIF2digits9digits = "12123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF2letters9digits, country);
        NIF nif2 = new NIF(stringNIF2digits9digits, country);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void germanyNIFCanBeCreated_DE(){
        //arrange
        String stringNIF9digits = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF9digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void greeceNIFCanBeCreated_EL(){
        //arrange
        String stringNIF9digits = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF9digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void hungaryNIFCanBeCreated_HU(){
        //arrange
        String stringNIF8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF8digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void irelandNIFCanBeCreated_IE(){
        //arrange
        String stringNIF7digits1letter = "1234567A";
        String stringNIF7digits2letters = "1234567AB";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF7digits1letter, country);
        NIF nif2 = new NIF(stringNIF7digits2letters, country);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void italyNIFCanBeCreated_IT(){
        //arrange
        String stringNIF11digits = "12345678901";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF11digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void latviaNIFCanBeCreated_LV(){
        //arrange
        String stringNIF11digits = "12345678901";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF11digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void lithuaniaNIFCanBeCreated_LT(){
        //arrange
        String stringNIF9digits = "123456789";
        String stringNIF12digits = "123456789012";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF9digits, country);
        NIF nif2 = new NIF(stringNIF12digits, country);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void luxembourgNIFCanBeCreated_LU(){
        //arrange
        String stringNIF8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF8digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void maltaNIFCanBeCreated_MT(){
        //arrange
        String stringNIF8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF8digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void netherlandsNIFCanBeCreated_NL(){
        //arrange
        String stringNIF9digitsB2digits = "123456789B12";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF9digitsB2digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void polandNIFCanBeCreated_PL(){
        //arrange
        String stringNIF10digits = "1234567890";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF10digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void portugalNIFCanBeCreated_PT(){
        //arrange
        String stringNIF9digits = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF9digits, country);
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
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(stringNIF2digits, country);
        NIF nif2 = new NIF(stringNIF3digits, country);
        NIF nif3 = new NIF(stringNIF4digits, country);
        NIF nif4 = new NIF(stringNIF5digits, country);
        NIF nif5 = new NIF(stringNIF6digits, country);
        NIF nif6 = new NIF(stringNIF7digits, country);
        NIF nif7 = new NIF(stringNIF8digits, country);
        NIF nif8 = new NIF(stringNIF9digits, country);
        NIF nif9 = new NIF(stringNIF10digits, country);
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
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(string10digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void sloveniaNIFCanBeCreated_SI(){
        //arrange
        String string8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(string8digits, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void spainNIFCanBeCreated_ES(){
        //arrange
        String string1letter7digits1letter = "A1234567A";
        String string1letter8digits = "A12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(string1letter7digits1letter, country);
        NIF nif2 = new NIF(string1letter8digits, country);
        //assert
        assertNotNull(nif1);
        assertNotNull(nif2);
    }

    @Test
    void swedenNIFCanBeCreated_SE(){
        //arrange
        String string12digits01 = "12345678901201";String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif1 = new NIF(string12digits01, country);
        //assert
        assertNotNull(nif1);
    }

    @Test
    void shouldReturnTrueIfSameNIF(){
        //arrange
        String string8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif = new NIF(string8digits,country);
        boolean result = nif.equals(nif);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfSameNIFContent(){
        //arrange
        String string8digits = "12345678";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif = new NIF(string8digits, country);
        NIF nif2 = new NIF(string8digits, country);
        boolean result = nif.equals(nif2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNIFNotTheSame(){
        //arrange
        String string8digits = "12345678";
        String string9digits = "123456789";
        String countryName = "Portugal";
        Country country = new Country(countryName);
        //act
        NIF nif = new NIF(string8digits, country);
        NIF nif2 = new NIF(string9digits, country);
        boolean result = nif.equals(nif2);
        //assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnCorrectNIF() {
        // Arrange
        Country country = mock(Country.class);
        String stringNIF = "123456789";
        NIF nif = new NIF(stringNIF, country);

        // Act
        String result = nif.getNIF();

        // Assert
        assertEquals(stringNIF, result);
    }
}