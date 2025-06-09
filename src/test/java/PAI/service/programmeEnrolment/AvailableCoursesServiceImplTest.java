package PAI.service.programmeEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

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


}