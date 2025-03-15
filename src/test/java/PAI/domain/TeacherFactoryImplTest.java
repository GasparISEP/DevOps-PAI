package PAI.domain;
import PAI.factory.AddressFactoryImpl;
import PAI.factory.TeacherCareerProgressionFactoryImpl;
import PAI.factory.TeacherCareerProgressionListFactoryImpl;
import PAI.factory.TeacherFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherFactoryImplTest {

    @Test
    void shouldCreateTeacher() {

        //arrange
        TeacherCareerProgressionFactoryImpl tcpFactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherCareerProgressionListFactoryImpl tcpListFactoryDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
        TeacherFactoryImpl tf1 = new TeacherFactoryImpl(tcpFactoryDouble, tcpListFactoryDouble);

        TeacherCategory tcDouble = mock(TeacherCategory.class);
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department dptDouble = mock(Department.class);


        //act
        Teacher teacher = tf1.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789",
                "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble, "15-04-2005",
                tcDouble, 70, dptDouble);

        //assert
        assertNotNull(teacher);
    }
}