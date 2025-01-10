package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {

    //testing create a new valid teacher
    @Test
    void shouldCreateValidTeacher() throws Exception {
        // Arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One", "1234-678", "Porto", "Portugal");
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        // Act
        boolean result = repository.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", address, category, department);


        // Assert
        assertTrue(result, "The teacher should be registered successfully.");

    }

    //Test to register two valid teachers
    @Test
    public void testRegisterValidTeacher() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        Address address1 = new Address("Street Two",  "1347-677", "Porto","Portugal");
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");
        //act
        boolean result1 = repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
        boolean result2 = repository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123", address1, category, department);

        //assert
        assertTrue(result1, "The first teacher should be registered successfully.");
        assertTrue(result2, "The second teacher should be registered successfully.");
    }

    //Testing when the department has an existing acronym
    @Test
    public void testRegisterTeacherWithDuplicateAcronym() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
            repository.registerTeacher("ABC", "Jane Doe", "ABC@isep.ipp.pt", "987654321", "B123", address, category, department);
        });
        // Assert
        assertEquals("A teacher with the same acronym already exists.", exception.getMessage());
    }

    //Testing when the department has an existing nif
    @Test
    public void testRegisterTeacherWithDuplicateNif() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
            repository.registerTeacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123456789", "B123", address, category, department);
            // Assert
        });
        assertEquals("A teacher with the same NIF already exists.", exception.getMessage());
    }
}