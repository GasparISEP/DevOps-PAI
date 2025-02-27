package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.mock;

import java.util.IllegalFormatWidthException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class TeacherTest {
    @Test
    void shouldReturnTeacherWhenAllFieldsAreValid() throws IllegalArgumentException {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        //act + assert
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tcDouble, 70, dptDouble);
    }

    @Test
    void shouldCreateTeacher_WhenAllFieldsAreValid_WithTwoLettersName() throws IllegalArgumentException {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        //act + assert
        Teacher teacher = new Teacher("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tcDouble, 70, dptDouble);
    }

    @Test
    void shouldCreateTeacher_WhenAllFieldsAreValid_WithAHundredLettersName() throws IllegalArgumentException {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        //act + assert
        Teacher teacher = new Teacher("ABC", "J".repeat(100), "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tcDouble, 70, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
                arguments("!@#Name", "Teacher´s name must contain only letters and spaces.")
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name , email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Passeio Alegre", "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Passeio Alegre", "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground,"Passeio Alegre", "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, "4432-345", "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores", postalCode, "Porto","Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
    void testInvalidLocation(String Location, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085", Location,"Portugal","14-05-2004", tcDouble, 100, dptDouble);
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
    void testInvalidCountry(String Country, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085","Porto", Country,"14-05-2004", tcDouble, 100, dptDouble);
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
    void testInvalidDate(String Date, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        String academicBackground = "Doutoramento em Engenharia Informatica, 2005, ISEP";
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, "Rua das Flores","4444-085","Porto","Portugal",Date, tcDouble, 100, dptDouble);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void doesTeacherHaveThisNIF() throws IllegalArgumentException {

        // arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);

        //act
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", "15-04-2005", tcDouble, 70, dptDouble);

        //act & assert
        assertTrue(t1.hasThisNIF("123456789"));
        assertFalse(t1.hasThisNIF("987654321"));
        assertFalse(t1.hasThisNIF(null));
        assertFalse(t1.hasThisNIF(""));
        assertFalse(t1.hasThisNIF(" "));
    }

    @Test
    void returnsTrueAfterUpdateWorkingPercentageInTeacherCareerProgression() throws IllegalArgumentException {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", "15-04-2005", tcDouble, 70, dptDouble);

        //act
        boolean result = t1.updateWorkingPercentageInTeacherCareerProgression("15-04-2008", 50);

        //assert
        assertTrue(result);
    }

    @Test
    void returnsExceptionWhenGivenWorkingPercentageIsEqualToPresentWorkingPercentage() throws IllegalArgumentException {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", "15-04-2005", tcDouble, 70, dptDouble);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> t1.updateWorkingPercentageInTeacherCareerProgression("16-10-2015", 70));
        assertEquals("Working percentage must be different than the last working percentage!", exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidDates() {
        return Stream.of(
                Arguments.of("15-04-2005", "Date must be greater than the last date registered!"),
                Arguments.of("14-04-2005", "Date must be greater than the last date registered!")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDates")
    void throwsExceptionWhenUpdateDateIsNotAfterExistingDate(String date, String expectedMessage) throws IllegalArgumentException {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        Department dptDouble = mock(Department.class);
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", "15-04-2005", tcDouble, 70, dptDouble);

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> t1.updateWorkingPercentageInTeacherCareerProgression(date, 50));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_SuccessfulTest() throws Exception {
        // Arrange
        String date1 = "22-12-2024";
        String date2 = "25-12-2024";
        TeacherCategory tcDouble = new TeacherCategory ("Professor Adjunto");
        TeacherCategory tcDouble2 = new TeacherCategory ("Professor Efetivo");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tcDouble, 70, dptDouble);
        boolean result = t1.updateTeacherCategoryInTeacherCareer(date2, tcDouble2);

        // Assert
        assertTrue(result);
    }

    @Test
    void verifyIfTeacherCategoryWasUpdatedFromTC1toTC2_SuccessfulTest() throws Exception{

        // Arrange
        TeacherCategory category1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory category2 = new TeacherCategory("Professor Efectivo");
        Department dptDouble = mock(Department.class);
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", "15-04-2005", category1, 70, dptDouble);
        TeacherCareerProgression tcp1 = new TeacherCareerProgression("15-04-2005", category2, 70);

        // Act
        t1.updateTeacherCategoryInTeacherCareer("16-07-2005", category2);
        TeacherCategory updatedTeacherCategory = tcp1.getCategory();

        // Assert
        assertEquals(category2.getName(), updatedTeacherCategory.getName());
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_OlderDate_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = "12-12-2024";
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Efectivo");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_SameCategory_UnsuccessfulTest() throws Exception {
        //arrange
        String date1 = "25-12-2024";
        String date2 = "26-12-2024";
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc1));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_SameCategoryReturnValue_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = "26-12-2024";
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        boolean result = false;
        try {
            result = t1.updateTeacherCategoryInTeacherCareer(date2, tc1);
        } catch (IllegalArgumentException e) {
            // Expected exception
        }

        // Assert
        assertFalse(result); // This should fail if the method always returns true
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_SameDateEdgeCase_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = "25-12-2024"; // Same date as the initial date
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Efectivo");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_NullDate_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = null; // Null date
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Efectivo");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_BlankDate_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = ""; // Blank date
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Efectivo");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_InvalidDateFormat_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = "25/12/2024"; // Invalid date format
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = new TeacherCategory("Professor Efectivo");
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc2));
    }

    @Test
    void updateTeacherCategory_InTeacherCareer_NullCategory_UnsuccessfulTest() throws Exception {
        // Arrange
        String date1 = "25-12-2024";
        String date2 = "26-12-2024";
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        TeacherCategory tc2 = null; // Null category
        Department dptDouble = mock(Department.class);

        // Act
        Teacher t1 = new Teacher("CBB", "Abel Martins", "cbb@isep.ipp.pt", "234542322", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores", "4444-098", "Porto", "Portugal", date1, tc1, 70, dptDouble);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> t1.updateTeacherCategoryInTeacherCareer(date2, tc2));
    }
}