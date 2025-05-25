package PAI.assembler.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.dto.teacher.RegisterTeacherRequestDTO;
import PAI.dto.teacher.TeacherDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherAssemblerImplTest {

    @Test
    void shouldCreateTeacherAssembler() {
        //Arrange
        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();

        //Act + Assert
        assertNotNull(teacherAssembler);
    }

    @Test
    void shouldConvertRegisterTeacherRequestDTOToRegisterTeacherCommandDTO() {
        // Arrange
        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("ABC");
        when(requestDTO.name()).thenReturn("João Silva");
        when(requestDTO.email()).thenReturn("ABC@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("MEI");
        when(requestDTO.street()).thenReturn("Rua das Flores");
        when(requestDTO.postalCode()).thenReturn("4470-147");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();

        // Act
        RegisterTeacherCommandDTO registerDepartmentCommandDTO = teacherAssembler.toRegisterTeacherCommandDTO(requestDTO);

        // Assert
        assertNotNull(registerDepartmentCommandDTO);
    }

    @Test
    void shouldThrowExceptionWhenRegisterTeacherRequestDTOIsNull() {
        // Arrange
        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            teacherAssembler.toRegisterTeacherCommandDTO(null);
        });
    }

    @Test
    void shouldConvertTeacherToDTO() {
        // Arrange
        Teacher teacher = mock(Teacher.class);
        TeacherID teacherID = mock(TeacherID.class);
        when(teacher.getTeacherID()).thenReturn(teacherID);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(teacher.getName()).thenReturn(name);
        when(name.getName()).thenReturn("João");

        Email email = mock(Email.class);
        when(teacher.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("JAB@isep.ipp.pt");

        NIF nif = mock(NIF.class);
        when(teacher.getNIF()).thenReturn(nif);
        when(nif.getNIF()).thenReturn("123456789");

        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        when(teacher.getPhoneNumber()).thenReturn(phoneNumber);
        when(phoneNumber.getCountryCode()).thenReturn("+351");
        when(phoneNumber.getNumber()).thenReturn("987654321");


        AcademicBackground academicBackground = mock(AcademicBackground.class);
        when(teacher.getAcademicBackground()).thenReturn(academicBackground);
        when(academicBackground.getAcademicBackground()).thenReturn("LEI");

        Address address = mock(Address.class);
        Street street = mock(Street.class);
        when(teacher.getAddress()).thenReturn(address);
        when(address.getStreet()).thenReturn(street);
        when(street.getStreet()).thenReturn("Rua Numero 1");

        PostalCode postalCode = mock(PostalCode.class);
        when(address.getPostalCode()).thenReturn(postalCode);
        when(postalCode.getPostalCode()).thenReturn("4000-100");

        Location location = mock(Location.class);
        when(address.getLocation()).thenReturn(location);
        when(location.getLocation()).thenReturn("Porto");

        Country country = mock(Country.class);
        when(address.getCountry()).thenReturn(country);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        when(teacher.getDepartmentID()).thenReturn(departmentID);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");

        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();

        // Act
        TeacherDTO dto = teacherAssembler.toDTO(teacher);

        // Assert
        assertNotNull(dto);
        assertEquals("JAB", dto.id());
        assertEquals("João", dto.name());
        assertEquals("JAB@isep.ipp.pt", dto.email());
        assertEquals("123456789", dto.nif());
        assertEquals("+351", dto.countryCode());
        assertEquals("987654321", dto.phoneNumber());
        assertEquals("LEI", dto.academicBackground());
        assertEquals("Rua Numero 1", dto.street());
        assertEquals("4000-100", dto.postalCode());
        assertEquals("Porto", dto.location());
        assertEquals("Portugal", dto.country());
        assertEquals("DEI", dto.departmentID());
    }

    @Test
    void shouldThrowExceptionWhenTeacherIsNull() {
        // Arrange
        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            teacherAssembler.toDTO(null);
        });
    }

    @Test
    void shouldConvertListOfTeachersToDTOs() {
        // Arrange
        Teacher teacher = mock(Teacher.class);
        TeacherID teacherID = mock(TeacherID.class);
        when(teacher.getTeacherID()).thenReturn(teacherID);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("JAB");

        Name name = mock(Name.class);
        when(teacher.getName()).thenReturn(name);
        when(name.getName()).thenReturn("João");

        Email email = mock(Email.class);
        when(teacher.getEmail()).thenReturn(email);
        when(email.getEmail()).thenReturn("JAB@isep.ipp.pt");

        NIF nif = mock(NIF.class);
        when(teacher.getNIF()).thenReturn(nif);
        when(nif.getNIF()).thenReturn("123456789");

        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        when(teacher.getPhoneNumber()).thenReturn(phoneNumber);
        when(phoneNumber.getCountryCode()).thenReturn("+351");
        when(phoneNumber.getNumber()).thenReturn("987654321");

        AcademicBackground academicBackground = mock(AcademicBackground.class);
        when(teacher.getAcademicBackground()).thenReturn(academicBackground);
        when(academicBackground.getAcademicBackground()).thenReturn("LEI");

        Address address = mock(Address.class);
        Street street = mock(Street.class);
        when(teacher.getAddress()).thenReturn(address);
        when(address.getStreet()).thenReturn(street);
        when(street.getStreet()).thenReturn("Rua Numero 1");

        PostalCode postalCode = mock(PostalCode.class);
        when(address.getPostalCode()).thenReturn(postalCode);
        when(postalCode.getPostalCode()).thenReturn("4000-100");

        Location location = mock(Location.class);
        when(address.getLocation()).thenReturn(location);
        when(location.getLocation()).thenReturn("Porto");

        Country country = mock(Country.class);
        when(address.getCountry()).thenReturn(country);
        when(country.getCountryName()).thenReturn("Portugal");

        DepartmentID departmentID = mock(DepartmentID.class);
        when(teacher.getDepartmentID()).thenReturn(departmentID);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");

        Iterable<Teacher> teachers = List.of(teacher);
        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();

        // Act
        Iterable<TeacherDTO> teacherDTOs = teacherAssembler.toDTOs(teachers);

        // Assert
        assertNotNull(teacherDTOs);
        assertTrue(teacherDTOs.iterator().hasNext());
    }

    @Test
    void shouldReturnEmptyListWhenInputIsNull() {
        // Arrange
        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();
        // Act
        Iterable<TeacherDTO> teacherDTOs = teacherAssembler.toDTOs(null);
        // Assert
        assertNotNull(teacherDTOs);
        assertFalse(teacherDTOs.iterator().hasNext());
    }

    @Test
    void shouldReturnEmptyListWhenInputIsEmpty() {
        // Arrange
        TeacherAssemblerImpl teacherAssembler = new TeacherAssemblerImpl();
        // Act
        Iterable<TeacherDTO> teacherDTOs = teacherAssembler.toDTOs(List.of());
        // Assert
        assertNotNull(teacherDTOs);
        assertFalse(teacherDTOs.iterator().hasNext());
    }

}

