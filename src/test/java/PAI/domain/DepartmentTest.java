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
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Passeio Alegre", "4432-345", "Porto","Portugal", "20-12-2010", teacherCategory1, 100, department1);
        //act
        Department department = new Department(acronym, name,  teacherDirector1);
        //assert
        assertNotNull (department);
    }
@Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withTwoLettersName () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "DE";
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Passeio Alegre", "4432-345", "Porto", "Portugal","20-12-2010", teacherCategory1, 100, department1);
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
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Passeio Alegre", "4432-345", "Porto", "Portugal", "20-12-2010", teacherCategory1,100, department1);
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
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Passeio Alegre", "4432-345", "Porto", "Portugal", "20-12-2010", teacherCategory1,100,department1);
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
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Passeio Alegre", "4432-345", "Porto", "Portugal", "20-12-2010", teacherCategory1,100, department1);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Department(acronym, name,  teacherDirector1);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //US06
    @Test
    void shouldReturnTrueWhenTeacherIsOfTheDepartment() throws Exception{
        //arrange
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ( "DEI","Department1");
        Teacher teacher1 = new Teacher( "JOA","Joao", "JOA@isep.ipp.pt","213456789","B234","Doutoramento em Engenharia Informatica, 2005, ISEP", "Passeio Alegre", "4432-345", "Porto", "Portugal", "20-12-2010", teacherCategory1,100, department1);
        //act
        boolean result = department1.changeDirector(teacher1);
        //assert
        assertTrue (result);
    }


    //Testing the equals method
    @Test
    void testShouldReturnTrueForEqualDepartments()throws Exception {
        // Arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Department department2 = new Department("DEI", "Departamento Engenharia Informática");

        // Act & Assert
        assertTrue(department1.equals(department2));
    }

    @Test
    void testShouldReturnFalseForDifferentNames()throws Exception {
        // Arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Department department2 = new Department("DEQ", "Departamento Engenharia Química");

        // Act & Assert
        assertFalse(department1.equals(department2));
    }

    @Test
    void testShouldReturnFalseForDifferentAcronyms()throws Exception {
        // Arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Department department2 = new Department("DEQ", "Departamento Engenharia Química");

        // Act & Assert
        assertFalse(department1.equals(department2));
    }

    @Test
    void testShouldReturnFalseWhenNullDepartment() throws Exception{
        // Arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        Department department2 = null;

        // Act & Assert
        assertFalse(department1.equals(department2));
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentClassObject() throws Exception {
        // Arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");

        // Act & Assert
        assertFalse(department1.equals(schoolYear1));
    }
}