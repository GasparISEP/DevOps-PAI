package PAI.repository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.domain.SchoolYear;
import PAI.factory.ProgrammeEditionFactoryImpl;
import PAI.factory.ProgrammeEditionListFactoryImpl;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryDDDImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionRepositoryTest {

//    @Test
//    void shouldReturnTrueIfProgrammeEditionIsCreatedSuccessfully() throws Exception {
//        // arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//
//        // act
//        boolean result = per1.createProgrammeEdition(p1, sy1);
//
//        // assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnTrueIfTheAddedProgrammeEditionHasTheSameProgrammeAsAnExistingOneButADifferentYear() throws Exception {
//        // arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
//        per1.createProgrammeEdition(p1, sy1);
//
//        // act
//        boolean result = per1.createProgrammeEdition(p1, sy2);
//
//        // assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnTrueIfTheAddedProgrammeEditionHasTheSameYearAsAnExistingOneButADifferentProgramme() throws Exception {
//        // arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        Programme p2 = new Programme("LEI", "LEI", 20, 6, master, CSE, teacher);
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//
//        per1.createProgrammeEdition(p1, sy1);
//
//        // act
//        boolean result = per1.createProgrammeEdition(p2, sy1);
//
//        // assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfProgrammeEditionAlreadyRegistered() throws Exception {
//        // arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        per1.createProgrammeEdition(p1, sy1);
//
//        // act
//        boolean result = per1.createProgrammeEdition(p1, sy1);
//
//        // assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfProgrammeIsNull() throws Exception {
//        // arrange
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        // act
//        boolean result = per1.createProgrammeEdition(null, sy1);
//
//        // assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfGivenSchoolYearIsNull() throws Exception {
//        // arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        // act
//        boolean result = per1.createProgrammeEdition(p1, null);
//
//        // assert
//        assertFalse(result);
//    }
//
//    //US17
//    @Test
//    void shouldFindProgrammeEditionBySchoolYearAndProgramme() throws Exception {
//        // Arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
//                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository repository = new ProgrammeEditionRepository(programmeEditionFactory);
//
//        repository.createProgrammeEdition(programme, schoolYear);
//
//        // Act
//        Optional<ProgrammeEdition> result = repository.findProgrammeEditionBySchoolYearAndProgramme(programme, schoolYear);
//
//        // Assert
//        assertTrue(result.isPresent());
//
//    }
//
//    //US17
//    @Test
//    void shouldReturnEmptyIfProgrammeEditionNotFound() throws Exception {
//        // Arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
//                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        Programme programme1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear schoolYear1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//
//        Programme programme2 = new Programme("Electrical Engineering", "EE", 20, 6, master, CSE, teacher);
//        SchoolYear schoolYear2 = new SchoolYear("Ano letivo de", "01-01-2025", "31-12-2025");
//
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository repository = new ProgrammeEditionRepository(programmeEditionFactory);
//
//        repository.createProgrammeEdition(programme1, schoolYear1);
//
//        // Act
//        Optional<ProgrammeEdition> result = repository.findProgrammeEditionBySchoolYearAndProgramme(programme2, schoolYear2);
//
//        // Assert
//        assertFalse(result.isPresent());
//    }
//
//    @Test
//    void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty() {
//        //Arrange
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        //Act
//        List<ProgrammeEdition> allEditions = per1.getAllProgrammeEditions();
//        //Assert
//        assertNotNull(allEditions);
//    }
//
//    @Test
//    void shouldReturnSizeofListOfProgrammeEditionsForGetAllProgrammeEditionsMethod() throws Exception {
//        //Arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
//        per1.createProgrammeEdition(p1,sy1);
//        per1.createProgrammeEdition(p1,sy2);
//        //Act
//        List<ProgrammeEdition> allEditions = per1.getAllProgrammeEditions();
//        //Assert
//        assertEquals(2,allEditions.size());
//    }
//
//    @Test
//    void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition() throws Exception {
//        //Arrange
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository per1 = new ProgrammeEditionRepository(programmeEditionFactory);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
//        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
//        per1.createProgrammeEdition(p1,sy1);
//        List<ProgrammeEdition> allEditions = per1.getAllProgrammeEditions();
//        //Act + Assert
//        assertTrue(allEditions.contains(pe1));
//    }
//
//    @Test
//    void shouldFindProgrammeInProgrammeEditionSuccessfuly() throws Exception {
//        //Arrange
//        IProgrammeEditionFactoryImpl programmeEditionFactory = new IProgrammeEditionFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
//        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        DegreeType master = new DegreeType("Master", 240);
//        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
//                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
//        ProgrammeEdition programmeEdition = new ProgrammeEdition(p1, sy1);
//        //Act
//        Programme result = programmeEditionRepository.findProgrammeInProgrammeEdition(programmeEdition);
//        //Assert
//        assertNotNull(result);
//        assertEquals(p1, result);
//    }



//                                            ISOLATED UNIT TESTS

//
//    @Test
//    void shouldReturnValidProgrammeEditionRepositoryMock() {
//        // SUT = ProgrammeEditionRepository
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//
//        // Act
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        // Assert
//        assertNotNull(programmeEditionRepository);
//    }
//
//    @Test
//    void shouldReturnTrueIfProgrammeEditionIsCreatedMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - createProgrammeEdition
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenReturn(programmeEditionMock);
//
//        // Act
//        boolean result = programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//        // Assert
//        assertTrue(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfProgrammeEditionIsAlreadyExistsInTheRepositoryMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - createProgrammeEdition
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenReturn(programmeEditionMock);
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//        // Act
//        boolean result = programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//        // Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfProgrammeIsInvalidMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - createProgrammeEdition
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = null;
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenThrow(new Exception("Programme cannot be Null"));
//
//        // Act
//        boolean result = programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//        // Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfSchoolYearIsInvalidMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - createProgrammeEdition
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        SchoolYear schoolYearMock = null;
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenThrow(new Exception("Programme cannot be Null"));
//
//        // Act
//        boolean result = programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//        // Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnAnOptionalWithProgrammeEditionPresentMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - findProgrammeEditionBySchoolYearAndProgramme
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepositoryDDDImpl programmeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        ProgrammeID programmeMockId = mock(ProgrammeID.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        SchoolYearID schoolYearMockId = mock(SchoolYearID.class);
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//        ProgrammeEditionID programmeEditionMockId = mock(ProgrammeEditionID.class);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenReturn(programmeEditionMock);
//        when(programmeEditionListFactoryImplMock.createProgrammeEditionArrayList()).thenReturn(new ArrayList<>());
//
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//
//        List<ProgrammeEdition> programmeEditions = programmeEditionRepository.getAllProgrammeEditions();
//        programmeEditions.add(programmeEditionMock);
//        when(programmeEditionMock.findProgrammeInProgrammeEdition()).thenReturn(programmeMock);
//        when(programmeEditionMock.findSchoolYearInProgrammeEdition()).thenReturn(schoolYearMock);
//
//        when(programmeEditionMock.findProgrammeInProgrammeEdition().).thenReturn(programmeMockId);
//        when(programmeEditionMock.findSchoolYearInProgrammeEdition().identity()).thenReturn(schoolYearMockId);
//        when(programmeEditionMock.identity()).thenReturn(programmeEditionMockId);
//
//        // Act
//        Optional<ProgrammeEditionID> result = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programmeMockId, schoolYearMockId);
//
//        // Assert
//        assertTrue(result.isPresent());
//    }
//
//    @Test
//    void shouldReturnAnEmptyOptionalIfNoProgrammeEditionHasTheSchoolYearSearchedMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - findProgrammeEditionBySchoolYearAndProgramme
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        ProgrammeID programmeMockId = mock(ProgrammeID.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        SchoolYearID schoolYearMockId = mock(SchoolYearID.class);
//        SchoolYearID schoolYearMock2Id = mock(SchoolYearID.class);
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//        ProgrammeEditionID programmeEditionMockId = mock(ProgrammeEditionID.class);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenReturn(programmeEditionMock);
//        when(programmeEditionListFactoryImplMock.createProgrammeEditionArrayList()).thenReturn(new ArrayList<>());
//
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//
//        List<ProgrammeEdition> programmeEditions = programmeEditionRepository.getAllProgrammeEditions();
//        programmeEditions.add(programmeEditionMock);
//        when(programmeEditionMock.findProgrammeInProgrammeEdition()).thenReturn(programmeMock);
//        when(programmeEditionMock.findSchoolYearInProgrammeEdition()).thenReturn(schoolYearMock);
//
//        when(programmeEditionMock.findProgrammeInProgrammeEdition().identity()).thenReturn(programmeMockId);
//        when(programmeEditionMock.findSchoolYearInProgrammeEdition().identity()).thenReturn(schoolYearMockId);
//        when(programmeEditionMock.identity()).thenReturn(programmeEditionMockId);
//
//        // Act
//        Optional <ProgrammeEditionID> result = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programmeMockId, schoolYearMock2Id);
//
//        // Assert
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void shouldReturnAnEmptyOptionalIfNoProgrammeEditionHasTheProgrammeSearchedMock() throws Exception {
//        // SUT = ProgrammeEditionRepository - findProgrammeEditionBySchoolYearAndProgramme
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        ProgrammeID programmeMock2Id = mock(ProgrammeID.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        SchoolYearID schoolYearMockId = mock(SchoolYearID.class);
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//
//        List<ProgrammeEdition> programmeEditions = programmeEditionRepository.getAllProgrammeEditions();
//        programmeEditions.add(programmeEditionMock);
//        when(programmeEditionMock.findProgrammeInProgrammeEdition()).thenReturn(programmeMock);
//        when(programmeEditionMock.findSchoolYearInProgrammeEdition()).thenReturn(schoolYearMock);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock,schoolYearMock)).thenReturn(programmeEditionMock);
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//        when(programmeEditionMock.findProgrammeInProgrammeEdition()).thenReturn(programmeMock);
//        when(programmeEditionMock.findSchoolYearInProgrammeEdition()).thenReturn(schoolYearMock);
//
//        // Act
//        Optional <ProgrammeEditionID> result = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programmeMock2Id, schoolYearMockId);
//
//        // Assert
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void shouldReturnAnEmptyOptionalIfRepositoryIsEmptyMock() {
//        // SUT = ProgrammeEditionRepository - findProgrammeEditionBySchoolYearAndProgramme
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        ProgrammeID programmeMockId = mock(ProgrammeID.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        SchoolYearID schoolYearMockId = mock(SchoolYearID.class);
//
//        // Act
//        Optional <ProgrammeEditionID> result = programmeEditionRepository.findProgrammeEditionBySchoolYearAndProgramme(programmeMockId, schoolYearMockId);
//
//        // Assert
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void shouldReturnEmptyListIfRepositoryHasNoProgrammeEditions() {
//        // SUT = ProgrammeEditionRepository - getAllProgrammeEditions
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        // Act
//        List<ProgrammeEdition> result = programmeEditionRepository.getAllProgrammeEditions();
//
//        // Assert
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    void shouldReturnListNotEmptyIfRepositoryHasProgrammeEditions() throws Exception {
//        // SUT = ProgrammeEditionRepository - getAllProgrammeEditions
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        SchoolYear schoolYearMock2 = mock(SchoolYear.class);
//        SchoolYear schoolYearMock3 = mock(SchoolYear.class);
//
//        Programme  programmeMock = mock(Programme.class);
//
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//        ProgrammeEdition programmeEditionMock2 = mock(ProgrammeEdition.class);
//        ProgrammeEdition programmeEditionMock3 = mock(ProgrammeEdition.class);
//
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenReturn(programmeEditionMock);
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock2)).thenReturn(programmeEditionMock2);
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock3)).thenReturn(programmeEditionMock3);
//
//
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock2);
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock3);
//
//        // Act
//        List<ProgrammeEdition> result = programmeEditionRepository.getAllProgrammeEditions();
//
//        // Assert
//        assertEquals(3, result.size());
//        assertTrue(result.contains(programmeEditionMock));
//        assertTrue(result.contains(programmeEditionMock2));
//        assertTrue(result.contains(programmeEditionMock3));
//    }
//
//    @Test
//    void shouldReturnProgrammeOfAProgrammeEdition() throws Exception {
//        // SUT = ProgrammeEditionRepository - findProgrammeInProgrammeEdition
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactoryImplMock = mock(ProgrammeEditionFactoryImpl.class);
//        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImplMock = mock(ProgrammeEditionListFactoryImpl.class);
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactoryImplMock, programmeEditionListFactoryImplMock);
//
//        Programme programmeMock = mock(Programme.class);
//        SchoolYear schoolYearMock = mock(SchoolYear.class);
//        SchoolYear schoolYearMock2 = mock(SchoolYear.class);
//        ProgrammeEdition programmeEditionMock = mock(ProgrammeEdition.class);
//        ProgrammeEdition programmeEditionMock2 = mock(ProgrammeEdition.class);
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock)).thenReturn(programmeEditionMock);
//        when(programmeEditionFactoryImplMock.createProgrammeEdition(programmeMock, schoolYearMock2)).thenReturn(programmeEditionMock2);
//
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock);
//        programmeEditionRepository.createProgrammeEdition(programmeMock, schoolYearMock2);
//        when (programmeEditionMock.findProgrammeInProgrammeEdition()).thenReturn(programmeMock);
//
//        //Act
//        Programme result = programmeEditionRepository.findProgrammeInProgrammeEdition(programmeEditionMock);
//
//        //assert
//        assertEquals(programmeMock, result);
//    }
}