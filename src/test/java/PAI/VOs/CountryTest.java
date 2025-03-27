package PAI.VOs;

import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

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
}