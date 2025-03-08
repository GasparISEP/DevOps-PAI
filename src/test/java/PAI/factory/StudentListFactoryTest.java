package PAI.factory;

import PAI.domain.Department;
import PAI.domain.ProgrammeEdition;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
