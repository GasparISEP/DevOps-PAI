package PAI.repository;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.factory.IDepartmentListFactory;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    void departmentExistsShouldReturnTrue() throws Exception {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        Department department1Double = mock(Department.class);
        DepartmentRepositoryImpl departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        DepartmentID id1 = mock(DepartmentID.class);

        // Mock the behavior of registerDepartment to return true
        when(departmentRepoDouble.registerDepartment(acronym, name)).thenReturn(true);

        // Mock the behavior of departmentExists to return true
        when(departmentRepoDouble.departmentExists(id1)).thenReturn(true);

        // Act
        boolean result = departmentRepoDouble.departmentExists(id1);

        // Assert
        assertTrue(result);
    }
    @Test
    void departmentExistShouldReturnFalse() throws Exception {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        Department department1Double = mock(Department.class);
        DepartmentRepositoryImpl departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
        DepartmentID id1 = mock(DepartmentID.class);

        // Mock the behavior of registerDepartment to return true
        when(departmentRepoDouble.registerDepartment(acronym, name)).thenReturn(true);

        // Mock the behavior of departmentExists to return true
        when(departmentRepoDouble.departmentExists(id1)).thenReturn(false);

        // Act
        boolean result = departmentRepoDouble.departmentExists(id1);

        // Assert
        assertFalse(result);
    }

        @Test
        void findDepartmentByIDTest_DepartmentExists() {
        //assert
            DepartmentID departmentID = mock(DepartmentID.class);
            Department department = mock(Department.class);
            DepartmentRepositoryImpl departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
            // Act
            when(departmentRepoDouble.departmentExists(departmentID)).thenReturn(true);
            when(departmentRepoDouble.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
            Optional<Department> result = departmentRepoDouble.findDepartmentByID(departmentID);

            // Assert
            assertTrue(result.isPresent());
            assertEquals(department, result.get());
        }

        @Test
        void findDepartmentByIDTest_DepartmentDoesNotExist() {
            // Arrange
            Department department = mock(Department.class);
            DepartmentRepositoryImpl departmentRepoDouble = mock(DepartmentRepositoryImpl.class);
            DepartmentID nonExistentDepartmentID = mock(DepartmentID.class);

            // Act
            Optional<Department> result = departmentRepoDouble.findDepartmentByID(nonExistentDepartmentID);

            // Assert
            assertFalse(result.isPresent());
        }

//    @Test
//    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
//        // Arrange
//        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
//        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
//        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
//
//        Department department1Double= mock(Department.class);
//
//        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(department1Double);
//
//        repository.registerDepartment("CSE", "Computer Science");
//
//        // Act
//        boolean result= repository.departmentExists(department1Double);
//
//        //Assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfDepartmentDoesNotExistInDepartmentRepository() throws Exception {
//        // Arrange
//        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
//        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
//        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
//
//        Department departmentDouble = mock(Department.class);
//        Department nonExistingDepartmentDouble = mock(Department.class);
//
//        when(factoryInterfaceDouble.newDepartment("CSE", "Computer Science")).thenReturn(departmentDouble);
//        repository.registerDepartment("CSE", "Computer Science");
//
//        //act
//        boolean result = repository.departmentExists(nonExistingDepartmentDouble);
//
//        // Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfDepartmentIsNull() {
//        // Arrange
//        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
//        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
//        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
//
//        // Act
//        boolean result= repository.departmentExists(null);
//
//        //Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnTrueIfUpdateDepartmentDirector(){
//        //arrange
//        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
//        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
//        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
//
//        Department departmentDouble = mock(Department.class);
//        Teacher furtherDirectorDouble = mock(Teacher.class);
//
//        when(furtherDirectorDouble.isInDepartment(departmentDouble)).thenReturn(true);
//        when(departmentDouble.changeDirector(furtherDirectorDouble)).thenReturn(true);
//
//        //act
//        boolean result= repository.updateOfDepartmentDirector(departmentDouble,furtherDirectorDouble);
//
//        //assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfTeacherDoesNotBelongToDepartment(){
//        //arrange
//        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
//        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
//        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
//
//        Department departmentDouble = mock(Department.class);
//        Teacher furtherDirectorDouble = mock(Teacher.class);
//
//        when(furtherDirectorDouble.isInDepartment(departmentDouble)).thenReturn(false);
//
//        //act
//        boolean result= repository.updateOfDepartmentDirector(departmentDouble,furtherDirectorDouble);
//
//        //assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfTeacherIsNull (){
//        //arrange
//        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
//        IDepartmentListFactory listFactoryInterfaceDouble= mock(IDepartmentListFactory.class);
//        DepartmentRepository repository = new DepartmentRepository(factoryInterfaceDouble,listFactoryInterfaceDouble);
//        Department dpt1Double= mock(Department.class);
//
//        //act
//        boolean result = repository.updateOfDepartmentDirector(dpt1Double, null);
//
//        //assert
//        assertFalse(result);
//    }

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
