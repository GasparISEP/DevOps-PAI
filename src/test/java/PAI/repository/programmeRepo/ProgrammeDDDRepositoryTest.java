package PAI.repository.programmeRepo;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeDDDRepositoryTest {
    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeList = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        Acronym acronym = mock(Acronym.class);
        DegreeType_ID master = mock(DegreeType_ID.class);
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
        ProgrammeDDDRepository programmeList = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType_ID master = mock(DegreeType_ID.class);
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

        // Asssert
        assertFalse(result);
    }


    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Professor");
        Acronym acronym = new Acronym("MEI");
        QuantEcts qtyEcts = new QuantEcts(6);
        QuantSemesters qtySemesters = new QuantSemesters(6);
        DegreeType_ID degreeTypeID = mock(DegreeType_ID.class);
        Department department1 = new Department("ABC","Abc");
        TeacherID programmeDirectorID = mock(TeacherID.class);

        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDD programme = new ProgrammeDDD(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);
        TeacherID teacher2 = mock(TeacherID.class);
        ProgrammeDDDRepository programmeList = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeRepoListFactory);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programme, teacher2);
        // Assert
        assertTrue(result);
        assertEquals(programme.getProgrammeDirectorID(), teacher2);
    }

    @Test
    void shouldNotChangeProgrammedDirectorIfNewDirectorIsNull() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeList = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        TeacherID teacher1 = null;
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);
        when(programmeDouble.newProgrammeDirector(teacher1)).thenReturn(false);
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, teacher1);

        // Asssert
        assertFalse(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeList = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeListListFactory);
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, null);

        // Asssert
        assertFalse(result);
    }



    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactoryDouble = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeRepoListFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType_ID master = mock(DegreeType_ID.class);

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
        ProgrammeDDDRepository programmeList = new ProgrammeDDDRepository(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType_ID master = mock(DegreeType_ID.class);
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
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactory, programmeRepoListFactory);

        List<ProgrammeDDD> programmeListMock = List.of(mock(ProgrammeDDD.class), mock(ProgrammeDDD.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeType_ID master1 = mock(DegreeType_ID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);

        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym2= mock(Acronym.class);
        QuantEcts quantityOfEcts2 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters2 = mock(QuantSemesters.class);
        DegreeType_ID master2 = mock(DegreeType_ID.class);
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
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactory, programmeRepoListFactory);

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
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeType_ID master1 = mock(DegreeType_ID.class);
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
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeType_ID master1 = mock(DegreeType_ID.class);
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
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeType_ID master1 = mock(DegreeType_ID.class);
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
        ProgrammeDDDRepository programmeRepo = new ProgrammeDDDRepository(IProgrammeFactory, programmeListRepoFactory);

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
        DegreeType_ID master = mock(DegreeType_ID.class);
        Department cse = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);

        when(IProgrammeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher))
                .thenAnswer(invocation -> new ProgrammeDDD(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher));

        when(programmeListRepoFactory.copyProgrammeArrayList(anyList()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProgrammeDDDRepository repository = new ProgrammeDDDRepository(IProgrammeFactory, programmeListRepoFactory);

        // Act
        repository.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, master, cse, teacher);
        ProgrammeDDD createdProgramme = repository.getAllProgrammes().get(0);
        ProgrammeID programmeID = createdProgramme.getProgrammeID();

        // Act
        Optional<ProgrammeDDD> result = repository.findProgrammeByID(programmeID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(createdProgramme, result.get());
    }

    @Test
    void testFindByProgrammeIDNotFound() throws Exception {
        // Arrange
        IProgrammeDDDFactory IProgrammeFactory = mock(IProgrammeDDDFactory.class);
        IProgrammeDDDRepositoryListFactory programmeListRepoFactory = mock(IProgrammeDDDRepositoryListFactory.class);
        List<ProgrammeDDD> programmeDDDList = new ArrayList<>();
        when(programmeListRepoFactory.newProgrammeArrayList()).thenReturn(programmeDDDList);

        ProgrammeDDDRepository repository = new ProgrammeDDDRepository(IProgrammeFactory, programmeListRepoFactory);

        // Act
        ProgrammeID nonExistentID = new ProgrammeID();
        Optional<ProgrammeDDD> foundPlan = repository.findProgrammeByID(nonExistentID);

        // Assert
        assertFalse(foundPlan.isPresent());
    }
}