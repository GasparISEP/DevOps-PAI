package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AcronymTest {

    static Stream<Arguments> testValidAcronym(){
        return Stream.of(
                Arguments.of("A"),
                Arguments.of("B123"),
                Arguments.of("AB12"),
                Arguments.of("XYZ"),
                Arguments.of("X9")
        );
    }
    @ParameterizedTest
    @MethodSource("testValidAcronym")
    void shouldCreateAcronymVO(String acronym){
        //arrange

        //act
        new Acronym(acronym);
        //assert
    }

    static Stream<Arguments> testInvalidAcronym(){
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((Object) null),
                Arguments.of("a123"),
                Arguments.of("123A"),
                Arguments.of("X_99"),
                Arguments.of("A 99"),
                Arguments.of("9"),
                Arguments.of("Ç99"),
                Arguments.of("B-12"),
                Arguments.of("Z1SD2")
        );
    }
    @ParameterizedTest
    @MethodSource("testInvalidAcronym")
    void shouldReturnExceptionWhenCreatingAcronymWithInvalidInputs(String acronym){
        //arrange

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new Acronym(acronym));
    }

    @Test
    void shouldReturnTrueIfAcronymsAreEqual() {
        //arrange
        Acronym acronym1 = new Acronym("APROG");
        Acronym acronym2 = new Acronym("APROG");
        //act
        boolean result = acronym1.equals(acronym2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAcronymsAreNotEqual() {
        //arrange
        Acronym acronym1 = new Acronym("APROG");
        Acronym acronym2 = new Acronym("APROG1");
        //act
        boolean result = acronym1.equals(acronym2);
        //assert
        assertFalse(result);
    }
}