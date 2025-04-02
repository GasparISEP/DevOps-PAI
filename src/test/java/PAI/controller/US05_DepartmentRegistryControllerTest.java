package PAI.controller;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.factory.DepartmentFactoryImpl;
import PAI.factory.DepartmentListFactoryImpl;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.repository.IDepartmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US05_DepartmentRegistryControllerTest {

    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoryIsValid() {
        // arrange
        IDepartmentRepository IdepartmentRepositoryDouble = mock(DepartmentRepositoryImpl.class);

        // Act & assert
        US05_DepartmentRegistryController controller =
                new US05_DepartmentRegistryController(IdepartmentRepositoryDouble);
    }

    //invalid constructor
    @Test
    void shouldThrowExceptionWhenRepositoryIsNull() {
        // arrange
        IDepartmentRepository IdepartmentRepositoryDouble = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US05_DepartmentRegistryController(IdepartmentRepositoryDouble)
        );
        assertEquals("Department Repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenDepartmentIsRegistered() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        IDepartmentRepository IdepartmentRepositoryDouble = mock(IDepartmentRepository.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(IdepartmentRepositoryDouble);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull() {
        //arrange
        DepartmentAcronym acronym =null;
        Name name = mock(Name.class);
        IDepartmentRepository IdepartmentRepositoryDouble = mock(IDepartmentRepository.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(IdepartmentRepositoryDouble);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, name);
        });
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = null;
        IDepartmentRepository IdepartmentRepositoryDouble = mock(IDepartmentRepository.class);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(IdepartmentRepositoryDouble);
        //act
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> controller.registerDepartment(acronym, name)
        );
        assertTrue(exception.getMessage().contains("Acronym or name cannot be null."));
    }

    @Test
    void shouldReturnTrueWhenDepartmentIs_Registered() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentFactory, iDepartmentListFactory);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentRepositoryImpl);
        //act
        boolean result = controller.registerDepartment(acronym, name);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIs_Null() {
        //arrange
        Name name = mock(Name.class);
        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentFactory, iDepartmentListFactory);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentRepositoryImpl);
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
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        DepartmentFactoryImpl iDepartmentFactory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl iDepartmentListFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl(iDepartmentFactory, iDepartmentListFactory);
        US05_DepartmentRegistryController controller = new US05_DepartmentRegistryController(departmentRepositoryImpl);
        //act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.registerDepartment(acronym, null);
        });
        //assert
        assertEquals("Acronym or name cannot be null.", exception.getMessage());
    }
}