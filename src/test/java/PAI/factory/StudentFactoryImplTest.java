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
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
            Student student = studentFactory.newStudent(mockStudentID, name, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);

            // Assert
            assertEquals(1, studentDouble.constructed().size());
            assertSame(studentDouble.constructed().get(0), student);
            assertEquals(mockStudentID, student.identity());
        }
    }

    void studentCreationShouldThrowExceptionWhenStudentIDIsNull () {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
                studentFactory.newStudent(null, name, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's ID is invalid."));
            }
        }
    }

    void studentCreationShouldThrowExceptionWhenInputNameIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
                studentFactory.newStudent(mockStudentID, null, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's name cannot be empty!"));
            }
        }
    }

    void studentCreationShouldThrowExceptionWhenInputNIFIsNull () {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
                studentFactory.newStudent(mockStudentID, name, null, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's NIF is invalid!"));
            }
        }
    }

    void studentCreationShouldThrowExceptionWhenInputPhoneNumberIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
                studentFactory.newStudent(mockStudentID, name, nif, null, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's phone is invalid!"));
            }
        }
    }

    void studentCreationShouldThrowExceptionWhenInputEmailIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
                studentFactory.newStudent(mockStudentID, name, nif, phone, null, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's email is not valid!"));
            }
        }
    }

    void studentCreationShouldThrowExceptionWhenAcademicEmailIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
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
                studentFactory.newStudent(mockStudentID, name, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, null);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Student's Academic Email is not valid!"));
            }
        }
    }

    void studentCreationShouldThrowExceptionWhenStreetIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        Email email = mock(Email.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Street is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, phone, email, null, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Street is not valid!"));
            }
        }
    }

    void shouldThrowExceptionWhenPostalCodeIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        Email email = mock(Email.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Postal Code is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, phone, email, streetDouble, null, locationDouble, countryDouble, academicEmailDouble);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Postal Code is not valid!"));
            }
        }
    }

    void shouldThrowExceptionWhenLocationIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Country countryDouble = mock(Country.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        Email email = mock(Email.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Location is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, phone, email, streetDouble, postalCodeDouble, null, countryDouble, academicEmailDouble);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Location is not valid!"));
            }
        }
    }

    void shouldThrowExceptionWhenCountryIsNull() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        StudentID mockStudentID = mock(StudentID.class);
        Name name = mock(Name.class);
        NIF nif = mock(NIF.class);
        Email email = mock(Email.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentMock = mockConstruction(Student.class, (mock, context) -> {
            throw new IllegalArgumentException("Country is not valid!");
        })) {
            // Act & Assert
            try {
                studentFactory.newStudent(mockStudentID, name, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, null, academicEmailDouble);
                fail("Excepted exception not thrown");
            } catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Country is not valid!"));
            }
        }
    }
}