package PAI.factory;

import PAI.domain.Address;
import PAI.domain.Student;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentFactoryImplTest {

    @Test
    void shouldReturnStudentWhenInputIsValid() {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);
        String uniqueNumber = "1234567";

        try (MockedConstruction<Student> studentDouble = mockConstruction(Student.class, (mock, context) -> {

                String actualUniqueNumber = (String) context.arguments().get(0);
                when(mock.getUniqueNumber()).thenReturn(actualUniqueNumber);
        })) {
            // Act
            Student student = studentFactory.newStudent(uniqueNumber, "Daniela", "123456789", "963741258", "rita@gmail.com", address);

            // Assert
            assertEquals(1, studentDouble.constructed().size());
            assertSame(studentDouble.constructed().get(0), student);
            assertEquals(uniqueNumber, student.getUniqueNumber());
        }
    }


    static Stream<Arguments> testInputUniqueNumberIsInvalid() {
        return Streams.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((Object) null),
                Arguments.of( "0000000"),
                Arguments.of( "-1000000"),
                Arguments.of("123"),
                Arguments.of("1234567890")
        );
    }
    @ParameterizedTest
    @MethodSource("testInputUniqueNumberIsInvalid")
    void shouldThrowExceptionWhenInputUniqueNumberIsInvalid(String uniqueNumber) {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's unique number must have 7 digits and start with 1!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(uniqueNumber, "Daniela", "123456789", "963741258", "rita@gmail.com", address);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's unique number must have 7 digits and start with 1!"));
            }
        }
    }

    static Stream<Arguments> testInputNameIsInvalid() {
        return Streams.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((Object) null)
        );
    }
    @ParameterizedTest
    @MethodSource("testInputNameIsInvalid")
    void shouldThrowExceptionWhenInputNameIsInvalid(String name) {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's name cannot be empty!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent("1234567", name, "123456789", "963741258", "rita@gmail.com", address);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's name cannot be empty!"));
            }
        }
    }

    static Stream<Arguments> testInputNIFIsInvalid() {
        return Streams.of(
                Arguments.of("1234567890123456"),
                Arguments.of("1234!56789"),
                Arguments.of("A1234 56789"),
                Arguments.of("1234#56789"),
                Arguments.of("1234SD6789")
        );
    }
    @ParameterizedTest
    @MethodSource("testInputNIFIsInvalid")
    void shouldThrowExceptionWhenInputNIFIsInvalid(String NIF) {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's NIF is invalid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent("1234567", "Daniela", NIF, "963741258", "rita@gmail.com", address);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's NIF is invalid!"));
            }
        }
    }

    static Stream<Arguments> testInputPhoneNumberIsInvalid() {
        return Streams.of(
                Arguments.of("12345"),
                Arguments.of("+12345"),
                Arguments.of("123-45-678"),
                Arguments.of("+12 345 67 89"),
                Arguments.of("+12 345 (456)-789"),
                Arguments.of("12.34.56.78.90"),
                Arguments.of("+1(2345)67890"),
                Arguments.of("91234567(8)"),
                Arguments.of("91234EC78")
        );
    }
    @ParameterizedTest
    @MethodSource("testInputPhoneNumberIsInvalid")
    void shouldThrowExceptionWhenInputPhoneNumberIsInvalid(String phone) {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's phone is invalid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent("1234567", "Daniela", "123456789", phone, "rita@gmail.com", address);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's phone is invalid!"));
            }
        }
    }

    static Stream<Arguments> testInputEmailIsInvalid() {
        return Streams.of(
                Arguments.of("12345"),
                Arguments.of("+12345"),
                Arguments.of("123-45-678"),
                Arguments.of("+12 345 67 89"),
                Arguments.of("+12 345 (456)-789"),
                Arguments.of("12.34.56.78.90"),
                Arguments.of("+1(2345)67890"),
                Arguments.of("91234567(8)"),
                Arguments.of("91234EC78")
        );
    }
    @ParameterizedTest
    @MethodSource("testInputEmailIsInvalid")
    void shouldThrowExceptionWhenInputEmailIsInvalid(String email) {
        //arrange
        StudentFactory studentFactory = new StudentFactoryImpl();
        Address address = mock(Address.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Student's email is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent("1234567", "Daniela", "123456789", "912345678", email, address);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's email is not valid!"));
            }
        }
    }
}