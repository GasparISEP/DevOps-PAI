package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.department.Department;
import PAI.domain.department.IDepartmentFactory;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

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

        Department departmentDouble = mock(Department.class);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentFactoryDouble.newDepartment(acronymDouble, nameDouble)).thenReturn(departmentDouble);
        when(departmentRepositoryDouble.containsOfIdentity(mock(Department.class).identity())).thenReturn(false);
        when(departmentRepositoryDouble.save(mock(Department.class))).thenReturn(departmentDouble);

        // Act
        Department result = service.createAndSaveDepartment(acronymDouble, nameDouble);

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

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.createAndSaveDepartment(acronymDouble, nameDouble));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = null;

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.createAndSaveDepartment(acronymDouble, nameDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentFactoryIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = null;
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentRepositoryIsNull() {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = null;

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);

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

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentFactoryDouble.newDepartment(acronymDouble, nameDouble)).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> service.createAndSaveDepartment(acronymDouble, nameDouble));
    }

    @Test
    void shouldThrowExceptionWhenDepartmentAlreadyExists() throws Exception {
        // Arrange
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);

        Department departmentDouble = mock(Department.class);

        IDepartmentRegistrationService service = new DepartmentRegistrationServiceImpl(departmentFactoryDouble, departmentRepositoryDouble);

        when(departmentFactoryDouble.newDepartment(acronymDouble, nameDouble)).thenReturn(departmentDouble);
        when(departmentRepositoryDouble.containsOfIdentity(mock(Department.class).identity())).thenReturn(true);

        // Act & Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.createAndSaveDepartment(acronymDouble, nameDouble));
    }

}