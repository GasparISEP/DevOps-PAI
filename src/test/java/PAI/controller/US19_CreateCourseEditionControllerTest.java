
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
    void shouldReturnNullWhenGetAllDegreeTypesMethodIsCalled() {
        // SUT = Controller
        // Arrange
        IDegreeTypeService degreeTypeService = mock(IDegreeTypeService.class);
        IProgrammeService programmeService = mock(IProgrammeService.class);
        IStudyPlanService studyPlanService = mock(IStudyPlanService.class);
        ICourseInStudyPlanService courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionService programmeEditionService = mock(IProgrammeEditionService.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeService, programmeService,studyPlanService, courseInStudyPlanService, programmeEditionService, courseEditionService);

        // Act
        List<DegreeType> result = us19Controller.getAllDegreeTypes();

        // Assert
        assertNull(result);
    }

    //-----getProgrammesByDegreeTypeID Tests-----
    @Test
    void shouldReturnNullWhenGetProgrammesByDegreeTypeIDMethodIsCalled() throws Exception {
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

        // Act
        List<Programme> result = us19Controller.getProgrammesByDegreeTypeID(degreeTypeID);

        // Assert
        assertNull(result);
    }

    //-----getCoursesInStudyPlanByProgrammeID Tests-----
    @Test
    void shouldReturnNullWhenGetCoursesInStudyPlanByProgrammeIDMethodIsCalled() throws Exception {
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
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeID);

        // Assert
        assertNull(result);
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
