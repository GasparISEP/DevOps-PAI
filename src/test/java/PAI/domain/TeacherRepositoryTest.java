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

}