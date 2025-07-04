
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.courseEdition.ICreateCourseEditionService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US19_CreateCourseEditionControllerTest {

    //-----Constructor Tests-----
    @Test
    void shouldCreateControllerSuccessfully() {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);

        // Act
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        // Assert
        assertNotNull(us19Controller);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionServiceIsNull() {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(courseEditionService);});

        // Assert
        assertEquals("courseEditionService cannot be null", exception.getMessage());
    }

    //-----getAllDegreeTypes Tests-----
    @Test
    void shouldReturnAListWithAllDegreeTypesInTheSystem() {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        DegreeType degreeType1 = mock(DegreeType.class);
        DegreeType degreeType2 = mock(DegreeType.class);
        DegreeType degreeType3 = mock(DegreeType.class);

        List<DegreeType> degreeTypes = List.of(degreeType1, degreeType2, degreeType3);
        when(courseEditionService.getAllDegreeTypes()).thenReturn(degreeTypes);

        // Act
        List<DegreeType> result = us19Controller.getAllDegreeTypes();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(degreeType1));
        assertTrue(result.contains(degreeType2));
        assertTrue(result.contains(degreeType3));
    }

    @Test
    void shouldReturnAnEmptyListIfTheSystemHasNoDegreeTypes() {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        List<DegreeType> degreeTypes = List.of();
        when(courseEditionService.getAllDegreeTypes()).thenReturn(degreeTypes);

        // Act
        List<DegreeType> result = us19Controller.getAllDegreeTypes();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    //-----getProgrammesByDegreeTypeID Tests-----
    @Test
    void shouldReturnAListWithAllProgrammesInTheSystemThatHaveTheDegreeTypeGiven() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        Programme programme3 = mock(Programme.class);
        List<Programme> programmes = List.of(programme1, programme2, programme3);
        when(courseEditionService.getProgrammesByDegreeTypeID(degreeTypeID)).thenReturn(programmes);

        // Act
        List<Programme> result = us19Controller.getProgrammesByDegreeTypeID(degreeTypeID);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(programme1));
        assertTrue(result.contains(programme2));
        assertTrue(result.contains(programme3));
    }

    @Test
    void shouldReturnAnEmptyListIfTheSystemHasNoProgrammeWithTheTheDegreeTypeGiven() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        List<Programme> programmes = List.of();
        when(courseEditionService.getProgrammesByDegreeTypeID(degreeTypeID)).thenReturn(programmes);

        // Act
        List<Programme> result = us19Controller.getProgrammesByDegreeTypeID(degreeTypeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAnEmptyListIfTheTheDegreeTypeIDGivenIsNull() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        DegreeTypeID degreeTypeID = null;

        // Act
        List<Programme> result = us19Controller.getProgrammesByDegreeTypeID(degreeTypeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    //-----getCoursesInStudyPlanByProgrammeID Tests-----
    @Test
    void shouldReturnEmptyListIfProgrammeIDGivenIsNull() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = null;

        // Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnEmptyListIfTheProgrammeIDGivenHasNoStudyPlanAssociatedToIt() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(courseEditionService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(null);

        // Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnEmptyListIfLatestStudyPlanOfTheProgrammeIDGivenHasNoCoursesInIt() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(courseEditionService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        when(courseEditionService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(List.of());

        // Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAListWithCoursesInTheLatestStudyPlanFromTheGivenProgrammeID() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(courseEditionService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course3 = mock(CourseInStudyPlan.class);
        List<CourseInStudyPlan> courses = List.of(course1, course2, course3);
        when(courseEditionService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(courses);

        // Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(course1));
        assertTrue(result.contains(course2));
        assertTrue(result.contains(course3));
    }

    @Test
    void shouldReturnAnEmptyListIfThereAreNoCoursesInTheLatestStudyPlanFromTheGivenProgrammeID() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(courseEditionService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        when(courseEditionService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(List.of());

        // Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    //-----getProgrammeEditionsByProgrammeID Tests-----

    @Test
    void shouldReturnAnEmptyListIfProgrammeIdGivenIsNull() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = null;

        // Act
        List<ProgrammeEdition> result = us19Controller.getProgrammeEditionsByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAnEmptyListIfTheSystemHasNoProgrammeEditionsWithTheProgrammeGiven() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        List<ProgrammeEdition> programmeEditions = List.of();
        when(courseEditionService.getProgrammeEditionsByProgrammeID(programmeID)).thenReturn(programmeEditions);

        // Act
        List<ProgrammeEdition> result = us19Controller.getProgrammeEditionsByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAListWithAllProgrammeEditionsInTheSystemThatHaveTheProgrammeGiven() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition3 = mock(ProgrammeEdition.class);

        List<ProgrammeEdition> programmeEditions = List.of(programmeEdition1, programmeEdition2, programmeEdition3);
        when(courseEditionService.getProgrammeEditionsByProgrammeID(programmeID)).thenReturn(programmeEditions);

        // Act
        List<ProgrammeEdition> result = us19Controller.getProgrammeEditionsByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(programmeEdition1));
        assertTrue(result.contains(programmeEdition2));
        assertTrue(result.contains(programmeEdition3));
    }


    //-----createCourseEdition Tests-----

    @Test
    void shouldReturnFalseIfCourseInStudyPlanIsNullWhenCreateCourseEditionMethodIsCalled() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        CourseInStudyPlanID courseInStudyPlanID = null;
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        boolean result = us19Controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeIsNullWhenCreateCourseEditionMethodIsCalled() throws Exception {
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = null;

        // Act
        boolean result = us19Controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCreateCourseEditionMethodIsCalled(){
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID)).thenReturn(null);

        // Act
        boolean result = us19Controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenCreateCourseEditionMethodIsCalled(){
        // SUT = Controller
        // Arrange
        ICreateCourseEditionService courseEditionService = mock(ICreateCourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(courseEditionService);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID)).thenReturn(courseEdition);

        // Act
        boolean result = us19Controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertTrue(result);
    }
}
