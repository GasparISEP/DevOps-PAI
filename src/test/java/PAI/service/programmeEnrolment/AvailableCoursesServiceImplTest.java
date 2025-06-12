package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AvailableCoursesServiceImplTest {
    @Test
    void shouldCreatConstructor(){
        //arrange
        ICourseEditionRepository _courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository _courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl availableCoursesService = new AvailableCoursesServiceImpl(_courseEditionRepository,_courseInStudyPlanRepository);
        //assert
        assertNotNull(availableCoursesService);
    }
    @Test
    void shouldThrowExceptionIfCourseEditionRepositoryIsNull() {
        // arrange
        ICourseInStudyPlanRepository _courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AvailableCoursesServiceImpl(null, _courseInStudyPlanRepository)
        );
        assertEquals("Cannot be null", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfCourseInStudyPlanRepositoryIsNull() {
        // arrange
        ICourseEditionRepository _courseEditionRepository = mock(ICourseEditionRepository.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AvailableCoursesServiceImpl(_courseEditionRepository, null)
        );
        assertEquals("Cannot be null", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionIfBothRepositoriesAreNull() {
        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AvailableCoursesServiceImpl(null, null)
        );
        assertEquals("Cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnListOfCourseEditionIDsForValidProgrammeEditionID() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        List<CourseEditionID> expectedList = List.of(mock(CourseEditionID.class), mock(CourseEditionID.class));

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(expectedList);

        // act
        List<CourseEditionID> result = service.allCourseEditionIdsFromProgrammeEdition(programmeEditionID);

        // assert
        assertEquals(expectedList, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoCourseEditionsFound() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of());

        // act
        List<CourseEditionID> result = service.allCourseEditionIdsFromProgrammeEdition(programmeEditionID);

        // assert
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldPropagateExceptionIfRepositoryFails() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID))
                .thenThrow(new RuntimeException("Database error"));

        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.allCourseEditionIdsFromProgrammeEdition(programmeEditionID)
        );
        assertEquals("Database error", exception.getMessage());
    }

    @Test
    void shouldReturnListOfCourseInStudyPlanIDFromCourseEditionIDList() {
        // arrange
        CourseInStudyPlanID course1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID course2 = mock(CourseInStudyPlanID.class);

        CourseEditionID edition1 = mock(CourseEditionID.class);
        CourseEditionID edition2 = mock(CourseEditionID.class);

        when(edition1.getCourseInStudyPlanID()).thenReturn(course1);
        when(edition2.getCourseInStudyPlanID()).thenReturn(course2);

        List<CourseEditionID> inputList = List.of(edition1, edition2);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<CourseInStudyPlanID> result = service.allCoursesInStudyFromProgrammeEdition(inputList);

        // assert
        assertEquals(List.of(course1, course2), result);
    }

    @Test
    void shouldReturnEmptyListWhenInputListIsEmpty() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<CourseInStudyPlanID> result = service.allCoursesInStudyFromProgrammeEdition(List.of());

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenInputListIsNull() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act & assert
        assertThrows(NullPointerException.class, () ->
                service.allCoursesInStudyFromProgrammeEdition(null)
        );
    }

    @Test
    void shouldReturnListOfCourseInStudyPlanWhenAllFound() {
        // arrange
        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id2 = mock(CourseInStudyPlanID.class);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        when(courseInStudyPlanRepository.ofIdentity(id1)).thenReturn(Optional.of(course1));
        when(courseInStudyPlanRepository.ofIdentity(id2)).thenReturn(Optional.of(course2));

        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<CourseInStudyPlan> result = service.getByIdentity(List.of(id1, id2));

        // assert
        assertEquals(List.of(course1, course2), result);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAnyCourseInStudyPlanIsMissing() {
        // arrange
        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id2 = mock(CourseInStudyPlanID.class);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        when(courseInStudyPlanRepository.ofIdentity(id1)).thenReturn(Optional.of(course1));
        when(courseInStudyPlanRepository.ofIdentity(id2)).thenReturn(Optional.empty());

        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act & assert
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                service.getByIdentity(List.of(id1, id2))
        );
        assertTrue(exception.getMessage().contains("CourseInStudyPlan not found"));
    }

    @Test
    void getByIdentityShouldReturnEmptyListWhenInputIsEmpty() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<CourseInStudyPlan> result = service.getByIdentity(List.of());

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getListOfCoursesFromACurrentCurricularYearShouldReturnCourseIDs() {
        // arrange
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);

        when(course1.getCourseID()).thenReturn(courseID1);
        when(course2.getCourseID()).thenReturn(courseID2);

        List<CourseInStudyPlan> inputList = List.of(course1, course2);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<CourseID> result = service.getListOfCoursesID(inputList);

        // assert
        assertEquals(List.of(courseID1, courseID2), result);
    }

    @Test
    void getListOfCoursesFromACurrentCurricularYearShouldReturnEmptyListWhenInputIsEmpty() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<CourseID> result = service.getListOfCoursesID(List.of());

        // assert
        assertTrue(result.isEmpty());
    }
    @Test
    void getListOfCoursesFromACurrentCurricularYearShouldThrowWhenInputIsNull() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act & assert
        assertThrows(NullPointerException.class, () ->
                service.getListOfCoursesID(null)
        );
    }

    @Test
    void getListOfCourseIdForAGivenProgrammeEditionAndInASpecificCurricularYearShouldReturnCourseIDs() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        CourseEditionID ceid1 = mock(CourseEditionID.class);
        CourseEditionID ceid2 = mock(CourseEditionID.class);

        CourseInStudyPlanID cspid1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID cspid2 = mock(CourseInStudyPlanID.class);

        CourseInStudyPlan csp1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan csp2 = mock(CourseInStudyPlan.class);

        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // mock chain
        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of(ceid1, ceid2));
        when(ceid1.getCourseInStudyPlanID()).thenReturn(cspid1);
        when(ceid2.getCourseInStudyPlanID()).thenReturn(cspid2);
        when(courseInStudyPlanRepository.ofIdentity(cspid1)).thenReturn(Optional.of(csp1));
        when(courseInStudyPlanRepository.ofIdentity(cspid2)).thenReturn(Optional.of(csp2));
        when(csp1.getCourseID()).thenReturn(courseID1);
        when(csp2.getCourseID()).thenReturn(courseID2);

        // act
        List<CourseID> result = service.getListOfCourseIdForAGivenProgrammeEdition(programmeEditionID);

        // assert
        assertEquals(List.of(courseID1, courseID2), result);
    }
    @Test
    void getListOfCourseIdForAGivenProgrammeEditionAndInASpecificCurricularYearShouldThrowIfCourseInStudyPlanNotFound() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        CourseEditionID ceid = mock(CourseEditionID.class);
        CourseInStudyPlanID cspid = mock(CourseInStudyPlanID.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of(ceid));
        when(ceid.getCourseInStudyPlanID()).thenReturn(cspid);
        when(courseInStudyPlanRepository.ofIdentity(cspid)).thenReturn(Optional.empty());

        // act & assert
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                service.getListOfCourseIdForAGivenProgrammeEdition(programmeEditionID)
        );
        assertTrue(exception.getMessage().contains("CourseInStudyPlan not found"));
    }

    @Test
    void getListOfCourseIdForAGivenProgrammeEditionAndInASpecificCurricularYearShouldReturnEmptyListWhenNoCourseEditions() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);

        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of());

        // act
        List<CourseID> result = service.getListOfCourseIdForAGivenProgrammeEdition(programmeEditionID);

        // assert
        assertTrue(result.isEmpty());
    }



    @Test
    void getListOfCourseInfo_shouldReturnAvailableCourseInfoList() {
        // arrange
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        CourseQuantityCreditsEcts ects1 = mock(CourseQuantityCreditsEcts.class);
        CourseQuantityCreditsEcts ects2 = mock(CourseQuantityCreditsEcts.class);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);

        when(course1.getCourseID()).thenReturn(courseID1);
        when(course1.getQuantityOfCreditsEcts()).thenReturn(ects1);
        when(course2.getCourseID()).thenReturn(courseID2);
        when(course2.getQuantityOfCreditsEcts()).thenReturn(ects2);

        List<CourseInStudyPlan> inputList = List.of(course1, course2);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service =
                new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<AvailableCourseInfo> result = service.getListOfCourseInfo(inputList);

        // assert
        assertEquals(2, result.size());
        assertEquals(new AvailableCourseInfo(courseID1, ects1), result.get(0));
        assertEquals(new AvailableCourseInfo(courseID2, ects2), result.get(1));
    }

    @Test
    void getListOfCourseInfo_shouldReturnEmptyListWhenInputIsEmpty() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service =
                new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act
        List<AvailableCourseInfo> result = service.getListOfCourseInfo(List.of());

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getListOfCourseInfo_shouldThrowWhenInputIsNull() {
        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service =
                new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // act & assert
        assertThrows(NullPointerException.class, () ->
                service.getListOfCourseInfo(null)
        );
    }

    @Test
    void getListOfAvailableCourseInfoForAGivenProgrammeEdition_shouldReturnCourseInfos() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        CourseEditionID ceid1 = mock(CourseEditionID.class);
        CourseEditionID ceid2 = mock(CourseEditionID.class);

        CourseInStudyPlanID cspid1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID cspid2 = mock(CourseInStudyPlanID.class);

        CourseInStudyPlan csp1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan csp2 = mock(CourseInStudyPlan.class);

        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        CourseQuantityCreditsEcts ects1 = mock(CourseQuantityCreditsEcts.class);
        CourseQuantityCreditsEcts ects2 = mock(CourseQuantityCreditsEcts.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        // mock chain
        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of(ceid1, ceid2));
        when(ceid1.getCourseInStudyPlanID()).thenReturn(cspid1);
        when(ceid2.getCourseInStudyPlanID()).thenReturn(cspid2);
        when(courseInStudyPlanRepository.ofIdentity(cspid1)).thenReturn(Optional.of(csp1));
        when(courseInStudyPlanRepository.ofIdentity(cspid2)).thenReturn(Optional.of(csp2));
        when(csp1.getCourseID()).thenReturn(courseID1);
        when(csp2.getCourseID()).thenReturn(courseID2);
        when(csp1.getQuantityOfCreditsEcts()).thenReturn(ects1);
        when(csp2.getQuantityOfCreditsEcts()).thenReturn(ects2);

        // act
        List<AvailableCourseInfo> result = service.getListOfAvailableCourseInfoForAGivenProgrammeEdition(programmeEditionID);

        // assert
        assertEquals(2, result.size());
        assertEquals(new AvailableCourseInfo(courseID1, ects1), result.get(0));
        assertEquals(new AvailableCourseInfo(courseID2, ects2), result.get(1));
    }

    @Test
    void getListOfAvailableCourseInfoForAGivenProgrammeEdition_shouldThrowIfCourseInStudyPlanNotFound() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseEditionID ceid = mock(CourseEditionID.class);
        CourseInStudyPlanID cspid = mock(CourseInStudyPlanID.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of(ceid));
        when(ceid.getCourseInStudyPlanID()).thenReturn(cspid);
        when(courseInStudyPlanRepository.ofIdentity(cspid)).thenReturn(Optional.empty());

        // act & assert
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                service.getListOfAvailableCourseInfoForAGivenProgrammeEdition(programmeEditionID)
        );
        assertTrue(exception.getMessage().contains("CourseInStudyPlan not found"));
    }

    @Test
    void getListOfAvailableCourseInfoForAGivenProgrammeEdition_shouldReturnEmptyListWhenNoCourseEditions() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ICourseInStudyPlanRepository courseInStudyPlanRepository = mock(ICourseInStudyPlanRepository.class);
        AvailableCoursesServiceImpl service = new AvailableCoursesServiceImpl(courseEditionRepository, courseInStudyPlanRepository);

        when(courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionID)).thenReturn(List.of());

        // act
        List<AvailableCourseInfo> result = service.getListOfAvailableCourseInfoForAGivenProgrammeEdition(programmeEditionID);

        // assert
        assertTrue(result.isEmpty());
    }


}