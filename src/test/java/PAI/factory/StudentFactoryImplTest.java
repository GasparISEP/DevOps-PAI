package PAI.factory;

import PAI.domain.Address;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentFactoryImplTest {

    @Test
    void shouldCreateStudentWhenConstructorIsCalled() {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        String uniqueNumber = "1234567";

        try (MockedConstruction<Student> studentDouble = mockConstruction(Student.class, (mock, context) -> {

                String actualUniqueNumber = (String) context.arguments().get(0);
                when(mock.getUniqueNumber()).thenReturn(actualUniqueNumber);
        })) {
            //act
            Student student = studentFactory.newStudent(uniqueNumber, "Daniela", "123456789", "963741258", "rita@gmail.com", address);

            //assert
            List<Student> students = studentDouble.constructed();
            assertEquals(1, students.size());

            assertEquals(uniqueNumber, studentDouble.constructed().get(0).getUniqueNumber());
            assertEquals(uniqueNumber, student.getUniqueNumber());

        }
    }
}