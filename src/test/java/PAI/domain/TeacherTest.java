package PAI.domain;

import PAI.factory.AddressFactory;
import PAI.factory.TeacherCareerProgressionFactoryImpl;
import PAI.factory.TeacherCareerProgressionListFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;


class TeacherTest {

    private TeacherCategory _tcDouble;
    private AddressFactory _addressFactoryDouble;
    private Address _addressDouble;
    private Department _dptDouble;
    private TeacherCareerProgressionFactoryImpl _tcpFactoryDouble;
    private TeacherCareerProgression _tcpDouble;
    private TeacherCareerProgressionListFactoryImpl _tcplFDouble;
    private List<TeacherCareerProgression> _listDouble;

    private void createDoubles () {
        _tcDouble = mock(TeacherCategory.class);
        _addressFactoryDouble = mock(AddressFactory.class);
        _addressDouble = mock(Address.class);
        _dptDouble = mock(Department.class);
        _tcpFactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        _tcpDouble = mock(TeacherCareerProgression.class);
        _tcplFDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
    }

    private void createListDouble () {
        _listDouble = mock(List.class);
        when(_tcplFDouble.createTeacherCareerProgressionList()).thenReturn(_listDouble);
    }

    @Test
    void shouldCreateTeacherWhenAllFieldsAreValid() throws IllegalArgumentException {
        // Arrange
        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("15-04-2005", _tcDouble, 70)).thenReturn(_tcpDouble);
        createListDouble();

