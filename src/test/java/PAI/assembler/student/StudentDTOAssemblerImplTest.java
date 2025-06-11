package PAI.assembler.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentIDDTO;
import PAI.dto.student.StudentResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentDTOAssemblerImplTest {

    @Test
    void toName() {
        //Arrange
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";

        StudentDTO studentDTO = new StudentDTO(studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email);
        IStudentDTOAssembler assembler = new StudentDTOAssemblerImpl();

        //Act
        Name result = assembler.toName(studentDTO);
        //Assert
        assertEquals("Paulo", result.getName());
    }

    @Test
    void toNIF() {
        //Arrange
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";

        StudentDTO studentDTO = new StudentDTO(studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email);
        IStudentDTOAssembler assembler = new StudentDTOAssemblerImpl();

        //Act
        NIF result = assembler.toNIF(studentDTO);

        //Assert
        assertEquals("123456789", result.getNIF());
        assertEquals("Portugal", result.getCountry().getCountryName());
    }

    @Test
    void toPhoneNumber() {
            //Arrange
            String studentName = "Paulo";
            String nif = "123456789";
            String nifCountry = "Portugal";
            String street = "Rua dos Pintos";
            String postalCode = "1234-000";
            String location = "Coimbra";
            String addressCountry = "Portugal";
            String phoneCountryCode = "+351";
            String phoneNumber = "987654321";
            String email = "paulo.andre@mail.pt";

            StudentDTO studentDTO = new StudentDTO(studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email);
            IStudentDTOAssembler assembler = new StudentDTOAssemblerImpl();

            //Act
            PhoneNumber result = assembler.toPhoneNumber(studentDTO);

            //Assert
            assertEquals("+351", result.getCountryCode());
            assertEquals("987654321", result.getNumber());
    }

    @Test
    void toEmail() {
        //Arrange
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";

        StudentDTO studentDTO = new StudentDTO(studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email);
        IStudentDTOAssembler assembler = new StudentDTOAssemblerImpl();

        //Act
        Email result = assembler.toEmail(studentDTO);

        //Assert
        assertEquals("paulo.andre@mail.pt", result.getEmail());
    }

    @Test
    void toAddress() {
        //Arrange
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";

        StudentDTO studentDTO = new StudentDTO(studentName, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email);
        IStudentDTOAssembler assembler = new StudentDTOAssemblerImpl();

        //Act
        Address result = assembler.toAddress(studentDTO);

        //Arrange
        assertEquals("Rua dos Pintos", result.getStreet().getStreet());
        assertEquals("1234-000", result.getPostalCode().getPostalCode());
        assertEquals("Coimbra", result.getLocation().getLocation());
        assertEquals("Portugal", result.getCountry().getCountryName());
    }

    @Test
    void studentResponseDTO() {

        //Arrange
        int studentID = 1234567;
        String studentName = "Paulo";
        String nif = "123456789";
        String nifCountry = "Portugal";
        String street = "Rua dos Pintos";
        String postalCode = "1234-000";
        String location = "Coimbra";
        String addressCountry = "Portugal";
        String phoneCountryCode = "+351";
        String phoneNumber = "987654321";
        String email = "paulo.andre@mail.pt";

        StudentID uniqueNumber = new StudentID(studentID);
        Name name = new Name(studentName);
        NIF nifObj = new NIF(nif, new Country(nifCountry));
        Street streetObj = new Street(street);
        PostalCode postalCodeObj = new PostalCode(postalCode);
        Location locationObj = new Location(location);
        Country addressCountryObj = new Country(addressCountry);
        PhoneNumber phoneNumberObj = new PhoneNumber(phoneCountryCode, phoneNumber);
        Email emailObj = new Email(email);
        Address addressObj = new Address(streetObj, postalCodeObj, locationObj, addressCountryObj);

        Student student = new Student(uniqueNumber, name, nifObj, phoneNumberObj, emailObj, addressObj, new StudentAcademicEmail(studentID));
        IStudentDTOAssembler assembler = new StudentDTOAssemblerImpl();

        //Act
        StudentResponseDTO result = assembler.toStudentResponseDTO(student);

        //Assert
        assertNotNull(result);
        assertEquals(studentID, result.getStudentID());
    }

    @Test
    void shouldTransformStudentIDtoIDDTO() {
        //arrange
        StudentIDDTO studentIDDTO = mock(StudentIDDTO.class);
        StudentDTOAssemblerImpl assembler = new StudentDTOAssemblerImpl();

        when(studentIDDTO.studentID()).thenReturn("1234567");

        //act
        StudentID id = assembler.toIdDTO(studentIDDTO);

        //assert
        assertEquals(1234567, id.getUniqueNumber());
    }

    @Test
    void shouldNotTransformToIdDTOBecauseItsNull(){
        //arrange
        StudentIDDTO studentIDDTO = null;
        StudentDTOAssemblerImpl assembler = new StudentDTOAssemblerImpl();

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toIdDTO(studentIDDTO));
    }
}