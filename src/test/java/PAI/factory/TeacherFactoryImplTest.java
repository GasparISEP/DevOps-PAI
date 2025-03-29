package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherFactoryImplTest {

    @Test
    void shouldCreateAValidTeacherWhenMockedConstructorIsGiven() {
        // Arrange
        TeacherCareerProgressionFactoryImpl tcpFactoryDouble = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherCareerProgressionListFactoryImpl tcpListFactoryDouble = mock(TeacherCareerProgressionListFactoryImpl.class);
        TeacherFactoryImpl teacherFactory = new TeacherFactoryImpl(tcpFactoryDouble, tcpListFactoryDouble);

        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Address addressDouble = mock(Address.class);
        Department departmentDouble = mock(Department.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        Date date = mock(Date.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);


        when(addressFactoryDouble.createAddress("Rua Das Flores", "4000-001", "Porto", "Portugal")).thenReturn(addressDouble);
        when(tcpFactoryDouble.createTeacherCareerProgression(date, tcIDDouble, wpDouble, teacherIDDouble)).thenReturn(mock(TeacherCareerProgression.class));
        List<TeacherCareerProgression> mockList = mock(ArrayList.class);
        when(tcpListFactoryDouble.createTeacherCareerProgressionList()).thenReturn(mockList);


        String acronym = "ABC";
        String name = "João Silva";
        String email = "abc@school.com";
        String nif = "123456789";
        String phoneNumber = "A123";
        String academicBackground = "PhD";
        String street = "Rua X";
        String postalCode = "4000-001";
        String location = "Porto";
        String country = "Portugal";

        try (MockedConstruction<Teacher> teacherDouble = mockConstruction(Teacher.class, (mock, context) -> {
            when(mock.hasSameAcronym(any(Teacher.class))).thenReturn(true);
        })) {
            // Act
            Teacher teacher = teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                    street, postalCode, location, country, addressFactoryDouble, date, tcIDDouble, wpDouble, teacherIDDouble, departmentDouble);

            // Assert
            List<Teacher> teachers = teacherDouble.constructed();
            assertEquals(1, teachers.size());
            assertTrue(teacher.hasSameAcronym(teachers.get(0)));
        }
    }
}