package PAI.controllerRest;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.student.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

class StudentRestControllerTest {

    @Mock
    private IStudentService studentService;

    @Mock
    private IStudentDTOAssembler mapper;

    @Mock
    private IStudentHateoasAssembler hateoasAssembler;

    @InjectMocks
    private StudentRestController studentRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenDtoIsNull_thenReturnsBadRequest() {
        ResponseEntity<EntityModel<StudentResponseDTO>> response = studentRestController.registerAStudent(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void whenServiceReturnsStudent_thenReturnsCreatedWithHateoasAndLinks() {
        // Arrange
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

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                1234567, "João Silva", "123456789", "Portugal",
                "Rua Central", "1234-567", "Porto", "Portugal",
                "+351", "912345678", "joao.silva@example.com", "1234567@ipt.pt"
        );

        EntityModel<StudentResponseDTO> entityModel = EntityModel.of(responseDTO);
        entityModel.add(linkTo(methodOn(StudentRestController.class).getLastStudentID()).withRel("last-student-id"));

        when(hateoasAssembler.toModel(student)).thenReturn(entityModel);

        // Act
        ResponseEntity<EntityModel<StudentResponseDTO>> response = studentRestController.registerAStudent(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(responseDTO, response.getBody().getContent());

        assertTrue(response.getBody().getLinks().hasLink("last-student-id"));
        Link link = response.getBody().getLink("last-student-id").orElse(null);
        assertNotNull(link);
        assertEquals("last-student-id", link.getRel().value());
        assertEquals(linkTo(methodOn(StudentRestController.class).getLastStudentID()).toUri().toString(), link.getHref());

        verify(hateoasAssembler, times(1)).toModel(student);
        verify(mapper, times(1)).toStudentID(dto);
        verify(mapper, times(1)).toName(dto);
        verify(mapper, times(1)).toNIF(dto);
        verify(mapper, times(1)).toPhoneNumber(dto);
        verify(mapper, times(1)).toEmail(dto);
        verify(mapper, times(1)).toAddress(dto);
        verify(mapper, times(1)).toAcademicEmail(dto);
    }

    @Test
    void whenGetLastStudentID_thenReturnsExpectedValueInMap() {
        int lastStudentID = 1234567;
        when(studentService.getLastStudentID()).thenReturn(lastStudentID);

        ResponseEntity<Map<String, Integer>> response = studentRestController.getLastStudentID();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("lastStudentID"));
        assertEquals(lastStudentID, response.getBody().get("lastStudentID"));
    }
}