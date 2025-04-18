package PAI.VOs;

import org.apache.commons.lang3.stream.Streams;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;


class EmailTest {

    @Test
    void validStringCreatesEmail(){
        //arrange
        String email = "abc@email.pt";
        //act
        Email personalEmail = new Email(email);
        //assert
        assertNotNull(personalEmail);
    }

    @Test
    void emptyStringThrowsException(){
        //arrange
        String email = "";
        //act + assert
        assertThrows(Exception.class, () -> new Email(email));
    }

    @Test
    void nullStringThrowsException(){
        //arrange
        String email = null;
        //act + assert
        assertThrows(Exception.class, () -> new Email(email));
    }

    static Stream<Arguments> testEmailWithInvalidInputs() {
        return Streams.of(
                Arguments.of("emailemail"),
                Arguments.of("@email.email"),
                Arguments.of("email@email..pt"),
                Arguments.of("email@email."),
                Arguments.of("email@email"),
                Arguments.of("email@email-pt"),
                Arguments.of("email@email-.pt"),
                Arguments.of("email@email.p"),
                Arguments.of("ema?il@email.pt"),
                Arguments.of("email@em??ail.pt"),
                Arguments.of("email@email-co.uk"),
                Arguments.of("email@email.co-uk"),
                Arguments.of("email@email.pt?")
        );
    }
    @ParameterizedTest
    @MethodSource("testEmailWithInvalidInputs")
    void invalidEmailInputShouldReturnException(String email) throws IllegalArgumentException {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Email(email));
    }

    @Test
    void getEmailShouldReturnEmail() {
        //arrange
        Email email = new Email("abc@email.pt");

        //act
        String result = email.getEmail();

        //assert
        assertEquals("abc@email.pt", result);
    }
}