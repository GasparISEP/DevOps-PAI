package PAI.controller;

import PAI.VOs.*;

import PAI.repository.IStudentRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US08_IWantToRegisterAStudentInTheSystemControllerTest {

    @Test
    void IWantToRegisterAStudentInTheSystemControllerConstructorTest () throws Exception {
        // Arrange
        IStudentRepository iStudentRepository = mock(IStudentRepository.class);

        // Act
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

        // Arrange
        IStudentRepository iStudentRepository = mock(IStudentRepository.class);
        US08_IWantToRegisterAStudentInTheSystemController ctrl = new US08_IWantToRegisterAStudentInTheSystemController(iStudentRepository);

        int uniqueNumber = 1000005;
        String name = "Miguel";
        String nif = "123456789";
        Country nifCountry = mock(Country.class);
        String countryCode = "+351";
        String phoneNumber = "987654321";
        String email = "miguel@gmail.com";
        String street = "Praça da Canção";
        String postalCode = "12345";
        String location = "Coimbra";
        String country = "Portugal";

        when(iStudentRepository.registerStudent(
                any(StudentID.class), any(Name.class), any(NIF.class),
                any(PhoneNumber.class), any(Email.class),any(Street.class), any(PostalCode.class),
                any(Location.class), any(Country.class), any(StudentAcademicEmail.class)))
                .thenReturn(true);

        // Act
        boolean result = ctrl.registerStudent(uniqueNumber, name, nif, nifCountry, countryCode, phoneNumber, email, street, postalCode, location, country);

        // Assert
        assertTrue(result);
    }
}