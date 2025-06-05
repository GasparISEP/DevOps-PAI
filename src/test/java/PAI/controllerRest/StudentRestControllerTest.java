package PAI.controllerRest;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.student.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentRestControllerTest {

    @Mock
    private IStudentService studentService;

    @Mock
    private IStudentDTOAssembler mapper;

    @InjectMocks
    private StudentRestController studentRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenDtoIsNull_thenReturnsBadRequest() {
        ResponseEntity<StudentResponseDTO> response = studentRestController.registerAStudent(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void whenServiceReturnsStudent_thenReturnsCreated() {
        StudentDTO dto = mock(StudentDTO.class);

        StudentID id = new StudentID(1234567);
        Name name = new Name("João Silva");
        NIF nif = new NIF("123456789", new Country("Portugal"));
        PhoneNumber phone = new PhoneNumber("+351", "912345678");
        Email email = new Email("joao.silva@example.com");

        Street street = new Street("Rua Central");
        PostalCode postalCode = new PostalCode("1234-567");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        Address address = new Address(street, postalCode, location, country);

        StudentAcademicEmail acadEmail = new StudentAcademicEmail(1234567);

        when(mapper.toStudentID(dto)).thenReturn(id);
        when(mapper.toName(dto)).thenReturn(name);
        when(mapper.toNIF(dto)).thenReturn(nif);
        when(mapper.toPhoneNumber(dto)).thenReturn(phone);
        when(mapper.toEmail(dto)).thenReturn(email);
        when(mapper.toAddress(dto)).thenReturn(address);
        when(mapper.toAcademicEmail(dto)).thenReturn(acadEmail);

        Student student = mock(Student.class);
        when(studentService.registerStudent(
                id, name, nif, phone, email,
                street, postalCode, location, country, acadEmail
        )).thenReturn(student);

        StudentResponseDTO responseDTO = mock(StudentResponseDTO.class);
        when(mapper.toStudentResponseDTO(student)).thenReturn(responseDTO);

        ResponseEntity<StudentResponseDTO> response = studentRestController.registerAStudent(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(responseDTO, response.getBody());
    }
    @Test
    void whenGetLastStudentID_thenReturnsExpectedValueInMap() {
        // Arrange
        int lastStudentID = 1234567;
        when(studentService.getLastStudentID()).thenReturn(lastStudentID);

        // Act
        ResponseEntity<Map<String, Integer>> response = studentRestController.getLastStudentID();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("lastStudentID"));
        assertEquals(lastStudentID, response.getBody().get("lastStudentID"));
    }


}