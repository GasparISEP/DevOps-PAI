package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        //Arrange (provided by @MethodSource)

        //Act & Assert (constructor should not throw any exception)
        new Acronym(acronym);
    }

    static Stream<Arguments> testInvalidAcronym(){
        return Stream.of(
                Arguments.of("", "Acronym must not be blank"),
                Arguments.of(" ", "Acronym must not be blank"),
                Arguments.of((Object) null, "Acronym must not be null"),
                Arguments.of("a123", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("123A", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("X_99", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("A 99", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("9", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("Ç99", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("B-12", "Acronym must contain only uppercase letters, followed by optional digits"),
                Arguments.of("Z1SD2", "Acronym must contain only uppercase letters, followed by optional digits")
        );
    }
    @ParameterizedTest
    @MethodSource("testInvalidAcronym")
    void shouldReturnExceptionWhenCreatingAcronymWithInvalidInputs(String acronym, String expectedMessage){
        //Arrange (provided by @MethodSource)
        //Act
        Executable action = () -> new Acronym(acronym);
        //Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, action);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenComparingWithItself() {
        //Arrange
        Acronym acronym = new Acronym("APROG");
        //Act
        boolean result = acronym.equals(acronym);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenComparingWithDifferentObjectType() {
        //Arrange
        Acronym acronym = new Acronym("APROG");
        Object diferentObject = new Object();
        //Act
        boolean result = acronym.equals(diferentObject);
        //Assert
        assertFalse(result);
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

    @Test
    public void shouldReturnCorrectValueWhenCallingGetAcronym() {
        //Arrange
        Acronym acronym = new Acronym("ABC123");
        //Act
        String result = acronym.getAcronym();
        //Assert
        assertEquals("ABC123", result);
    }

    @Test
    public void shouldReturnEqualWhenTwoObjectsHaveTheSameHashCode() {
        //Arrange
        Acronym acronym1 = new Acronym("ABC123");
        Acronym acronym2 = new Acronym("ABC123");
        //Act & Assert
        assertEquals(acronym1.hashCode(), acronym2.hashCode());
    }

    @Test
    public void shouldReturnNotEqualWhenTwoObjectsHaveDifferentHashCodes() {
        //Arrange
        Acronym acronym1 = new Acronym("ABC123");
        Acronym acronym2 = new Acronym("XYZ789");
        //Act & Assert
        assertNotEquals(acronym1.hashCode(), acronym2.hashCode());
    }
}