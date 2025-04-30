package PAI.service;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.factory.IStudentFactory;
import PAI.factory.StudentFactoryImpl;
import PAI.persistence.springdata.StudentRepositorySpringDataImpl;
import PAI.repository.IStudentRepository;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {

    @Test
    void shouldCreateStudentService() {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        //act
        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        //assert
        assertNotNull(studentServiceImpl);
    }

    static Stream<Arguments> testNullInputs() {
        return Streams.of(
                Arguments.of(null, mock(IStudentRepository.class), "Student Factory cannot be null!"),
                Arguments.of(mock(IStudentFactory.class), null, "Student Repository cannot be null!")
        );
    }

    @ParameterizedTest
    @MethodSource("testNullInputs")
    void shouldThrowExceptionIfInputsAreNull(IStudentFactory studentFactoryDouble, IStudentRepository studentRepositoryDouble, String expectedMessage) {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldRegisterStudentSuccessfully() throws Exception {
        //arrange

        //serviceParameters
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        //parameters to register Student
        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);
        Student studentDouble = mock(Student.class);

        when(studentFactoryDouble.newStudent(studentIDDouble, nameDouble, nifDouble, phoneNumberDouble, emailDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble)).thenReturn(studentDouble);

        when(studentRepositoryDouble.existsByStudentIDOrNIF(studentIDDouble, nifDouble)).thenReturn(false);

        when(studentRepositoryDouble.save(studentDouble)).thenReturn(studentDouble);


        //act
        Student result = studentServiceImpl.registerStudent(studentIDDouble, nameDouble, nifDouble, phoneNumberDouble, emailDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble);

        //assert
        assertEquals(studentDouble, result);
    }

    static Stream<Arguments> parametersToCreateStudentAreInvalid() {
        return Streams.of(
                Arguments.of(null, mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Student's ID is invalid."),
                Arguments.of(mock(StudentID.class), null, mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Student's name cannot be empty!"),
                Arguments.of(mock(StudentID.class), mock(Name.class), null, mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Student's NIF is invalid!"),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), null, mock(Email.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Student's phone is invalid!"),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), null, mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Student's email is not valid!"),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), null, mock(PostalCode.class), mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Street cannot be null."),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), null, mock(Location.class), mock(Country.class), mock(StudentAcademicEmail.class), "Postal Code cannot be null."),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), null, mock(Country.class), mock(StudentAcademicEmail.class), "Postal Code cannot be null."),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), null, mock(Country.class), mock(StudentAcademicEmail.class), "Location cannot be null."),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), null, mock(StudentAcademicEmail.class), "Country cannot be null."),
                Arguments.of(mock(StudentID.class), mock(Name.class), mock(NIF.class), mock(PhoneNumber.class), mock(Email.class), mock(Street.class), mock(PostalCode.class), mock(Location.class), mock(Country.class), null, "Student's Academic Email is not valid!")
        );
    }

    @ParameterizedTest
    @MethodSource("parametersToCreateStudentAreInvalid")
    void shouldThrowExceptionWhenParametersToCreateStudentAreNotValid(StudentID studentID, Name name, NIF nif, PhoneNumber phoneNumber, Email email, Street street, PostalCode postalCode, Location location, Country country, StudentAcademicEmail studentAcademicEmail, String expectedMessage) throws Exception {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        when(studentFactoryDouble.newStudent(studentID, name, nif, phoneNumber, email, street, postalCode, location, country, studentAcademicEmail)).thenThrow(new IllegalArgumentException(expectedMessage));

        when(studentRepositoryDouble.existsByStudentIDOrNIF(studentID, nif)).thenReturn(false);

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> studentServiceImpl.registerStudent(studentID, name, nif, phoneNumber, email, street, postalCode, location, country, studentAcademicEmail));

        //assert
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfStudentWithThatStudentIDOrNIFAlreadyExists() throws Exception {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);
        Student studentDouble = mock(Student.class);

        when(studentFactoryDouble.newStudent(studentIDDouble, nameDouble, nifDouble, phoneNumberDouble, emailDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble)).thenReturn(studentDouble);

        when(studentRepositoryDouble.existsByStudentIDOrNIF(studentIDDouble, nifDouble)).thenReturn(true);

        //act + assert
        assertThrows(Exception.class, () -> studentServiceImpl.registerStudent(studentIDDouble, nameDouble, nifDouble, phoneNumberDouble, emailDouble, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble));
    }

    @Test
    void shouldThrowExceptionWhenStudentCannotBeSavedOnDataBase() throws Exception {
        //arrange
        IStudentFactory studentFactoryDouble = mock(IStudentFactory.class);
        IStudentRepository studentRepositoryDouble = mock(IStudentRepository.class);

        StudentServiceImpl studentServiceImpl = new StudentServiceImpl(studentFactoryDouble, studentRepositoryDouble);

        StudentID studentIDDouble = mock(StudentID.class);
        Name nameDouble = mock(Name.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Email emailDouble = mock(Email.class);
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        StudentAcademicEmail academicEmailDouble = mock(StudentAcademicEmail.class);
        Student studentDouble = mock(Student.class);

        when(studentFactoryDouble.newStudent(studentIDDouble, nameDouble, nifDouble, phoneNumberDouble, emailDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble)).thenReturn(studentDouble);

        when(studentRepositoryDouble.existsByStudentIDOrNIF(studentIDDouble, nifDouble)).thenReturn(false);

        when(studentRepositoryDouble.save(studentDouble)).thenThrow(new RuntimeException());

        //act + assert
        assertThrows(RuntimeException.class, () -> studentServiceImpl.registerStudent(studentIDDouble, nameDouble, nifDouble, phoneNumberDouble, emailDouble, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmailDouble));
    }



}