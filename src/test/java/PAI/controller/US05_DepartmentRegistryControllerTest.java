package PAI.controller;
import PAI.domain.DepartmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class US05_DepartmentRegistryControllerTest {

    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoryIsValid(){
        // arrange
        DepartmentRepository departmentRepository = new DepartmentRepository();

        // Act
        US05_DepartmentRegistryController controller =
                new US05_DepartmentRegistryController (departmentRepository);
        //Assert
        assertNotNull(controller);
    }

    //invalid constructor
    @Test
    void shouldThrowExceptionWhenRepositoryIsNull(){
        // arrange
        DepartmentRepository departmentRepository = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US05_DepartmentRegistryController (departmentRepository)
        );
        //Assert
        assertEquals("Department Repository cannot be null.",exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenDepartmentIsRegistered() throws Exception{
        //arrange
        String acronym= "DEI";
        String name= "Departamento Engenharia Informática";
        DepartmentRepository departmentRepository = new DepartmentRepository();
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepository);
        //act
        boolean result=  controller.registerDepartment(acronym,name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull(){
        //arrange
        String acronym=null;
        String name= "Departamento Engenharia Informática";
        DepartmentRepository departmentRepository = new DepartmentRepository();
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, name);
        });

        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        //arrange
        String acronym="DEI";
        String name= null;
        DepartmentRepository departmentRepository = new DepartmentRepository();
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, name);
        });

        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }
}