package PAI.VOs;

import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void shouldCreatPhoneNumber() throws Exception{
        //act
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");

        //assert
        assertNotNull(phoneNumber);
    }

    @Test
    void nullCountryCodeThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber(null, "999999999"));
    }

    @Test
    void emptyCountryCodeThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("", "999999999"));
    }

    @Test
    void nullNumberThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("+351", null));
    }

    @Test
    void emptyNumberThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("+351", ""));
    }

    @Test
    void invalidCountryCodeThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new PhoneNumber("000", "999999999"));
    }

    static Stream<Arguments> testCountryCodeWithInvalidInputs() {
        return Streams.of(
                Arguments.of("000", "999999999"),
                Arguments.of("351", "999999999"),
                Arguments.of("-351", "999999999"),
                Arguments.of("+", "999999999"),
                Arguments.of("+35111", "999999999"),
                Arguments.of("+3 1", "999999999"),
                Arguments.of("+3-1", "999999999"),
                Arguments.of("+3 1", "999999999"),
                Arguments.of("+0001", "999999999"),
                Arguments.of("+000", "999999999"),
                Arguments.of("+abc", "999999999"),
                Arguments.of("+1a2", "999999999"),
                Arguments.of("+1#2", "999999999"),
                Arguments.of("+11 ", "999999999"),
                Arguments.of("+ 11", "999999999")

        );
    }
    @ParameterizedTest
    @MethodSource("testCountryCodeWithInvalidInputs")
    void invalidEmailInputShouldReturnException(String countryCode, String number) {
        //arrange

        //act + assert
        Assertions.assertThrows(Exception.class, () -> new PhoneNumber(countryCode, number));
    }
}