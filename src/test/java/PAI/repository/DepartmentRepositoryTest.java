package PAI.repository;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.factory.IDepartmentListFactory;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentRepositoryTest {

    //Verifying the creation of a new valid department
    @Test
    void shouldRegisterValidDepartment() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        // Act
        boolean result = repository.registerDepartment(acronym,name);
        // Assert
        assertTrue(result, "The department should be successfully registered.");
    }

    //Verifying the creation of two new valid departments
    @Test
    void shouldRegisterMultipleDifferentDepartments() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        DepartmentAcronym acronym2= mock(DepartmentAcronym.class);

        Name name= mock(Name.class);
        Name name2= mock(Name.class);

        when(factoryInterfaceDouble.newDepartment(acronym,name)).thenReturn(department1Double);
        when(factoryInterfaceDouble.newDepartment(acronym2, name2)).thenReturn(department2Double);

        // Act
        boolean result1 = repository.registerDepartment(acronym,name);
        boolean result2 = repository.registerDepartment(acronym2,name2);

        // Assert
        assertTrue(result1, "The first department should be registered.");
        assertTrue(result2, "The second department should be registered.");
    }

    // Testing an attempt to register a department that already exists
    @Test
    void shouldReturnFalseIfDepartmentAlreadyExists() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department1Double =  new Department(acronym,name);
        Department department2Double= new Department(acronym,name);

        when(factoryInterfaceDouble.newDepartment(acronym,name)).thenReturn(department1Double);
        repository.registerDepartment(acronym,name);
        when(factoryInterfaceDouble.newDepartment(acronym,name)).thenReturn(department2Double);

        //act
        boolean result = repository.registerDepartment(acronym,name);

        //assert
        assertFalse(result);
    }

    //Testing that the list should not be retrieved if empty
    @Test
    void shouldReturnExceptionIfDepartmentListIsEmpty() throws IllegalStateException {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            repository.getDepartmentIDs();
        });
        assertEquals("Department list is empty.", exception.getMessage());
    }

    //Testing that the retrieved list has registered objects
    @Test
    void shouldReturnDepartmentListWithRegisteredDepartments() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        DepartmentAcronym acronym2= mock(DepartmentAcronym.class);
        Name name2= mock(Name.class);

        DepartmentID id1 = mock(DepartmentID.class);
        DepartmentID id2 = mock(DepartmentID.class);

        when(factoryInterfaceDouble.newDepartment(acronym, name)).thenReturn(department1Double);
        when(factoryInterfaceDouble.newDepartment(acronym2, name2)).thenReturn(department2Double);

        when(department1Double.identity()).thenReturn(id1);
        when(department2Double.identity()).thenReturn(id2);

        repository.registerDepartment(acronym, name);
        repository.registerDepartment(acronym2, name2);

        // Act
        Set<DepartmentID> result = repository.getDepartmentIDs();
        // Assert
        assertEquals(2, result.size());
    }
    @Test
    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);
        DepartmentAcronym departmentAcronym= mock(DepartmentAcronym.class);
        Department department1Double= mock(Department.class);
        Name name= mock(Name.class);
        DepartmentID departmentID= mock(DepartmentID.class);

        when(factoryInterfaceDouble.newDepartment(departmentAcronym,name)).thenReturn(department1Double);
        when(department1Double.getDepartmentID()).thenReturn(departmentID);

        repository.registerDepartment(departmentAcronym,name);

        // Act
        boolean result= repository.departmentExists(departmentID);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentDoesNotExistInDepartmentRepository() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);
        DepartmentAcronym departmentAcronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        DepartmentID departmentID= mock(DepartmentID.class);
        Department departmentDouble = mock(Department.class);
        DepartmentID nonExistingDepartmentDouble = mock(DepartmentID.class);

        when(factoryInterfaceDouble.newDepartment(departmentAcronym,name)).thenReturn(departmentDouble);
        when(departmentDouble.getDepartmentID()).thenReturn(departmentID);

        repository.registerDepartment(departmentAcronym,name);


        //act
        boolean result = repository.departmentExists(nonExistingDepartmentDouble);

        // Assert
        assertFalse(result);
    }
//
    @Test
    void shouldReturnFalseIfDepartmentIsNull() {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryInterfaceDouble,listFactoryInterfaceDouble);

        // Act
        boolean result= repository.departmentExists(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector() {
        // Arrange
        IDepartmentFactory factoryDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryDouble, listFactoryDouble);
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        TeacherID furtherDirectorIDDouble = mock(TeacherID.class);

        departmentSet.add(departmentDouble);

        when(departmentDouble.identity()).thenReturn(departmentIDDouble);
        when(departmentDouble.changeDirector(furtherDirectorIDDouble)).thenReturn(true);

        // Act
        boolean result = repository.updateOfDepartmentDirector(departmentIDDouble, furtherDirectorIDDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIDIsNull (){
        //arrange
        IDepartmentFactory factoryDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryDouble, listFactoryDouble);
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);

        departmentSet.add(departmentDouble);

        when(departmentDouble.identity()).thenReturn(departmentIDDouble);

        // Act
        boolean result = repository.updateOfDepartmentDirector(departmentIDDouble, null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentIDIsNull (){
        //arrange
        IDepartmentFactory factoryDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(factoryDouble, listFactoryDouble);
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        TeacherID furtherDirectorIDDouble = mock(TeacherID.class);

        departmentSet.add(departmentDouble);

        when(departmentDouble.identity()).thenReturn(departmentIDDouble);
        when(departmentDouble.changeDirector(furtherDirectorIDDouble)).thenReturn(true);

        // Act
        boolean result = repository.updateOfDepartmentDirector(null, furtherDirectorIDDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldSaveDepartment() {
        //arrange
        IDepartmentFactory doubleIDF = mock(IDepartmentFactory.class);
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDF,doubleIDLF);

        DepartmentID departmentID = mock(DepartmentID.class);
        Department department = mock(Department.class);

        when(department.identity()).thenReturn(departmentID);

        //act
        Department departmentSaved = repository.save(department);

        //assert
        assertNotNull(departmentSaved);
        assertTrue(repository.containsOfIdentity(departmentSaved.identity()));
    }

    @Test
    void shouldReturnAllDepartments() {

        //arrange
        IDepartmentFactory doubleIDF = mock(IDepartmentFactory.class);
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDF,doubleIDLF);
        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);

        repository.save(department1);
        repository.save(department2);

        //act
        Iterable<Department> departments = repository.findAll();
        List<Department> departmentList = new ArrayList<>();
        departments.forEach(departmentList::add);

        //assert
        assertNotNull(departments);
        assertEquals(2, StreamSupport.stream(departments.spliterator(),false).count());
    }

    @Test
    void shouldFindDepartmentsByIdentity() {

        //arrange
        IDepartmentFactory doubleIDF = mock(IDepartmentFactory.class);
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDF,doubleIDLF);
        Department department1 = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        when(department1.identity()).thenReturn(departmentID);
        repository.save(department1);

        //act
        Optional<Department> departmentFound = repository.ofIdentity(departmentID);

        //assert
        assertTrue(departmentFound.isPresent());
        assertEquals(department1,departmentFound.get());
    }

    @Test
    void shouldCheckIfRepositoryContainsDepartmentByIdentity() {
        //arrange
        IDepartmentFactory doubleIDF = mock(IDepartmentFactory.class);
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDF,doubleIDLF);
        Department department1 = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        when(department1.identity()).thenReturn(departmentID);
        repository.save(department1);

        //act + assert
        assertTrue(repository.containsOfIdentity(departmentID));
    }
}
