package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentRepositoryTest {

    //Verifying the creation of a new valid department
    @Test
    void shouldRegisterValidDepartment() throws Exception {
        // Arrange
        DepartmentRepository repository = new DepartmentRepository();

        // Act
        boolean result = repository.registerDepartment("CSE", "Computer Science");

        // Assert
        assertTrue(result, "The department should be successfully registered.");
    }

    //Verifying the creation of two new valid departments
    @Test
    void shouldRegisterMultipleDifferentDepartments() throws Exception {
        // Arrange
        DepartmentRepository repository = new DepartmentRepository();

        // Act
        boolean result1 = repository.registerDepartment("CSE", "Computer Science");
        boolean result2 = repository.registerDepartment("ECE", "Electronics");

        // Assert
        assertTrue(result1, "The first department should be registered.");
        assertTrue(result2, "The second department should be registered.");
    }

    //Testing when the department has an existing acronym
    @Test
    void shouldThrowExceptionForDuplicateAcronym() {
        // Arrange
        DepartmentRepository repository = new DepartmentRepository();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerDepartment("CSE", "Computer Science");
            repository.registerDepartment("CSE", "Civil Engineering");
        });

        // Assert
        assertEquals("Department with that acronym already exists.", exception.getMessage());
    }

    //Testing when the department has an existing name
    @Test
    void shouldThrowExceptionForDuplicateName() {
        // Arrange
        DepartmentRepository repository = new DepartmentRepository();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerDepartment("CSE", "Computer Science");
            repository.registerDepartment("CIV", "Computer Science");
        });

        // Assert
        assertEquals("Department with that name already exists.", exception.getMessage());
    }

    //Testing that the list should not be retrieved if empty
    @Test
    void shouldReturnExceptionIfDepartmentListIsEmpty() throws IllegalStateException {
        // Arrange
        DepartmentRepository tcr = new DepartmentRepository();
        // Act + Assert
        assertThrows(IllegalStateException.class, () -> tcr.getDepartmentsList());
    }

    //Testing that the retrieved list has registered objects
    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        DepartmentRepository tcr = new DepartmentRepository();
        tcr.registerDepartment("CSE", "Computer Science");
        tcr.registerDepartment("CIV", "Civil Engineering");
        // Act
        List<Department> result = tcr.getDepartmentsList();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
        // Arrange
        Department department1= new Department("DEF","Departamento Engenharia Física");
        DepartmentRepository repository = new DepartmentRepository();
        repository.registerDepartment("DEF", "Departamento Engenharia Física");
        repository.registerDepartment("DEC", "Departamento de Engenharia Civil");

        // Act
        boolean resultado= repository.departmentExists(department1);

        //Assert
        assertTrue(resultado);
    }

    @Test
    void shouldReturnFalseIfDepartmentDoesNotExistInDepartmentRepository() throws Exception {
        // Arrange
        Department department1= new Department("DEQ","Departamento Engenharia Química");
        DepartmentRepository repository = new DepartmentRepository();
        repository.registerDepartment("DEF", "Departamento Engenharia Física");
        repository.registerDepartment("DEC", "Departamento Engenharia Civil");

        // Act
        boolean resultado= repository.departmentExists(department1);

        //Assert
        assertFalse(resultado);
    }

    @Test
    void shouldReturnFalseIfDepartmentIsNull() throws Exception {
        // Arrange
        Department department1= null;
        DepartmentRepository repository = new DepartmentRepository();
        repository.registerDepartment("DEF", "Departamento Engenharia Física");
        repository.registerDepartment("DEC", "Departamento Engenharia Civil");

        // Act
        boolean resultado= repository.departmentExists(department1);

        //Assert
        assertFalse(resultado);
    }
}
