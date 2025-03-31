package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.Student;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentFactoryImplTest {

    @Test
    void shouldReturnStudentWhenInputIsValid() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentDouble = mockConstruction(Student.class, (mock, context) -> {

                StudentID studentID = (StudentID) context.arguments().get(0);
                when(mock.identity()).thenReturn(mockStudentID);
        })) {
            // Act
            Student student = studentFactory.newStudent(mockStudentID, name, nif, phone, email, address, academicEmail);

            // Assert
            assertEquals(1, studentDouble.constructed().size());
            assertSame(studentDouble.constructed().get(0), student);
            assertEquals(mockStudentID, student.identity());
        }
    }

    void shouldThrowExceptionWhenStudentIDIsNull () {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's ID is invalid.");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(null, name, nif, phone, email, address, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's ID is invalid."));
            }
        }
    }

    void shouldThrowExceptionWhenInputNameIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's name cannot be empty!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, null, nif, phone, email, address, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's name cannot be empty!"));
            }
        }
    }

    void shouldThrowExceptionWhenInputNIFIsNull () {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's NIF is invalid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, null, phone, email, address, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's NIF is invalid!"));
            }
        }
    }

    void shouldThrowExceptionWhenInputPhoneNumberIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's phone is invalid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, null, email, address, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's phone is invalid!"));
            }
        }
    }

    void shouldThrowExceptionWhenInputEmailIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's email is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, phone, null, address, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's email is not valid!"));
            }
        }
    }

    void shouldThrowExceptionWhenAcademicEmailIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        Email email = mock(Email.class);
        PhoneNumber phone = mock(PhoneNumber.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's Academic Email is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, phone, email, address, null);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's Academic Email is not valid!"));
            }
        }
    }
}