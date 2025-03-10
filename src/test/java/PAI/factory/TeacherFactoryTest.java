package PAI.factory;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherFactoryTest {

    @Test
    void shouldCreateAValidTeacherWhenMockedConstructorIsGiven() {
        // Arrange
        TeacherFactory teacherFactory = new TeacherFactory();

        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory tcpFactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = new TeacherCareerProgressionListFactory();
        Address addressDouble = mock(Address.class);
        Department departmentDouble = mock(Department.class);
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        when(addressFactoryDouble.createAddress("Rua Das Flores", "4000-001", "Porto", "Portugal")).thenReturn(addressDouble);
        when(tcpFactoryDouble.createTeacherCareerProgression("26-12-2024", tcDouble, 100))
                .thenReturn(mock(TeacherCareerProgression.class));

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
                    street, postalCode, location, country, addressFactoryDouble, date, tcDouble, workingPercentage, departmentDouble, tcpFactoryDouble, tcpLFactoryDouble);

            // Assert
            List<Teacher> teachers = teacherDouble.constructed();
            assertEquals(1, teachers.size());
            assertTrue(teacher.hasSameAcronym(teachers.get(0)));
        }
    }
}