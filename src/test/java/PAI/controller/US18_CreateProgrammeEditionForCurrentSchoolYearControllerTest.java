package PAI.controller;

import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDDFactoryImpl;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.factory.SchoolYearListFactoryImpl;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.SchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionDDDListFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeEditionRepository.ProgrammeEditionDDDListFactoryImpl;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryDDDImpl;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryListFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    @Test
    void shouldCreateController() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionRepositoryListFactory = new ProgrammeEditionDDDListFactoryImpl();
        IProgrammeEditionDDDFactory programmeEditionFactory = new ProgrammeEditionDDDFactoryImpl();
        IProgrammeEditionRepositoryDDD ProgrammeEditionRepository = new ProgrammeEditionRepositoryDDDImpl(programmeEditionRepositoryListFactory, programmeEditionFactory);

        IProgrammeDDDRepositoryListFactory programmeRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDFactory programmeFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepository programmeRepository = new ProgrammeDDDRepositoryImpl(programmeFactory, programmeRepositoryListFactory);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearRepositoryListFactory);

        // Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(ProgrammeEditionRepository, schoolYearRepository, programmeRepository);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateProgrammeEditionMock() {
/*        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        Programme programmeLEI = mock(Programme.class);
        Optional<Programme> programmeOpt = mock(Optional.class);

        when(programmeList.getProgrammeByName(programmeName)).thenReturn(programmeOpt);
        when(programmeOpt.orElse(null)).thenReturn(programmeLEI);

        SchoolYear currentSchoolYear = mock(SchoolYear.class);
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(currentSchoolYear);

        when(programmeEditionRepository.createProgrammeEdition(programmeLEI, currentSchoolYear)).thenReturn(true);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Assert
        assertTrue(result);*/
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionAlreadyExists() {
//        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
//        // Arrange
//        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);
//
//        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
//        NameWithNumbersAndSpecialChars programmeName2 = mock(NameWithNumbersAndSpecialChars.class);
//
//        Programme programmeLEI = mock(Programme.class);
//        Optional<Programme> programmeOpt = mock(Optional.class);
//
//        when(programmeList.getProgrammeByName(programmeName)).thenReturn(programmeOpt);
//        when(programmeOpt.orElse(null)).thenReturn(programmeLEI);
//
//        SchoolYear currentSchoolYear = mock(SchoolYear.class);
//        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(currentSchoolYear);
//
//        when(controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName)).thenReturn(true);
//        when(controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName2)).thenReturn(false);
//
//        controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);
//
//        // Act
//        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName2);
//
//        // Assert
//        assertFalse(result);
//

    }

    @Test
    void shouldNotCreateProgrammeEditionIfCurrentSchoolYearIsNullMock() {
    /*    // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        Programme programmeLEI = mock(Programme.class);
        Optional<Programme> programmeOpt = mock(Optional.class);
        when(programmeList.getProgrammeByName(programmeName)).thenReturn(programmeOpt);
        when(programmeOpt.orElse(null)).thenReturn(programmeLEI);

        SchoolYear currentSchoolYear = null;
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(currentSchoolYear);
        when(programmeEditionRepository.createProgrammeEdition(programmeLEI, currentSchoolYear)).thenReturn(false);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);*/
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionRepositoryIsNullMock() {
     /*   // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        programmeEditionRepository = null;
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);*/
    }

    @Test
    void shouldReturnFalseIfSchoolYearRepositoryIsNullMock() {
      /*  // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        schoolYearRepository = null;
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);
        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);*/
    }

    @Test
    void shouldReturnAListOfAllProgrammeNamesFromRProgrammeList () {
      /*  // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getAllProgrammeNames
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeRepository programmeList = mock(ProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);

        when(name1.getnameWithNumbersAndSpecialChars()).thenReturn("Licenciatura em Engenharia Informatica");
        when(name2.getnameWithNumbersAndSpecialChars()).thenReturn("Mestrado em Engenharia Informatica");

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);


        when(programmeList.getAllProgrammeNames()).thenReturn(List.of(name1, name2));

        // Act
        List <NameWithNumbersAndSpecialChars> ListOfProgrammeNames = controller.getAllProgrammeNames();

        // Assert
        assertEquals(2, ListOfProgrammeNames.size());
        assertEquals(name1, ListOfProgrammeNames.get(0));
        assertEquals(name2, ListOfProgrammeNames.get(1));*/
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammeNamesFromRepositoryIfProgrammeListIsNull() {
      /*  // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getAllProgrammeNames
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeRepository programmeList = null;
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        // Act
        List <NameWithNumbersAndSpecialChars> ListOfProgrammeNames = controller.getAllProgrammeNames();

        // Assert
        assertEquals(0, ListOfProgrammeNames.size());
        assertDoesNotThrow(() -> ListOfProgrammeNames.add(name)); //mutation killer*/
    }
}