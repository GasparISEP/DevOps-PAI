package PAI.service.programmeEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
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


}