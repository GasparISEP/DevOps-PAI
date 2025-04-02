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
    void IWantToRegisterAStudentInTheSystemControllerConstructorTest () {
        //arrange
        IStudentRepository iStudentRepository = mock(IStudentRepository.class);
        //act
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(iStudentRepository);
    }

    @Test
    void nullRepositoryDoesNotCreateObject () {
        //arrange

        //act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new US08_IWantToRegisterAStudentInTheSystemController(null));

        //assert
        assertEquals(exception.getMessage(), "Student repository cannot be null!");
    }

    @Test
    void registerStudentWithValidParametersReturnsTrue () throws Exception {
        //arrange
        Address addressDouble = mock(Address.class);
        StudentID studentID = mock(StudentID.class);
        Name name = mock(Name.class);
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phone = mock(PhoneNumber.class);
        Email email = mock(Email.class);
        StudentAcademicEmail academicEmail = mock(StudentAcademicEmail.class);

        IStudentRepository iStudentRepository = mock(IStudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(iStudentRepository);

        when(iStudentRepository.registerStudent(studentID, name, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail))
                .thenReturn(true);

        //act
        boolean result = ctrl.registerStudent(studentID, name, nif, phone, email, streetDouble, postalCodeDouble, locationDouble, countryDouble, academicEmail);

        //assert
        assertTrue(result);
    }
}