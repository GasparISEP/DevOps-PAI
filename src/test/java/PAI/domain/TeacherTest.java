package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.factory.IAddressFactory;
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

    private TeacherCategoryID _tcIDDouble;
    private IAddressFactory _addressFactoryDouble;
    private Address _addressDouble;
    private Department _dptDouble;
    private TeacherCareerProgressionFactoryImpl _tcpFactoryDouble;
    private TeacherCareerProgression _tcpDouble;
    private TeacherCareerProgressionListFactoryImpl _tcplFDouble;
    private List<TeacherCareerProgression> _listDouble;
    private Date _dateDouble;
    private WorkingPercentage _wpDouble;
    private TeacherID _teacherIDDouble;

    private void createDoubles () {
        _tcIDDouble = mock(TeacherCategoryID.class);
        _addressFactoryDouble = mock(IAddressFactory.class);
        _addressDouble = mock(Address.class);
        _dptDouble = mock(Department.class);
        _tcpFactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        _tcpDouble = mock(TeacherCareerProgression.class);
        _tcplFDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
        _dateDouble = mock(Date.class);
        _wpDouble = mock(WorkingPercentage.class);
        _teacherIDDouble = mock(TeacherID.class);
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
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        // Assert
        assertNotNull(teacher);
    }

    @Test
    void shouldCreateTeacher_WhenAllFieldsAreValid_WithTwoLettersName() throws IllegalArgumentException {
        //arrange
        createDoubles();
        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        //act
        Teacher teacher = new Teacher("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "+351 912 345 678","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //assert
        assertNotNull(teacher);
    }

    @Test
    void shouldCreateTeacher_WhenAllFieldsAreValid_WithAHundredLettersName() throws IllegalArgumentException {
        //arrange
        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        //act
        Teacher teacher = new Teacher("ABC", "J".repeat(100), "abc@isep.ipp.pt", "123456789", "+351 912 345 678","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua das Flores","4444-098","Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name , email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress( "Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing teacher's office phone number
    public static Stream<Arguments> provideInvalidTeacherPhoneNumber() {
        return Stream.of(
                arguments(null, "Teacher's phone number is invalid!"),
                arguments("", "Teacher's phone number is invalid!"),
                arguments("   ", "Teacher's phone number is invalid!"),
                arguments("+351-91 23 45678", "Teacher's phone number is invalid!"),
                arguments("+351.912.34.567", "Teacher's phone number is invalid!"),
                arguments("(351) 912-3456-78", "Teacher's phone number is invalid!"),
                arguments("+351 9123-45678", "Teacher's phone number is invalid!"),
                arguments("+12345 678 910 1112", "Teacher's phone number is invalid!"),
                arguments("+35a 912 345 678", "Teacher's phone number is invalid!"),
                arguments("9123456", "Teacher's phone number is invalid!"),
                arguments("+351--912..345...678", "Teacher's phone number is invalid!"),
                arguments("0044-20-7946-01234", "Teacher's phone number is invalid!")
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
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";

        createDoubles();
        when(_addressFactoryDouble.createAddress("Passeio Alegre", "4432-345", "Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress(street, "4432-345", "Porto","Portugal")).thenThrow(new IllegalArgumentException("Street cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, "4432-345", "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores", postalCode, "Porto","Portugal")).thenThrow(new IllegalArgumentException("Postal Code cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores", postalCode, "Porto","Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores", "4444-085", location,"Portugal")).thenThrow(new IllegalArgumentException("Location cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085", location,"Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-085","Porto", country)).thenThrow(new IllegalArgumentException("Country cannot be empty!"));
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085","Porto", country, _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
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
        String phoneNumber = "+351 912 345 678";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";

        createDoubles();

        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-085","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenThrow(new IllegalArgumentException("Date cannot be empty!"));
        createListDouble();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085","Porto","Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfThereIsATeacherWithThisNif() throws IllegalArgumentException {
        // arrange
        createDoubles();
        when(_addressFactoryDouble.createAddress("Rua das Flores","4444-098","Porto","Portugal")).thenReturn(_addressDouble);
        when(_tcpFactoryDouble.createTeacherCareerProgression(_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble)).thenReturn(_tcpDouble);
        createListDouble();

        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act & assert
        assertTrue(t1.hasThisNIF("123456789"));
    }


    @Test
    void testShouldReturnTrueForTeachersWithSameAcronym() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("CBB", "João Fonseca", "cbb@isep.ipp.pt", "744872363", "+351 987 654 321", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble,_dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameAcronym(t2);

        //assert
        assertTrue(result);
    }

    @Test
    void testShouldReturnFalseForTeachersWithDifferentAcronym() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("JJF", "João Fonseca", "jjf@isep.ipp.pt", "744872363", "+351 987 654 321", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameAcronym(t2);

        //assert
        assertFalse(result);
    }

    @Test
    void testShouldReturnTrueForTeachersWithSameNIF() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("JJF", "João Fonseca", "jjf@isep.ipp.pt", "234542322", "+351 987 654 321", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameNif(t2);

        //assert
        assertTrue(result);
    }

    @Test
    void testShouldReturnFalseForTeachersWithDifferentNIF() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);
        Teacher t2 = new Teacher("JJF", "João Fonseca", "jjf@isep.ipp.pt", "744872363", "+351 987 654 321", "Doutoramento em Engenharia Mecânica, 2008, ISEP", "Rua do Parque", "6543-044", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result= t1.hasSameNif(t2);

        //assert
        assertFalse(result);
    }

    @Test
    void testIfTeacherIsInASpecificDepartment() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result = t1.isInDepartment(_dptDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void testIfTeacherIsNotInASpecificDepartment() throws IllegalArgumentException {
        //Arrange

        createDoubles();

        Department _dptDouble2 = mock(Department.class);

        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", _addressFactoryDouble, _dateDouble, _tcIDDouble, _wpDouble, _teacherIDDouble, _dptDouble, _tcpFactoryDouble, _tcplFDouble);

        //act
        boolean result = t1.isInDepartment(_dptDouble2);

        //assert
        assertFalse(result);
    }
}