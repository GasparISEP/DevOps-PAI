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
import PAI.repository.TeacherRepository;
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
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
    IDepartmentRepository departmentRepo = mock (IDepartmentRepository.class);

    //act
    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller=
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo, teacherRepo);
    //assert
    assertNotNull(controller);
}

@Test
void shouldReturnExceptionIfDepartmentRepositoryIsNull() {
    //arrange
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
    //act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(null, teacherRepo);
    });

    //assert
    assertEquals("Department Repository cannot be null!", exception.getMessage());
}

@Test
void shouldReturnExceptionIfTeacherRepositoryIsNull() {
    //arrange
    IDepartmentRepository departmentRepo= mock(DepartmentRepositoryImpl.class);
    //act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo, null);
    });

    //assert
    assertEquals("Teacher Repository cannot be null!", exception.getMessage());
}

@Test
void shouldReturnTrueIfUpdateDepartmentDirector () {
    // Arrange
    IDepartmentRepository departmentRepo = mock(IDepartmentRepository.class);
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);

    DepartmentID departmentIdDouble = mock(DepartmentID.class);
    TeacherID teacherIdDouble = mock(TeacherID.class);
    Teacher teacherDouble = mock(Teacher.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo, teacherRepo);

    when(teacherRepo.ofIdentity(teacherIdDouble)).thenReturn(Optional.of(teacherDouble));
    when(teacherDouble.isInDepartment(departmentIdDouble)).thenReturn(true);
    when(departmentRepo.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble)).thenReturn(true);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    // Assert
    assertTrue(result);
}

@Test
void shouldReturnFalseIfUpdateDepartmentDirectorNotSucessfull (){
    //arrange
    IDepartmentRepository departmentRepo= mock(DepartmentRepositoryImpl.class);
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);

    DepartmentID departmentIdDouble= mock(DepartmentID.class);

    TeacherID teacherIdDouble = mock(TeacherID.class);
    Teacher teacherDouble = mock(Teacher.class);


    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo,teacherRepo);


    when(teacherRepo.ofIdentity(teacherIdDouble)).thenReturn(Optional.of(teacherDouble));
    when(teacherDouble.isInDepartment(departmentIdDouble)).thenReturn(true);
    when(departmentRepo.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble)).thenReturn(false);

    //act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfDepartmentIDIsNull (){
    //arrange
    IDepartmentRepository departmentRepo= mock(DepartmentRepositoryImpl.class);
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);

    TeacherID teacherIdDouble=mock(TeacherID.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo,teacherRepo);

    //act
    boolean result = controller.updateOfDepartmentDirector(null, teacherIdDouble);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfTeacherIDIsNull (){
    //arrange
    IDepartmentRepository departmentRepo= mock(DepartmentRepositoryImpl.class);
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);

    DepartmentID departmentIdDouble= mock(DepartmentID.class);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo,teacherRepo);

    //act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, null);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfTeacherIdDoesNotCorrespondToATeacher (){
    // Arrange
    IDepartmentRepository departmentRepo = mock(IDepartmentRepository.class);
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);

    DepartmentID departmentIdDouble = mock(DepartmentID.class);
    TeacherID teacherIdDouble = mock(TeacherID.class);

    when(teacherRepo.ofIdentity(teacherIdDouble)).thenReturn(Optional.empty());

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo, teacherRepo);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    // Assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfTeacherIsNotInDepartment (){
    // Arrange
    IDepartmentRepository departmentRepo = mock(IDepartmentRepository.class);
    ITeacherRepository teacherRepo = mock(ITeacherRepository.class);

    DepartmentID departmentIdDouble = mock(DepartmentID.class);
    TeacherID teacherIdDouble = mock(TeacherID.class);
    Teacher teacherDouble = mock(Teacher.class);

    when(teacherRepo.ofIdentity(teacherIdDouble)).thenReturn(Optional.of(teacherDouble));
    when(teacherDouble.isInDepartment(departmentIdDouble)).thenReturn(false);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepo, teacherRepo);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentIdDouble, teacherIdDouble);

    // Assert
    assertFalse(result);
}

