package PAI.VOs;

import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class CountryTest {

    @Test
    void validStringCreatesCountry(){
        //arrange
        String country = "Portugal";
        //act
        Country addressCountry = new Country(country);
        //assert
        Assertions.assertNotNull(addressCountry);
    }

    @Test
    void emptyStringThrowsException(){
        //arrange
        String country = "";
        //act + assert
        Assertions.assertThrows(Exception.class, () -> new Country(country));
    }

    @Test
    void nullStringThrowsException(){
        //arrange
        String country = null;
        //act + assert
        Assertions.assertThrows(Exception.class, () -> new Country(country));
    }

    static Stream<Arguments> testCountryWithInvalidInputs() {
        return Stream.of(
                arguments("Portugal!"),
                arguments("P0rtugal"),
                arguments("Portugal_"),
                arguments("Portugal@Porto"),
                arguments("  Portugal"),
                arguments("Portugal  "),
                arguments("Portugal4")
        );
    }
    @ParameterizedTest
    @MethodSource("testCountryWithInvalidInputs")
    void invalidCountryInputShouldReturnException(String country) throws IllegalArgumentException {
        //arrange

        //act + assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Country(country));
    }

    @Test
    void shouldReturnTrueIfSameObject(){
        //arrange
        String country = "Portugal";
        Country countryO = new Country(country);
        //act
        boolean result = countryO.equals(countryO);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfSameCountryName(){
        //arrange
        String country = "Portugal";
        Country country1 = new Country(country);
        Country country2 = new Country(country);
        //act
        boolean result = country1.equals(country2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAnotherObject(){
        //arrange
        String country = "Portugal";
        Country countryO = new Country(country);
        //act
        boolean result = countryO.equals(country);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCountriesHaveDifferentNames(){
        //arrange
        String countryP = "Portugal";
        Country country1 = new Country(countryP);
        String countryA = "Austria";
        Country country2 = new Country(countryA);
        //act
        boolean result = country1.equals(country2);
        //assert
        assertFalse(result);
    }
}