        // Act
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", _addressFactoryDouble,"15-04-2005", _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Assert
        assertNotNull(teacher);
    }

    @Test
    void shouldCreateTeacher_WhenAllFieldsAreValid_WithTwoLettersName() throws IllegalArgumentException {
        //arrange
        createDoubles();
        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("15-04-2005", _tcDouble, 70)).thenReturn(_tcpDouble);
        createListDouble();

        //act
        Teacher teacher = new Teacher("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", _addressFactoryDouble,"15-04-2005", _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //assert
        assertNotNull(teacher);
    }

    @Test
    void shouldCreateTeacher_WhenAllFieldsAreValid_WithAHundredLettersName() throws IllegalArgumentException {
        //arrange
        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("15-04-2005", _tcDouble, 70)).thenReturn(_tcpDouble);
        createListDouble();

        //act
        Teacher teacher = new Teacher("ABC", "J".repeat(100), "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua das Flores","4444-098","Porto","Portugal", _addressFactoryDouble,"15-04-2005", _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //assert
        assertNotNull(teacher);
    }


    //testing the Teacher acronym
    public static Stream<Arguments> provideInvalidTeacherAcronyms() {
        return Stream.of(
                arguments(null, "Teacher´s acronym must be a 3 capital letter non-empty String."),
                arguments("", "Teacher´s acronym must be a 3 capital letter non-empty String."),
                arguments("   ", "Teacher´s acronym must be a 3 capital letter non-empty String."),
                arguments("A", "Teacher´s acronym must contain only three capital letters."),
                arguments("AF".repeat(101), "Teacher´s acronym must contain only three capital letters."),
                arguments("AFRT".repeat(101), "Teacher´s acronym must contain only three capital letters."),
                arguments("A F R", "Teacher´s acronym must contain only three capital letters."),
                arguments(" AFR", "Teacher´s acronym must contain only three capital letters."),
                arguments("AFR ", "Teacher´s acronym must contain only three capital letters."),
                arguments("AF1", "Teacher´s acronym must contain only three capital letters."),
                arguments("!RT", "Teacher´s acronym must contain only three capital letters."),
                arguments("aaa", "Teacher´s acronym must contain only three capital letters.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherAcronyms")
    void testInvalidAcronyms(String acronym, String expectedMessage) throws IllegalArgumentException {

        // Arrange
        String name = "Maria Manuela Lima";
        String nif = "123456789";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing the Teacher name
    public static Stream<Arguments> provideInvalidTeacherNames() {
        return Stream.of(
                arguments(null, "Teacher´s name must be a non-empty String."),
                arguments("", "Teacher´s name must be a non-empty String."),
                arguments("   ", "Teacher´s name must be a non-empty String."),
                arguments("A", "Teacher´s name must be between 2 and 100 characters."),
                arguments("A".repeat(101), "Teacher´s name must be between 2 and 100 characters."),
                arguments("john", "Teacher´s name should start with a capital letter."),
                arguments(" John", "Teacher´s name should start with a capital letter."),
                arguments("123Name", "Teacher´s name must contain only letters and spaces."),
                arguments("!@#Name", "Teacher´s name must contain only letters and spaces."),
                arguments("J-@", "Teacher´s name must contain only letters and spaces.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherNames")
    void testInvalidNames(String name, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String acronym = "MMM";
        String nif = "123456789";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name , email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing Teacher's email
    public static Stream<Arguments> provideInvalidTeacherEmail() {
        return Stream.of(
                arguments(null, "Teacher´s email must be a non-empty String."),
                arguments("", "Teacher´s email must be a non-empty String."),
                arguments("   ", "Teacher´s email must be a non-empty String."),
                arguments("MMO@isep.ipp.pt", "Invalid email format."),
                arguments("*MO@isep.ipp.pt", "Invalid email format."),
                arguments("M2N@isep.ipp.pt", "Invalid email format."),
                arguments("M MM@isep.ipp.pt", "Invalid email format."),
                arguments("MMJ@gmail.com", "Invalid email format."),
                arguments("MMN", "Invalid email format.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherEmail")
    void testInvalidEmail(String email, String expectedMessage) throws Exception {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "123456789";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress( "Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing teacher's NIF
    public static Stream<Arguments> provideInvalidTeacherNIF() {
        return Stream.of(
                arguments(null, "Teacher´s NIF must be a non-empty String."),
                arguments("", "Teacher´s NIF must be a non-empty String."),
                arguments("   ", "Teacher´s NIF must be a non-empty String."),
                arguments("A23321423", "Teacher´s NIF must contain only 9 characters."),
                arguments("+23321423", "Teacher´s NIF must contain only 9 characters."),
                arguments("1 2 3 3 2 1 4 2 3", "Teacher´s NIF must contain only 9 characters."),
                arguments(" 123456789", "Teacher´s NIF must contain only 9 characters."),
                arguments("123456789 ", "Teacher´s NIF must contain only 9 characters."),
                arguments("1234567890", "Teacher´s NIF must contain only 9 characters.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherNIF")
    void testInvalidNIF(String nif, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing teacher's office phone number
    public static Stream<Arguments> provideInvalidTeacherPhoneNumber() {
        return Stream.of(
                arguments(null, "Teacher´s office phoneNumber must be a non-empty String."),
                arguments("", "Teacher´s office phoneNumber must be a non-empty String."),
                arguments("   ", "Teacher´s office phoneNumber must be a non-empty String."),
                arguments("919234567", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments(" B123", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B123 ", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("b123", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B12", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B1234", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("-123", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B12 3", "Teacher´s office phoneNumber must have a letter followed by 3 digits."),
                arguments("B+23", "Teacher´s office phoneNumber must have a letter followed by 3 digits.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidTeacherPhoneNumber")
    void testInvalidPhoneNumber(String phoneNumber, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing teacher's academic background
    public static Stream<Arguments> provideInvalidAcademicBackground() {
        return Stream.of(
                arguments(null, "Teacher's academic background must be a non-empty String."),
                arguments("", "Teacher's academic background must be a non-empty String."),
                arguments("   ", "Teacher's academic background must be a non-empty String.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidAcademicBackground")
    void testInvalidAcademicBackground(String academicBackground, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing street
    public static Stream<Arguments> provideInvalidStreet() {
        return Stream.of(
                arguments(null, "Street cannot be empty!"),
                arguments("", "Street cannot be empty!"),
                arguments("   ", "Street cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidStreet")
    void testInvalidStreet(String street, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress(street, "4432-345", "Porto","Portugal")).thenThrow(new IllegalArgumentException("Street cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, "4432-345", "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing postalCode
    public static Stream<Arguments> provideInvalidPostalCode() {
        return Stream.of(
                arguments(null, "Postal Code cannot be empty!"),
                arguments("", "Postal Code cannot be empty!"),
                arguments("   ", "Postal Code cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidPostalCode")
    void testInvalidPostalCode(String postalCode, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores", postalCode, "Porto","Portugal")).thenThrow(new IllegalArgumentException("Postal Code cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores", postalCode, "Porto","Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing location
    public static Stream<Arguments> provideInvalidLocation() {
        return Stream.of(
                arguments(null, "Location cannot be empty!"),
                arguments("", "Location cannot be empty!"),
                arguments("   ", "Location cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidLocation")
    void testInvalidLocation(String location, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-085", location,"Portugal")).thenThrow(new IllegalArgumentException("Location cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085", location,"Portugal", _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing Country
    public static Stream<Arguments> provideInvalidCountry() {
        return Stream.of(
                arguments(null, "Country cannot be empty!"),
                arguments("", "Country cannot be empty!"),
                arguments("   ", "Country cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidCountry")
    void testInvalidCountry(String country, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-085","Porto", country)).thenThrow(new IllegalArgumentException("Country cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression("14-05-2004", _tcDouble, 100)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085","Porto", country, _addressFactoryDouble,"14-05-2004", _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing Date
    public static Stream<Arguments> provideInvalidDate() {
        return Stream.of(
                arguments(null, "Date cannot be empty!"),
                arguments("", "Date cannot be empty!"),
                arguments("   ", "Date cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDate")
    void testInvalidDate(String date, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-085","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(date, _tcDouble, 100)).thenThrow(new IllegalArgumentException("Date cannot be empty!"));
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085","Porto","Portugal", _addressFactoryDouble, date, _tcDouble, 100, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfThereIsATeacherWithThisNif() throws IllegalArgumentException {
        // arrange
        createDoubles();
        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("15-04-2005", _tcDouble, 70)).thenReturn(_tcpDouble);
        createListDouble();

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble,"15-04-2005", _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act & assert
        assertTrue(t1.hasThisNIF("123456789"));
    }

    @Test
    void shouldReturnFalseIfNoTeacherWithThisNif() throws IllegalArgumentException {
        // arrange
        createDoubles();
        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression("15-04-2005", _tcDouble, 70)).thenReturn(_tcpDouble);
        createListDouble();

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble,"15-04-2005", _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act & assert
        assertFalse(t1.hasThisNIF("123856789"));
    }

    @Test
    void returnsTrueAfterUpdateWorkingPercentageInTeacherCareerProgression() throws IllegalArgumentException {
        //arrange
        String date1 = "15-04-2005";
        String date2 = "17-04-2005";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        TeacherCareerProgression updatedtcpDouble = mock(TeacherCareerProgression.class);
        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(updatedtcpDouble.isDateAfter(_tcpDouble)).thenReturn(true);
        when(_listDouble.add(updatedtcpDouble)).thenReturn(true);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, _tcDouble, 50)).thenReturn(updatedtcpDouble);

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble,date1 , _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result = t1.updateWorkingPercentageInTeacherCareerProgression(date2, 50);

        //assert
        assertTrue(result);
    }

    @Test
    void returnsExceptionWhenGivenWorkingPercentageIsEqualToPresentWorkingPercentage() throws IllegalArgumentException {
        //arrange
        String date1 = "15-04-2005";
        String date2 = "17-04-2005";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> t1.updateWorkingPercentageInTeacherCareerProgression(date2, 70));
        assertEquals("Working percentage must be different than the last working percentage!", exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidUpdateDate() {
        return Stream.of(
                Arguments.of("15-04-2005", "Date must be greater than the last date registered!"),
                Arguments.of("14-04-2005", "Date must be greater than the last date registered!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidUpdateDate")
    void throwsExceptionWhenUpdateDateIsNotAfterExistingDateWhileUpdatingWorkingPercentage(String date2, String expectedMessage) throws IllegalArgumentException {
        //arrange
        String date1 = "15-04-2005";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        TeacherCareerProgression updatedtcpDouble = mock(TeacherCareerProgression.class);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);
        when(updatedtcpDouble.isDateAfter(_tcpDouble)).thenReturn(false);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, _tcDouble, 100)).thenReturn(updatedtcpDouble);

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> t1.updateWorkingPercentageInTeacherCareerProgression(date2, 100));
        assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidDatesWorkingPercentage() {
        return Stream.of(
                Arguments.of("2005-04-14", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14-04-05", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14 de Setembro de 2005", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14/13/2024", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("32112024", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14-04-05Sd", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14-04-0??2", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDatesWorkingPercentage")
    void throwsExceptionWhenDateIsInvalidWhileUpdatingWorkingPercentage(String date2, String expectedMessage) throws IllegalArgumentException {
        //arrange
        String date1 = "15-04-2005";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, _tcDouble, 100)).thenThrow(new IllegalArgumentException("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."));

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> t1.updateWorkingPercentageInTeacherCareerProgression(date2, 100));
        assertEquals(expectedMessage, exception.getMessage());
    }


    public static Stream<Arguments> provideInvalidWorkingPercentage() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(101)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidWorkingPercentage")
    void returnsExceptionWhenWorkingPercentageIsInvalid(int workingPercentage) throws IllegalArgumentException {
        //arrange
        String date1 = "15-04-2005";
        String date2 = "17-04-2005";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher

        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, _tcDouble, workingPercentage)).thenThrow(new IllegalArgumentException("Working Percentage must be a value between 0 and 100."));

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> t1.updateWorkingPercentageInTeacherCareerProgression(date2, workingPercentage));
        assertEquals("Working Percentage must be a value between 0 and 100.", exception.getMessage());
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_SuccessfulTest() throws IllegalArgumentException {
        // Arrange
        String date1 = "22-12-2024";
        String date2 = "25-12-2024";

        createDoubles();

        TeacherCategory _tcDouble2 = mock(TeacherCategory.class);

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        TeacherCareerProgression tcpDouble2 = mock(TeacherCareerProgression.class);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);
        when(tcpDouble2.isDateAfter(_tcpDouble)).thenReturn(true);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, _tcDouble2, 70)).thenReturn(tcpDouble2);

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Act
        boolean result = t1.updateTeacherCategoryInTeacherCareer(date2, _tcDouble2);

        // Assert
        assertTrue(result);
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_OlderDate_ExceptionIsCaught() throws IllegalArgumentException {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = "12-12-2024";

        TeacherCategory tcDouble2 = mock(TeacherCategory.class);
        TeacherCareerProgression tcpDouble2 = mock(TeacherCareerProgression.class);

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, tcDouble2, 70)).thenReturn(tcpDouble2);

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tcDouble2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_SameCategory_ExceptionIsCaughtTest() throws IllegalArgumentException {
        //arrange
        String date1 = "25-12-2024";
        String date2 = "26-12-2024";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, _tcDouble));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_NullDate_ExceptionIsCaught() throws IllegalArgumentException {
        // Arrange
        String date1 = "20-12-2024";
        String date2 = null;

        TeacherCategory tcDouble2 = mock(TeacherCategory.class);

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, tcDouble2, 70)).thenThrow(new IllegalArgumentException("Date cannot be empty!"));

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tcDouble2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_BlankDate_UnsuccessfulTest() throws IllegalArgumentException {
        // Arrange
        String date1 = "20-12-2024";
        String date2 = " "; // Blank date

        createDoubles();

        TeacherCategory tcDouble2 = mock(TeacherCategory.class);

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, tcDouble2, 70)).thenThrow(new IllegalArgumentException("Date cannot be empty!"));

        createListDouble();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tcDouble2));
    }

    public static Stream<Arguments> provideInvalidDatesTeacherCategory() {
        return Stream.of(
                Arguments.of("2005-04-14", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14-04-05", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14 de Setembro de 2005", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14/13/2024", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("32112024", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14-04-05Sd", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                Arguments.of("14-04-0??2", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDatesTeacherCategory")
    void updateTeacherCategory_InTeacherCareer_InvalidDateFormat_UnsuccessfulTest(String date2, String result) throws IllegalArgumentException {
        // Arrange
        String date1 = "24-12-2024";

        TeacherCategory tcDouble2 = mock(TeacherCategory.class);

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        // Set up behaviors needed for the System Under Test
        when(_listDouble.getLast()).thenReturn(_tcpDouble);
        when(_tcpDouble.getWorkingPercentage()).thenReturn(70);
        when(_tcpDouble.getCategory()).thenReturn(_tcDouble);

        // Arranging the creation of a TCP which will be inherent to the creation of a Teacher
        when(_tcpFactoryDouble.createTeacherCareerProgression(date2, tcDouble2, 70)).thenThrow(new IllegalArgumentException("Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."));

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Act + Assert
        IllegalArgumentException exceptionResult = assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tcDouble2));
        assertEquals(result, exceptionResult.getMessage());
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_NullCategory_UnsuccessfulTest() throws IllegalArgumentException {
        // Arrange
        String date1 = "26-12-2024";
        String date2 = "27-12-2024";

        createDoubles();

        //Arranging creation of an Address
        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-098", "Porto", "Portugal")).thenReturn(_addressDouble);

        createListDouble();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, null));
    }

    @Test
    void testShouldReturnTrueForTeachersWithSameAcronym() throws IllegalArgumentException {
        //Arrange
        String date1 = "26-12-2024";
        String date2 = "27-12-2024";

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("CBB", "João Fonseca", "cbb@isep.ipp.pt", "744872363", "C203", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, date2, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameAcronym(t2);

        //assert
        assertTrue(result);
    }

    @Test
    void testShouldReturnFalseForTeachersWithDifferentAcronym() throws IllegalArgumentException {
        //Arrange
        String date1 = "26-12-2024";
        String date2 = "27-12-2024";

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("JJF", "João Fonseca", "jjf@isep.ipp.pt", "744872363", "C203", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, date2, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameAcronym(t2);

        //assert
        assertFalse(result);
    }

    @Test
    void testShouldReturnTrueForTeachersWithSameNIF() throws IllegalArgumentException {
        //Arrange
        String date1 = "26-12-2024";
        String date2 = "27-12-2024";

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("JJF", "João Fonseca", "jjf@isep.ipp.pt", "234542322", "C203", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, date2, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameNif(t2);

        //assert
        assertTrue(result);
    }

    @Test
    void testShouldReturnFalseForTeachersWithDifferentNIF() throws IllegalArgumentException {
        //Arrange
        String date1 = "26-12-2024";
        String date2 = "27-12-2024";

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("JJF", "João Fonseca", "jjf@isep.ipp.pt", "744872363", "C203", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, date2, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameNif(t2);

        //assert
        assertFalse(result);
    }

    @Test
    void testIfTeacherIsInASpecificDepartment() throws IllegalArgumentException {
        //Arrange
        String date1 = "26-12-2024";

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result = t1.isInDepartment(_dptDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void testIfTeacherIsNotInASpecificDepartment() throws IllegalArgumentException {
        //Arrange
        String date1 = "26-12-2024";

        createDoubles();

        Department _dptDouble2 = mock(Department.class);

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, date1, _tcDouble, 70, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result = t1.isInDepartment(_dptDouble2);

        //assert
        assertFalse(result);
    }
}