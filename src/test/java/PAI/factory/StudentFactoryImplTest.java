package PAI.factory;

import PAI.VOs.*;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentFactoryImplTest {

    @Test
    void shouldReturnStudentWhenInputIsValid() throws Exception {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();

        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentDouble = mockConstruction(Student.class, (mock, context) -> {

                when(mock.identity()).thenReturn(studentIDDouble);
        })) {
            // Act
            Student student = studentFactory.newStudent(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble);

            // Assert
            assertEquals(1, studentDouble.constructed().size());
            assertSame(studentDouble.constructed().get(0), student);
            assertEquals(studentIDDouble, student.identity());
        }
    }

    @Test
    void whenNewStudentFromDataModelConstructorInvokedThenMockedObjectShouldBeCreated() {
        //arrange
        IStudentFactory studentFactory = new StudentFactoryImpl();

        Address addressDouble = mock(Address.class);
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);

        try (MockedConstruction<Student> studentDouble = mockConstruction(Student.class, (mock, context) -> {
            when(mock.identity()).thenReturn(studentIDDouble);
        })) {
            // Act
            Student student = studentFactory.newStudentFromDataModel(studentIDDouble, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

            // Assert
            assertEquals(1, studentDouble.constructed().size());
            assertSame(studentDouble.constructed().get(0), student);
            assertEquals(studentIDDouble, student.identity());
        }
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

        try (MockedConstruction<Address> addressMock = mockConstruction(Address.class, (mock, context) -> {
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

    @Test
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

        try (MockedConstruction<Address> addressMock = mockConstruction(Address.class, (mock, context) -> {
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

    @Test
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

        try (MockedConstruction<Address> addressMock = mockConstruction(Address.class, (mock, context) -> {
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

    @Test
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

        try (MockedConstruction<Address> addressMock = mockConstruction(Address.class, (mock, context) -> {
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