@Test
void testGetAllDepartments() {
    // Arrange
    IDepartmentRepository departmentRepoDouble = mock(IDepartmentRepository.class);
    ITeacherRepository teacherRepoDouble = mock (ITeacherRepository.class);
    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepoDouble, teacherRepoDouble);

    DepartmentID departmentId1 = mock(DepartmentID.class);
    DepartmentID departmentId2 = mock(DepartmentID.class);

    Set<DepartmentID> departmentSet = new HashSet<>();
    departmentSet.add(departmentId1);
    departmentSet.add(departmentId2);

    when(departmentRepoDouble.getDepartmentIDs()).thenReturn(departmentSet);

    // Act
    Set<DepartmentID> departments = controller.getAllDepartmentID();

    // Assert
    assertEquals(2, departments.size());
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
    DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(factory, listFactory);


    ITeacherRepository teacherRepositorySpringData = mock(TeacherRepositorySpringDataImpl.class);
    ITeacherFactory teacherFactory = new TeacherFactoryImpl(teacherRepositorySpringData);
    TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
    ITeacherRepository teacherRepository= new TeacherRepository(teacherFactory, teacherListFactoryImpl);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepository, teacherRepository);

    teacherRepository.save(teacher);

    departmentRepository.registerDepartment(dAcronym,name);

    // Act
    boolean result = controller.updateOfDepartmentDirector(departmentID, teacherID);

    // Assert
    assertTrue(result);

}

@Test
void shouldReturnFalseIfTeacherIdIsNull_IntegrationTest () throws Exception {
    //arrange
    DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
    DepartmentID departmentID = new DepartmentID(dAcronym);

    TeacherAcronym tAcronym = new TeacherAcronym("POB");
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
    DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(factory, listFactory);


    ITeacherRepository teacherRepositorySpringData = mock(TeacherRepositorySpringDataImpl.class);
    ITeacherFactory teacherFactory = new TeacherFactoryImpl(teacherRepositorySpringData);
    TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
    ITeacherRepository teacherRepository= new TeacherRepository(teacherFactory, teacherListFactoryImpl);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepository, teacherRepository);

    teacherRepository.save(teacher);

    departmentRepository.registerDepartment(dAcronym,name);

    //act
    boolean result = controller.updateOfDepartmentDirector(departmentID, null);

    //assert
    assertFalse(result);
}

@Test
void shouldReturnFalseIfDepartmentIdIsNull_IntegrationTest () throws Exception {
    //arrange
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
    DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(factory, listFactory);


    ITeacherRepository teacherRepositorySpringData = mock(TeacherRepositorySpringDataImpl.class);
    ITeacherFactory teacherFactory = new TeacherFactoryImpl(teacherRepositorySpringData);
    TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
    ITeacherRepository teacherRepository= new TeacherRepository(teacherFactory, teacherListFactoryImpl);

    US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
            new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepository, teacherRepository);

    teacherRepository.save(teacher);
    departmentRepository.registerDepartment(dAcronym,name);

    //act
    boolean result = controller.updateOfDepartmentDirector(null, teacherID);

    //assert
    assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherNotInDepartment_IntegrationTest () throws Exception {
        //arrange
        DepartmentAcronym dAcronym= new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(dAcronym);
        DepartmentAcronym d2Acronym= new DepartmentAcronym("DBI");
        DepartmentID departmentID2 = new DepartmentID(d2Acronym);

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
        Teacher teacher = new Teacher(tAcronym,  name,  email,  nif,  phoneNumber,  academicBackground, address,  departmentID2);

        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();
        DepartmentListFactoryImpl listFactory = new DepartmentListFactoryImpl();
        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(factory, listFactory);


        ITeacherRepository teacherRepositorySpringData = mock(TeacherRepositorySpringDataImpl.class);
        ITeacherFactory teacherFactory = new TeacherFactoryImpl(teacherRepositorySpringData);
        TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository= new TeacherRepository(teacherFactory, teacherListFactoryImpl);

        US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController controller =
                new US06_IWantToUpdateTheDepartmentDirectorOfADepartmentController(departmentRepository, teacherRepository);

        teacherRepository.save(teacher);
        departmentRepository.registerDepartment(dAcronym,name);

        //act
        boolean result = controller.updateOfDepartmentDirector(departmentID, teacherID);

        //assert
        assertFalse(result);
    }
}
