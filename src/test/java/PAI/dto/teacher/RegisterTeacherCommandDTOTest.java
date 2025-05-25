package PAI.dto.teacher;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterTeacherCommandDTOTest {

    @Test
    void shouldCreateRegisterTeacherCommandCorrectly() {
        // Arrange
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

        //Act
        RegisterTeacherCommandDTO command = new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);

        //Assert
        assertEquals(teacherID, command.id());
        assertEquals(name, command.name());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Arrange
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(null, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Id is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIdIsBlank() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("");

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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Id is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, null, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("João");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, null, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Email is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsBlank() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("João");

        Email email = mock(Email.class);
        when(email.getEmail()).thenReturn("");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Email is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNifIsNull() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("João");

        Email email = mock(Email.class);
        when(email.getEmail()).thenReturn("JAB@isep.ipp.pt");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, null, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("NIF is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNifIsBlank() {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("João");

        Email email = mock(Email.class);
        when(email.getEmail()).thenReturn("JAB@isep.ipp.pt");

        NIF nif = mock(NIF.class);
        when(nif.getNIF()).thenReturn("");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("NIF is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsNull() {
        // Arrange
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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, null, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("PhoneNumber is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsBlank() {
        // Arrange
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
        when(phoneNumber.getCountryCode()).thenReturn("");
        when(phoneNumber.getNumber()).thenReturn("");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("PhoneNumber is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcademicBackgroundIsNull() {
        // Arrange
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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, null, street, postalCode, location, country, departmentID);
        });
        assertEquals("Academic Background is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcademicBackgroundIsBlank() {
        // Arrange
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
        when(academicBackground.getAcademicBackground()).thenReturn("");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Academic Background is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        // Arrange
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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, null, postalCode, location, country, departmentID);
        });
        assertEquals("Street is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsBlank() {
        // Arrange
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
        when(street.getStreet()).thenReturn("");

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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Street is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPostalCodeIsNull() {
        // Arrange
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

        Location location = mock(Location.class);
        when(location.getLocation()).thenReturn("Porto");

        Country country = mock(Country.class);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, null, location, country, departmentID);
        });
        assertEquals("Postal Code is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPostalCodeIsBlank() {
        // Arrange
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
        when(postalCode.getPostalCode()).thenReturn("");

        Location location = mock(Location.class);
        when(location.getLocation()).thenReturn("Porto");

        Country country = mock(Country.class);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Postal Code is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLocationIsNull() {
        // Arrange
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
        when(location.getLocation()).thenReturn(null);

        Country country = mock(Country.class);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, null, country, departmentID);
        });
        assertEquals("Location is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLocationIsBlank() {
        // Arrange
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
        when(location.getLocation()).thenReturn("");

        Country country = mock(Country.class);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Location is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCountryIsNull() {
        // Arrange
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

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, null, departmentID);
        });
        assertEquals("Country is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCountryIsBlank() {
        // Arrange
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
        when(country.getCountryName()).thenReturn("");

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Country is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIdIsNull() {
        // Arrange
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
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, null);
        });
        assertEquals("Department ID is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIdIsBlank() {
        // Arrange
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
        when(departmentAcronym.getAcronym()).thenReturn("");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterTeacherCommandDTO(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        });
        assertEquals("Department ID is required", exception.getMessage());
    }
}

