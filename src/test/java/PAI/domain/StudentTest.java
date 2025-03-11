package PAI.domain;

import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentTest {

    @Test
    void validAttributesCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act
        Student student1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);
    }

    static Stream<Arguments> test_NIF_WithDifferentValidInputs() {
        return Streams.of(
                Arguments.of("A12345678"),
                Arguments.of("AB12345678"),
                Arguments.of("123456789012345"),
                Arguments.of("A1234567890123E")
        );
    }
    @ParameterizedTest
    @MethodSource("test_NIF_WithDifferentValidInputs")
    void valid_NIF_InputShouldCreateStudent(String NIF) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        new Student("1234567", "Rita", NIF, "963741258", "rita@gmail.com", address1);
    }

    static Stream<Arguments> testPhoneNumberWithDifferentValidInputs() {
        return Streams.of(
                Arguments.of("913 322 123"),
                Arguments.of("913322123"),
                Arguments.of("+351 913 322 123"),
                Arguments.of("+351913322123"),
                Arguments.of("+351 913322123"),
                Arguments.of("+44-1234-567890"),
                Arguments.of("+123 (4567) 890-123")
        );
    }
    @ParameterizedTest
    @MethodSource("testPhoneNumberWithDifferentValidInputs")
    void testPhoneNumberWithValidInputsCreatesStudent(String phoneNumber) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        new Student("1234567", "Rita", "123456789", phoneNumber, "rita@gmail.com", address1);
    }

    static Stream<Arguments> testEmailWithDifferentValidInputs() {
        return Streams.of(
                Arguments.of("test@email.com"),
                Arguments.of("email.name@domain.co.uk"),
                Arguments.of("email_em123@gmail.com"),
                Arguments.of("email@sub.test.org"),
                Arguments.of("firstname-lastname@email.net"),
                Arguments.of("user+alias@domain.io"),
                Arguments.of("jon-cobi@email.com"),
                Arguments.of("admin@server.live"),
                Arguments.of("danijose@gmail.com")
        );
    }
    @ParameterizedTest
    @MethodSource("testEmailWithDifferentValidInputs")
    void testEmailWithValidInputsCreatesStudent(String email) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        new Student("1234567", "Rita", "123456789", "963741258", email, address1);
    }

    @Test
    void zeroAsUniqueNumberDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("0000000", "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void negativeUniqueNumberDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("-1000000", "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    static Stream<Arguments> testUniqueNumber_Null_Blank_InvalidLength() {
        return Streams.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((Object) null),
                Arguments.of("123"),
                Arguments.of("1234567890")
        );
    }
    @ParameterizedTest
    @MethodSource("testUniqueNumber_Null_Blank_InvalidLength")
    void invalidUniqueNumberInputShouldReturnException(String uniqueNumber) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student(uniqueNumber, "Rita", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyNameDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void blankNameDoesNotCreateAnObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", " ", "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void nullNameDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", null, "123456789", "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyNIFDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Joaquim", "", "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void blankNIFDoesNotCreateAnObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Joaquim", " ", "933741758", "joaquim@gmail.com", address1));
    }

    @Test
    void nullNIFDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Joaquim", null, "933741758", "joaquim@gmail.com", address1));
    }

    static Stream<Arguments> test_NIF_WithInvalidInputs() {
        return Streams.of(
                Arguments.of("1234567890123456"),
                Arguments.of("1234!56789"),
                Arguments.of("A1234 56789"),
                Arguments.of("1234#56789"),
                Arguments.of("1234SD6789")
        );
    }
    @ParameterizedTest
    @MethodSource("test_NIF_WithInvalidInputs")
    void invalid_NIF_InputShouldReturnException(String NIF) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Rita", NIF, "963741258", "rita@gmail.com", address1));
    }

    @Test
    void emptyPhoneDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Rute", "569874126", "", "rute@gmail.com", address1));
    }

    @Test
    void blankPhoneDoesNotCreateAnObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Joaquim", "569874126", " ", "joaquim@gmail.com", address1));
    }

    @Test
    void nullPhoneDoesNotCreateObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Rute", "32165498", null, "rute@gmail.com", address1));
    }

    static Stream<Arguments> testPhoneNumberWithInvalidInputs() {
        return Streams.of(
                Arguments.of("12345"),
                Arguments.of("+12345"),
                Arguments.of("123-45-678"),
                Arguments.of("+12 345 67 89"),
                Arguments.of("+12 345 (456)-789"),
                Arguments.of("12.34.56.78.90"),
                Arguments.of("+1(2345)67890"),
                Arguments.of("91234567(8)"),
                Arguments.of("91234EC78")
        );
    }
    @ParameterizedTest
    @MethodSource("testPhoneNumberWithInvalidInputs")
    void invalidPhoneNumberInputShouldReturnException(String phoneNumber) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Rita", "32165498", phoneNumber, "rita@gmail.com", address1));
    }

    @Test
    void emptyEmailDoesNotCreateObject () {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Pedro", "159753824", "963996987", "", address1));
    }

    @Test
    void blankEmailDoesNotCreateAnObject() {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Joaquim", "569874126", "963996987", " ", address1));
    }

    @Test
    void nullEmailDoesNotCreateObject () {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Pedro", "159753824", "963996987", null, address1));
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
    void invalidEmailInputShouldReturnException(String email) {

        //arrange
        Address address1 = mock(Address.class);

        //act + assert
        assertThrows(Exception.class, () -> new Student("1234567", "Rita", "32165498", "963996987", email, address1));
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameUniqueNumber() {
        // Arrange
        Address address1 = mock(Address.class);
        Student student1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);


        Address address2 = mock(Address.class);
        Student student2 = new Student("1234567", "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        // Act
        boolean result = student1.hasSameUniqueNumber(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameUniqueNumber() {
        // Arrange
        Address address1 = mock(Address.class);
        Student student1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);


        Address address2 = mock(Address.class);
        Student student2 = new Student("1345678", "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        // Act
        boolean result = student1.hasSameUniqueNumber(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoStudentsHaveTheSameNIF() {
        // Arrange
        Address address1 = mock(Address.class);
        Address address2 = mock(Address.class);

        Student student1 = new Student("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student student2 = new Student("1345678", "Daniela", "123456789", "911855911", "danijose@gmail.com", address2);

        // Act
        boolean result = student1.hasSameNIF(student2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoStudentsDontHaveTheSameNIF() {
        // Arrange
        Address address1 = mock(Address.class);
        Address address2 = mock(Address.class);

        Student student1 = new Student("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);
        Student student2 = new Student("1345678", "João", "112233445", "919999999", "joao@gmail.com", address2);

        // Act
        boolean result = student1.hasSameNIF(student2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTheUniqueNumber() {
        //assert
        Address address1 = mock(Address.class);
        Student student1 = new Student("1234567", "Daniela", "123456789", "911855911", "danijose@gmail.com", address1);

        //act
        String result = student1.getUniqueNumber();

        //assert
        assertEquals(result, "1234567");
    }


    @Test
    void shouldReturnTrueIfTheUniqueNumberIsFoundInAStudent() {
        // Arrange
        Address address1 = mock(Address.class);
        Student student1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        // Act
        boolean result = student1.hasThisUniqueNumber("1234567");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheUniqueNumberIsNotFoundInAStudent() {
        // Arrange
        Address address1 = mock(Address.class);
        Student student1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        // Act
        boolean result = student1.hasThisUniqueNumber("1231117");

        // Assert
        assertFalse(result);
    }
}