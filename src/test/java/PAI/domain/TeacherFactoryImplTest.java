package PAI.domain;
import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
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

        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = TeacherID.createNew();
        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Department dptDouble = mock(Department.class);


        //act
        Teacher teacher = tf1.createTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789",
                "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble, dateDouble,
                tcIDDouble, wpDouble, teacherIDDouble, dptDouble);

        //assert
        assertNotNull(teacher);
    }
}