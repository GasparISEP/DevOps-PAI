package PAI.domain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherFactoryTest {

    @Test
    void shouldCreateTeacher() {

        //arrange
        TeacherFactory tf1 = new TeacherFactory();
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dptDouble = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgression TCPdouble = mock(TeacherCareerProgression.class);

        //act
        Teacher teacher = tf1.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789",
                "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble, "15-04-2005",
                tcDouble, 70, dptDouble, TCPfactoryDouble);

        //assert
        assertNotNull(teacher);
    }
}