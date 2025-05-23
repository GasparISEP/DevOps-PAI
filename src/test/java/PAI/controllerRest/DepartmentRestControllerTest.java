package PAI.controllerRest;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.assembler.department.IDepartmentAssembler;
import PAI.domain.department.Department;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.RegisterDepartmentCommand;
import PAI.dto.department.RegisterDepartmentRequest;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.department.IDepartmentRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentRestControllerTest {

    @Test
    void shouldCreateDepartmentRestController() {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationServiceDouble = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);
        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationServiceDouble, departmentAssemblerDouble);

        // Act + Assert
        assertNotNull(departmentRestController);
    }

        @Mock
        private IDepartmentRegistrationService registrationService;

        @Mock
        private IDepartmentAssembler departmentAssembler;

        @InjectMocks
        private DepartmentRestController controller;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void shouldReturnCreatedDTOIfSuccessful() throws Exception {
            // Arrange
            RegisterDepartmentRequest request = mock (RegisterDepartmentRequest.class);
            when(request.name()).thenReturn("Software Engineering Department");
            when(request.acronym()).thenReturn("DEI");

            DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
            when(departmentAcronym.getAcronym()).thenReturn("DEI");

            Name name = mock(Name.class);
            when(name.getName()).thenReturn("Software Engineering Department");

            RegisterDepartmentCommand command = mock (RegisterDepartmentCommand.class);
            when(command.name()).thenReturn(name);
            when(command.acronym()).thenReturn(departmentAcronym);

            Department department = mock(Department.class);
            DepartmentDTO dto = mock(DepartmentDTO.class);

            when(dto.name()).thenReturn("Software Engineering Department");
            when(dto.acronym()).thenReturn("DEI");

            when(departmentAssembler.toRegisterDepartmentCommand(request)).thenReturn(command);
            when(registrationService.createAndSaveDepartment(command)).thenReturn(department);
            when(departmentAssembler.toDTO(department)).thenReturn(dto);

            // Act
            ResponseEntity<?> response = controller.registerDepartment(request);

            // Assert
            assertEquals(201, response.getStatusCodeValue());
            assertEquals(dto, response.getBody());
        }

        @Test
        void shouldReturnsConflictWhenBusinessRuleViolation() throws Exception {
            // Arrange
            RegisterDepartmentRequest request = mock(RegisterDepartmentRequest.class);
            when(request.name()).thenReturn("Software Engineering Department");
            when(request.acronym()).thenReturn("DEI");
            RegisterDepartmentCommand command = mock (RegisterDepartmentCommand.class);
            when(command.name()).thenReturn(mock(Name.class));
            when(command.acronym()).thenReturn(mock(DepartmentAcronym.class));

            when(departmentAssembler.toRegisterDepartmentCommand(request)).thenReturn(command);
            when(registrationService.createAndSaveDepartment(command)).thenReturn(mock(Department.class));

            when(departmentAssembler.toRegisterDepartmentCommand(request)).thenReturn(command);
            when(registrationService.createAndSaveDepartment(command))
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

            when(departmentAssembler.toRegisterDepartmentCommand(request))
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

            when(departmentAssembler.toRegisterDepartmentCommand(request))
                    .thenThrow(new RuntimeException("Unexpected error occurred"));

            // Act
            ResponseEntity<?> response = controller.registerDepartment(request);

            // Assert
            assertEquals(500, response.getStatusCodeValue());
            assertEquals("Unexpected error occurred", response.getBody());
        }

        @Test
        void shouldReturnListOfDepartmentDTO () {
            // Arrange
            IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
            IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);

            DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler);

            Department department1 = mock(Department.class);
            Department department2 = mock(Department.class);
            Department department3 = mock(Department.class);
            DepartmentDTO departmentDTO1 = mock(DepartmentDTO.class);
            DepartmentDTO departmentDTO2 = mock(DepartmentDTO.class);
            DepartmentDTO departmentDTO3 = mock(DepartmentDTO.class);

            List<Department> departments = List.of(department1, department2, department3);
            List<DepartmentDTO> departmentDTOs = List.of(departmentDTO1, departmentDTO2, departmentDTO3);

            when(departmentRegistrationService.getAllDepartments()).thenReturn(departments);
            when(departmentAssembler.toDTOs(departments)).thenReturn(departmentDTOs);

            // Act
            ResponseEntity<?> response = departmentRestController.getAllDepartments();

            // Assert
            assertEquals(200, response.getStatusCodeValue());
            assertTrue(((Iterable<?>) response.getBody()).iterator().hasNext());
            assertTrue(((Collection<?>) response.getBody()).contains(departmentDTO1));
        }

    @Test
    void shouldReturnEmptyListOfDepartmentDTOIfThereAreNoDepartments () {
        // Arrange
        IDepartmentRegistrationService departmentRegistrationService = mock(IDepartmentRegistrationService.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);

        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler);


        List<Department> departments = List.of();
        List<DepartmentDTO> departmentDTOs = List.of();

        when(departmentRegistrationService.getAllDepartments()).thenReturn(departments);
        when(departmentAssembler.toDTOs(departments)).thenReturn(departmentDTOs);

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

        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler);

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
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);

        DepartmentRestController departmentRestController = new DepartmentRestController(departmentRegistrationService, departmentAssembler);

        when(departmentRegistrationService.getAllDepartments()).thenThrow(new RuntimeException("Database is down"));

        // Act
        ResponseEntity<?> response = departmentRestController.getAllDepartments();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }
}
