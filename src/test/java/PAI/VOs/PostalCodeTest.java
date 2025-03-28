package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PostalCodeTest {

    @Test
    void shouldConstructPostalCodeIfValidString() {
        //Arrange

        //Act
        PostalCode pc1 = new PostalCode ("4452-357");

    }

    public static Stream<Arguments> provideInvalidStringForPostalCode() {
        return Stream.of(
                arguments(""),
                arguments(" "),
                arguments((String) null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStringForPostalCode")
    void shouldNotConstructPostalCodeIfInvalidString(String postalCode) {
        //arrange

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCode(postalCode));
    }

}