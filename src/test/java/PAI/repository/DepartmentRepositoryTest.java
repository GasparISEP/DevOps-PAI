package PAI.repository;
import PAI.domain.Department;
import PAI.domain.DepartmentDoubleEqualsTrue;
import PAI.domain.Teacher;
import PAI.factory.DepartmentFactoryInterface;
import PAI.factory.DepartmentListFactoryInterface;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentRepositoryTest {

    //Verifying the creation of a new valid department
    @Test
    void shouldRegisterValidDepartment() throws Exception {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        // Act
        boolean result = repository.registerDepartment("CSE", "Computer Science");

        // Assert
        assertTrue(result, "The department should be successfully registered.");
    }

    //Verifying the creation of two new valid departments
    @Test
    void shouldRegisterMultipleDifferentDepartments() throws Exception {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        when(factoryInterfaceDouble.newDepartment("ECE", "Electronics")).thenReturn(department2Double);

        // Act
        boolean result1 = repository.registerDepartment("CSE", "Computer Science");
        boolean result2 = repository.registerDepartment("ECE", "Electronics");

        // Assert
        assertTrue(result1, "The first department should be registered.");
        assertTrue(result2, "The second department should be registered.");
    }

    // Testing an attempt to register a department that already exists
    @Test
    void shouldReturnFalseIfDepartmentAlreadyExists() throws Exception {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
        Department department1Double =  new DepartmentDoubleEqualsTrue("CSE", "Computer Science");
        Department department2Double= new DepartmentDoubleEqualsTrue("CSE", "Computer Science");

        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        repository.registerDepartment("CSE", "Computer Science");
        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(department2Double);

        //act
        boolean result = repository.registerDepartment("CSE", "Computer Science");

        //assert
        assertFalse(result);
    }

    //Testing that the list should not be retrieved if empty
    @Test
    void shouldReturnExceptionIfDepartmentListIsEmpty() throws IllegalStateException {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            repository.getDepartments();
        });
        assertEquals("Department list is empty.", exception.getMessage());
    }

    //Testing that the retrieved list has registered objects
    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        when(factoryInterfaceDouble.newDepartment("ECE", "Electronics")).thenReturn(department2Double);

        repository.registerDepartment("CSE", "Computer Science");
        repository.registerDepartment("ECE", "Electronics");

        // Act
        Set<Department> result = repository.getDepartments();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department department1Double= mock(Department.class);

        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);

        repository.registerDepartment("CSE", "Computer Science");

        // Act
        boolean result= repository.departmentExists(department1Double);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentDoesNotExistInDepartmentRepository() throws Exception {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department departmentDouble = mock(Department.class);
        Department nonExistingDepartmentDouble = mock(Department.class);

        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(departmentDouble);
        repository.registerDepartment("CSE", "Computer Science");

        //act
        boolean result = repository.departmentExists(nonExistingDepartmentDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentIsNull() {
        // Arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        // Act
        boolean result= repository.departmentExists(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector(){
        //arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department departmentDouble = mock(Department.class);
        Teacher furtherDirectorDouble = mock(Teacher.class);

        when(furtherDirectorDouble.isInDepartment(departmentDouble)).thenReturn(true);
        when(departmentDouble.changeDirector(furtherDirectorDouble)).thenReturn(true);

        //act
        boolean result= repository.updateOfDepartmentDirector(departmentDouble,furtherDirectorDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherDoesNotBelongToDepartment(){
        //arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department departmentDouble = mock(Department.class);
        Teacher furtherDirectorDouble = mock(Teacher.class);

        when(furtherDirectorDouble.isInDepartment(departmentDouble)).thenReturn(false);

        //act
        boolean result= repository.updateOfDepartmentDirector(departmentDouble,furtherDirectorDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIsNull (){
        //arrange
        DepartmentFactoryInterface factoryInterfaceDouble = mock(DepartmentFactoryInterface.class);
        DepartmentListFactoryInterface listFactoryInterfaceDouble= mock(DepartmentListFactoryInterface.class);
        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
        Department dpt1Double= mock(Department.class);

        //act
        boolean result = repository.updateOfDepartmentDirector(dpt1Double, null);

        //assert
        assertFalse(result);
    }
}
