package PAI.controller;
import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import PAI.mapper.*;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.persistence.springdata.ITeacherRepositorySpringData;
import PAI.persistence.springdata.TeacherRepositorySpringDataImpl;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.repository.IDepartmentRepository;
import PAI.repository.ITeacherRepository;
import PAI.service.ITeacherService;
import PAI.service.department.DepartmentServiceImpl;
import PAI.service.department.IDepartmentService;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
void shouldReturnTrueIfUpdateDepartmentDirector () {
    // Arrange
    IDepartmentService departmentService = mock (IDepartmentService.class);

    DepartmentID departmentIdDouble = mock(DepartmentID.class);
    TeacherID teacherIdDouble = mock(TeacherID.class);
    Teacher teacherDouble = mock(Teacher.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);

    when(departmentService.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble)).thenReturn(true);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    // Assert
    assertTrue(result);
}

@Test
void shouldReturnFalseIfUpdateDepartmentDirectorWasNotSucessful () {
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
void shouldReturnFalseIfDepartmentIDIsNull (){
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
void shouldReturnFalseIfTeacherIDIsNull (){
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
    void shouldReturnFalseIfBothAreNull (){
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
    Name name = new Name("John Doe");
    Email email = new Email("john@doe.com");
    PAI.VOs.Location location = new PAI.VOs.Location("Porto");
    Street street = new Street("123 street");
    PostalCode postalCode = new PostalCode("12345");
    Country country = new Country("Portugal");
    Address address = new Address(street, postalCode,location,country);

    NIF nif = new NIF("123431123",country);
    PhoneNumber phoneNumber = new PhoneNumber("+351","912123123");
    AcademicBackground  academicBackground= new AcademicBackground("Doctor");
    Teacher teacher = new Teacher(tAcronym,  name,  email,  nif,  phoneNumber,  academicBackground, address,  departmentID);

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
    boolean result = controller.updateOfDepartmentDirector(departmentID, null);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfDepartmentIdIsNull_IntegrationTest () throws Exception {
    //arrange
    DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
    Name departmentName= new Name("DepartmentName");
    TeacherAcronym tAcronym = new TeacherAcronym("POB");
    TeacherID teacherID = new TeacherID(tAcronym);

    DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
    DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(listFactory);
    IDepartmentService departmentService = new DepartmentServiceImpl(factory,departmentRepository);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);
    departmentService.registerDepartment(dAcronym,departmentName);

    //act
    boolean result = controller.updateOfDepartmentDirector(null, teacherID);

    //assert
    assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfBothAreNull_IntegrationTest () throws Exception {
        //arrange

        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(listFactory);
        IDepartmentService departmentService = new DepartmentServiceImpl(factory,departmentRepository);

        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
                new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentService);

        //act
        boolean result = controller.updateOfDepartmentDirector(null, null);

        //assert
        assertFalse(result);
    }
}
