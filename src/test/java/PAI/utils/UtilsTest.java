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

    @Test
    void germany_DE_isCorrect(){
        //arrange
        String countryName = "Germany";
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
    void germany_DE_isIncorrect(){
        //arrange
        String countryName = "Germany";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678";
        String stringNIF2 = "123456789012";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        //assert
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    void greece_EL_isCorrect(){
        //arrange
        String countryName = "Greece";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        //assert
        assertTrue(result1);
    }

    @Test
    void greece_EL_isIncorrect(){
        //arrange
        String countryName = "Greece";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        String stringNIF2 = "12345678";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        //assert
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    void hungary_HU_isCorrect(){
        //arrange
        String countryName = "Hungary";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        //assert
        assertTrue(result1);
    }

    @Test
    void hungary_HU_isIncorrect(){
        //arrange
        String countryName = "Hungary";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678901";
        String stringNIF2 = "123456789";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        //assert
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    void ireland_IE_isCorrect(){
        //arrange
        String countryName = "Ireland";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567A";
        String stringNIF2 = "1234567AB";
        String stringNIF3 = "1234567aB";
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
    void ireland_IE_isIncorrect(){
        //arrange
        String countryName = "Ireland";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567ABC";
        String stringNIF2 = "123456AB";
        //act
        boolean result1 = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        //assert
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    void italy_IT_isCorrect(){
        //arrange
        String countryName = "Italy";
        Country country = new Country(countryName);
        String stringNIF1 = "ABCDEF12A12A123A";
        //act
        boolean result = Utils.NIFValidator(country,stringNIF1);
        //assert
        assertTrue(result);
    }

    @Test
    void italy_IT_isIncorrect(){
        //arrange
        String countryName = "Italy";
        Country country = new Country(countryName);
        String stringNIF1 = "ABCDE12A12A123A";
        String stringNIF2 = "ABCDEF123A12A123A";
        String stringNIF3 = "ABCDEF12AB12A123A";
        String stringNIF4 = "ABCDEF12A123A123A";
        //act
        boolean result = Utils.NIFValidator(country,stringNIF1);
        boolean result2 = Utils.NIFValidator(country,stringNIF2);
        boolean result3 = Utils.NIFValidator(country,stringNIF3);
        boolean result4 = Utils.NIFValidator(country,stringNIF4);
        //assert
        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    void latvia_LV_isCorrect(){
        //arrange
        String countryName = "Latvia";
        Country country = new Country(countryName);
        String stringNIF = "12345678901";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertTrue(result);
    }

    @Test
    void latvia_LV_isIncorrect(){
        //arrange
        String countryName = "Latvia";
        Country country = new Country(countryName);
        String stringNIF = "1234567890";
        String stringNIF2 = "123456789012";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void lithuania_LT_isCorrect(){
        //arrange
        String countryName = "Lithuania";
        Country country = new Country(countryName);
        String stringNIF = "12345678901";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertTrue(result);
    }

    @Test
    void lithuania_LT_isIncorrect(){
        //arrange
        String countryName = "Lithuania";
        Country country = new Country(countryName);
        String stringNIF = "1234567890";
        String stringNIF2 = "123456789012";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void luxembourg_LU_isCorrect(){
        //arrange
        String countryName = "Luxembourg";
        Country country = new Country(countryName);
        String stringNIF = "1234567890123";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF);
        //assert
        assertTrue(result);
    }

    @Test
    void luxembourg_LU_isIncorrect(){
        //arrange
        String countryName = "Luxembourg";
        Country country = new Country(countryName);
        String stringNIF1 ="12345678901234";
        String stringNIF2 = "123456789012";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void malta_MT_isCorrect(){
        //arrange
        String countryName = "Malta";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567M";
        String stringNIF2 = "1234567G";
        String stringNIF3 = "1234567A";
        String stringNIF4 = "1234567P";
        String stringNIF5 = "1234567L";
        String stringNIF6 = "1234567H";
        String stringNIF7 = "1234567B";
        String stringNIF8 = "1234567Z";
        //act
        boolean result1 = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        boolean result4 = Utils.NIFValidator(country, stringNIF4);
        boolean result5 = Utils.NIFValidator(country, stringNIF5);
        boolean result6 = Utils.NIFValidator(country, stringNIF6);
        boolean result7 = Utils.NIFValidator(country, stringNIF7);
        boolean result8 = Utils.NIFValidator(country, stringNIF8);
        //assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
        assertTrue(result6);
        assertTrue(result7);
        assertTrue(result8);
    }

    @Test
    void malta_MT_isIncorrect(){
        //arrange
        String countryName = "Malta";
        Country country = new Country(countryName);
        String stringNIF1 = "123456M";
        String stringNIF2 = "1234567F";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void netherlands_NL_isCorrect(){
        //arrange
        String countryName = "Netherlands";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        //assert
        assertTrue(result);
    }

    @Test
    void netherlands_NL_isIncorrect(){
        //arrange
        String countryName = "Netherlands";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        String stringNIF2 = "12345678";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void poland_PL_isCorrect(){
        //arrange
        String countryName = "Poland";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        String stringNIF2 = "12345678901";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void poland_PL_isIncorrect(){
        //arrange
        String countryName = "Poland";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        String stringNIF2 = "123456789012";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void portugal_PT_isCorrect(){
        //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        //assert
        assertTrue(result);
    }

    @Test
    void portugal_PT_isIncorrect(){
       //arrange
        String countryName = "Portugal";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        String stringNIF2 = null;
        String stringNIF3 = "12345678B";
       //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
       //assert
        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void romania_RO_isCorrect(){
        //arrange
        String countryName = "Romania";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890123";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        //assert
        assertTrue(result);
    }

    @Test
    void romania_RO_isIncorrect(){
        //arrange
        String countryName = "Romania";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789012";
        String stringNIF2 = "12345678901234";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void slovakia_SK_isCorrect(){
        //arrange
        String countryName = "Slovakia";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        String stringNIF2 = "123456789";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void slovakia_SK_isIncorrect(){
        //arrange
        String countryName = "Slovakia";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678901";
        String stringNIF2 = "12345678";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void slovenia_SI_isCorrect(){
        //arrange
        String countryName = "Slovenia";
        Country country = new Country(countryName);
        String stringNIF1 = "12345678";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        //assert
        assertTrue(result);
    }

    @Test
    void slovenia_SI_isIncorrect(){
        //arrange
        String countryName = "Slovenia";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567";
        String stringNIF2 = "123456789";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void spain_ES_isCorrect(){
        //arrange
        String countryName = "Spain";
        Country country = new Country(countryName);
        String stringNIF1 = "11234567A";
        String stringNIF2 = "L1234567B";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void spain_ES_isIncorrect(){
        //arrange
        String countryName = "Spain";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567A";
        String stringNIF2 = "A123456789";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

    @Test
    void sweden_SE_isCorrect(){
        //arrange
        String countryName = "Sweden";
        Country country = new Country(countryName);
        String stringNIF1 = "1234567890";
        String stringNIF2 = "12345678901";
        String stringNIF3 = "123456789012";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        boolean result3 = Utils.NIFValidator(country, stringNIF3);
        //assert
        assertTrue(result);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    void sweden_SE_isIncorrect(){
        //arrange
        String countryName = "Sweden";
        Country country = new Country(countryName);
        String stringNIF1 = "123456789";
        String stringNIF2 = "1234567890123";
        //act
        boolean result = Utils.NIFValidator(country, stringNIF1);
        boolean result2 = Utils.NIFValidator(country, stringNIF2);
        //assert
        assertFalse(result);
        assertFalse(result2);
    }

}