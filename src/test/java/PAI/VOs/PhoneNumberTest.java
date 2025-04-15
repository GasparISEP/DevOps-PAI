package PAI.VOs;

import PAI.domain.course.Course;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
                Arguments.of("+ 11", "999999999"),
                Arguments.of("++123", "999999999"),
                Arguments.of("+++1", "999999999"),
                Arguments.of("1", "999999999"),
                Arguments.of("+001", "999999999"),
                Arguments.of("+-351", "999999999"),
                Arguments.of("+3 1", "999999999"),
                Arguments.of("+abc", "999999999")
        );
    }
    @ParameterizedTest
    @MethodSource("testCountryCodeWithInvalidInputs")
    void invalidCountryCodeInputShouldReturnException(String countryCode, String number) {
        //arrange

        //act + assert
        Assertions.assertThrows(Exception.class, () -> new PhoneNumber(countryCode, number));
    }

    static Stream<Arguments> testCountryCodeWithvalidInputs() {
        return Streams.of(
                Arguments.of("+1", "999999999"),
                Arguments.of("+7", "999999999"),
                Arguments.of("+20", "999999999"),
                Arguments.of("+33", "999999999"),
                Arguments.of("+44", "999999999"),
                Arguments.of("+49", "999999999"),
                Arguments.of("+55", "999999999"),
                Arguments.of("+91", "999999999"),
                Arguments.of("+351", "999999999"),
                Arguments.of("+358", "999999999"),
                Arguments.of("+380", "999999999"),
                Arguments.of("+972", "999999999"),
                Arguments.of("+1242", "999999999"),
                Arguments.of("+1264", "999999999")
        );
    }
    @ParameterizedTest
    @MethodSource("testCountryCodeWithvalidInputs")
    void validCountryCodeInputShouldReturnValueObject(String countryCode, String number) throws Exception {
        //arrange
        //act
        PhoneNumber phoneNumber = new PhoneNumber(countryCode, number);

        //assert
        assertNotNull(phoneNumber);
    }

    static Stream<Arguments> testPhoneNumbersWithInvalidInputs() {
        return Streams.of(
                Arguments.of("+111", "12345"),
                Arguments.of("+111", "1234567890123456"),
                Arguments.of("+111", ""),
                Arguments.of("+111", " "),
                Arguments.of("+111", "123 456 789 012 345 6"),
                Arguments.of("+111", "abcd1234"),
                Arguments.of("+111", "1234!@#5678"),
                Arguments.of("+111", "(123) 45"),
                Arguments.of("+111", "555-abc-7890"),
                Arguments.of("+111", "１２３４５６"),
                Arguments.of("+111", "٠١٢٣٤٥٦")
        );
    }
    @ParameterizedTest
    @MethodSource("testPhoneNumbersWithInvalidInputs")
    void invalidPhoneNumbersInputShouldReturnException(String countryCode, String number) {
        //arrange

        //act + assert
        Assertions.assertThrows(Exception.class, () -> new PhoneNumber(countryCode, number));
    }

    static Stream<Arguments> testPhoneNumbersWithValidInputs() {
        return Streams.of(
                Arguments.of("+111", "123456"),
                Arguments.of("+111", "987654321"),
                Arguments.of("+111", "123456789012345"),
                Arguments.of("+111", "000001"),
                Arguments.of("+111", "999999999999999"),
                Arguments.of("+111", "000001"),
                Arguments.of("+111", "000000000000000"),
                Arguments.of("+111", "555123456"),
                Arguments.of("+111", "351987654321"),
                Arguments.of("+111", "918273645"),
                Arguments.of("+111", "1234567890"),
                Arguments.of("+111", "4912345678901"),
                Arguments.of("+111", "447123456789"),
                Arguments.of("+111", "8613812345678")
                );
    }
    @ParameterizedTest
    @MethodSource("testPhoneNumbersWithValidInputs")
    void validPhoneNumbersInputShouldReturnValueObject(String countryCode, String number) throws Exception{
        //arrange
        //act
        PhoneNumber phoneNumber = new PhoneNumber(countryCode, number);

        //assert
        assertNotNull(phoneNumber);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameObject() throws Exception {
        //Arrange
        String countryCode = "+351";
        String number = "999999999";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);

        //Act
        boolean result = phoneNumberTest.equals(phoneNumberTest);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForEqualsWithNullObjectToCompare() throws Exception {
        //Arrange
        String countryCode = "+351";
        String number = "999999999";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);
        //Act
        boolean result = phoneNumberTest.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueEqualsWhenComparedWithSameInstanceOfObject() throws Exception{
        //Arrange
        String countryCode = "+351";
        String number = "999999999";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);
        PhoneNumber phoneNumberTest2 = new PhoneNumber(countryCode,number);
        //Act
        boolean result = phoneNumberTest.equals(phoneNumberTest2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForEqualsOfPhoneNumberWithSameParameters() throws Exception{
        //Arrange
        String countryCode = "+351";
        String number = "999999999";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);
        PhoneNumber phoneNumberTest2 = new PhoneNumber(countryCode,number);
        //Act
        boolean result = phoneNumberTest.equals(phoneNumberTest2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfClassesAreDifferent() throws Exception{
        //Arrange
        String countryCode = "+351";
        String number = "999999999";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);
        Course course = mock(Course.class);

        //Act
        boolean result = phoneNumberTest.equals(course);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCountryCodeAreNotTheSame() throws Exception {
        //Arrange
        String countryCode = "+351";
        String countryCode2 = "+352";
        String number = "999999999";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);
        PhoneNumber phoneNumberTest2 = new PhoneNumber(countryCode2,number);

        //Act
        boolean result = phoneNumberTest.equals(phoneNumberTest2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfNumbersAreNotTheSame() throws Exception {
        //Arrange
        String countryCode = "+351";
        String number = "999999999";
        String number2 = "999999998";

        PhoneNumber phoneNumberTest = new PhoneNumber(countryCode,number);
        PhoneNumber phoneNumberTest2 = new PhoneNumber(countryCode,number2);

        //Act
        boolean result = phoneNumberTest.equals(phoneNumberTest2);

        //Assert
        assertFalse(result);
    }

    @Test
    void toStringShouldBeEqualToObject() throws Exception {
        //arrange
        String countryCode = "+351";
        String number = "999999999";
        PhoneNumber phoneNumber = new PhoneNumber(countryCode,number);
        String expected = "Country Code = +351 PhoneNumber = 999999999";

        //act
        String phoneNumberAttributtes = phoneNumber.toString();

        //asert
        assertEquals(expected, phoneNumberAttributtes);
    }

    @Test
    void getCountryCodeShouldReturnCountryCode() throws Exception {
        //arrange
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");

        //act
        String result = phoneNumber.getCountryCode();

        //assert
        assertEquals("+351", result);
    }

    @Test
    void getNumberShouldReturnNumber() throws Exception {
        //arrange
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");

        //act
        String result = phoneNumber.getNumber();

        //assert
        assertEquals("912345678", result);
    }
}