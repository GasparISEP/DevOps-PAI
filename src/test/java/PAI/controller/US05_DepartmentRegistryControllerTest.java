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
        when(departmentServiceDouble.registerDepartment(acronym, name)).thenReturn(true);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDepartmentNotRegistered() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        IDepartmentService departmentServiceDouble = mock(IDepartmentService.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentServiceDouble);
        when(departmentServiceDouble.registerDepartment(acronym, name)).thenReturn(false);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertFalse(result);
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

//    Integration tests
    @Test
    void shouldReturnTrueWhenDepartmentIsRegistered_IntegrationTest() throws Exception {
        //arrange
        DepartmentAcronym acronym = new DepartmentAcronym("SED");
        Name name = new Name("Software Engineer Department");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull_IntegrationTest() {
        //arrange
        Name name = new Name("Software Engineer Department");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentListFactory);

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
    void shouldThrowExceptionWhenNameIsNull_IntegrationTest() {
        //arrange
        DepartmentAcronym acronym = new DepartmentAcronym("SED");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, null);
        });
        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnFalseWhenTryingToRegisterDepartmentAlreadyRegistered_IntegrationTest() throws Exception {
        //arrange
        DepartmentAcronym acronym = new DepartmentAcronym("SED");
        Name name = new Name("Software Engineer Department");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);

        controller.registerDepartment(acronym,name);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenTryingToRegisterDifferentDepartments_IntegrationTest() throws Exception {
        //arrange
        DepartmentAcronym acronym = new DepartmentAcronym("SED");
        Name name = new Name("Software Engineer Department");

        DepartmentAcronym acronym1= new DepartmentAcronym("MED");
        Name name1= new Name("Mechanical Engineer Department");

        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentListFactory);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(iDepartmentFactory, departmentRepositoryImpl);

        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentService);

        controller.registerDepartment(acronym1,name1);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }
}
