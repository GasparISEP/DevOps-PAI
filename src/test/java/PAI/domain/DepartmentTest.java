package PAI.domain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class DepartmentTest {

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "Department1";
        //act & assert
        Department department = new Department(acronym, name);
    }

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withDirector () {
        //arrange
        String acronym = "DEI";
        String name = "Department1";
        Teacher teacherDirectorDouble = mock(Teacher.class);
        //act & assert
        Department department = new Department(acronym, name, teacherDirectorDouble);
    }

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withTwoLettersName () {
        //arrange
        String acronym = "DEI";
        String name = "DE";
        Teacher teacherDirectorDouble = mock(Teacher.class);
        //act & assert
        Department department = new Department(acronym, name, teacherDirectorDouble);
    }

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withHundredLettersName (){
        //arrange
        String acronym = "DEI";
        String name = "D".repeat(100);
        Teacher teacherDirectorDouble = mock(Teacher.class);

        //act & assert
        Department department = new Department(acronym, name, teacherDirectorDouble);
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
    void testInvalidNames(String name, String expectedMessage) {
        // Arrange
        String acronym = "DEI";
        Teacher teacherDirector1Double = mock(Teacher.class);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Department(acronym, name, teacherDirector1Double);
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
    void testInvalidAcronyms(String acronym, String expectedMessage) {
        // Arrange
        String name = "Department1";
        Teacher teacherDirector1Double = mock(Teacher.class);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new Department(acronym, name, teacherDirector1Double);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    //US06
    @Test
    void shouldReturnTrueWhenTeacherIsOfTheDepartment() throws Exception{
        //arrange
        Teacher teacher1Double = mock(Teacher.class);
        Department dpt1= new Department("DEI","Engenharia Informática");
        //act
        boolean result = dpt1.changeDirector(teacher1Double);
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
        // Act & Assert
        assertFalse(department1.equals(null));
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentClassObject() throws Exception {
        // Arrange
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-07-2025");

        // Act & Assert
        assertFalse(department1.equals(schoolYear1));
    }

    @Test
    void shouldReturnTrueWhenSameObjectIsCompared() throws Exception {
        // arrange
        Department department = new Department("DEI", "Departamento Engenharia Informática");

        // act & assert
        assertTrue(department.equals(department));
    }

    @Test
    void shouldReturnDepartmentName() throws Exception {
        // arrange
        String name = "Departamento Engenharia Informática";
        String acronym= "DEI";
        Department department = new Department(acronym,name);

        // act
        String actualName = department.getName();

        // assert
        assertEquals(name, actualName);
    }

    @Test
    void shouldReturnDepartmentAcronym() throws Exception {
        // arrange
        String acronym = "DEI";
        String name= "Departamento Engenharia Informática";
        Department department = new Department(acronym, name);
        // act
        String actualAcronym = department.getAcronym();

        // assert
        assertEquals(acronym, actualAcronym);
    }
}