package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionRepositoryImplTest {

    //US19
    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        // Arrange
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);

            // Create a spy instead of a real instance
        ICourseEditionRepository courseEditionRepository = spy(new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

            // Define behaviors for mocks
        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble))
                .thenReturn(courseEditionDouble);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);

            // Use doReturn() instead of when() because we are spying on a real object
        doReturn(false).when(courseEditionRepository).containsOfIdentity(courseEditionIDDouble);

        // Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        // Assert
        assertTrue(result);
    }


    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasDifferentCourseButTheSameProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        CourseEdition_2 courseEditionDouble1 = mock(CourseEdition_2.class);
        CourseEdition_2 courseEditionDouble2 = mock(CourseEdition_2.class);

            // Spy
        ICourseEditionRepository courseEditionRepository = spy(new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

            // instructions
        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble))
                .thenReturn(courseEditionDouble1);

        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble2, programmeEditionIDDouble))
                .thenReturn(courseEditionDouble2);

        doReturn(false).when(courseEditionRepository).containsOfIdentity(any());
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble);

        // Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble);

        // Assert
        assertTrue(result);
    }


    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasSameCourseButDifferentProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition_2 courseEditionDouble1 = mock(CourseEdition_2.class);
        CourseEdition_2 courseEditionDouble2 = mock(CourseEdition_2.class);

            // Spy
        ICourseEditionRepository courseEditionRepository = spy(new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

            // instructions
        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble1))
                .thenReturn(courseEditionDouble1);

        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble2))
                .thenReturn(courseEditionDouble2);

        doReturn(false).when(courseEditionRepository).containsOfIdentity(any());

        // Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble2);

        // Assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfTheCourseEditionIsAlreadyRegistered() throws Exception {
        //SUT = CourseEditionRepository
        //Arrange
        //Doubles' instantiation
        // Arrange
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition_2 courseEditionDouble1 = mock(CourseEdition_2.class);

        // Spy
        ICourseEditionRepository courseEditionRepository = spy(new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

        // instructions
        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble))
                .thenReturn(courseEditionDouble1);

        doReturn(true).when(courseEditionRepository).containsOfIdentity(any());

        // Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        // Assert
        assertFalse(result);
    }
}