package PAI.repository.programmeRepo;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.factory.ProgrammeRepositoryListFactoryImpl;
import PAI.repository.studyPlanRepo.IStudyPlanDDDListFactory;
import PAI.repository.studyPlanRepo.StudyPlanDDDRepositoryImpl;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeDDDRepositoryImplTest {
    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeList = new ProgrammeDDDRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        Acronym acronym = mock(Acronym.class);
        DegreeTypeID master = mock(DegreeTypeID.class);
        Department CSE = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);
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
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeList = new ProgrammeDDDRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeTypeID master = mock(DegreeTypeID.class);
        Department CSE = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);
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
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListListFactory = mock(IProgrammeDDDRepositoryListFactory.class);

        // Criar uma lista simulada de programas
        List<ProgrammeDDD> fakeRepo = new ArrayList<>();
        when(programmeListListFactory.newProgrammeArrayList()).thenReturn(fakeRepo);
        when(programmeListListFactory.copyProgrammeArrayList(any())).thenReturn(new ArrayList<>(fakeRepo));

        // Criar instância do repositório com mocks
        ProgrammeDDDRepositoryImpl programmeList = new ProgrammeDDDRepositoryImpl(IProgrammeFactoryDouble, programmeListListFactory);

        // Mocks de VO e entidades
        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);
        ProgrammeDDD programme = mock(ProgrammeDDD.class);

        // Configurar o mock para devolver o ID quando for pedido
        when(programme.identity()).thenReturn(programmeID);

        // Adicionar o programa ao repositório "manual" usando reflexão
        Field repoField = ProgrammeDDDRepositoryImpl.class.getDeclaredField("_programmeRepo");
        repoField.setAccessible(true);
        List<ProgrammeDDD> repo = (List<ProgrammeDDD>) repoField.get(programmeList);
        repo.add(programme);

        // Configurar comportamento do método de mudança de diretor
        when(programme.newProgrammeDirector(teacherID)).thenReturn(true);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeID, teacherID);

        // Assert
        assertTrue(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeList = new ProgrammeDDDRepositoryImpl(IProgrammeFactoryDouble, programmeListListFactory);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeID, null);

        // Asssert
        assertFalse(result);
    }


    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeTypeID master = mock(DegreeTypeID.class);

        Department departmentDouble = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);

        ProgrammeDDD programme = new ProgrammeDDD(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher);

        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher))
                .thenReturn(programme);

        programmeRepo.registerProgramme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher);

        // Act
        Optional<ProgrammeDDD> result = programmeRepo.getProgrammeByName(name);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeList = new ProgrammeDDDRepositoryImpl(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeTypeID master = mock(DegreeTypeID.class);
        Department cse = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        ProgrammeDDD programme = mock(ProgrammeDDD.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        Acronym acronym = mock(Acronym.class);


        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts,quantSemesters, master, cse,  teacher))
                .thenReturn(programme);

        programmeList.registerProgramme(name, acronym, quantEcts, quantSemesters, master, cse,  teacher);

        // Act
        Optional<ProgrammeDDD> result = programmeList.getProgrammeByName(name);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnNullList() throws Exception {
        // Arrange
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        List<ProgrammeDDD> programmeListMock = List.of(mock(ProgrammeDDD.class), mock(ProgrammeDDD.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);

        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym2= mock(Acronym.class);
        QuantEcts quantityOfEcts2 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters2 = mock(QuantSemesters.class);
        DegreeTypeID master2 = mock(DegreeTypeID.class);
        Department cse2 = mock(Department.class);
        TeacherID teacher2 = mock(TeacherID.class);

        ProgrammeDDD programme1 = mock(ProgrammeDDD.class);
        ProgrammeDDD programme2 = mock(ProgrammeDDD.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(IProgrammeFactory.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        programmeRepo.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2);

        //Act
        List<ProgrammeDDD> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertNotNull(programmeList, "The returned list should not be null");
    }

    @Test
    void shouldReturnCorrectProgrammeList() throws Exception {
        // Arrange
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        List<ProgrammeDDD> programmeListMock = List.of(mock(ProgrammeDDD.class), mock(ProgrammeDDD.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        // Act
        List<ProgrammeDDD> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertEquals(programmeListMock, programmeList, "The returned list should match the copied list");
    }

    @Test
    void shouldReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);

        ProgrammeDDD programme1 = mock(ProgrammeDDD.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        //Act
        ProgrammeDDD programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme, programme1);

    }

    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);


        ProgrammeDDD programme1 = mock(ProgrammeDDD.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        //Act
        ProgrammeDDD programme = programmeRepo.getProgrammeByAcronym(acronym2);

        //Assert
        assertNull(programme);


    }

    @Test
    void shouldReturnAListOfProgrammeNamesMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeTypeID master1 = mock(DegreeTypeID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);

        when(name1.getnameWithNumbersAndSpecialChars()).thenReturn("Matemática");
        when(name2.getnameWithNumbersAndSpecialChars()).thenReturn("Computer Engineering");


        ProgrammeDDD programme1 = mock(ProgrammeDDD.class);
        ProgrammeDDD programme2 = mock(ProgrammeDDD.class);
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
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListRepoFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepositoryImpl programmeRepo = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeListRepoFactory);

        // Act
        List<NameWithNumbersAndSpecialChars> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(0, listOfProgrammeNames.size());
    }

    @Test
    void testFindProgrammeByIDFound() throws  Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListRepoFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeDDDList = new ArrayList<>();
        when(programmeListRepoFactory.newProgrammeArrayList()).thenReturn(programmeDDDList);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID master = mock(DegreeTypeID.class);
        Department cse = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);

        when(IProgrammeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher))
                .thenAnswer(invocation -> new ProgrammeDDD(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher));

        when(programmeListRepoFactory.copyProgrammeArrayList(anyList()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeListRepoFactory);

        // Act
        repository.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher);
        ProgrammeDDD createdProgramme = repository.getAllProgrammes().get(0);
        ProgrammeID programmeID = createdProgramme.getProgrammeID();

        // Act
        Optional<ProgrammeDDD> result = repository.ofIdentity(programmeID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(createdProgramme, result.get());
    }

    @Test
    void testFindByProgrammeIDNotFound() throws IllegalArgumentException {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListRepoFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeDDDList = new ArrayList<>();
        when(programmeListRepoFactory.newProgrammeArrayList()).thenReturn(programmeDDDList);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(IProgrammeFactory, programmeListRepoFactory);

        // Act
        ProgrammeID nonExistentID = new ProgrammeID(name, acronym);
        Optional<ProgrammeDDD> foundPlan = repository.ofIdentity(nonExistentID);

        // Assert
        assertFalse(foundPlan.isPresent());
    }

    @Test
    void testSaveAddsProgramme() {
        ProgrammeDDD programmeDDD1 = mock(ProgrammeDDD.class);

        IProgrammeDDDFactory factory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory listFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> ProgrammeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(ProgrammeList);

        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programmeDDD1.identity()).thenReturn(id);

        ProgrammeDDD saved = repository.save(programmeDDD1);

        assertEquals(programmeDDD1, saved);
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testFindAllReturnsAllProgrammes() {
        ProgrammeDDD programmeDDD1 = mock(ProgrammeDDD.class);
        ProgrammeDDD programmeDDD2 = mock(ProgrammeDDD.class);

        IProgrammeDDDFactory factory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory listFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeDDDList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeDDDList);

        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(factory, listFactory);

        repository.save(programmeDDD1);
        repository.save(programmeDDD2);

        //act
        List<ProgrammeDDD> all = (List<ProgrammeDDD>) repository.findAll();

        //assert
        assertEquals(2, all.size());
        assertTrue(all.contains(programmeDDD1) && all.contains(programmeDDD2));
    }

    @Test
    void testOfIdentityReturnsCorrectPlan() {
        ProgrammeDDD programmeDDD1 = mock(ProgrammeDDD.class);


        IProgrammeDDDFactory factory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory listFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programmeDDD1.identity()).thenReturn(id);

        repository.save(programmeDDD1);

        Optional<ProgrammeDDD> found = repository.ofIdentity(id);

        assertTrue(found.isPresent());
        assertEquals(programmeDDD1, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {

        IProgrammeDDDFactory factory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory listFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        Optional<ProgrammeDDD> found = repository.ofIdentity(id);

        assertFalse(found.isPresent());
    }
    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        ProgrammeDDD programmeDDD1 = mock(ProgrammeDDD.class);

        IProgrammeDDDFactory factory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory listFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);

        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);
        when(programmeDDD1.identity()).thenReturn(id);

        repository.save(programmeDDD1);

        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {

        IProgrammeDDDFactory factory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory listFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeList = new ArrayList<>();
        when(listFactory.newProgrammeArrayList()).thenReturn(programmeList);
        ProgrammeDDDRepositoryImpl repository = new ProgrammeDDDRepositoryImpl(factory, listFactory);
        ProgrammeID id = mock(ProgrammeID.class);

        assertFalse(repository.containsOfIdentity(id));
    }
}