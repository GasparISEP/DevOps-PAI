package PAI.controller;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.*;
import PAI.repository.ProgrammeEditionRepository;
import PAI.repository.ProgrammeRepository;
import PAI.repository.SchoolYearRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    private SchoolYearRepository schoolYearRepository;
    private ProgrammeEditionRepository programmeEditionRepository;
    private ProgrammeRepository programmeList;

    @BeforeEach
    void setUp() {
        schoolYearRepository = mock(SchoolYearRepository.class);
        programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        programmeList = mock(ProgrammeRepository.class);
    }

    @Test
    void shouldCreateControllerMock() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController
        // Arrange

        // Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository,schoolYearRepository, programmeList);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateProgrammeEditionMock() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
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
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionAlreadyExists() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars programmeName2 = mock(NameWithNumbersAndSpecialChars.class);

        Programme programmeLEI = mock(Programme.class);
        Optional<Programme> programmeOpt = mock(Optional.class);

        when(programmeList.getProgrammeByName(programmeName)).thenReturn(programmeOpt);
        when(programmeOpt.orElse(null)).thenReturn(programmeLEI);

        SchoolYear currentSchoolYear = mock(SchoolYear.class);
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(currentSchoolYear);

        when(controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName)).thenReturn(true);
        when(controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName2)).thenReturn(false);

        controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName2);

        // Assert
        assertFalse(result);


    }

    @Test
    void shouldNotCreateProgrammeEditionIfCurrentSchoolYearIsNullMock() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
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
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionRepositoryIsNullMock() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        programmeEditionRepository = null;
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearRepositoryIsNullMock() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        schoolYearRepository = null;
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);
        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAListOfAllProgrammeNamesFromRProgrammeList () {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getAllProgrammeNames
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
        assertEquals(name2, ListOfProgrammeNames.get(1));
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammeNamesFromRepositoryIfProgrammeListIsNull() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getAllProgrammeNames
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
        assertDoesNotThrow(() -> ListOfProgrammeNames.add(name)); //mutation killer
    }
}