package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StreetTest {
    @Test
    void shouldConstructStreetIfValidString() {
        //Arrange

        //Act
        Street s1 = new Street ("Rua de São Pedro, nº12");

    }

    public static Stream<Arguments> provideInvalidStringForStreet() {
        return Stream.of(
                arguments(""),
                arguments(" "),
                arguments((String) null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStringForStreet")
    void shouldNotConstructStreetIfInvalidString(String street) throws Exception {
        //arrange

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new Street(street));
    }
}