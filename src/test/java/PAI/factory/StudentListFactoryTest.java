package PAI.factory;

import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;

class StudentListFactoryTest {

    @Test
    void shouldCreateArrayListWhenConstructorIsCalled() {
        // Arrange
        StudentListFactory studentListFactory = new StudentListFactory();

        // Act
        List<Student> listStudents = studentListFactory.newArrayList();

        // Assert
        assertNotNull(listStudents);
        assertInstanceOf(ArrayList.class, listStudents);
    }
}
