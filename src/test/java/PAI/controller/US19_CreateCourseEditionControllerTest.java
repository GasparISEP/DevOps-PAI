
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.DegreeType.IDegreeTypeService;
import PAI.service.StudyPlan.IStudyPlanService;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEdition.IProgrammeEditionService;
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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);

        // Act
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        // Assert
        assertNotNull(us19Controller);
    }

    @Test
    void shouldThrowExceptionIfDegreeTypeServiceIsNull() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = null;
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);});

        // Assert
        assertEquals("degreeTypeService cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeServiceIsNull() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = null;
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);});

        // Assert
        assertEquals("programmeService cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfStudyPlanServiceIsNull() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = null;
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);});

        // Assert
        assertEquals("studyPlanService cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanServiceIsNull() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = null;
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);});

        // Assert
        assertEquals("courseInStudyPlanService cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionServiceIsNull() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = null;
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);});

        // Assert
        assertEquals("programmeEditionService cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionRepositoryIsNull() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);});

        // Assert
        assertEquals("courseEditionService cannot be null", exception.getMessage());
    }

    //-----getAllDegreeTypes Tests-----
    @Test
    void shouldReturnAListWithAllDegreeTypesInTheSystem() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        DegreeType degreeType1 = mock(DegreeType.class);
        DegreeType degreeType2 = mock(DegreeType.class);
        DegreeType degreeType3 = mock(DegreeType.class);

        List<DegreeType> degreeTypes = List.of(degreeType1, degreeType2, degreeType3);
        when(degreeTypeService.getAllDegreeTypes()).thenReturn(degreeTypes);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        List<DegreeType> degreeTypes = List.of();
        when(degreeTypeService.getAllDegreeTypes()).thenReturn(degreeTypes);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        Programme programme3 = mock(Programme.class);
        List<Programme> programmes = List.of(programme1, programme2, programme3);
        when(programmeService.getProgrammesByDegreeTypeID(degreeTypeID)).thenReturn(programmes);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        List<Programme> programmes = List.of();
        when(programmeService.getProgrammesByDegreeTypeID(degreeTypeID)).thenReturn(programmes);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(null);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        when(courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(List.of());

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course3 = mock(CourseInStudyPlan.class);
        List<CourseInStudyPlan> courses = List.of(course1, course2, course3);
        when(courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(courses);

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
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(studyPlanID);
        when(courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID)).thenReturn(List.of());

        // Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    //-----getProgrammeEditionsByProgrammeID Tests-----
    @Test
    void shouldReturnNullWhenGetProgrammeEditionsByProgrammeIDMethodIsCalled() throws Exception {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        List<ProgrammeEdition> result = us19Controller.getProgrammeEditionsByProgrammeID(programmeID);

        // Assert
        assertNull(result);
    }

    //-----createCourseEdition Tests-----

    @Test
    void shouldReturnFalseWhenCreateCourseEditionMethodIsCalled(){
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act
        boolean result = us19Controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);

        // Assert
        assertFalse(result);
    }
}
