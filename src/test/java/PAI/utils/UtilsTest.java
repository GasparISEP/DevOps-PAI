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

}