package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DepartmentTest {

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "Department1";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto","Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        //act
        Department department = new Department(acronym, name);
        //assert
        assertNotNull (department);
    }

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withDirector () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "Department1";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto","Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123",address1,teacherCategory1,department1);
        //act
        Department department = new Department(acronym, name,  teacherDirector1);
        //assert
        assertNotNull (department);
    }

    void shouldReturnDepartment_whenAllTheAtributesAreValid_withTwoLettersName () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "DE";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto", "Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123",address1,teacherCategory1,department1);
        //act
        Department department = new Department(acronym, name,  teacherDirector1);
        //assert
        assertNotNull (department);
    }

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withHundredLettersName () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "D".repeat(100);
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto","Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123",address1,teacherCategory1,department1);
        //act
        Department department = new Department(acronym, name,  teacherDirector1);
        //assert
        assertNotNull (department);
    }
    @Test
    void everythingNullGenerateException () throws Exception {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Department(null, null, null));
    }

    @Test
    void everythingIsEmptyGenerateException () throws Exception {
        //arrange

        //act + assert
        assertThrows(Exception.class, () -> new Department ("", "",null));
    }

    //testing failure cases of Department's name
    public static Stream<Arguments> provideInvalidDepartmentsNames() {
        return Stream.of(
                arguments(null, "Department´s name must be a non-empty String."),
                arguments("", "Department´s name must be a non-empty String."),
                arguments("   ", "Department´s name must be a non-empty String."),
                arguments("A", "Department´s name must be between 2 and 100 characters."),
                arguments("A".repeat(101), "Department´s name must be between 2 and 100 characters."),
                arguments("department1", "Department´s name should start with a capital letter."),
                arguments("123Name", "Department´s name should start with a capital letter."),
                arguments("!@#Name", "Department´s name should start with a capital letter.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDepartmentsNames")
    void testInvalidNames(String name, String expectedMessage) throws Exception {
        // Arrange
        String acronym = "DEI";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto", "Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123",address1,teacherCategory1,department1);
        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Department(acronym, name, teacherDirector1);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //testing failure cases of Department's acronyms
    public static Stream<Arguments> provideInvalidDepartmentsAcronyms() {
        return Stream.of(
                arguments(null, "Department´s acronym must be a 3 letter non-empty String."),
                arguments("", "Department´s acronym must be a 3 letter non-empty String."),
                arguments("   ", "Department´s acronym must be a 3 letter non-empty String."),
                arguments("A", "Department´s acronym must contain only three capital letters."),
                arguments("AF".repeat(121), "Department´s acronym must contain only three capital letters."),
                arguments("AFRT".repeat(121), "Department´s acronym must contain only three capital letters."),
                arguments("A F R", "Department´s acronym must contain only three capital letters."),
                arguments(" AFR", "Department´s acronym must contain only three capital letters."),
                arguments("AFR ", "Department´s acronym must contain only three capital letters."),
                arguments("AF1", "Department´s acronym must contain only three capital letters."),
                arguments("!RT", "Department´s acronym must contain only three capital letters.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDepartmentsAcronyms")
    void testInvalidAcronyms(String acronym, String expectedMessage) throws Exception {
        // Arrange
        String name = "Department1";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto", "Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123",address1,teacherCategory1,department1);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Department(acronym, name,  teacherDirector1);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }
}