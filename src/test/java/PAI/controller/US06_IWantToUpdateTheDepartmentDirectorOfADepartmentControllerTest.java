package PAI.controller;
import PAI.VOs.*;
import PAI.domain.department.DepartmentFactoryImpl;
import PAI.persistence.mem.department.DepartmentListFactoryImpl;
import PAI.persistence.mem.department.DepartmentRepositoryImpl;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.service.department.DepartmentServiceImpl;
import PAI.service.department.IDepartmentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US06_IWantToUpdateTheDepartmentDirectorOfADepartmentControllerTest {


@Test
void shouldReturnValidController () {
    //arrange
    IDepartmentService departmentService = mock (IDepartmentService.class);

    //act
    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller=
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);
    //assert
    assertNotNull(controller);
}

@Test
void shouldReturnExceptionIfDepartmentServiceIsNull() {
    //arrange
    //act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(null);
    });

    //assert
    assertEquals("Department Service cannot be null!", exception.getMessage());
}

@Test
void shouldReturnTrueIfUpdateDepartmentDirector () throws Exception {
    // Arrange
    IDepartmentService departmentService = mock (IDepartmentService.class);

    DepartmentID departmentIdDouble = mock(DepartmentID.class);
    TeacherID teacherIdDouble = mock(TeacherID.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);

    when(departmentService.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble)).thenReturn(true);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    // Assert
    assertTrue(result);
}

@Test
void shouldReturnFalseIfUpdateDepartmentDirectorWasNotSucessful () throws Exception {
    //arrange
    IDepartmentService departmentService = mock (IDepartmentService.class);
    DepartmentID departmentIdDouble= mock(DepartmentID.class);

    TeacherID teacherIdDouble = mock(TeacherID.class);


    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);


    when(departmentService.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble)).thenReturn(false);

    //act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfDepartmentIDIsNull () throws Exception {
    //arrange
    IDepartmentService departmentService = mock(IDepartmentService.class);

    TeacherID teacherIdDouble=mock(TeacherID.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);

    //act
    boolean result = controller.updateOfDepartmentDirector(null, teacherIdDouble);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfTeacherIDIsNull () throws Exception {
    //arrange
    IDepartmentService departmentService = mock(IDepartmentService.class);

   DepartmentID departmentIdDouble=mock(DepartmentID.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);

    //act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, null);

    //assert
    assertFalse(result);
}
    @Test
    void shouldReturnFalseIfBothAreNull () throws Exception {
        //arrange
        IDepartmentService departmentService = mock(IDepartmentService.class);

        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
                new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);

        //act
        boolean result = controller.updateOfDepartmentDirector(null, null);

        //assert
        assertFalse(result);
    }


//    Integration tests
@Test
void shouldReturnTrueIfUpdateOfDirectorSucessfull () throws Exception {
    // Arrange
    DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
    DepartmentID departmentID = new DepartmentID(dAcronym);

    TeacherAcronym tAcronym = new TeacherAcronym("POB");
    TeacherID teacherID = new TeacherID(tAcronym);
    Name name = new Name("Department");

    DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
    DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(listFactory);
    IDepartmentService departmentService = new DepartmentServiceImpl(factory,departmentRepository);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);
    departmentService.registerDepartment(dAcronym,name);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentID, teacherID);

    // Assert
    assertTrue(result);

}
    @Test
    void shouldReturnFalseIfDepartmentDoesntExist () throws Exception {
        // Arrange
        DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);

        TeacherAcronym tAcronym = new TeacherAcronym("POB");
        TeacherID teacherID = new TeacherID(tAcronym);
        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(listFactory);
        IDepartmentService departmentService = new DepartmentServiceImpl(factory,departmentRepository);

        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
                new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);
        departmentService.updateOfDepartmentDirector(departmentID, teacherID);

        // Act
        boolean result = controller.updateOfDepartmentDirector(departmentID, teacherID);

        // Assert
        assertFalse(result);

    }
//
@Test
void shouldReturnFalseIfTeacherIdIsNull_IntegrationTest () throws Exception {
    //arrange
    DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
    DepartmentID departmentID = new DepartmentID(dAcronym);
    Name departmentName= new Name("DepartmentName");

    DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
    DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(listFactory);
    IDepartmentService departmentService = new DepartmentServiceImpl(factory,departmentRepository);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);
    departmentService.registerDepartment(dAcronym,departmentName);

    //act
    Exception exception = assertThrows(Exception.class, () -> {controller.updateOfDepartmentDirector(departmentID, null);});


    //assert
    assertEquals("Teacher ID cannot be null.",exception.getMessage());
}

@Test
void shouldReturnFalseIfDepartmentIdIsNull_IntegrationTest () throws Exception {
    //arrange
    DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
    Name departmentName= new Name("DepartmentName");
    TeacherAcronym tAcronym = new TeacherAcronym("POB");
    DepartmentID departmentID = new DepartmentID(dAcronym);
    TeacherID teacherID = new TeacherID(tAcronym);

    DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
    DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(listFactory);
    IDepartmentService departmentService = new DepartmentServiceImpl(factory,departmentRepository);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);
    departmentService.registerDepartment(dAcronym,departmentName);
    departmentService.updateOfDepartmentDirector(departmentID,teacherID);

    //act
    Exception exception = assertThrows(Exception.class, () -> {controller.updateOfDepartmentDirector(null,teacherID );});


    //assert
    assertEquals("Department ID cannot be null.",exception.getMessage());
    }
}
