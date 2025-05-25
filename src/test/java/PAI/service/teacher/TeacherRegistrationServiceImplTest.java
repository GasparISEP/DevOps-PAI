package PAI.service.teacher;

import PAI.VOs.*;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.ITeacherFactory;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherRegistrationServiceImplTest {

    @Test
    void shouldCreateTeacherRegistrationService() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);

        // Act
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        // Assert
        assertNotNull(teacherRegistrationService);
    }

    @Test
    void shouldThrowExceptionIfTeacherFactoryIsNull() {
        // Arrange
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);

        // Act + Assert
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRegistrationServiceImpl(null, teacherRepositoryDouble);
        });

        // Assert
        assertEquals("Teacher Factory cannot be null.", expectedException.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTeacherRepositoryIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);

        // Act & Assert
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherRegistrationServiceImpl(teacherFactoryDouble, null);
        });

        // Assert
        assertEquals("Teacher Repository cannot be null.", expectedException.getMessage());
    }

    @Test
    void shouldCreateAndSaveTeacher() throws Exception {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);

        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);

        when(teacherAcronym.getAcronym()).thenReturn("JAB");
        Name name = mock(Name.class);

        when(name.getName()).thenReturn("João");
        Email email = mock(Email.class);

        when(email.getEmail()).thenReturn("JAB@isep.ipp.pt");
        NIF nif = mock(NIF.class);

        when(nif.getNIF()).thenReturn("123456789");
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        when(phoneNumber.getCountryCode()).thenReturn("+351");

        when(phoneNumber.getNumber()).thenReturn("987654321");
        AcademicBackground academicBackground = mock(AcademicBackground.class);

        when(academicBackground.getAcademicBackground()).thenReturn("LEI");
        Street street = mock(Street.class);

        when(street.getStreet()).thenReturn("Rua Numero 1");
        PostalCode postalCode = mock(PostalCode.class);

        when(postalCode.getPostalCode()).thenReturn("4000-100");
        Location location = mock(Location.class);

        when(location.getLocation()).thenReturn("Porto");
        Country country = mock(Country.class);

        when(country.getCountryName()).thenReturn("Portugal");
        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);

        when(departmentAcronym.getAcronym()).thenReturn("DEI");
        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);

        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        Teacher teacherDouble = mock(Teacher.class);
        when(teacherFactoryDouble.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID)).thenReturn(teacherDouble);

        when(teacherRepositoryDouble.existsByTeacherIdOrNif(mock(Teacher.class).getTeacherID(), mock(Teacher.class).getNIF())).thenReturn(false);
        when(teacherRepositoryDouble.save(mock(Teacher.class))).thenReturn(teacherDouble);

        // Act
        Teacher teacher = teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO);

        // Assert
        assertNotNull(teacher);
        assertEquals(teacher, teacherDouble);
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = null;
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = null;
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = null;
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenNifIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = null;
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = null;
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenAcademicBackgroundIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = null;
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = null;
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenPostalCodeIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = null;
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenLocationIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = null;
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenCountryIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = null;
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIdIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = null;

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        when(teacherFactoryDouble.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID)).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldThrowExceptionWhenTeacherAlreadyExits() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        RegisterTeacherCommandDTO registerTeacherCommandDTO = mock(RegisterTeacherCommandDTO.class);
        when(registerTeacherCommandDTO.id()).thenReturn(teacherID);
        when(registerTeacherCommandDTO.name()).thenReturn(name);
        when(registerTeacherCommandDTO.email()).thenReturn(email);
        when(registerTeacherCommandDTO.nif()).thenReturn(nif);
        when(registerTeacherCommandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(registerTeacherCommandDTO.academicBackground()).thenReturn(academicBackground);
        when(registerTeacherCommandDTO.street()).thenReturn(street);
        when(registerTeacherCommandDTO.postalCode()).thenReturn(postalCode);
        when(registerTeacherCommandDTO.location()).thenReturn(location);
        when(registerTeacherCommandDTO.country()).thenReturn(country);
        when(registerTeacherCommandDTO.departmentID()).thenReturn(departmentID);

        Teacher teacherDouble = mock(Teacher.class);

        when(teacherFactoryDouble.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID)).thenReturn(teacherDouble);
        when(teacherRepositoryDouble.existsByTeacherIdOrNif(mock(Teacher.class).getTeacherID(), mock(Teacher.class).getNIF())).thenReturn(true);

        // Act & Assert
        assertThrows(BusinessRuleViolationException.class, () -> teacherRegistrationService.createAndSaveTeacher(registerTeacherCommandDTO));
    }

    @Test
    void shouldReturnAllTeachersWhenCallingGetAllTeachers(){
        //Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        Teacher teacherDouble1 = mock(Teacher.class);
        Teacher teacherDouble2 = mock(Teacher.class);
        List<Teacher> teachersList = List.of(teacherDouble1, teacherDouble2);
        when(teacherRepositoryDouble.findAll()).thenReturn(teachersList);

        //Act
        Iterable<Teacher> result = teacherRegistrationService.getAllTeachers();

        //Assert
        List<Teacher> resultList = (List<Teacher>) result;
        assertEquals(2, resultList.size());
    }

    @Test
    void shouldReturnEmptyListIfNoTeachersExistWhenCallingGetAllTeachers() {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        TeacherRegistrationServiceImpl teacherRegistrationService = new TeacherRegistrationServiceImpl(teacherFactoryDouble, teacherRepositoryDouble);

        when(teacherRepositoryDouble.findAll()).thenReturn(List.of());

        // Act
        Iterable<Teacher> result = teacherRegistrationService.getAllTeachers();

        // Assert
        List<Teacher> resultList = (List<Teacher>) result;
        assertTrue(resultList.isEmpty());
    }
}