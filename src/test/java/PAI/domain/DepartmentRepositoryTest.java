package PAI.domain;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentRepositoryTest {

    //Verifying the creation of a new valid department
    @Test
    void shouldRegisterValidDepartment() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

        // Act
        boolean result = repository.registerDepartment("CSE", "Computer Science");

        // Assert
        assertTrue(result, "The department should be successfully registered.");
    }

    //Verifying the creation of two new valid departments
    @Test
    void shouldRegisterMultipleDifferentDepartments() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble= mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

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

    //Testing when the department has an existing acronym
    @Test
    void shouldThrowExceptionForDuplicateAcronym() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        when(factoryDouble.newDepartment("CSE", "Civil Engineering")).thenReturn(department2Double);

        when(department2Double.hasSameAcronym(department1Double)).thenReturn(true);

        repository.registerDepartment("CSE", "Computer Science");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.registerDepartment("CSE", "Civil Engineering");
        });

        assertEquals("Department with that acronym already exists.", exception.getMessage());
    }

     //Testing when the department has an existing name
     @Test
     void shouldThrowExceptionForDuplicateName() throws Exception{
         // Arrange
         DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
         DepartmentRepository repository = new DepartmentRepository(factoryDouble);

         Department department1Double = mock(Department.class);
         Department department2Double = mock(Department.class);

         when(factoryDouble.newDepartment("CSB", "Civil Engineering")).thenReturn(department1Double);
         when(factoryDouble.newDepartment("CSE", "Civil Engineering")).thenReturn(department2Double);

         when(department2Double.hasSameName(department1Double)).thenReturn(true);

         repository.registerDepartment("CSB", "Civil Engineering");

         // Act & Assert
         Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             repository.registerDepartment("CSE", "Civil Engineering");
         });

         assertEquals("Department with that name already exists.", exception.getMessage());
    }

    //Testing that the list should not be retrieved if empty
    @Test
    void shouldReturnExceptionIfDepartmentListIsEmpty() throws IllegalStateException {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            repository.getDepartmentList();
        });
        assertEquals("Department list is empty.", exception.getMessage());
    }

    //Testing that the retrieved list has registered objects
    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble = mock(DepartmentFactory.class);
        DepartmentRepository departmentRepository = new DepartmentRepository(factoryDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        when(factoryDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
        when(factoryDouble.newDepartment("ECE", "Electronics")).thenReturn(department2Double);

        departmentRepository.registerDepartment("CSE", "Computer Science");
        departmentRepository.registerDepartment("ECE", "Electronics");

        // Act
        List<Department> result = departmentRepository.getDepartmentList();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
        // Arrange
        DepartmentFactory factoryDouble= mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

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
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

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
        DepartmentFactory factoryDouble=  mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);

        // Act
        boolean result= repository.departmentExists(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector(){
        //arrange
        DepartmentFactory factoryDouble= mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);
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
        DepartmentFactory factoryDouble= mock(DepartmentFactory.class);
        DepartmentRepository repository = new DepartmentRepository(factoryDouble);
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
            DepartmentFactory factoryDouble= mock(DepartmentFactory.class);
            DepartmentRepository repository = new DepartmentRepository(factoryDouble);
            Department dpt1Double= mock(Department.class);

            //act
            boolean result= repository.updateOfDepartmentDirector(dpt1Double,null);

            //assert
            assertFalse(result);
    }
}
