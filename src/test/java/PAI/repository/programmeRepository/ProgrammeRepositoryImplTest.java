package PAI.repository.programmeRepository;

import PAI.VOs.*;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepositoryImplTest {
    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeList = new ProgrammeRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        Acronym acronym = mock(Acronym.class);
        DegreeTypeID master = mock(DegreeTypeID.class);
        DepartmentID CSE = mock(DepartmentID.class);
        TeacherID teacher = mock(TeacherID.class);
        Programme programmeDouble = mock(Programme.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts, quantSemesters, master, CSE,  teacher)).thenReturn(programmeDouble);
        // Act
        boolean result = programmeList.registerProgramme(name, acronym, quantEcts, quantSemesters, master, CSE,  teacher);
        // Asssert
        assertTrue(result);
    }

    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeList = new ProgrammeRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeTypeID master = mock(DegreeTypeID.class);
        DepartmentID CSE = mock(DepartmentID.class);
        TeacherID teacher = mock(TeacherID.class);
        Programme programmeDouble = mock(Programme.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts, quantSemesters, master, CSE,  teacher)).thenReturn(programmeDouble);

        // Act
        programmeList.registerProgramme(name, acronym, quantEcts, quantSemesters, master, CSE,  teacher);
        boolean result = programmeList.registerProgramme(name, acronym, quantEcts, quantSemesters, master, CSE,  teacher);

        // Assert
        assertFalse(result);
    }

    @Test
    void changeProgrammeDirectorReturnsTrue() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListListFactory = mock(IProgrammeRepositoryListFactory.class);

        List<Programme> fakeRepo = new ArrayList<>();
        when(programmeListListFactory.newProgrammeArrayList()).thenReturn(fakeRepo);
        when(programmeListListFactory.copyProgrammeArrayList(any())).thenReturn(new ArrayList<>(fakeRepo));

        ProgrammeRepositoryImpl programmeList = new ProgrammeRepositoryImpl(IProgrammeFactoryDouble, programmeListListFactory);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Programme programme = mock(Programme.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        when(IProgrammeFactoryDouble.registerProgramme(name,acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID)).thenReturn(programme);
        programmeList.registerProgramme(name,acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        when(programme.identity()).thenReturn(programmeID);

        when(programme.newProgrammeDirector(teacherID)).thenReturn(true);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeID, teacherID);

        // Assert
        assertTrue(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeList = new ProgrammeRepositoryImpl(IProgrammeFactoryDouble, programmeListListFactory);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeID, null);

        // Asssert
        assertFalse(result);
    }


    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeTypeID master = mock(DegreeTypeID.class);

        DepartmentID departmentDouble = mock(DepartmentID.class);
        TeacherID teacher = mock(TeacherID.class);
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);

        Programme programme = new Programme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher);

        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher))
                .thenReturn(programme);

        programmeRepo.registerProgramme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher);

        // Act
        Optional<Programme> result = programmeRepo.getProgrammeByName(name);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeList = new ProgrammeRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeTypeID master = mock(DegreeTypeID.class);
        DepartmentID cse = mock(DepartmentID.class);
        TeacherID teacher = mock(TeacherID.class);
        Programme programme = mock(Programme.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        Acronym acronym = mock(Acronym.class);


        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts,quantSemesters, master, cse,  teacher))
                .thenReturn(programme);

        programmeList.registerProgramme(name, acronym, quantEcts, quantSemesters, master, cse,  teacher);

        // Act
        Optional<Programme> result = programmeList.getProgrammeByName(name);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnNullList() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        DepartmentID cse2 = mock(DepartmentID.class);
        TeacherID teacher1 = mock(TeacherID.class);

        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym2= mock(Acronym.class);
        QuantEcts quantityOfEcts2 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters2 = mock(QuantSemesters.class);
        DegreeTypeID master2 = mock(DegreeTypeID.class);
        DepartmentID cse1 = mock(DepartmentID.class);
        TeacherID teacher2 = mock(TeacherID.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(IProgrammeFactory.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        programmeRepo.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2);

        //Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertNotNull(programmeList, "The returned list should not be null");
    }

    @Test
    void shouldReturnCorrectProgrammeList() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        List<Programme> programmeListMock = List.of(mock(Programme.class), mock(Programme.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        // Act
        List<Programme> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertEquals(programmeListMock, programmeList, "The returned list should match the copied list");
    }

    @Test
    void shouldReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        DepartmentID cse1 = mock(DepartmentID.class);
        TeacherID teacher1 = mock(TeacherID.class);

        Programme programme1 = mock(Programme.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme, programme1);

    }

    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        DepartmentID cse1 = mock(DepartmentID.class);
        TeacherID teacher1 = mock(TeacherID.class);


        Programme programme1 = mock(Programme.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1,  teacher1);

        //Act
        Programme programme = programmeRepo.getProgrammeByAcronym(acronym2);

        //Assert
        assertNull(programme);


    }

    @Test
    void shouldReturnAListOfProgrammeNamesMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeRepoListFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        DepartmentID cse1 = mock(DepartmentID.class);
        TeacherID teacher1 = mock(TeacherID.class);

        when(name1.getnameWithNumbersAndSpecialChars()).thenReturn("Matemática");
        when(name2.getnameWithNumbersAndSpecialChars()).thenReturn("Computer Engineering");


        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        when(programme1.getProgrammeName()).thenReturn(name1);
        when(programme2.getProgrammeName()).thenReturn(name2);
        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(IProgrammeFactory.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1);
        programmeRepo.registerProgramme(name2, acronym1, quantityOfEcts1, quantityOfSemesters1, master1, cse1, teacher1);

        when(programme1.getProgrammeName()).thenReturn(name1);
        when(programme2.getProgrammeName()).thenReturn(name2);

        // Act
        List<NameWithNumbersAndSpecialChars> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(2, listOfProgrammeNames.size());
        assertTrue(listOfProgrammeNames.contains(name1));
        assertTrue(listOfProgrammeNames.contains(name2));
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammeNamesIfThereAreNoProgrammesInTheProgrammeListMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListRepoFactory = mock(IProgrammeRepositoryListFactory.class);
        ProgrammeRepositoryImpl programmeRepo = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeListRepoFactory);

        // Act
        List<NameWithNumbersAndSpecialChars> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(0, listOfProgrammeNames.size());
    }

    @Test
    void testFindProgrammeByIDFound() throws  Exception {
        // Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListRepoFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListRepoFactory.newProgrammeArrayList()).thenReturn(programmeList);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID master = mock(DegreeTypeID.class);
        DepartmentID cse = mock(DepartmentID.class);
        TeacherID teacher = mock(TeacherID.class);

        when(IProgrammeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher))
                .thenAnswer(invocation -> new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher));

        when(programmeListRepoFactory.copyProgrammeArrayList(anyList()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeListRepoFactory);

        // Act
        repository.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher);
        Programme createdProgramme = repository.getAllProgrammes().get(0);
        ProgrammeID programmeID = createdProgramme.getProgrammeID();

        // Act
        Optional<Programme> result = repository.ofIdentity(programmeID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(createdProgramme, result.get());
    }

    @Test
    void testFindByProgrammeIDNotFound() throws IllegalArgumentException {
        // Arrange
        IProgrammeFactory IProgrammeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListRepoFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListRepoFactory.newProgrammeArrayList()).thenReturn(programmeList);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(IProgrammeFactory, programmeListRepoFactory);

        // Act
        ProgrammeID nonExistentID = new ProgrammeID(name, acronym);
        Optional<Programme> foundPlan = repository.ofIdentity(nonExistentID);

        // Assert
        assertFalse(foundPlan.isPresent());
    }

    @Test
    void testSaveAddsProgramme() {
        Programme programme1 = mock(Programme.class);

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> ProgrammeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(ProgrammeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
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

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);

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


        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        repository.save(programme1);

        Optional<Programme> found = repository.ofIdentity(id);

        assertTrue(found.isPresent());
        assertEquals(programme1, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        Optional<Programme> found = repository.ofIdentity(id);

        assertFalse(found.isPresent());
    }
    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        Programme programme1 = mock(Programme.class);

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programme1.identity()).thenReturn(id);

        repository.save(programme1);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        boolean result = repository.containsOfIdentity(id);

        assertFalse(result);
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeExists() throws Exception {
        //Arrange
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);

        Programme programme = mock(Programme.class);
        ProgrammeID id = mock(ProgrammeID.class);
        ProgrammeID storedID = mock(ProgrammeID.class);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);



        when(factory.registerProgramme(name,acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID)).thenReturn(programme);
        when(programme.identity()).thenReturn(storedID);

        repository.registerProgramme(name,acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        //Act
        Optional<Programme> result = repository.ofIdentity(id);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnListOfProgrammesForProgrammesByDegreeTypeIDMethod_MatchFound() throws Exception {
        //Arrange
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        Programme programme = mock(Programme.class);
        when(programme.getDegreeTypeID()).thenReturn(degreeTypeID);

        List<Programme> ListWithProgramme = Arrays.asList(programme);
        when(listFactory.newProgrammeArrayList()).thenReturn(ListWithProgramme);

            //SUT
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(factory, listFactory);

        //Act
        List<Programme> result = programmeRepository.getProgrammesByDegreeTypeID(degreeTypeID);

        //Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(programme));
    }

    @Test
    void shouldReturnEmptyListForProgrammesByDegreeTypeIDMethod_NoMatch() throws Exception {
        //Arrange
        DegreeTypeID searchedID = mock(DegreeTypeID.class);
        DegreeTypeID otherID = mock(DegreeTypeID.class);
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        Programme programme = mock(Programme.class);
        when(programme.getDegreeTypeID()).thenReturn(otherID);

            //SUT
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(factory, listFactory);

        //Act
        List<Programme> result = programmeRepository.getProgrammesByDegreeTypeID(searchedID);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListForProgrammesByDegreeTypeIDMethod_EmptyRepo() throws Exception {
        //Arrange
        DegreeTypeID anyID = mock(DegreeTypeID.class);

        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);

            //SUT
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(factory, listFactory);

        //Act
        List<Programme> result = programmeRepository.getProgrammesByDegreeTypeID(anyID);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindProgrammeIdByTeacherReturnsCorrectIdWhenProgrammeExists() {
        // Arrange
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);


        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        Programme programme = mock(Programme.class);
        ProgrammeID id = mock(ProgrammeID.class);
        repository.save(programme);
        when(programme.identity()).thenReturn(id);
        when(programme.sameAs(programme)).thenReturn(true);

        // Act
        Optional<ProgrammeID> result = repository.findProgrammeIdByProgramme(programme);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void testFindProgrammeIdByTeacherReturnsCorrectIdWhenProgrammeDoesNotExist() {
        // Arrange
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory listFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);


        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(factory, listFactory);
        Programme programme = mock(Programme.class);
        Programme programme1 = mock(Programme.class);
        ProgrammeID id = mock(ProgrammeID.class);
        repository.save(programme);
        when(programme.identity()).thenReturn(id);

        // Act
        Optional<ProgrammeID> result = repository.findProgrammeIdByProgramme(programme1);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnIdWhenProgrammeDoesntExistsInList() throws Exception {
        // Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepositoryListFactory programmeListFactory = mock(IProgrammeRepositoryListFactory.class);
        List<Programme> programmeList = new ArrayList<>();
        when(programmeListFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeRepositoryImpl repository = new ProgrammeRepositoryImpl(programmeFactory, programmeListFactory);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Programme programme = mock(Programme.class);

        ProgrammeID programmeID = mock(ProgrammeID.class);

        when(programme.identity()).thenReturn(programmeID);


        when(programmeFactory.registerProgramme(name,acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID)).thenReturn(programme);
        when(programme.sameAs(any())).thenReturn(false);

        // Act
        repository.registerProgramme(name,acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        Optional<ProgrammeID> result = repository.findProgrammeIdByProgramme(programme);

        // Assert
        assertTrue(result.isEmpty());
    }
}