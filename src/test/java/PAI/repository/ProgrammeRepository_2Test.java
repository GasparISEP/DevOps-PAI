package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeRepository_2Test {
    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeList = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeRepoListFactory);
        Acronym acronym = mock(Acronym.class);
        DegreeType_ID master = mock(DegreeType_ID.class);
        Department CSE = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        Programme_2 programmeDouble = mock(Programme_2.class);
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
        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeList = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType_ID master = mock(DegreeType_ID.class);
        Department CSE = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        Programme_2 programmeDouble = mock(Programme_2.class);
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

        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        Programme_2 programme = new Programme_2(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);
        TeacherID teacher2 = mock(TeacherID.class);
        ProgrammeRepository_2 programmeList = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeRepoListFactory);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programme, teacher2);
        // Assert
        assertTrue(result);
        assertEquals(programme.getProgrammeDirectorID(), teacher2);
    }

    @Test
    void shouldNotChangeProgrammedDirectorIfNewDirectorIsNull() throws Exception {
        // Arrange
        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeList = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeRepoListFactory);
        TeacherID teacher1 = null;
        Programme_2 programmeDouble = mock(Programme_2.class);
        when(programmeDouble.newProgrammeDirector(teacher1)).thenReturn(false);
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, teacher1);

        // Asssert
        assertFalse(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeListListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeList = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeListListFactory);
        Programme_2 programmeDouble = mock(Programme_2.class);

        // Act
        boolean result = programmeList.changeProgrammeDirector(programmeDouble, null);

        // Asssert
        assertFalse(result);
    }



    @Test
    void shouldReturnProgrammeWithTheRequiredName() throws Exception {
        // Arrange
        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType_ID master = mock(DegreeType_ID.class);

        Department departmentDouble = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);

        Programme_2 programme = new Programme_2(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher);

        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher))
                .thenReturn(programme);

        programmeRepo.registerProgramme(name, acronym, quantEcts, quantSemesters, master, departmentDouble,  teacher);

        // Act
        Optional<Programme_2> result = programmeRepo.getProgrammeByName(name);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception {
        // Arrange
        IProgrammeFactory_2 IProgrammeFactoryDouble = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeList = new ProgrammeRepository_2(IProgrammeFactoryDouble, programmeRepoListFactory);
        DegreeType_ID master = mock(DegreeType_ID.class);
        Department cse = mock(Department.class);
        TeacherID teacher = mock(TeacherID.class);
        Programme_2 programme = mock(Programme_2.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts quantEcts = mock(QuantEcts.class);
        QuantSemesters quantSemesters = mock(QuantSemesters.class);
        Acronym acronym = mock(Acronym.class);


        when(IProgrammeFactoryDouble.registerProgramme(name, acronym, quantEcts,quantSemesters, master, cse,  teacher))
                .thenReturn(programme);

        programmeList.registerProgramme(name, acronym, quantEcts, quantSemesters, master, cse,  teacher);

        // Act
        Optional<Programme_2> result = programmeList.getProgrammeByName(name);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnNullList() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        IProgrammeFactory_2 IProgrammeFactory = mock(IProgrammeFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactory, programmeRepoListFactory);

        List<Programme_2> programmeListMock = List.of(mock(Programme_2.class), mock(Programme_2.class));
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

        Programme_2 programme1 = mock(Programme_2.class);
        Programme_2 programme2 = mock(Programme_2.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(IProgrammeFactory.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2)).thenReturn(programme2);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        programmeRepo.registerProgramme(name2, acronym2, quantityOfEcts2,quantityOfSemesters2, master2, cse2, teacher2);

        //Act
        List<Programme_2> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertNotNull(programmeList, "The returned list should not be null");
    }

    @Test
    void shouldReturnCorrectProgrammeList() throws Exception {
        // Arrange
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        IProgrammeFactory_2 IProgrammeFactory = mock(IProgrammeFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactory, programmeRepoListFactory);

        List<Programme_2> programmeListMock = List.of(mock(Programme_2.class), mock(Programme_2.class));
        when(programmeRepoListFactory.copyProgrammeArrayList(any())).thenReturn(programmeListMock);

        // Act
        List<Programme_2> programmeList = programmeRepo.getAllProgrammes();

        // Assert
        assertEquals(programmeListMock, programmeList, "The returned list should match the copied list");
    }

    @Test
    void shouldReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeFactory_2 IProgrammeFactory = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeType_ID master1 = mock(DegreeType_ID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);

        Programme_2 programme1 = mock(Programme_2.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        programmeRepo.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1);
        //Act
        Programme_2 programme = programmeRepo.getProgrammeByAcronym(acronym1);

        //Assert
        assertNotNull(programme);
        assertEquals(programme, programme1);

    }

    @Test
    void shouldNotReturnAProgrammeByAcronym() throws Exception {

        //Arrange
        IProgrammeFactory_2 IProgrammeFactory = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactory, programmeRepoListFactory);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts quantityOfEcts1 = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters1 = mock(QuantSemesters.class);
        DegreeType_ID master1 = mock(DegreeType_ID.class);
        Department cse1 = mock(Department.class);
        TeacherID teacher1 = mock(TeacherID.class);


        Programme_2 programme1 = mock(Programme_2.class);

        when(IProgrammeFactory.registerProgramme(name1, acronym1, quantityOfEcts1,quantityOfSemesters1, master1, cse1, teacher1)).thenReturn(programme1);
        when(programme1.getAcronym()).thenReturn(acronym1);

        //Act
        Programme_2 programme = programmeRepo.getProgrammeByAcronym(acronym2);

        //Assert
        assertNull(programme);


    }

    @Test
    void shouldReturnAListOfProgrammeNamesMock() throws Exception {
        // SUT = ProgrammeList - getAllProgrammeNames
        // Arrange
        IProgrammeFactory_2 IProgrammeFactory = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeRepoListFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactory, programmeRepoListFactory);

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


        Programme_2 programme1 = mock(Programme_2.class);
        Programme_2 programme2 = mock(Programme_2.class);
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
        IProgrammeFactory_2 IProgrammeFactory = mock(IProgrammeFactory_2.class);
        IProgrammeRepositoryListFactory_2 programmeListRepoFactory = mock(IProgrammeRepositoryListFactory_2.class);
        ProgrammeRepository_2 programmeRepo = new ProgrammeRepository_2(IProgrammeFactory, programmeListRepoFactory);

        // Act
        List<NameWithNumbersAndSpecialChars> listOfProgrammeNames = programmeRepo.getAllProgrammeNames();

        // Assert
        assertEquals(0, listOfProgrammeNames.size());
    }
}