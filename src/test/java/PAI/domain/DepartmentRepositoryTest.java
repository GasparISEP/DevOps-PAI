package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentRepositoryTest {


    //Verifying the creation of a new valid department
    @Test
    void shouldCreateValidDepartment() throws Exception {
        // Arrange
        DepartmentRepository repository = new DepartmentRepository();

        // Act
        Department department = repository.createDepartment("CSE", "Computer Science");

        // Assert
        assertNotNull(department);
    }

    // Testing when the department is null
    @Test
    public void testRegisterNullDepartment() {
        //arrange
        DepartmentRepository repository = new DepartmentRepository();
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> repository.registerDepartment(null));
    }

    //Testing when the department has an existing acronym
    @Test
    public void testRegisterDuplicateAcronym() throws Exception {
        //arrange
        DepartmentRepository repository = new DepartmentRepository();
        Department department1 = new Department( "CSE","Computer Science");
        repository.registerDepartment(department1);
        //act
        Department duplicateAcronymDepartment = new Department( "CSE","Physics");
        //assert
        assertThrows(IllegalArgumentException.class, () -> repository.registerDepartment(duplicateAcronymDepartment));
    }

    //Testing when the department has an existing name
    @Test
    public void testRegisterDuplicateName() throws Exception {
        //arrange
        DepartmentRepository repository = new DepartmentRepository();
        Department department1 = new Department( "CSE","Computer Science");
        repository.registerDepartment(department1);
        //act
        Department duplicateNameDepartment = new Department( "CSS","Computer Science");
        //assert
        assertThrows(IllegalArgumentException.class, () -> repository.registerDepartment(duplicateNameDepartment));
    }
}