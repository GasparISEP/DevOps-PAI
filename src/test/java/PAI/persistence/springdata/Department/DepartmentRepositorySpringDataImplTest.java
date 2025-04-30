package PAI.persistence.springdata.Department;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.mapper.ITeacherIDMapper;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.mapper.department.IDepartmentMapper;
import PAI.persistence.datamodel.department.DepartmentDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentRepositorySpringDataImplTest {


    @Test
    void shouldReturnEqualIfSaveWasSuccessful() throws Exception {
        // Arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        IDepartmentIDMapper departmentIDMapper = Mockito.mock(IDepartmentIDMapper.class);

        DepartmentRepositorySpringDataImpl departmentRepositorySpringData =
                new DepartmentRepositorySpringDataImpl(departmentRepository, departmentIDMapper, departmentMapper);

        Department departmentMock = Mockito.mock(Department.class);
        DepartmentDataModel departmentDataModel = Mockito.mock(DepartmentDataModel.class);
        when(departmentMapper.toDataModel(departmentMock)).thenReturn(departmentDataModel);
        when(departmentRepository.save(departmentDataModel)).thenReturn(departmentDataModel);
        when(departmentMapper.toDomain(departmentDataModel)).thenReturn(departmentMock);

        // Act
        Department result = departmentRepositorySpringData.save(departmentMock);

        // Assert
        assertEquals(departmentMock, result);
    }
    @Test
    void shouldThrowExceptionIfSaveWasNotSuccessful() {
        // Arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        IDepartmentIDMapper departmentIDMapper = Mockito.mock(IDepartmentIDMapper.class);
        DepartmentRepositorySpringDataImpl repoImpl =
                new DepartmentRepositorySpringDataImpl(departmentRepository, departmentIDMapper, departmentMapper);

        Department departmentMock = Mockito.mock(Department.class);
        Name nameMock = Mockito.mock(Name.class);

        when(departmentMock.getName()).thenReturn(nameMock);
        when(nameMock.getName()).thenReturn("Test Department");

        // Simulate failure
        when(departmentMapper.toDataModel(departmentMock)).thenThrow(new RuntimeException("Mapping failed"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> repoImpl.save(departmentMock));
        assertTrue(thrown.getMessage().contains("Failed to save department: Test Department"));
    }
    @Test
    void save_ShouldThrowRuntimeException_WhenJpaSaveFails() {
        // Arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        IDepartmentIDMapper departmentIDMapper = Mockito.mock(IDepartmentIDMapper.class);
        DepartmentRepositorySpringDataImpl repoImpl =
                new DepartmentRepositorySpringDataImpl(departmentRepository, departmentIDMapper, departmentMapper);

        Department departmentMock = Mockito.mock(Department.class);
        DepartmentDataModel departmentDataModel = Mockito.mock(DepartmentDataModel.class);
        Name nameMock = Mockito.mock(Name.class);

        when(departmentMock.getName()).thenReturn(nameMock);
        when(nameMock.getName()).thenReturn("FailDept");
        when(departmentMapper.toDataModel(departmentMock)).thenReturn(departmentDataModel);
        when(departmentRepository.save(departmentDataModel)).thenThrow(new RuntimeException("Failed to save department: FailDept"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> repoImpl.save(departmentMock));
        assertTrue(thrown.getMessage().contains("Failed to save department: FailDept"));
    }
    @Test
    void ShouldReturnAListOfDepartmentsWhenFindAllIsCalled() throws Exception {
        // arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        IDepartmentIDMapper departmentIDMapper = Mockito.mock(IDepartmentIDMapper.class);
        DepartmentRepositorySpringDataImpl departmentRepositorySpringData = new DepartmentRepositorySpringDataImpl(departmentRepository, departmentIDMapper, departmentMapper);
        DepartmentDataModel departmentDataModelMock = Mockito.mock(DepartmentDataModel.class);
        Department departmentMock = Mockito.mock(Department.class);
        List<DepartmentDataModel> departmentDataModels = Collections.singletonList(departmentDataModelMock);

        when(departmentRepository.findAll()).thenReturn(departmentDataModels);
        when(departmentMapper.toDomain(departmentDataModelMock)).thenReturn(departmentMock);

        // act
        List<Department> result = departmentRepositorySpringData.findAll();

        // assert
        assertEquals(1, result.size());
        assertEquals(departmentMock, result.getFirst());
    }
    @Test
    void ShouldReturnEmptyListWhenNoDepartmentsAreFound() {
        // arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        IDepartmentIDMapper departmentIDMapper = Mockito.mock(IDepartmentIDMapper.class);


        DepartmentRepositorySpringDataImpl departmentRepositorySpringData =
                new DepartmentRepositorySpringDataImpl(departmentRepository, departmentIDMapper, departmentMapper);

        // act
        List<Department> result = departmentRepositorySpringData.findAll();

        // assert
        assertTrue(result.isEmpty());
    }
    @Test
    void ShouldThrowExceptionWhenMappingFailsInFindAll() throws Exception {
        // arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        IDepartmentIDMapper departmentIDMapper = Mockito.mock(IDepartmentIDMapper.class);


        DepartmentRepositorySpringDataImpl departmentRepositorySpringData =
                new DepartmentRepositorySpringDataImpl(departmentRepository, departmentIDMapper, departmentMapper);

        DepartmentDataModel departmentDataModelMock = Mockito.mock(DepartmentDataModel.class);
        List<DepartmentDataModel> departmentDataModels = Collections.singletonList(departmentDataModelMock);
        when(departmentRepository.findAll()).thenReturn(departmentDataModels);

        when(departmentMapper.toDomain(departmentDataModelMock)).thenThrow(new RuntimeException("Mapping failed"));

        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            departmentRepositorySpringData.findAll();
        });
        assertEquals("Failed to map department data model.", exception.getMessage());
    }

    @Test
    void testOfIdentity_existingDepartment() throws Exception {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl departmentRepository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        DepartmentID departmentID = mock(DepartmentID.class);
        Department department = mock(Department.class);
        DepartmentDataModel departmentDataModel = mock(DepartmentDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);
        DepartmentAcronym departmentAcronym = mock(DepartmentAcronym.class);
        String departmentIDString = "AAA";
        when(departmentAcronym.getAcronym()).thenReturn("AAA");
        when(departmentID.getAcronym()).thenReturn(departmentAcronym);
        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn(departmentIDString);
        when(jpaRepo.findById(departmentIDString)).thenReturn(Optional.of(departmentDataModel));
        when(departmentMapper.toDomain(departmentDataModel)).thenReturn(department);

        // Act
        Optional<Department> result = departmentRepository.ofIdentity(departmentID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(department, result.get());
    }
    @Test
    void testOfIdentity_nonExistingDepartment(){
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl departmentRepository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);
        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.empty());

        // Act
        Optional<Department> result = departmentRepository.ofIdentity(departmentID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testContainsOfIdentity_departmentExists() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl repository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentIDDataModel dataModel = mock(DepartmentIDDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(dataModel);
        when(dataModel.getDepartmentID()).thenReturn("ABC");
        when(jpaRepo.existsById("ABC")).thenReturn(true);

        // Act
        boolean result = repository.containsOfIdentity(departmentID);

        // Assert
        assertTrue(result);
    }

    @Test
    void testContainsOfIdentity_departmentDoesNotExist() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl repository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentIDDataModel dataModel = mock(DepartmentIDDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(dataModel);
        when(dataModel.getDepartmentID()).thenReturn("ABC");
        when(jpaRepo.existsById("ABC")).thenReturn(false);

        // Act
        boolean result = repository.containsOfIdentity(departmentID);

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetDepartmentIDs_whenDepartmentsExist() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);

        DepartmentRepositorySpringDataImpl repository =
                new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        Department dep1 = mock(Department.class);
        Department dep2 = mock(Department.class);

        DepartmentID id1 = new DepartmentID(new DepartmentAcronym("ABC"));
        DepartmentID id2 = new DepartmentID(new DepartmentAcronym("ACB"));

        when(dep1.identity()).thenReturn(id1);
        when(dep2.identity()).thenReturn(id2);

        List<Department> departments = List.of(dep1, dep2);

        DepartmentRepositorySpringDataImpl spyRepository = spy(repository);
        doReturn(departments).when(spyRepository).findAll();

        // Act
        Set<DepartmentID> result = spyRepository.getDepartmentIDs();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(id1));
        assertTrue(result.contains(id2));
    }
    @Test
    void testGetDepartmentIDs_whenNoDepartments_throwsException() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl repository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        DepartmentRepositorySpringDataImpl spyRepo = spy(repository);
        doReturn(Collections.emptyList()).when(spyRepo).findAll();

        // Act + Assert
        IllegalStateException ex = assertThrows(IllegalStateException.class, spyRepo::getDepartmentIDs);
        assertEquals("No departments found in repository.", ex.getMessage());
    }

    @Test
    void ShouldThrowIllegalArgumentExceptionWhenDepartmentIDIsNull() {
        // arrange
        DepartmentRepositorySpringDataImpl departmentRepositorySpringData = new DepartmentRepositorySpringDataImpl(
                Mockito.mock(IDepartmentRepositorySpringData.class),
                Mockito.mock(IDepartmentIDMapper.class),
                Mockito.mock(IDepartmentMapper.class)
        );

        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            departmentRepositorySpringData.findDepartmentByID(null);
        });

        // assert
        assertEquals("Department ID cannot be null.", exception.getMessage());
    }
    @Test
    void ShouldReturnDepartmentWhenFoundByID() throws Exception {
        // arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = Mockito.mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        ITeacherIDMapper teacherIDMapper = Mockito.mock(ITeacherIDMapper.class);

        DepartmentRepositorySpringDataImpl departmentRepositorySpringData =
                new DepartmentRepositorySpringDataImpl(departmentRepository, idMapper, departmentMapper);

        DepartmentID departmentID = Mockito.mock(DepartmentID.class);
        DepartmentIDDataModel departmentIDDataModel = Mockito.mock(DepartmentIDDataModel.class);
        DepartmentDataModel departmentDataModel = Mockito.mock(DepartmentDataModel.class);
        Department department = Mockito.mock(Department.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn("DEP");

        Optional<DepartmentDataModel> optionalDataModel = Optional.of(departmentDataModel);
        when(departmentRepository.findById("DEP")).thenReturn(optionalDataModel);

        when(departmentMapper.toDomain(departmentDataModel)).thenReturn(department);

        // act
        Optional<Department> result = departmentRepositorySpringData.findDepartmentByID(departmentID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(department, result.get());
    }
    @Test
    void ShouldReturnEmptyOptionalWhenDepartmentNotFound() {
        // arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = Mockito.mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        ITeacherIDMapper teacherIDMapper = Mockito.mock(ITeacherIDMapper.class);

        DepartmentRepositorySpringDataImpl departmentRepositorySpringData =
                new DepartmentRepositorySpringDataImpl(departmentRepository, idMapper, departmentMapper);

        DepartmentID departmentID = Mockito.mock(DepartmentID.class);
        DepartmentIDDataModel departmentIDDataModel = Mockito.mock(DepartmentIDDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn("DEP");
        when(departmentRepository.findById("DEP")).thenReturn(Optional.empty());

        // act
        Optional<Department> result = departmentRepositorySpringData.findDepartmentByID(departmentID);

        // assert
        assertFalse(result.isPresent());
    }
    @Test
    void ShouldThrowRuntimeExceptionWhenMappingFails() throws Exception {
        // arrange
        IDepartmentRepositorySpringData departmentRepository = Mockito.mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = Mockito.mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = Mockito.mock(IDepartmentMapper.class);
        ITeacherIDMapper teacherIDMapper = Mockito.mock(ITeacherIDMapper.class);

        DepartmentRepositorySpringDataImpl departmentRepositorySpringData =
                new DepartmentRepositorySpringDataImpl(departmentRepository, idMapper, departmentMapper);

        DepartmentID departmentID = Mockito.mock(DepartmentID.class);
        DepartmentIDDataModel departmentIDDataModel = Mockito.mock(DepartmentIDDataModel.class);
        DepartmentDataModel departmentDataModel = Mockito.mock(DepartmentDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn("DEP");

        Optional<DepartmentDataModel> optionalDataModel = Optional.of(departmentDataModel);
        when(departmentRepository.findById("DEP")).thenReturn(optionalDataModel);
        when(departmentMapper.toDomain(departmentDataModel)).thenThrow(new RuntimeException("Mapping failed"));

        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            departmentRepositorySpringData.findDepartmentByID(departmentID);
        });
        assertEquals("Failed to map DepartmentDataModel to domain for ID: null", exception.getMessage());
    }
    @Test
    void testContainsIdentity_whenDepartmentExists() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl repository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(departmentIDDataModel.getDepartmentID()).thenReturn("DEPT123"); // or any string representing ID
        when(jpaRepo.existsById("DEPT123")).thenReturn(true);

        // Act
        boolean result = repository.containsOfIdentity(departmentID);

        // Assert
        assertTrue(result);
    }
    @Test
    void testContainsIdentity_whenDepartmentDoesNotExists() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl repository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        when(idMapper.toDataModel(departmentID)).thenReturn(departmentIDDataModel);
        when(repository.findDepartmentByID(departmentID)).thenReturn(Optional.empty());

        // Act
        boolean result = repository.containsOfIdentity(departmentID);

        // Assert
        assertFalse(result);
    }
    @Test
    void testDepartmentExists_whenDepartmentIDIsNull() {
        // Arrange
        IDepartmentRepositorySpringData jpaRepo = mock(IDepartmentRepositorySpringData.class);
        IDepartmentIDMapper idMapper = mock(IDepartmentIDMapper.class);
        IDepartmentMapper departmentMapper = mock(IDepartmentMapper.class);
        ITeacherIDMapper directorIDMapper = mock(ITeacherIDMapper.class);
        DepartmentRepositorySpringDataImpl repository = new DepartmentRepositorySpringDataImpl(jpaRepo, idMapper, departmentMapper);

        // Act
        boolean result = repository.containsOfIdentity(null);

        // Assert
        assertFalse(result);
    }
}