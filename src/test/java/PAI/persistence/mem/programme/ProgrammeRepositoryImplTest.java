package PAI.persistence.mem.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
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
        //Arrange
        ProgrammeID id1 = mock(ProgrammeID.class);
        ProgrammeID id2 = mock(ProgrammeID.class);

        Programme programme1 = mock(Programme.class);
        when(programme1.identity()).thenReturn(id1);

        Programme programme2 = mock(Programme.class);
        when(programme2.identity()).thenReturn(id2);

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);

        repository.save(programme1);
        repository.save(programme2);

        // Act
        List<Programme> all = (List<Programme>) repository.findAll();

        // Assert
        assertEquals(2, all.size());
        assertTrue(all.contains(programme1));
        assertTrue(all.contains(programme2));
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

        List<ProgrammeID> result = repository.findProgrammesIdByDepartmentId(departmentID);

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
        List<ProgrammeID> result = repository.findProgrammesIdByDepartmentId(departmentID);

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
        List<ProgrammeID> result = repository.findProgrammesIdByDepartmentId(null);

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
        List<ProgrammeID> result = repository.findProgrammesIdByDepartmentId(departmentID);

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
        List<ProgrammeID> result = repository.findProgrammesIdByDepartmentId(departmentID);

        // Assert
        assertFalse(result.contains(programmeID));
        assertTrue(result.isEmpty());
    }

    @Test
    void testExistsByNameReturnsTrueWhenProgrammeExistsWithName() {
        // Arrange
        Programme programme = mock(Programme.class);
        NameWithNumbersAndSpecialChars nameDouble1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars nameDouble2 = mock(NameWithNumbersAndSpecialChars.class);

        when(programme.getProgrammeName()).thenReturn(nameDouble1);
        when(nameDouble1.getNameWithNumbersAndSpecialChars()).thenReturn("Test Programme");
        when(nameDouble2.getNameWithNumbersAndSpecialChars()).thenReturn("Test Programme");

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        repository.save(programme);

        // Act
        boolean result = repository.existsByName(nameDouble2);

        // Assert
        assertTrue(result);
    }

    @Test
    void testExistsByNameReturnsFalseWhenProgrammeWithGivenNameDoesNotExist() {
        // Arrange
        Programme programme = mock(Programme.class);
        NameWithNumbersAndSpecialChars nameDouble1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars nameDouble2 = mock(NameWithNumbersAndSpecialChars.class);

        when(programme.getProgrammeName()).thenReturn(nameDouble1);
        when(nameDouble1.getNameWithNumbersAndSpecialChars()).thenReturn("ProgrammeName1");
        when(nameDouble2.getNameWithNumbersAndSpecialChars()).thenReturn("ProgrammeName2");

        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        repository.save(programme);

        // Act
        boolean result = repository.existsByName(nameDouble2);

        // Assert
        assertFalse(result);
    }

    @Test
    void testExistsByAcronymReturnsTrueWhenProgrammeExistsWithAcronym() {
        // Arrange
        Programme programme = mock(Programme.class);
        Acronym acronymDouble1 = mock(Acronym.class);
        Acronym acronymDouble2 = mock(Acronym.class);

        when(programme.getAcronym()).thenReturn(acronymDouble1);
        when(acronymDouble1.getAcronym()).thenReturn("TP");
        when(acronymDouble2.getAcronym()).thenReturn("TP");


        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        repository.save(programme);

        // Act
        boolean result = repository.existsByAcronym(acronymDouble2);

        // Act & Assert
        assertTrue(result);
    }

    @Test
    void testExistsByAcronymReturnsFalseWhenProgrammeWithGivenAcronymDoesNotExist() {
        // Arrange
        Programme programme = mock(Programme.class);
        Acronym acronymDouble1 = mock(Acronym.class);
        Acronym acronymDouble2 = mock(Acronym.class);

        when(programme.getAcronym()).thenReturn(acronymDouble1);
        when(acronymDouble1.getAcronym()).thenReturn("TP");
        when(acronymDouble2.getAcronym()).thenReturn("AB");


        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(listFactory);
        repository.save(programme);

        // Act
        boolean result = repository.existsByAcronym(acronymDouble2);

        // Act & Assert
        assertFalse(result);
    }
}