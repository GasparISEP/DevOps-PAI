package PAI.controllerRest;

import PAI.VOs.TeacherAcronym;
import PAI.dto.department.DepartmentWithDirectorDTO;
import PAI.dto.department.DepartmentWithDirectorRequest;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.dto.department.*;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.assembler.department.IDepartmentAssembler;
import PAI.assembler.department.IDepartmentHateoasAssembler;
import PAI.domain.department.Department;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.department.IDepartmentRegistrationService;
import PAI.service.department.IUpdateDepartmentDirectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentRestControllerTest {

    @Test
    void shouldCreateDepartmentRestController() {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationServiceDouble = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorServiceDouble = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssemblerDouble = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationServiceDouble, departmentAssemblerDouble, updateDepartmentDirectorServiceDouble, departmentHateoasAssemblerDouble);


        // Act + Assert
        assertNotNull(departmentRestController);
    }

    @Mock
    private IDepartmentRegistrationService registrationService;

    @Mock
    private IDepartmentAssembler departmentAssembler;

    @Mock
    private IDepartmentHateoasAssembler departmentHateoasAssembler;

    @InjectMocks
    private DepartmentRestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCreatedHateoasDTOIfSuccessful() throws Exception {
        // Arrange
        RegisterDepartmentRequest request = mock(RegisterDepartmentRequest.class);
        when(request.name()).thenReturn("Software Engineering Department");
        when(request.acronym()).thenReturn("DEI");

        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        when(departmentAcronym.getAcronym()).thenReturn("DEI");

        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");

        RegisterDepartmentRequestVOs requestVOs = mock(RegisterDepartmentRequestVOs.class);
        when(requestVOs.name()).thenReturn(name);
        when(requestVOs.acronym()).thenReturn(departmentAcronym);

        Department department = mock(Department.class);
        DepartmentDTO dto = mock(DepartmentDTO.class);
        EntityModel<DepartmentDTO> hateoasModel = mock(EntityModel.class);
        when(hateoasModel.getContent()).thenReturn(dto);


        when(dto.name()).thenReturn("Software Engineering Department");
        when(dto.acronym()).thenReturn("DEI");

        when(departmentAssembler.toRegisterDepartmentRequestVOs(request)).thenReturn(requestVOs);
        when(registrationService.createAndSaveDepartment(requestVOs)).thenReturn(department);
        when(departmentAssembler.toDTO(department)).thenReturn(dto);
        when(departmentHateoasAssembler.toModel(dto)).thenReturn(hateoasModel);

        // Act
        ResponseEntity<?> response = controller.registerDepartment(request);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(hateoasModel, response.getBody());
    }

    @Test
    void shouldReturnsConflictWhenBusinessRuleViolation() throws Exception {
        // Arrange
        RegisterDepartmentRequest request = mock(RegisterDepartmentRequest.class);
        when(request.name()).thenReturn("Software Engineering Department");
        when(request.acronym()).thenReturn("DEI");

        RegisterDepartmentRequestVOs requestVOs = mock(RegisterDepartmentRequestVOs.class);
        when(requestVOs.name()).thenReturn(mock(Name.class));
        when(requestVOs.acronym()).thenReturn(mock(DepartmentAcronym.class));

        when(departmentAssembler.toRegisterDepartmentRequestVOs(request)).thenReturn(requestVOs);
        when(registrationService.createAndSaveDepartment(requestVOs)).thenReturn(mock(Department.class));

        when(departmentAssembler.toRegisterDepartmentRequestVOs(request)).thenReturn(requestVOs);
        when(registrationService.createAndSaveDepartment(requestVOs))
                .thenThrow(new BusinessRuleViolationException("Department already exists"));

        // Act
        ResponseEntity<?> response = controller.registerDepartment(request);

        // Assert
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Department already exists", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgument() {
        // Arrange
        RegisterDepartmentRequest request = mock(RegisterDepartmentRequest.class);
        when(request.name()).thenReturn("Software Engineering Department");
        when(request.acronym()).thenReturn("dei");

        when(departmentAssembler.toRegisterDepartmentRequestVOs(request))
                .thenThrow(new IllegalArgumentException("Invalid Acronym"));

        // Act
        ResponseEntity<?> response = controller.registerDepartment(request);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid Acronym", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedException() {
        // Arrange
        RegisterDepartmentRequest request = mock(RegisterDepartmentRequest.class);
        when(request.name()).thenReturn("Software Engineering Department");
        when(request.acronym()).thenReturn("DEI");

        when(departmentAssembler.toRegisterDepartmentRequestVOs(request))
                .thenThrow(new RuntimeException("Unexpected error occurred"));

        // Act
        ResponseEntity<?> response = controller.registerDepartment(request);

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnListOfDepartmentDWDDTO() {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorServiceDouble = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssemblerDouble = mock(IDepartmentHateoasAssembler.class);
        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorServiceDouble, departmentHateoasAssemblerDouble);

        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);
        Department department3 = mock(Department.class);
        DepartmentWithDirectorDTO departmentDTO1 = mock(DepartmentWithDirectorDTO.class);
        DepartmentWithDirectorDTO departmentDTO2 = mock(DepartmentWithDirectorDTO.class);
        DepartmentWithDirectorDTO departmentDTO3 = mock(DepartmentWithDirectorDTO.class);

        List<Department> departments = List.of(department1, department2, department3);
        List<DepartmentWithDirectorDTO> departmentDTOs = List.of(departmentDTO1, departmentDTO2, departmentDTO3);

        when(departmentRegistrationService.getAllDepartments()).thenReturn(departments);
        when(departmentAssembler.toDWDDTOs(departments)).thenReturn(departmentDTOs);

        // Act
        ResponseEntity<?> response = departmentRestController.getAllDepartments();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(((Iterable<?>) response.getBody()).iterator().hasNext());
        assertTrue(((Collection<?>) response.getBody()).contains(departmentDTO1));
    }

    @Test
    void shouldReturnEmptyListOfDepartmentDWDDTOIfThereAreNoDepartments() {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorServiceDouble = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssemblerDouble = mock(IDepartmentHateoasAssembler.class);
        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorServiceDouble, departmentHateoasAssemblerDouble);


        List<Department> departments = List.of();
        List<DepartmentWithDirectorDTO> departmentDTOs = List.of();

        when(departmentRegistrationService.getAllDepartments()).thenReturn(departments);
        when(departmentAssembler.toDWDDTOs(departments)).thenReturn(departmentDTOs);

        // Act
        ResponseEntity<?> response = departmentRestController.getAllDepartments();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(((Iterable<?>) response.getBody()).iterator().hasNext());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrown() {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorServiceDouble = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorServiceDouble, departmentHateoasAssembler);

        String errorMessage = "Invalid input data";

        when(departmentRegistrationService.getAllDepartments()).thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = departmentRestController.getAllDepartments();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionIsThrown() {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorServiceDouble = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorServiceDouble, departmentHateoasAssembler);

        when(departmentRegistrationService.getAllDepartments()).thenThrow(new RuntimeException("Database is down"));

        // Act
        ResponseEntity<?> response = departmentRestController.getAllDepartments();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturn200WhenDirectorUpdatedSuccessfully() throws Exception {
        // Arrange
        String departmentIDStr = "DEI";
        String teacherIDStr = "MAF";

        // Arrange
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorService = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class); // assumido como necessário

        DepartmentRestController departmentRestController =
                new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorService, departmentHateoasAssembler);

        // Arrange Request
        DepartmentWithDirectorRequest request = mock(DepartmentWithDirectorRequest.class);
        when(request.teacherID()).thenReturn(teacherIDStr);

        // Arrange Domain objects
        DepartmentAcronym departmentAcronym = new DepartmentAcronym(departmentIDStr);
        TeacherAcronym teacherAcronym = new TeacherAcronym(teacherIDStr);
        TeacherID teacherID = new TeacherID(teacherAcronym);
        DepartmentID departmentID = new DepartmentID(departmentAcronym);

        // Arrange Command
        DepartmentWithDirectorCommand command = mock(DepartmentWithDirectorCommand.class);
        when(departmentAssembler.fromRequestToCommand(departmentIDStr, request)).thenReturn(command);
        when(command.department()).thenReturn(departmentID);
        when(command.director()).thenReturn(teacherID);

        // Arrange Expected DTO
        DepartmentWithDirectorDTO expectedDTO = new DepartmentWithDirectorDTO(
                departmentIDStr,
                "Department of Engineering and Informatics",
                departmentIDStr,
                teacherIDStr
        );

        when(updateDepartmentDirectorService.updateDirector(departmentID, teacherID)).thenReturn(expectedDTO);

        // Act
        ResponseEntity<?> response = departmentRestController.updateDepartmentDirector(departmentIDStr, request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());
    }

    @Test
    void shouldReturn400WhenIllegalArgumentExceptionIsThrown() throws Exception {
        // Arrange
        String departmentIDStr = "DEI";
        String teacherIDStr = "MAF";

        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorService = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController controller =
                new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorService, departmentHateoasAssembler);

        // Arrange request
        DepartmentWithDirectorRequest request = mock(DepartmentWithDirectorRequest.class);
        when(request.teacherID()).thenReturn(teacherIDStr);

        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(departmentIDStr));
        TeacherID teacherID = new TeacherID(new TeacherAcronym(teacherIDStr));

        DepartmentWithDirectorCommand command = mock(DepartmentWithDirectorCommand.class);
        when(departmentAssembler.fromRequestToCommand(departmentIDStr, request)).thenReturn(command);
        when(command.department()).thenReturn(departmentID);
        when(command.director()).thenReturn(teacherID);

        // Simula o serviço a lançar a exceção
        when(updateDepartmentDirectorService.updateDirector(departmentID, teacherID))
                .thenThrow(new IllegalArgumentException("Invalid department ID"));

        // Act
        ResponseEntity<?> response = controller.updateDepartmentDirector(departmentIDStr, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid department ID", response.getBody());
    }


    @Test
    void shouldReturnDepartmentById() {
        // Arrange
        String departmentId = "DEI";
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(departmentId));
        Department department = mock(Department.class);
        DepartmentDTO departmentDTO = mock(DepartmentDTO.class);

        when(departmentAssembler.fromStringToDepartmentID(departmentId)).thenReturn(departmentID);
        when(registrationService.getDepartmentById(departmentID)).thenReturn(java.util.Optional.of(department));
        when(departmentAssembler.toDTO(department)).thenReturn(departmentDTO);

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(departmentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDTO, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenDepartmentDoesNotExist() {
        // Arrange
        String departmentId = "DEI";
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(departmentId));

        when(departmentAssembler.fromStringToDepartmentID(departmentId)).thenReturn(departmentID);
        when(registrationService.getDepartmentById(departmentID)).thenReturn(java.util.Optional.empty());

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(departmentId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Department not found", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrownForGetById() {
        // Arrange
        String invalidDepartmentId = "invalid-id";

        when(departmentAssembler.fromStringToDepartmentID(invalidDepartmentId))
                .thenThrow(new IllegalArgumentException("Invalid department ID"));

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(invalidDepartmentId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid department ID", response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionIsThrownForGetById() {
        // Arrange
        String departmentId = "DEI";
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(departmentId));

        when(departmentAssembler.fromStringToDepartmentID(departmentId)).thenReturn(departmentID);
        when(registrationService.getDepartmentById(departmentID)).thenThrow(new RuntimeException("Database is down"));

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(departmentId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrownForUpdateDirector() {
        // Arrange
        String departmentIDStr = "DEI";
        String teacherIDStr = "MAF";

        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorService = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController controller =
                new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorService, departmentHateoasAssembler);
        // Arrange request
        DepartmentWithDirectorRequest request = mock(DepartmentWithDirectorRequest.class);
        when(request.teacherID()).thenReturn(teacherIDStr);

        // Arrange command
        when(departmentAssembler.fromRequestToCommand(departmentIDStr, request))
                .thenThrow(new IllegalArgumentException("Invalid department ID"));

        // Act
        ResponseEntity<?> response = controller.updateDepartmentDirector(departmentIDStr, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid department ID", response.getBody());
    }

    @Test
    void shouldReturn409WhenBusinessRuleViolationExceptionIsThrownUpdateDirector() throws Exception {
        // Arrange
        String departmentIDStr = "DEI";
        String teacherIDStr = "MAF";

        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorService = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController departmentRestController =
                new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorService, departmentHateoasAssembler);

        DepartmentWithDirectorRequest request = mock(DepartmentWithDirectorRequest.class);
        when(request.teacherID()).thenReturn(teacherIDStr);

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(departmentIDStr);
        TeacherAcronym teacherAcronym = new TeacherAcronym(teacherIDStr);
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherID teacherID = new TeacherID(teacherAcronym);

        DepartmentWithDirectorCommand command = mock(DepartmentWithDirectorCommand.class);
        when(departmentAssembler.fromRequestToCommand(departmentIDStr, request)).thenReturn(command);
        when(command.department()).thenReturn(departmentID);
        when(command.director()).thenReturn(teacherID);

        when(updateDepartmentDirectorService.updateDirector(departmentID, teacherID))
                .thenThrow(new BusinessRuleViolationException("Director already assigned"));

        // Act
        ResponseEntity<?> response = departmentRestController.updateDepartmentDirector(departmentIDStr, request);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Director already assigned", response.getBody());
    }

    @Test
    void shouldReturn500WhenUnexpectedExceptionIsThrownUpdateDirector() throws Exception {

        // Arrange
        String departmentIDStr = "DEI";
        String teacherIDStr = "MAF";

        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        IUpdateDepartmentDirectorService updateDepartmentDirectorService = mock(IUpdateDepartmentDirectorService.class);
        IDepartmentHateoasAssembler departmentHateoasAssembler = mock(IDepartmentHateoasAssembler.class);

        DepartmentRestController departmentRestController =
                new DepartmentRestController(departmentRegistrationService, departmentAssembler, updateDepartmentDirectorService, departmentHateoasAssembler);

        DepartmentWithDirectorRequest request = mock(DepartmentWithDirectorRequest.class);
        when(request.teacherID()).thenReturn(teacherIDStr);

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(departmentIDStr);
        TeacherAcronym teacherAcronym = new TeacherAcronym(teacherIDStr);
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherID teacherID = new TeacherID(teacherAcronym);

        DepartmentWithDirectorCommand command = mock(DepartmentWithDirectorCommand.class);
        when(departmentAssembler.fromRequestToCommand(departmentIDStr, request)).thenReturn(command);
        when(command.department()).thenReturn(departmentID);
        when(command.director()).thenReturn(teacherID);

        when(updateDepartmentDirectorService.updateDirector(eq(departmentID), eq(teacherID)))
                .thenThrow(new RuntimeException("Unexpected database error"));

        // Act
        ResponseEntity<?> response = departmentRestController.updateDepartmentDirector(departmentIDStr, request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred.", response.getBody());
    }

    @Test
    void shouldReturnDepartmentByIdWithExpectedDTO() {
        // Arrange
        String id = "DEI";
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(id));
        Department department = mock(Department.class);

        DepartmentDTO expectedDTO = new DepartmentDTO("DEI", "Department of Informatics", "DEI");

        when(departmentAssembler.fromStringToDepartmentID(id)).thenReturn(departmentID);
        when(registrationService.getDepartmentById(departmentID)).thenReturn(Optional.of(department));
        when(departmentAssembler.toDTO(department)).thenReturn(expectedDTO);

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof DepartmentDTO);

        DepartmentDTO result = (DepartmentDTO) response.getBody();
        assertEquals("DEI", result.id());
        assertEquals("Department of Informatics", result.name());
        assertEquals("DEI", result.acronym());
    }

    @Test
    void shouldReturn404WhenDepartmentNotFound() {
        // Arrange
        String id = "DEI";
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(id));

        when(departmentAssembler.fromStringToDepartmentID(id)).thenReturn(departmentID);
        when(registrationService.getDepartmentById(departmentID)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Department not found", response.getBody());
    }

    @Test
    void shouldReturn400WhenIllegalArgumentExceptionIsThrownGetDepartmentByID() {
        // Arrange
        String invalidId = "dei";

        when(departmentAssembler.fromStringToDepartmentID(invalidId))
                .thenThrow(new IllegalArgumentException("Invalid department acronym"));

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(invalidId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid department acronym", response.getBody());
    }

    @Test
    void shouldReturn500WhenUnexpectedExceptionOccurs() {
        // Arrange
        String id = "DEI";
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym(id));

        when(departmentAssembler.fromStringToDepartmentID(id)).thenReturn(departmentID);
        when(registrationService.getDepartmentById(departmentID))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<?> response = controller.getDepartmentById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }


}