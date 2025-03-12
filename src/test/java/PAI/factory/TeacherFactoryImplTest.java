package PAI.factory;

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
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpListFactoryDouble = mock(TeacherCareerProgressionListFactory.class);
        TeacherFactoryImpl teacherFactory = new TeacherFactoryImpl(tcpFactoryDouble, tcpListFactoryDouble);

        AddressFactoryImpl addressFactoryDouble = mock(AddressFactoryImpl.class);
        Address addressDouble = mock(Address.class);
        Department departmentDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        when(addressFactoryDouble.createAddress("Rua Das Flores", "4000-001", "Porto", "Portugal")).thenReturn(addressDouble);
        when(tcpFactoryDouble.createTeacherCareerProgression("26-12-2024", tcDouble, 100)).thenReturn(mock(TeacherCareerProgression.class));
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
        String date = "26-12-2024";
        int workingPercentage = 100;

        try (MockedConstruction<Teacher> teacherDouble = mockConstruction(Teacher.class, (mock, context) -> {
            when(mock.hasSameAcronym(any(Teacher.class))).thenReturn(true);
        })) {
            // Act
            Teacher teacher = teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                    street, postalCode, location, country, addressFactoryDouble, date, tcDouble, workingPercentage, departmentDouble);

            // Assert
            List<Teacher> teachers = teacherDouble.constructed();
            assertEquals(1, teachers.size());
            assertTrue(teacher.hasSameAcronym(teachers.get(0)));
        }
    }
}