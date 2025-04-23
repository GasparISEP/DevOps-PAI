
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US19_CreateCourseEditionControllerTest {

    @Test
    void shouldCreateControllerSuccessfully() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble,studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);
        //Assert
        assertNotNull(us19Controller);
    }

    @Test
    void shouldThrowExceptionIfDegreeTypeRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(null, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, null, studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfStudyPlanRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, null, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanServiceIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble,studyPlanRepositoryDouble, null, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, null, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfCourseEditionRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, null);});
    }

    @Test
    void shouldReturnListOfDegreeTypes() {
        // Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        DegreeType degreeType1Double = mock(DegreeType.class);
        DegreeType degreeType2Double = mock(DegreeType.class);
        List<DegreeType> expectedList = List.of(degreeType1Double, degreeType2Double);

        when(degreeTypeRepositoryDouble.getAllDegreeTypes()).thenReturn(expectedList);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
                degreeTypeRepositoryDouble,
                programmeRepositoryDouble,
                studyPlanRepositoryDouble,
                courseInStudyPlanServiceDouble,
                programmeEditionRepositoryDouble,
                courseEditionRepositoryDouble
        );

        // Act
        List<DegreeType> result = controller.getAllDegreeTypes();

        // Assert
        assertEquals(expectedList, result);
        assertTrue(result.contains(degreeType1Double));
        assertTrue(result.contains(degreeType2Double));
        verify(degreeTypeRepositoryDouble).getAllDegreeTypes();
    }

    @Test
    void shouldReturnListOfProgrammesByDegreeTypeID() throws Exception {
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);


        DegreeTypeID degreeTypeIDDouble = mock(DegreeTypeID.class);

        Programme programme = mock(Programme.class);
        when(programme.getDegreeTypeID()).thenReturn(degreeTypeIDDouble);

        List<Programme> ListWithProgramme = Arrays.asList(programme);
        when(programmeRepositoryDouble.getProgrammesByDegreeTypeID(degreeTypeIDDouble)).thenReturn(ListWithProgramme);

        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);

        //Act
        List<Programme> result = us19Controller.getProgrammesByDegreeTypeID(degreeTypeIDDouble);

        //Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(programme));
    }

    @Test
    void shouldReturnListOfCoursesInStudyPLanByProgrammeID() throws Exception {
        //Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        CourseInStudyPlan courseInStudyPlan = mock(CourseInStudyPlan.class);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        StudyPlanID studyPlanIDDouble = mock(StudyPlanID.class);

        when(studyPlanRepositoryDouble.getLatestStudyPlanIDByProgrammeID(programmeIDDouble)).thenReturn(studyPlanIDDouble);

        List<CourseInStudyPlan> ListOfCourseInStudyPlan = Arrays.asList(courseInStudyPlan);
        when(courseInStudyPlanServiceDouble.getCoursesByStudyPlanId(studyPlanIDDouble)).thenReturn(ListOfCourseInStudyPlan);

        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanServiceDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);

        //Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeIDDouble);

        //Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(courseInStudyPlan));
    }

    @Test
    void shouldReturnListOfProgrammeEditionsByProgrammeID(){
        //SUT = Controller
        // Arrange
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        List<ProgrammeEdition> expectedList = List.of(edition1Double, edition2Double);

        when(programmeEditionRepositoryDouble.getProgrammeEditionsByProgrammeID(programmeIDDouble)).thenReturn(expectedList);

        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
                degreeTypeRepositoryDouble,
                programmeRepositoryDouble,
                studyPlanRepositoryDouble,
                courseInStudyPlanServiceDouble,
                programmeEditionRepositoryDouble,
                courseEditionRepositoryDouble
        );

        // Act
        List<ProgrammeEdition> result = controller.getProgrammeEditionsByProgrammeID(programmeIDDouble);

        // Assert
        assertEquals(expectedList, result);
        verify(programmeEditionRepositoryDouble).getProgrammeEditionsByProgrammeID(programmeIDDouble);
    }

//    @Test
//    void shouldReturnTrueIfCourseEditionIsCreatedSuccessfully() {
//        // Arrange
//        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
//        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
//        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
//        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
//        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
//        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);
//
//        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
//        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
//
//        when(courseEditionRepositoryDouble.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble))
//                .thenReturn(true);
//
//        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
//                degreeTypeRepositoryDouble,
//                programmeRepositoryDouble,
//                studyPlanRepositoryDouble,
//                courseInStudyPlanServiceDouble,
//                programmeEditionRepositoryDouble,
//                courseEditionRepositoryDouble
//        );
//
//        // Act
//        boolean result = controller.createCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);
//
//        // Assert
//        assertTrue(result);
//        verify(courseEditionRepositoryDouble, times(1)).createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);
//    }

//    @Test
//    void shouldReturnFalseIfCourseEditionIsNotCreated() {
//        // Arrange
//        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
//        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
//        IStudyPlanRepository studyPlanRepositoryDouble = mock(IStudyPlanRepository.class);
//        ICourseInStudyPlanService courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
//        IProgrammeEditionRepository programmeEditionRepositoryDouble = mock(IProgrammeEditionRepository.class);
//        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);
//
//        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
//        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
//
//        when(courseEditionRepositoryDouble.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble))
//                .thenReturn(false);
//
//        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
//                degreeTypeRepositoryDouble,
//                programmeRepositoryDouble,
//                studyPlanRepositoryDouble,
//                courseInStudyPlanServiceDouble,
//                programmeEditionRepositoryDouble,
//                courseEditionRepositoryDouble
//        );
//
//        // Act
//        boolean result = controller.createCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);
//
//        // Assert
//        assertFalse(result);
//        verify(courseEditionRepositoryDouble, times(1)).createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);
//    }

}
