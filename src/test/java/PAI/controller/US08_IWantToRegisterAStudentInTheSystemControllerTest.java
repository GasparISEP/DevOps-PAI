package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.Student;
import PAI.factory.IStudentFactory;
import PAI.factory.StudentFactoryImpl;
import PAI.factory.IStudentListFactory;
import PAI.factory.StudentListFactoryImpl;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US08_IWantToRegisterAStudentInTheSystemControllerTest {

    @Test
    void IWantToRegisterAStudentInTheSystemControllerConstructorTestWithIsolation() {
        //arrange
        IStudentRepository iStudentRepository = mock(IStudentRepository.class);
        //act
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(iStudentRepository);
    }

    @Test
    void nullRepositoryDoesNotCreateObject1() {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new US08_IWantToRegisterAStudentInTheSystemController(null));

        //assert
        assertEquals(exception.getMessage(), "Student repository cannot be null!");
    }

    @Test
    void registerStudentWithValidParametersReturnsTrueWithIsolation() throws Exception {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentID = new StudentID(1234567);
        Name name = new Name("Rita");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("123456789",country);
        PhoneNumber phone = new PhoneNumber("+351","963741258");
        Email email = new Email("rita@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        IStudentRepository iStudentRepository = mock(IStudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(iStudentRepository);

        when(iStudentRepository.registerStudent(studentID, name, nif, phone, email, addressDouble, academicEmail))
                .thenReturn(true);

        //act
        boolean result = ctrl.registerStudent(studentID, name, nif, phone, email, addressDouble, academicEmail);

        //assert
        assertTrue(result);
    }

    public static Stream<Arguments> provideInvalidParametersWithIsolation() {
        String countryName = "Portugal";
        Country country = new Country(countryName);
        return Stream.of(
                Arguments.of(1345678, new NIF("123456789", country)),
                Arguments.of(1234567, new NIF("987654321", country))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParametersWithIsolation")
    void throwsExceptionIfNIFOrIDAreRepeatedWithoutIsolationWithIsolation(int uniqueNumber, NIF NIF) throws Exception {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID mockStudentID1 = mock(StudentID.class);
        Name nameDouble = new Name("Rita");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nifDouble = new NIF("123456789", country);
        PhoneNumber phoneDouble = new PhoneNumber("+351","963741258");
        Email emailDouble = new Email("rita@gmail.com");
        StudentAcademicEmail academicEmailDouble = new StudentAcademicEmail(mockStudentID1);

        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble2 = mock(Name.class);
        PhoneNumber phoneDouble2 = mock(PhoneNumber.class);
        Email emailDouble2 = mock(Email.class);
        StudentAcademicEmail academicEmailDouble2 = new StudentAcademicEmail(mockStudentID2);

        when(mockStudentID2.getUniqueNumber()).thenReturn(uniqueNumber);

        StudentRepository studentRepositoryDouble = mock(StudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(studentRepositoryDouble);

        // ID is different, but NIF duplicated ("123456789")
        when(studentRepositoryDouble.registerStudent(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble))
                .thenReturn(true);

        ctrl.registerStudent(mockStudentID1, nameDouble, nifDouble, phoneDouble, emailDouble, addressDouble, academicEmailDouble);

        // Caso 2: ID is duplicated (1), but NIF is different ("987654321")
        when(studentRepositoryDouble.registerStudent(mockStudentID2, nameDouble2, NIF, phoneDouble2, emailDouble2, addressDouble, academicEmailDouble2))
                .thenThrow(new Exception("Duplicate ID or NIF detected. Student cannot be added."));

        //act
        Exception exception = assertThrows(Exception.class, () -> ctrl.registerStudent(mockStudentID2, nameDouble2, NIF, phoneDouble2, emailDouble2, addressDouble, academicEmailDouble2));

        //assert
        assertEquals(exception.getMessage(), "Duplicate ID or NIF detected. Student cannot be added.");
    }
}