package PAI.controllerRest;

import static org.junit.jupiter.api.Assertions.*;

import PAI.VOs.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.dto.teacher.RegisterTeacherRequestDTO;
import PAI.dto.teacher.TeacherDTO;
import PAI.dto.teacherCareerProgression.ITeacherCareerProgressionAssembler;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryRequestDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.teacher.ITeacherRegistrationService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.service.teacherCareerProgression.ITeacherCareerProgressionServiceV2;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

class TeacherRestControllerTest {

    @Test
    void shouldCreateTeacherRestController() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);
        // Act + Assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnListOfTeacherDTO() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        Teacher teacher3 = mock(Teacher.class);

        TeacherDTO teacherDTO1 = mock(TeacherDTO.class);
        TeacherDTO teacherDTO2 = mock(TeacherDTO.class);
        TeacherDTO teacherDTO3 = mock(TeacherDTO.class);

        List<Teacher> teachers = List.of(teacher1, teacher2, teacher3);
        List<TeacherDTO> teacherDTOs = List.of(teacherDTO1, teacherDTO2, teacherDTO3);

        when(teacherService.getAllTeachers()).thenReturn(teachers);
        when(teacherAssembler.toDTOs(teachers)).thenReturn(teacherDTOs);

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((Iterable<?>) response.getBody()).iterator().hasNext());
        assertTrue(((Collection<?>) response.getBody()).contains(teacherDTO1));
        assertTrue(((Collection<?>) response.getBody()).contains(teacherDTO2));
        assertTrue(((Collection<?>) response.getBody()).contains(teacherDTO3));
    }

    @Test
    void shouldReturnEmptyListOfTeacherDTOIfNoTeachersExist() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

        List<Teacher> teachers = List.of();
        List<TeacherDTO> teacherDTOs = List.of();

        when(teacherService.getAllTeachers()).thenReturn(teachers);
        when(teacherAssembler.toDTOs(teachers)).thenReturn(teacherDTOs);

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(((Iterable<?>) response.getBody()).iterator().hasNext());
    }

    @Test
    void shouldReturnBadRequestIfIllegalArgumentExceptionIsThrown() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

        String errorMessage = "Invalid input data";

        when(teacherService.getAllTeachers()).thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorIfUnexpectedExceptionIsThrown() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,careerAssembler);

        when(teacherService.getAllTeachers()).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<?> response = teacherRestController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnCreatedTeacherDTOIfSuccessful() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, careerAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

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

        RegisterTeacherCommandDTO commandDTO = mock(RegisterTeacherCommandDTO.class);
        when(commandDTO.id()).thenReturn(teacherID);
        when(commandDTO.name()).thenReturn(name);
        when(commandDTO.email()).thenReturn(email);
        when(commandDTO.nif()).thenReturn(nif);
        when(commandDTO.phoneNumber()).thenReturn(phoneNumber);
        when(commandDTO.academicBackground()).thenReturn(academicBackground);
        when(commandDTO.street()).thenReturn(street);
        when(commandDTO.postalCode()).thenReturn(postalCode);
        when(commandDTO.location()).thenReturn(location);
        when(commandDTO.country()).thenReturn(country);
        when(commandDTO.departmentID()).thenReturn(departmentID);

        Teacher teacher = mock(Teacher.class);
        TeacherDTO teacherDTO = mock(TeacherDTO.class);

        when(teacherDTO.id()).thenReturn("JAB");
        when(teacherDTO.name()).thenReturn("João");
        when(teacherDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(teacherDTO.nif()).thenReturn("123456789");
        when(teacherDTO.countryCode()).thenReturn("+351");
        when(teacherDTO.phoneNumber()).thenReturn("987654321");
        when(teacherDTO.academicBackground()).thenReturn("LEI");
        when(teacherDTO.street()).thenReturn("Rua Numero 1");
        when(teacherDTO.postalCode()).thenReturn("4000-100");
        when(teacherDTO.location()).thenReturn("Porto");
        when(teacherDTO.country()).thenReturn("Portugal");
        when(teacherDTO.departmentID()).thenReturn("DEI");

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenReturn(commandDTO);
        when(teacherService.createAndSaveTeacher(commandDTO)).thenReturn(teacher);
        when(teacherAssembler.toDTO(teacher)).thenReturn(teacherDTO);

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(teacherDTO, response.getBody());
    }

    @Test
    void shouldReturnConflictWhenBusinessRuleViolation() throws Exception {
        //Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, careerAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        RegisterTeacherCommandDTO commandDTO = mock(RegisterTeacherCommandDTO.class);
        when(commandDTO.id()).thenReturn(mock(TeacherID.class));
        when(commandDTO.name()).thenReturn(mock(Name.class));
        when(commandDTO.email()).thenReturn(mock(Email.class));
        when(commandDTO.nif()).thenReturn(mock(NIF.class));
        when(commandDTO.phoneNumber()).thenReturn(mock(PhoneNumber.class));
        when(commandDTO.academicBackground()).thenReturn(mock(AcademicBackground.class));
        when(commandDTO.street()).thenReturn(mock(Street.class));
        when(commandDTO.postalCode()).thenReturn(mock(PostalCode.class));
        when(commandDTO.location()).thenReturn(mock(Location.class));
        when(commandDTO.country()).thenReturn(mock(Country.class));
        when(commandDTO.departmentID()).thenReturn(mock(DepartmentID.class));

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenReturn(commandDTO);
        when(teacherService.createAndSaveTeacher(commandDTO)).thenReturn(mock(Teacher.class));
        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenReturn(commandDTO);
        when(teacherService.createAndSaveTeacher(commandDTO)).thenThrow(new BusinessRuleViolationException("Teacher already exists"));

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Teacher already exists", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgument() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, careerAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenThrow(new IllegalArgumentException("Invalid id"));

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid id", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedException() {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler careerAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, careerAssembler);

        RegisterTeacherRequestDTO requestDTO = mock(RegisterTeacherRequestDTO.class);
        when(requestDTO.id()).thenReturn("JAB");
        when(requestDTO.name()).thenReturn("João");
        when(requestDTO.email()).thenReturn("JAB@isep.ipp.pt");
        when(requestDTO.nif()).thenReturn("123456789");
        when(requestDTO.countryCode()).thenReturn("+351");
        when(requestDTO.phoneNumber()).thenReturn("987654321");
        when(requestDTO.academicBackground()).thenReturn("LEI");
        when(requestDTO.street()).thenReturn("Rua Numero 1");
        when(requestDTO.postalCode()).thenReturn("4000-100");
        when(requestDTO.location()).thenReturn("Porto");
        when(requestDTO.country()).thenReturn("Portugal");
        when(requestDTO.departmentID()).thenReturn("DEI");

        when(teacherAssembler.toRegisterTeacherCommandDTO(requestDTO)).thenThrow(new RuntimeException("Unexpected error occurred"));

        // Act
        ResponseEntity<?> response = teacherRestController.registerTeacher(requestDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void ShouldReturnCreatForUpdateTeacherCategory() throws Exception{
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,categoryAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        UpdateTeacherCategoryResponseDTO responseDTO = mock(UpdateTeacherCategoryResponseDTO.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenReturn(command);
        when(careerService.updateTeacherCategoryInTeacherCareerProgression(command)).thenReturn(Optional.of(teacherCareerProgression));
        when(categoryAssembler.toUpdateCategoryDTO(teacherCareerProgression)).thenReturn(responseDTO);

        //act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(request);
        //assert
        assertEquals(responseDTO,result.getBody());
    }

    @Test
    void ShouldReturnBadRequestWhenOptionalOfTeacherCareerProgressionIsEmpty() throws Exception{
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler,careerService,categoryAssembler);
        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenReturn(command);
        when(careerService.updateTeacherCategoryInTeacherCareerProgression(command)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Unable to update teacher category", result.getBody());
    }
    @Test
    void ShouldReturnBadRequestWhenAssemblerThrowsIllegalArgumentException() throws Exception {
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, categoryAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenThrow(new IllegalArgumentException("Invalid input"));

        // Act
        ResponseEntity<?> result = teacherRestController.updateTeacherCategory(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid input", result.getBody());
    }
    @Test
    void ShouldThrowInternalServerErrorWhenServiceThrowsUnexpectedException() throws Exception{
        // Arrange
        ITeacherRegistrationService teacherService = mock(ITeacherRegistrationService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        ITeacherCareerProgressionServiceV2 careerService = mock(ITeacherCareerProgressionServiceV2.class);
        ITeacherCareerProgressionAssembler categoryAssembler = mock(ITeacherCareerProgressionAssembler.class);
        TeacherRestController teacherRestController = new TeacherRestController(teacherService, teacherAssembler, careerService, categoryAssembler);

        UpdateTeacherCategoryRequestDTO request = mock(UpdateTeacherCategoryRequestDTO.class);
        UpdateTeacherCategoryCommand command = mock(UpdateTeacherCategoryCommand.class);

        when(categoryAssembler.toUpdateTeacherCategoryCommand(request)).thenReturn(command);
        when(careerService.updateTeacherCategoryInTeacherCareerProgression(command)).thenThrow(new RuntimeException("Unexpected"));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                teacherRestController.updateTeacherCategory(request)
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Unexpected error", exception.getReason());
    }

}
