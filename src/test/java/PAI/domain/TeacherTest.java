package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class TeacherTest {
    @Test
    void shouldReturnTeacherWhenAllFieldsAreValid() throws Exception {
        //arrange
        Address address = new Address("Street One", "1234-678", "Porto", "Portugal");
        TeacherCategory category = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");
        //act
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", address, category, department);
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
    void testInvalidAcronyms(String acronym, String expectedMessage) throws Exception {
        // Arrange
        String name = "Maria Manuela Lima";
        String nif = "123456789";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto","Portugal");
        TeacherCategory category1 = new TeacherCategory("Professor Adjunto");
        Department department1 = new Department("DED", "Dept1");
        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, address1, category1, department1);
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
    void testInvalidNames(String name, String expectedMessage) throws Exception {
        // Arrange
        String acronym = "MMM";
        String nif = "123456789";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        Address address1 = new Address("Passeio Alegre",  "4432-345","Porto", "Portugal");
        TeacherCategory category1 = new TeacherCategory("Professor Adjunto");
        Department department1 = new Department("DED", "Dept1");

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Teacher(acronym, name , email, nif, phoneNumber, address1,  category1, department1);
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
        Address address1 = new Address("Passeio Alegre",  "4432-345","Porto", "Portugal");
        TeacherCategory category1 = new TeacherCategory("Professor Adjunto");
        Department department1 = new Department( "DED","Dep1");

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, address1, category1,department1);
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
    void testInvalidNIF(String nif, String expectedMessage) throws Exception {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String email = "MMM@isep.ipp.pt";
        String phoneNumber = "B123";
        Address address1 = new Address("Passeio Alegre",  "4432-345","Porto", "Portugal");
        TeacherCategory category1 = new TeacherCategory("Professor Adjunto");
        Department department1 = new Department( "DED","Dep1");

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, address1, category1,department1);
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
    void testInvalidPhoneNumber(String phoneNumber, String expectedMessage) throws Exception {
        // Arrange
        String name = "Maria Manuela Lima";
        String acronym = "MMM";
        String nif = "777777777";
        String email = "MMM@isep.ipp.pt";
        Address address1 = new Address("Passeio Alegre",  "4432-345","Porto","Portugal");
        TeacherCategory category1 = new TeacherCategory("Professor Adjunto");
        Department department1 = new Department( "DED","Dep1");

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Teacher(acronym, name, email, nif, phoneNumber, address1, category1,department1);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

}