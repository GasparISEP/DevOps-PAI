package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.domain.department.Department;
import PAI.domain.department.IDepartmentFactory;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.dto.department.RegisterDepartmentRequestVOs;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentRegistrationServiceImplTest {

    @Test
    void shouldCreateAndSaveDepartment() throws Exception {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);


        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        when(acronymDouble.getAcronym()).thenReturn("DEI");
        Name nameDouble = mock(Name.class);
        when(nameDouble.getName()).thenReturn("Department of Informatics");

        RegisterDepartmentRequestVOs registerDepartmentRequestVOsDouble = mock(RegisterDepartmentRequestVOs.class);
        when(registerDepartmentRequestVOsDouble.name()).thenReturn(nameDouble);
        when(registerDepartmentRequestVOsDouble.acronym()).thenReturn(acronymDouble);

        Department departmentDouble = mock(Department.class);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentFactoryDouble.newDepartment(acronymDouble, nameDouble)).thenReturn(departmentDouble);
        when(departmentRepositoryDouble.containsOfIdentity(mock(Department.class).identity())).thenReturn(false);
        when(departmentRepositoryDouble.save(mock(Department.class))).thenReturn(departmentDouble);

        // Act
        Department result = service.createAndSaveDepartment(registerDepartmentRequestVOsDouble);

        // Assert
        assertNotNull(result);
        assertEquals(result, departmentDouble);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = null;
        Name nameDouble = mock(Name.class);

        RegisterDepartmentRequestVOs registerDepartmentRequestVOsDouble = mock(RegisterDepartmentRequestVOs.class);
        when(registerDepartmentRequestVOsDouble.name()).thenReturn(nameDouble);
        when(registerDepartmentRequestVOsDouble.acronym()).thenReturn(acronymDouble);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.createAndSaveDepartment(registerDepartmentRequestVOsDouble));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = null;

        RegisterDepartmentRequestVOs registerDepartmentRequestVOsDouble = mock(RegisterDepartmentRequestVOs.class);
        when(registerDepartmentRequestVOsDouble.name()).thenReturn(nameDouble);
        when(registerDepartmentRequestVOsDouble.acronym()).thenReturn(acronymDouble);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.createAndSaveDepartment(registerDepartmentRequestVOsDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentFactoryIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = null;
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentRepositoryIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = null;


        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() throws Exception {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);

        RegisterDepartmentRequestVOs registerDepartmentRequestVOsDouble = mock(RegisterDepartmentRequestVOs.class);
        when(registerDepartmentRequestVOsDouble.name()).thenReturn(nameDouble);
        when(registerDepartmentRequestVOsDouble.acronym()).thenReturn(acronymDouble);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentFactoryDouble.newDepartment(acronymDouble, nameDouble)).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> service.createAndSaveDepartment(registerDepartmentRequestVOsDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentAlreadyExists() throws Exception {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);

        RegisterDepartmentRequestVOs registerDepartmentRequestVOsDouble = mock(RegisterDepartmentRequestVOs.class);
        when(registerDepartmentRequestVOsDouble.name()).thenReturn(nameDouble);
        when(registerDepartmentRequestVOsDouble.acronym()).thenReturn(acronymDouble);

        Department departmentDouble = mock(Department.class);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentFactoryDouble.newDepartment(acronymDouble, nameDouble)).thenReturn(departmentDouble);
        when(departmentRepositoryDouble.containsOfIdentity(mock(Department.class).identity())).thenReturn(true);

        // Act & Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.createAndSaveDepartment(registerDepartmentRequestVOsDouble));
    }

    @Test
    void shouldReturnEmptyListWhenNoDepartmentsExist() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentRepositoryDouble.findAll()).thenReturn(List.of());

        // Act
        Iterable<Department> result = service.getAllDepartments();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldReturnListOfDepartments() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);
        Department department3 = mock(Department.class);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentRepositoryDouble.findAll()).thenReturn(List.of(department1, department2, department3));

        // Act
        Iterable<Department> result = service.getAllDepartments();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        List<Department> listResult = new ArrayList<>();
        result.forEach(listResult::add);
        assertEquals(3, listResult.size());
        assertTrue(listResult.contains(department1));
        assertTrue(listResult.contains(department2));
        assertTrue(listResult.contains(department3));
    }

    @Test
    void shouldReturnEmptyOptionalWhenDepartmentNotFound() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        when(acronymDouble.getAcronym()).thenReturn("DEI");

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentRepositoryDouble.findDepartmentByID(mock(Department.class).identity())).thenReturn(java.util.Optional.empty());

        // Act
        java.util.Optional<Department> result = service.getDepartmentById(mock(Department.class).identity());

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnDepartmentWhenFound() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        when(acronymDouble.getAcronym()).thenReturn("DEI");
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        when(departmentDouble.identity()).thenReturn(departmentIDDouble);
        when(departmentIDDouble.getAcronym()).thenReturn(acronymDouble);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentRepositoryDouble.findDepartmentByID(departmentIDDouble)).thenReturn(Optional.of(departmentDouble));

        // Act
        Optional<Department> result = service.getDepartmentById(departmentIDDouble);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(departmentDouble, result.get());
    }
}