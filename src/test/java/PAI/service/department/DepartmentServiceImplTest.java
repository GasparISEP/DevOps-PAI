package PAI.service.department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.repository.IDepartmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentServiceImplTest {

    //testing constructor
    @Test
    void shouldReturnValidDepartmentServiceWhenValidAttributesAreProvided(){
        //arrange
        IDepartmentFactory departmentFactory= mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepo= mock(IDepartmentRepository.class);
        //act
        DepartmentServiceImpl departmentService= new DepartmentServiceImpl(departmentFactory,departmentRepo);
        //assert
        assertNotNull(departmentService);
    }

    @Test
    void shouldReturnExceptionWhenDepartmentFactoryIsNull(){
        //arrange
        IDepartmentRepository departmentRepo= mock(IDepartmentRepository.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentServiceImpl(null, departmentRepo);
        });

        assertEquals("DepartmentFactory cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionWhenDepartmentRepoIsNull(){
        //arrange
        IDepartmentFactory departmentFactory= mock(IDepartmentFactory.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentServiceImpl(departmentFactory, null);
        });
        assertEquals("DepartmentRepo cannot be null", exception.getMessage());
    }

    //testing registerDepartment method
    @Test
    void shouldReturnTrueWhenDepartmentDoesNotExist() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepoDouble = mock(IDepartmentRepository.class);
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryDouble, departmentRepoDouble);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Department department = mock(Department.class);

        when(departmentRepoDouble.containsOfIdentity(departmentID)).thenReturn(false);
        when(departmentFactoryDouble.newDepartment(acronym, name)).thenReturn(department);

        // Act
        boolean result = departmentService.registerDepartment(acronym, name);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDepartmentAlreadyExists() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepoDouble = mock(IDepartmentRepository.class);
        IDepartmentFactory departmentFactoryDouble = mock(IDepartmentFactory.class);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentFactoryDouble, departmentRepoDouble);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);

        when(departmentRepoDouble.containsOfIdentity(any(DepartmentID.class))).thenReturn(true);

        // Act
        boolean result = departmentService.registerDepartment(acronym, name);

        // Assert
        assertFalse(result);
    }
}