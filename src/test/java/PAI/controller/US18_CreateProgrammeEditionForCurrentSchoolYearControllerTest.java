package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    private SchoolYearRepository schoolYearRepository;
    private ProgrammeEditionRepository programmeEditionRepository;
    private ProgrammeList programmeList;

    @BeforeEach
    void setUp() {
        schoolYearRepository = mock(SchoolYearRepository.class);
        programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        programmeList = mock(ProgrammeList.class);
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
    void shouldCreateProgrammeEditionMock() throws Exception{
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        String programmeName = "Licenciatura Engenharia Informatica";
        Programme programmeLEI = mock(Programme.class);
        Optional<Programme> programmeOpt = mock(Optional.class);

        when(programmeList.getProgrammeByName(programmeName)).thenReturn(programmeOpt);
        when(programmeOpt.orElse(null)).thenReturn(programmeLEI);

        SchoolYear currentSchoolYear = mock(SchoolYear.class);
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(currentSchoolYear);

        when(programmeEditionRepository.createProgrammeEdition(programmeLEI, currentSchoolYear)).thenReturn(true);

        // Act
        boolean result = controller.createAProgrammeEditionInTheCurrentSchoolYear(programmeName);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotCreateProgrammeEditionIfCurrentSchoolYearIsNullMock() throws Exception{
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        String programmeName = "Licenciatura Informatica";
        Programme programmeLEI = mock(Programme.class);
        Optional<Programme> programmeOpt = mock(Optional.class);
        when(programmeList.getProgrammeByName(programmeName)).thenReturn(programmeOpt);
        when(programmeOpt.orElse(null)).thenReturn(programmeLEI);

        SchoolYear currentSchoolYear = null;
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(currentSchoolYear);
        when(programmeEditionRepository.createProgrammeEdition(programmeLEI, currentSchoolYear)).thenReturn(false);

        // Act
        boolean result = controller.createAProgrammeEditionInTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionRepositoryIsNullMock() throws Exception{
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        programmeEditionRepository = null;
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        String programmeName = "Licenciatura Informatica";
        // Act
        boolean result = controller.createAProgrammeEditionInTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearRepositoryIsNullMock() throws Exception{
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - createAProgrammeEditionInTheCurrentSchoolYear
        // Arrange
        schoolYearRepository = null;
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);
        String programmeName = "Licenciatura Informatica";

        // Act
        boolean result = controller.createAProgrammeEditionInTheCurrentSchoolYear(programmeName);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAListOfAllProgrammeNamesFromRProgrammeList () {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getAllProgrammeNames
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        String programmeName1 = "Licenciatura em Engenharia Informatica";
        String programmeName2 = "Mestrado em Engenharia Informatica";

        when(programmeList.getAllProgrammeNames()).thenReturn(List.of(programmeName1, programmeName2));

        // Act
        List <String> ListOfProgrammeNames = controller.getAllProgrammeNames();

        // Assert
        assertEquals(2, ListOfProgrammeNames.size());
        assertTrue(ListOfProgrammeNames.contains(programmeName1));
        assertTrue(ListOfProgrammeNames.contains(programmeName2));
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammeNamesFromRepositoryIfProgrammeListIsNull() {
        // SUT = US18_CreateProgrammeEditionForCurrentSchoolYearController - getAllProgrammeNames
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        ProgrammeList programmeList = null;

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionRepository, schoolYearRepository, programmeList);

        // Act
        List <String> ListOfProgrammeNames = controller.getAllProgrammeNames();

        // Assert
        assertEquals(0, ListOfProgrammeNames.size());
    }
}