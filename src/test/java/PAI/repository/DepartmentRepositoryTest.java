package PAI.repository;
import PAI.VOs.*;
import PAI.domain.Department;
import PAI.factory.IDepartmentFactory;
import PAI.factory.IDepartmentListFactory;
import PAI.persistence.mem.department.DepartmentRepositoryImpl;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentRepositoryTest {

    //Testing that the list should not be retrieved if empty
    @Test
    void shouldReturnExceptionIfDepartmentListIsEmpty() throws IllegalStateException {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryInterfaceDouble);

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
        IDepartmentListFactory listFactoryInterfaceDouble = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryInterfaceDouble);

        Department department1Double = mock(Department.class);
        Department department2Double = mock(Department.class);

        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        DepartmentAcronym acronym2 = mock(DepartmentAcronym.class);
        Name name2 = mock(Name.class);

        DepartmentID id1 = mock(DepartmentID.class);
        DepartmentID id2 = mock(DepartmentID.class);

        when(factoryInterfaceDouble.newDepartment(acronym, name)).thenReturn(department1Double);
        when(factoryInterfaceDouble.newDepartment(acronym2, name2)).thenReturn(department2Double);

        when(department1Double.identity()).thenReturn(id1);
        when(department2Double.identity()).thenReturn(id2);

        repository.save(department1Double);
        repository.save(department2Double);

        // Act
        Set<DepartmentID> result = repository.getDepartmentIDs();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTrueIfDepartmentExistsInDepartmentRepository() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryInterfaceDouble);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        Department department1Double = mock(Department.class);
        Name name = mock(Name.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        when(factoryInterfaceDouble.newDepartment(departmentAcronym, name)).thenReturn(department1Double);
        when(department1Double.getDepartmentID()).thenReturn(departmentID);

        repository.save(department1Double);

        // Act
        boolean result = repository.departmentExists(departmentID);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfDepartmentDoesNotExistInDepartmentRepository() throws Exception {
        // Arrange
        IDepartmentFactory factoryInterfaceDouble = mock(IDepartmentFactory.class);
        IDepartmentListFactory listFactoryInterfaceDouble = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryInterfaceDouble);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Department departmentDouble = mock(Department.class);
        DepartmentID nonExistingDepartmentDouble = mock(DepartmentID.class);

        when(factoryInterfaceDouble.newDepartment(departmentAcronym, name)).thenReturn(departmentDouble);
        when(departmentDouble.getDepartmentID()).thenReturn(departmentID);

        repository.save(departmentDouble);


        //act
        boolean result = repository.departmentExists(nonExistingDepartmentDouble);

        // Assert
        assertFalse(result);
    }

    //
    @Test
    void shouldReturnFalseIfDepartmentIsNull() {
        // Arrange
        IDepartmentListFactory listFactoryInterfaceDouble = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryInterfaceDouble);

        // Act
        boolean result = repository.departmentExists(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfUpdateDepartmentDirector() {
        // Arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
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
    void shouldReturnFalseIfTeacherIDIsNull() {
        //arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
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
    void shouldReturnFalseIfDepartmentIDIsNull() {
        //arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
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
    void shouldReturnFalseIfUpdateNotSuccessful() {
        //arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        TeacherID furtherDirectorIDDouble = mock(TeacherID.class);

        departmentSet.add(departmentDouble);

        when(departmentDouble.identity()).thenReturn(departmentIDDouble);
        when(departmentDouble.changeDirector(furtherDirectorIDDouble)).thenReturn(false);

        // Act
        boolean result = repository.updateOfDepartmentDirector(departmentIDDouble, furtherDirectorIDDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenDepartmentDoesNotExist() {
        // Arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
        DepartmentID nonExistingDepartmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);

        // Act
        boolean result = repository.updateOfDepartmentDirector(nonExistingDepartmentID, teacherID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldSaveDepartment() {
        //arrange
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDLF);

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
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDLF);
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
        assertEquals(2, StreamSupport.stream(departments.spliterator(), false).count());
    }

    @Test
    void shouldFindDepartmentsByIdentity() {
        //arrange
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDLF);
        Department department1 = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        when(department1.identity()).thenReturn(departmentID);
        repository.save(department1);

        //act
        Optional<Department> departmentFound = repository.ofIdentity(departmentID);

        //assert
        assertTrue(departmentFound.isPresent());
        assertEquals(department1, departmentFound.get());
    }

    @Test
    void shouldCheckIfRepositoryContainsDepartmentByIdentity() {
        //arrange
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDLF);
        Department department1 = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        when(department1.identity()).thenReturn(departmentID);
        repository.save(department1);

        //act + assert
        assertTrue(repository.containsOfIdentity(departmentID));
    }


    @Test
    void shouldReturnEmptyOptionalIfDepartmentListIsEmpty() {
        // Arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
        DepartmentID departmentID = mock(DepartmentID.class);

        // Act
        Optional<Department> result = repository.ofIdentity(departmentID);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalIfDepartmentIDIsNull() {
        // Arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);

        // Act
        Optional<Department> result = repository.ofIdentity(null);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnFalseIfDepartmentListIsEmptyInContainsOfIdentity() {
        // Arrange
        IDepartmentListFactory listFactoryDouble = mock(IDepartmentListFactory.class);
        Set<Department> departmentSet = new HashSet<>();

        when(listFactoryDouble.newDepartmentList()).thenReturn(departmentSet);

        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(listFactoryDouble);
        DepartmentID departmentID = mock(DepartmentID.class);

        // Act
        boolean result = repository.containsOfIdentity(departmentID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnCorrectIDWhenSeveralExists() {
        //arrange
        IDepartmentFactory doubleIDF = mock(IDepartmentFactory.class);
        IDepartmentListFactory doubleIDLF = mock(IDepartmentListFactory.class);
        DepartmentRepositoryImpl repository = new DepartmentRepositoryImpl(doubleIDLF);
        Department department1 = mock(Department.class);
        DepartmentID departmentID1 = mock(DepartmentID.class);
        Department department2= mock(Department.class);
        DepartmentID departmentID2= mock(DepartmentID.class);
        Department department3= mock(Department.class);
        DepartmentID departmentID3= mock(DepartmentID.class);

        when(department1.identity()).thenReturn(departmentID1);
        when(department2.identity()).thenReturn(departmentID2);
        when(department3.identity()).thenReturn(departmentID3);
        repository.save(department1);
        repository.save(department2);
        repository.save(department3);

        //act
        Optional<Department> idExists = repository.ofIdentity(departmentID2);

        //assert
        assertTrue(idExists.isPresent());
        assertEquals(departmentID2, idExists.get().identity());
    }
}
