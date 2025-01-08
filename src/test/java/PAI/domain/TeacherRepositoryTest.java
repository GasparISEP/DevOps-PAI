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
        TeacherCategory category = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");

        // Act
        Teacher teacher = repository.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", address, category, department);

        // Assert
        assertNotNull(teacher);
    }
    //Test to register a valid teacher
    @Test
    public void testRegisterValidTeacher() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        TeacherCategory category = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");
        //act
        Teacher teacher = new Teacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
        Teacher teacher1 = new Teacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123458889", "A123", address, category, department);
        repository.registerTeacher(teacher);
        repository.registerTeacher(teacher1);

        //assert
        assertEquals(2, repository.getTeachers().size());
        assertTrue(repository.getTeachers().contains(teacher));
        assertTrue(repository.getTeachers().contains(teacher));
    }

    //Testing when the department has an existing acronym
    @Test
    public void testRegisterTeacherWithDuplicateAcronym() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        Address address1 = new Address("Street Two",  "5647-778", "Braga","Portugal");
        TeacherCategory category = new TeacherCategory("Math");
        TeacherCategory category1 = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");
        Department department1 = new Department("CSE", "Computer Science");
        //act
        Teacher teacher1 = new Teacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
        Teacher teacher2 = new Teacher("ABC", "Jane Doe", "ABC@isep.ipp.pt", "987654321", "B123", address1, category1, department1);
        repository.registerTeacher(teacher1);

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher(teacher2);
        });
        assertEquals("A teacher with the same acronym already exists.", exception.getMessage());
    }

    //Testing when the department has an existing nif
    @Test
    public void testRegisterTeacherWithDuplicateNif() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        Address address1 = new Address("Street Two",  "5647-778", "Braga","Portugal");
        TeacherCategory category = new TeacherCategory("Math");
        TeacherCategory category1 = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");
        Department department1 = new Department("CSE", "Computer Science");
        //act
        Teacher teacher1 = new Teacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
        Teacher teacher2 = new Teacher("DEF", "Jane Doe", "DEF@isep.ipp.pt", "123456789", "B123", address1, category1, department1);
        repository.registerTeacher(teacher1);

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher(teacher2);
        });
        assertEquals("A teacher with the same NIF already exists.", exception.getMessage());
    }

    // Testing to register a null teacher
    @Test
    public void testRegisterNullTeacher() {
        //arrange
        TeacherRepository repository = new TeacherRepository();

        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerTeacher(null);
        });
        assertEquals("Teacher cannot be null.", exception.getMessage());

    }

    // Verifying if the teacher was register correctly
    @Test
    public void testRegisterTeacherAndCheckListOfRegisteredTeachers() throws Exception {
        //arrange
        TeacherRepository repository = new TeacherRepository();
        Address address = new Address("Street One",  "1234-678", "Porto","Portugal");
        TeacherCategory category = new TeacherCategory("Math");
        Department department = new Department("MAT", "Mathematics");
        //act
        Teacher teacher = new Teacher("ABC", "John Doe", "ABC@isep.ipp.pt", "123456789", "A123", address, category, department);
        repository.registerTeacher(teacher);

        //assert
        assertTrue(repository.getTeachers().contains(teacher));
        assertEquals(1, repository.getTeachers().size());
    }
}