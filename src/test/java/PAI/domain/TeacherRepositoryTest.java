package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {

    //testing create a new valid teacher
    @Test
    void shouldCreateValidTeacher() throws Exception {
        // Arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        // Act
        boolean result = repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal", "20-12-1010", category, 100, department);


        // Assert
        assertTrue(result, "The teacher should be registered successfully.");

    }

    //Test to register two valid teachers
    @Test
    public void testRegisterValidTeacher() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");
        //act
        boolean result1 = repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal","20-12-2010", category,100, department);
        boolean result2 = repository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Street Two", "1234-678", "Porto", "Portugal","20-12-2010", category,100, department);

        //assert
        assertTrue(result1, "The first teacher should be registered successfully.");
        assertTrue(result2, "The second teacher should be registered successfully.");
    }

    //Testing when the department has an existing acronym
    @Test
    public void testRegisterTeacherWithDuplicateAcronym() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal","20-12-2010", category,100, department);
            repository.registerTeacher("ABC", "Jane Doe", "ABC@isep.ipp.pt", "987654321", "B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal","20-12-2010", category,100, department);
        });
        // Assert
        assertEquals("A teacher with the same acronym already exists.", exception.getMessage());
    }

    //Testing when the department has an existing nif
    @Test
    public void testRegisterTeacherWithDuplicateNif() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal","20-12-2010", category,100, department);
            repository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123456789", "B123","Doutoramento em Engenharia Informatica, 2005, ISEP","Street One", "1234-678", "Porto", "Portugal","20-12-2010", category,100, department);
            // Assert
        });
        assertEquals("A teacher with the same NIF already exists.", exception.getMessage());
    }
    @Test
    void shouldReturnANewListOfTeachersWithSameSize() throws Exception {
        //ARRANGE
        TeacherRepository repo1 = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        repo1.registerTeacher( "AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789",
                "A106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005",
                category, 70, department);

        repo1.registerTeacher( "BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005",
                category, 70, department);

        //ACT
        List<Teacher> result = repo1.getAllTeachers();

        //
        assertEquals(2, result.size());

    }

    @Test
    void shouldReturnTeacherWhenNIFMatches() throws Exception {

        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);

        // act

        Optional<Teacher> optT1 = repository.getTeacherByNIF("123456789");

        // assert
        assertTrue(optT1.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenTeacherNotFoundByNIF() throws Exception {

        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);

        // act
        Optional<Teacher> optT1 = repository.getTeacherByNIF("987654321");

        // assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNIFIsEmpty() throws Exception {

        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);

        // act
        Optional<Teacher> optT1 = repository.getTeacherByNIF("");

        // assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNIFIsBlank() throws Exception {

        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);

        // act
        Optional<Teacher> optT1 = repository.getTeacherByNIF(" ");

        // assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNIFIsNull() throws Exception {

        //arrange
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);

        // act
        Optional<Teacher> optT1 = repository.getTeacherByNIF(null);

        // assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void shouldReturnTrue_If_UpdateTeacherCategoryisSuccessful() throws Exception{
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        TeacherCategory category2 = new TeacherCategory("Assistente");
        Department department = new Department("MAT", "Mathematics");
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);
        boolean result = repository.updateTeacherCategory("15-06-2005", t1, category2);
        assertTrue(result);
    }

    @Test
    void shouldReturnException_If_UpdateTeacherCategoryisNotSuccessful() throws Exception {
        TeacherRepository repository = new TeacherRepository();
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", category, 70, department);
        assertThrows(IllegalArgumentException.class, () -> repository.updateTeacherCategory("15-06-2005", t1, category));
    }
}