package PAI.controller;
import PAI.factory.DepartmentFactoryImpl;
import PAI.factory.DepartmentListFactoryImpl;
import PAI.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US05_DepartmentRegistryControllerTest {

    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoryIsValid(){
        // arrange
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);

        // Act & assert
        US05_DepartmentRegistryController controller =
                new US05_DepartmentRegistryController (departmentRepositoryDouble);
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
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepositoryDouble);
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
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepositoryDouble);
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
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepositoryDouble);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, name);
        });

        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenDepartmentIs_Registered() throws Exception{
        //arrange
        String acronym= "DEI";
        String name= "Departamento Engenharia Informática";
        DepartmentFactoryImpl iDepartmentFactory= new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory= new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(iDepartmentFactory, iDepartmentListFactory);
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepository);
        //act
        boolean result=  controller.registerDepartment(acronym,name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIs_Null(){
        //arrange
        String name= "Departamento Engenharia Informática";
        DepartmentFactoryImpl iDepartmentFactory= new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory= new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(iDepartmentFactory, iDepartmentListFactory);
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(null, name);
        });

        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIs_Null(){
        //arrange
        String acronym="DEI";
        DepartmentFactoryImpl iDepartmentFactory= new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory= new DepartmentListFactoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepository(iDepartmentFactory, iDepartmentListFactory);
        US05_DepartmentRegistryController controller= new US05_DepartmentRegistryController(departmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, null);
        });

        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }
}