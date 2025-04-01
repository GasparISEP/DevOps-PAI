package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionRepositoryImplTest {

    //US19
    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble))
                .thenReturn(courseEditionDouble);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);

        when(courseEditionsDouble.isEmpty()).thenReturn(true);
        when(courseEditionsDouble.iterator()).thenReturn(mock(Iterator.class));

        // Act
        boolean result = repository.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        // Assert
        assertTrue(result);
        verify(courseEditionsDouble).add(courseEditionDouble);
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
    void shouldReturnFalseIfTheCourseEditionIsAlreadyRegistered_WithSpy() throws Exception {
        //SUT = CourseEditionRepository
        //Arrange
        //Doubles' instantiation
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

    @Test
    void shouldReturnFalseIfTheCourseEditionIsAlreadyRegistered() throws Exception {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionFactoryDouble.newCourseEdition_2(courseInStudyPlanIDDouble, programmeEditionIDDouble)).thenReturn(courseEditionDouble);
        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);
        when(courseEditionsDouble.contains(courseEditionIDDouble)).thenReturn(true);

        // Act
        boolean result = repository.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        // Assert
        assertFalse(result);
        verify(courseEditionsDouble, never()).add(courseEditionDouble);
    }

    @Test
    void shouldReturnCourseEdition_SaveMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);

        // Act
        CourseEdition_2 result = repository.save(courseEditionDouble);

        // Assert
        verify(courseEditionsDouble).add(courseEditionDouble);
        assertEquals(courseEditionDouble, result);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIsNull_SaveMethod() {
        // Arrange
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        // Act & Assert
        Exception thrown = assertThrows(Exception.class, () -> repository.save(null));
        assertEquals("Course edition cannot be null", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNull_SaveMethod() {
        // Arrange
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionDouble.identity()).thenReturn(null);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> repository.save(courseEditionDouble));
        assertEquals("Course edition ID cannot be null", thrown.getMessage());
    }

    @Test
    void shouldReturnAllCourseEditions_FindAllMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        // Act
        Iterable<CourseEdition_2> result = repository.findAll();

        // Assert
        assertEquals(courseEditionsDouble, result);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNull_ofIdentityMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> repository.ofIdentity(null));
        assertEquals("Course edition ID cannot be null", thrown.getMessage());
    }

    @Test
    void shouldReturnEmptyIfNotFound_ofIdentityMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Iterator<CourseEdition_2> iteratorDouble = mock(Iterator.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);


        when(courseEditionsDouble.iterator()).thenReturn(iteratorDouble);
        when(iteratorDouble.hasNext()).thenReturn(false);

        // Act
        Optional<CourseEdition_2> result = repository.ofIdentity(courseEditionIDDouble);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueIfResultContainsCourseEditionWithCourseEditionID_ofIdentityMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);
        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);
        when(courseEditionsDouble.stream()).thenReturn(List.of(courseEditionDouble).stream());
        when(courseEditionsDouble.iterator()).thenReturn(List.of(courseEditionDouble).iterator());

        // Act
        Optional<CourseEdition_2> result = repository.ofIdentity(courseEditionIDDouble);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(courseEditionDouble, result.get());
    }

    @Test
    void shouldReturnTrueIfRepositoryContainsCourseEditionWithCourseEditionID_containsOfIdentityMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);
        when(courseEditionsDouble.iterator()).thenReturn(List.of(courseEditionDouble).iterator()); // Simula a iteração correta

        // Act
        boolean result = repository.containsOfIdentity(courseEditionIDDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfRepositoryNotContainsCourseEditionWithCourseEditionID_containsOfIdentityMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);

        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        Iterator<CourseEdition_2> iteratorDouble = mock(Iterator.class);
        when(iteratorDouble.hasNext()).thenReturn(false);
        when(courseEditionsDouble.iterator()).thenReturn(iteratorDouble);

        // Act
        boolean result = repository.containsOfIdentity(courseEditionIDDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfIdentityDoesNotMatch_containsOfIdentityMethod() {
        // Arrange
        List<CourseEdition_2> courseEditionsDouble = mock(List.class);
        ICourseEditionFactory_2 courseEditionFactoryDouble = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);

        CourseEditionRepositoryImpl repository = new CourseEditionRepositoryImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionsDouble.iterator()).thenReturn(Collections.singletonList(courseEditionDouble).iterator());
        when(courseEditionDouble.identity()).thenReturn(mock(CourseEditionID.class));

        // Act
        boolean result = repository.containsOfIdentity(courseEditionIDDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnCorrectNumberOfCourseEditionsInProgrammeEdition() throws Exception {
        // Arrange
        ICourseEditionFactory_2 doubleCourseEditionFactory = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEditionRepositoryImpl courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(doubleCourseEditionFactory, courseEditionListFactoryDouble);

        CourseEdition_2 doubleCourseEdition1 = mock(CourseEdition_2.class);
        CourseEdition_2 doubleCourseEdition2 = mock(CourseEdition_2.class);
        CourseEditionID doubleCourseEditionId1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEditionId2 = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);


        when(doubleCourseEditionFactory.newCourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition1);
        when(doubleCourseEdition1.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
        when(doubleCourseEditionFactory.newCourseEdition_2(courseInStudyPlanIDDouble2, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition2);
        when(doubleCourseEdition2.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
        when(doubleCourseEdition1.identity()).thenReturn(doubleCourseEditionId1);
        when(doubleCourseEdition2.identity()).thenReturn(doubleCourseEditionId2);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1);

        // Act
        List<CourseEdition_2> result = courseEditionRepositoryImpl.findCourseEditionsByProgrammeEdition(programmeEditionIDDouble1);

        // Assert
        assertEquals(2, result.size());

    }


    @Test
    void shouldReturnCorrectCourseEditionsInList() throws Exception {
        // Arrange
        ICourseEditionFactory_2 doubleCourseEditionFactory = mock(ICourseEditionFactory_2.class);
        ICourseEditionListFactory_2 courseEditionListFactoryDouble = mock(ICourseEditionListFactory_2.class);
        CourseEditionRepositoryImpl courseEditionRepositoryImpl = new CourseEditionRepositoryImpl(doubleCourseEditionFactory, courseEditionListFactoryDouble);

        CourseEdition_2 doubleCourseEdition1 = mock(CourseEdition_2.class);
        CourseEdition_2 doubleCourseEdition2 = mock(CourseEdition_2.class);
        CourseEditionID doubleCourseEditionId1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEditionId2 = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);

        when(doubleCourseEditionFactory.newCourseEdition_2(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition1);
        when(doubleCourseEditionFactory.newCourseEdition_2(courseInStudyPlanIDDouble2, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition2);
        when(doubleCourseEdition1.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
        when(doubleCourseEdition2.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
        when(doubleCourseEdition1.identity()).thenReturn(doubleCourseEditionId1);
        when(doubleCourseEdition2.identity()).thenReturn(doubleCourseEditionId2);
        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        courseEditionRepositoryImpl.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1);

        // Act
        List<CourseEdition_2> result = courseEditionRepositoryImpl.findCourseEditionsByProgrammeEdition(programmeEditionIDDouble1);

        // Assert
        assertTrue(result.contains(doubleCourseEdition1));
        assertTrue(result.contains(doubleCourseEdition2));

    }


    @Test
    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
        // Arrange

        CourseEdition_2 doubleCourseEdition = mock(CourseEdition_2.class);
        CourseEditionFactoryImpl doubleCourseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);

        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);


        CourseEditionRepository repository = new CourseEditionRepository(doubleCourseEditionFactoryImpl, courseEditionListFactoryImplDouble);

        when(doubleCourseEditionFactoryImpl.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition);

        repository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        when(repository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCourseEdition)).thenReturn(programmeEditionIDDouble1);

        // Act
        ProgrammeEditionID result = repository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCourseEdition);

        // Assert
        assertEquals(programmeEditionIDDouble1, result);
    }
}