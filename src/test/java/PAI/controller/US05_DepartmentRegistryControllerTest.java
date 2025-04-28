package PAI.controller;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.factory.DepartmentFactoryImpl;
import PAI.factory.DepartmentListFactoryImpl;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.service.department.DepartmentServiceImpl;
import PAI.service.department.IDepartmentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US05_DepartmentRegistryControllerTest {

    //testing the constructor
    @Test
    void shouldCreateControllerWhenArgumentIsValid() {
        // arrange
        IDepartmentService departmentServiceDouble = mock(IDepartmentService.class);
        // Act
        US05_DepartmentRegistryController controller =
                new US05_DepartmentRegistryController(departmentServiceDouble);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenArgumentIsNull() {
        // arrange
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US05_DepartmentRegistryController(null)
        );
        assertEquals("Department Service cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenDepartmentIsRegistered() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        IDepartmentService departmentServiceDouble = mock(IDepartmentService.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentServiceDouble);
        when(departmentServiceDouble.registerDepartment(acronym,name)).thenReturn(true);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull() {
        //arrange
        Name name = mock(Name.class);
        IDepartmentService departmentServiceDouble = mock(IDepartmentService.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentServiceDouble);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(null, name);
        });
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        IDepartmentService departmentServiceDouble = mock(IDepartmentService.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentServiceDouble);
        //act
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> controller.registerDepartment(acronym, null)
        );
        assertTrue(exception.getMessage().contains("Acronym or name cannot be null."));
    }


    //Integration tests
    @Test
    void shouldReturnTrueWhen_DepartmentIsRegistered() throws Exception {
        //arrange
        DepartmentAcronym acronym = new DepartmentAcronym("SED");
        Name name = new Name ("Software Engineer Department");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentFactory, iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIs_Null() {
        //arrange
        Name name = new Name ("Software Engineer Department");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentFactory, iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(null, name);
        });

        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIs_Null() {
        //arrange
        DepartmentAcronym acronym = new DepartmentAcronym("SED");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentFactory, iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, null);
        });
        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }
}