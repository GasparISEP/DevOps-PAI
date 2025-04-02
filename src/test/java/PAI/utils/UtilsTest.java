package PAI.utils;

import PAI.VOs.Country;
import PAI.VOs.NIF;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void austriaNIF_AT_isCorrect(){
        //arrange
        String countryName = "áustria";
        Country country = new Country(countryName);
        String stringNIF = "U12345678";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertTrue(result);
    }

    @Test
    void austriaNIF_AT_isIncorrect(){
        //arrange
        String countryName = "áustria";
        Country country = new Country(countryName);
        String stringNIF = "12345678";
        String stringNIF2 = "U1234567";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void belgiumNIF_BE_isCorrect(){
        //arrange
        String countryName = "Belgium";
        Country country = new Country(countryName);
        String stringNIF = "12345678901";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertTrue(result);
    }

    @Test
    void belgiumNIF_BE_isIncorrect(){
        //arrange
        String countryName = "Belgium";
        Country country = new Country(countryName);
        String stringNIF = "12345678";
        String stringNIF2 = "U1234567";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void bulgariaNIF_BG_isCorrect(){
        //arrange
        String countryName = "Bulgaria";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        String stringNIF2 = "1234567890";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void bulgariaNIF_BG_isIncorrect(){
        //arrange
        String countryName = "Bulgaria";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678";
        String stringNIF2 = "12345678901";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void croatiaNIF_HR_isCorrect(){
        //arrange
        String countryName = "Croatia";
        Country country = new Country(countryName);
        String stringNIF = "12345678901";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertTrue(result);
    }

    @Test
    void croatiaNIF_HR_isIncorrect(){
        //arrange
        String countryName = "Croatia";
        Country country = new Country(countryName);
        String stringNIF = "1234567890";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertFalse(result);
    }

    @Test
    void cyprusNIF_CY_isCorrect(){
        //arrange
        String countryName = "Cyprus";
        Country country = new Country(countryName);
        String stringNIF = "12345678U";
        String stringNIF2 = "12345678A";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void cyprusNIF_CY_isIncorrect(){
        //arrange
        String countryName = "Cyprus";
        Country country = new Country(countryName);
        String stringNIF = "1234567u";
        String stringNIF2 = "12345678u";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void czechRepublic_CZ_isCorrect(){
        //arrange
        String countryName = "Czech Republic";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678";
        String stringNIF2 = "123456789";
        String stringNIF3 = "1234567890";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        //assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    void czechRepublic_CZ_isIncorrect(){
        //arrange
        String countryName = "Czech Republic";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567";
        String stringNIF2 = "123456789u";
        String stringNIF3 = "12345678901";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        //assert
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void denmark_DK_isCorrect(){
        //arrange
        String countryName = "Denmark";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        //assert
        assertTrue(result1);
    }

    @Test
    void denmark_DK_isIncorrect(){
        //arrange
        String countryName = "Denmark";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        //assert
        assertFalse(result1);
    }

    @Test
    void estonia_EE_isCorrect(){
        //arrange
        String countryName = "Estonia";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        String stringNIF2 = "1234567890";
        String stringNIF3 = "12345678901";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        boolean result3 = Utils.NIFValidator(country,stringNIF3);
        //assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    void estonia_EE_iIncorrect(){
        //arrange
        String countryName = "Estonia";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678";
        String stringNIF2 = "1234567890t";
        String stringNIF3 = "1234567890321";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        boolean result3 = Utils.NIFValidator(country,stringNIF3);
        //assert
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void finland_FI_isCorrect(){
        //arrange
        String countryName = "Finland";
        Country country = new Country(countryName);
        String stringNIF1 = "123456+123A";
        String stringNIF2 = "123456-1234";
        String stringNIF3 = "123456A123A";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        //assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    void finland_FI_isIncorrect(){
        //arrange
        String countryName = "Finland";
        Country country = new Country(countryName);
        String stringNIF1 = "123456+123AA";
        String stringNIF2 = "123456-12a4";
        String stringNIF3 = "123456123A";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        //assert
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void france_FR_isCorrect(){
        //arrange
        String countryName = "France";
        Country country = new Country(countryName);
        String stringNIF1 = "0123456789012";
        String stringNIF2 = "1123456789012";
        String stringNIF3 = "2123456789012";
        String stringNIF4 = "3123456789012";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        boolean result4 = Utils.NIFValidator(country, stringNIF4);
        //assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
    }

    @Test
    void france_FR_isIncorrect(){
        //arrange
        String countryName = "France";
        Country country = new Country(countryName);
        String stringNIF1 = "4123456789012";
        String stringNIF2 = "11234567890123";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result1);
        assertFalse(result2);
    }

}