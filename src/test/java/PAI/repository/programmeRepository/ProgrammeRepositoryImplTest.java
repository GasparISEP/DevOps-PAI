package PAI.repository.programmeRepository;

import PAI.VOs.DepartmentID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.domain.programme.Programme;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRepositoryImplTest {

    @Test
    void testSaveAddsProgramme() {
        Programme programme1 = mock(Programme.class);

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> ProgrammeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(ProgrammeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        Programme saved = repository.save(programme1);

        assertEquals(programme1, saved);
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testFindAllReturnsAllProgrammes() {
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);

        repository.save(programme1);
        repository.save(programme2);

        //act
        List<Programme> all = (List<Programme>) repository.findAll();

        //assert
        assertEquals(2, all.size());
        assertTrue(all.contains(programme1) && all.contains(programme2));
    }

    @Test
    void testOfIdentityReturnsCorrectPlan() {
        Programme programme1 = mock(Programme.class);


        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        repository.save(programme1);

        Optional<Programme> found = repository.ofIdentity(id);

        assertTrue(found.isPresent());
        assertEquals(programme1, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {


        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        Optional<Programme> found = repository.ofIdentity(id);

        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        Programme programme1 = mock(Programme.class);

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        repository.save(programme1);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        boolean result = repository.containsOfIdentity(id);

        assertFalse(result);
    }


    @Test
    void shouldReturnListOfProgrammesIfDepartmentIsValidAndHasProgrammesAssociated() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory programmeListFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(programmeListFactory);
        DepartmentID departmentID = mock(DepartmentID.class);
        Programme programme = mock(Programme.class);
        when(programme.isInDepartment(departmentID)).thenReturn(true);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(programme.identity()).thenReturn(programmeID);

        repository.save(programme);
        // Act

        List<ProgrammeID> result = repository.findProgrammeByDepartment(departmentID);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertTrue(result.contains(programmeID));
    }

    @Test
    void shouldReturnListWith2ProgrammeBelongingToDepartment() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory programmeListFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(programmeListFactory);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        DepartmentID departmentID = mock(DepartmentID.class);
        Programme programme = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        when(programme.isInDepartment(departmentID)).thenReturn(true);
        when(programme2.isInDepartment(departmentID)).thenReturn(true);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);

        when(programme.identity()).thenReturn(programmeID);
        when(programme2.identity()).thenReturn(programmeID2);

        repository.save(programme);
        repository.save(programme2);
        // Act
        List<ProgrammeID> result = repository.findProgrammeByDepartment(departmentID);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(programmeID) && result.contains(programmeID2));
    }

    @Test
    void shouldReturnEmptyListIfDepartmentIsNull() throws Exception {

        // Arrange
        IProgrammeRepositoryListFactory programmeListFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(programmeListFactory);
        DepartmentID departmentID = mock(DepartmentID.class);
        Programme programme = mock(Programme.class);
        when(programme.isInDepartment(departmentID)).thenReturn(true);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        when(programme.identity()).thenReturn(programmeID);


repository.save(programme);
        // Act
        List<ProgrammeID> result = repository.findProgrammeByDepartment(null);

        // Assert
        assertFalse(result.contains(programmeID));
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldReturnEmptyListIfProgrammeDoesNotBelongToDepartment() throws Exception {

        // Arrange
        IProgrammeRepositoryListFactory programmeListFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(programmeListFactory);

        DepartmentID departmentID = mock(DepartmentID.class);
        Programme programme = mock(Programme.class);
        when(programme.isInDepartment(departmentID)).thenReturn(false);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        when(programme.identity()).thenReturn(programmeID);


repository.save(programme);
        // Act
        List<ProgrammeID> result = repository.findProgrammeByDepartment(departmentID);

        // Assert
        assertFalse(result.contains(programmeID));
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldReturnEmptyListIfNoProgrammesAreRegisteredInRepo() throws Exception {

        // Arrange
        IProgrammeRepositoryListFactory programmeListFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(programmeListFactory);
        DepartmentID departmentID = mock(DepartmentID.class);
        Programme programme = mock(Programme.class);
        when(programme.isInDepartment(departmentID)).thenReturn(false);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        when(programme.identity()).thenReturn(programmeID);
        // Act
        List<ProgrammeID> result = repository.findProgrammeByDepartment(departmentID);

        // Assert
        assertFalse(result.contains(programmeID));
        assertTrue(result.isEmpty());
    }
}