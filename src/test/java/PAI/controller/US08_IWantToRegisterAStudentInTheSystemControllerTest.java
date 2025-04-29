package PAI.controller;

import PAI.VOs.*;

import PAI.domain.Student;

import PAI.service.IStudentService;
import PAI.service.StudentServiceImpl;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US08_IWantToRegisterAStudentInTheSystemControllerTest {

    @Test
    void IWantToRegisterAStudentInTheSystemControllerConstructorTest() {
        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);

        //act
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        //assert
        assertNotNull(ctrl);
    }

    @Test
    void nullRepositoryDoesNotCreateObject() {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new US08_IWantToRegisterAStudentInTheSystemController(null));

        //assert
        assertEquals(exception.getMessage(), "Student service cannot be null!");
    }

    @Test
    void registerStudentWithValidParametersReturnsTrue() throws Exception {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "1234-56";
        String location = "Coimbra";
        String country = "Portugal";
        Student studentDouble = mock(Student.class);

        when(studentServiceDouble.registerStudent(
                any(StudentID.class), any(Name.class), any(NIF.class),
                any(PhoneNumber.class), any(Email.class), any(Street.class), any(PostalCode.class),
                any(Location.class), any(Country.class), any(StudentAcademicEmail.class)))
                .thenReturn(studentDouble);

        //act
        Student result = ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country);

        //assert
        assertEquals(studentDouble, result);
    }

    static Stream<Arguments> studentIDIsInvalid() {
        return Streams.of(
                Arguments.of(999999),
                Arguments.of(2000000)
        );
    }

    @ParameterizedTest
    @MethodSource("studentIDIsInvalid")
    void studentIsNotRegisteredBecauseStudentIDIsInvalid(int uniqueNumber) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "1234-56";
        String location = "Coimbra";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("Invalid unique number", exception.getMessage());
    }

    static Stream<Arguments> nameIsInvalid() {
        return Streams.of(
                Arguments.of((String) null),
                Arguments.of(" "),
                Arguments.of(""),
                Arguments.of("maria"),
                Arguments.of("maria3"),
                Arguments.of("ma"),
                Arguments.of("A".repeat(101))
        );
    }

    @ParameterizedTest
    @MethodSource("nameIsInvalid")
    void studentIsNotRegisteredBecauseNameIsInvalid(String name) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "1234-56";
        String location = "Coimbra";
        String country = "Portugal";


        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("Name does not meet the validation requirements.", exception.getMessage());
    }

    static Stream<Arguments> NIFIsInvalid() {
        return Streams.of(
                Arguments.of( null, "Portugal", "NIF cannot be empty."),
                Arguments.of(" ", "Portugal", "NIF cannot be empty."),
                Arguments.of("", "Portugal", "NIF cannot be empty."),
                Arguments.of("1", "Portugal", "NIF is Invalid"),
                Arguments.of("ABCDEFGHI", "Portugal", "NIF is Invalid"),
                Arguments.of("123456789", null, "This country is not valid."),
                Arguments.of("123456789", "", "This country is not valid."),
                Arguments.of("123456789", " ", "This country is not valid.")
        );
    }

    @ParameterizedTest
    @MethodSource("NIFIsInvalid")
    void studentIsNotRegisteredBecauseNIFIsNull(String nif, String nifCountry, String expectedMessage) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "1234-56";
        String location = "Coimbra";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    static Stream<Arguments> phoneNumberIsInvalid() {
        return Streams.of(
                Arguments.of( null, "987654321", "Country Code cannot be empty"),
                Arguments.of( "", "987654321", "Country Code cannot be empty"),
                Arguments.of( " ", "987654321", "Country Code cannot be empty"),
                Arguments.of("+351", null, "Number cannot be empty"),
                Arguments.of("+351", "", "Number cannot be empty"),
                Arguments.of("+351", " ", "Number cannot be empty"),
                Arguments.of("+abc", "987654321", "Country Code is invalid"),
                Arguments.of("+1b3", "987654321", "Country Code is invalid"),
                Arguments.of("+351", "abcd1234", "Phone Number is invalid"),
                Arguments.of("+351", "1234!@#5678", "Phone Number is invalid")
        );
    }

    @ParameterizedTest
    @MethodSource("phoneNumberIsInvalid")
    void studentIsNotRegisteredBecausePhoneNumberIsInvalid(String countryCode, String phoneNumber, String expectedMessage) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "1234-56";
        String location = "Coimbra";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    static Stream<Arguments> emailIsInvalid() {
        return Streams.of(
                Arguments.of((String) null),
                Arguments.of( ""),
                Arguments.of( " "),
                Arguments.of("emailemail"),
                Arguments.of("@email.email"),
                Arguments.of("email@email-pt")
        );
    }

    @ParameterizedTest
    @MethodSource("emailIsInvalid")
    void studentIsNotRegisteredBecauseEmailIsInvalid(String email) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String street = "Praça da Canção";
        String postalCode = "12345";
        String location = "Coimbra";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("This email is not valid.", exception.getMessage());
    }

    static Stream<Arguments> streetIsInvalid() {
        return Streams.of(
                Arguments.of((String) null),
                Arguments.of( ""),
                Arguments.of( " ")
        );
    }

    @ParameterizedTest
    @MethodSource("streetIsInvalid")
    void testIfStudentIsNotRegisteredBecauseStreetIsInvalid(String street) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String postalCode = "12345";
        String location = "Coimbra";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("Street cannot be empty.", exception.getMessage());
    }

    static Stream<Arguments> postalCodeIsInvalid() {
        return Streams.of(
                Arguments.of((String) null),
                Arguments.of( ""),
                Arguments.of( " ")
        );
    }

    @ParameterizedTest
    @MethodSource("postalCodeIsInvalid")
    void studentIsNotRegisteredBecausePostalCodeIsInvalid(String postalCode) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String location = "Coimbra";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("Postal code cannot be empty!", exception.getMessage());
    }

    static Stream<Arguments> locationIsInvalid() {
        return Streams.of(
                Arguments.of((String) null),
                Arguments.of( "")
        );
    }

    @ParameterizedTest
    @MethodSource("locationIsInvalid")
    void studentIsNotRegisteredBecauseLocationIsInvalid(String location)  {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "3440-307";
        String country = "Portugal";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("Location name cannot be null or empty", exception.getMessage());
    }

    static Stream<Arguments> countryIsInvalid() {
        return Streams.of(
                Arguments.of((String) null),
                Arguments.of( ""),
                Arguments.of( " ")
        );
    }

    @ParameterizedTest
    @MethodSource("countryIsInvalid")
    void studentIsNotRegisteredBecauseCountryIsInvalid(String country) {

        //arrange
        IStudentService studentServiceDouble = mock(IStudentService.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentServiceDouble);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "3440-307";
        String location = "Coimbra";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country));

        //assert
        assertEquals("This country is not valid.", exception.getMessage());
    }
}


