package PAI.repository;
import PAI.domain.Department;
import PAI.domain.Teacher;
import PAI.factory.DepartmentFactory;
import PAI.factory.DepartmentListFactory;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentRepositoryTest {

    //Verifying the creation of a new valid department
    @Test
    void shouldRegisterValidDepartment() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

        // Act
        boolean result = repository.registerDepartment("CSE", "Computer Science");

        // Assert
        assertTrue(result, "The department should be successfully registered.");
    }

    //Verifying the creation of two new valid departments
    @Test
    void shouldRegisterMultipleDifferentDepartments() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        when(factoryDouble.newDepartment("ECE", "Electronics")).thenReturn(department2Double);

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
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);
        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        repository.registerDepartment("CSE", "Computer Science");

        //act
        boolean result = repository.registerDepartment("CSE", "Computer Science");

        //
        assertFalse(result);
    }

    //Testing that the list should not be retrieved if empty
    @Test
    void shouldReturnExceptionIfDepartmentListIsEmpty() throws IllegalStateException {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

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
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository departmentRepository = new DepartmentRepository(factoryDouble,listFactoryDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        when(factoryDouble.newDepartment("ECE", "Electronics")).thenReturn(department2Double);

        departmentRepository.registerDepartment("CSE", "Computer Science");
        departmentRepository.registerDepartment("ECE", "Electronics");

        // Act
        Set<Department> result = departmentRepository.getDepartments();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

        Department department1Double= mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);

        repository.registerDepartment("CSE", "Computer Science");

        // Act
        boolean result= repository.departmentExists(department1Double);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentDoesNotExistInDepartmentRepository() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

        Department departmentDouble = mock(Department.class);
        Department nonExistingDepartmentDouble = mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(departmentDouble);
        repository.registerDepartment("CSE", "Computer Science");

        //act
        boolean result = repository.departmentExists(nonExistingDepartmentDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentIsNull() {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

        // Act
        boolean result= repository.departmentExists(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector(){
        //arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

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
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);

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
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentListFactory listFactoryDouble= mock(DepartmentListFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble,listFactoryDouble);
        Department dpt1Double= mock(Department.class);

        //act
        boolean result = repository.updateOfDepartmentDirector(dpt1Double, null);

        //assert
        assertFalse(result);
    }
}
