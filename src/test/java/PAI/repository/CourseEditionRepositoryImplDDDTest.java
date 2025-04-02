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

class CourseEditionRepositoryImplDDDTest {

    //US19
    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        // Arrange
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

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
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);

        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        CourseEditionDDD courseEditionDouble1 = mock(CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock(CourseEditionDDD.class);

            // Spy
        ICourseEditionRepositoryDDD courseEditionRepository = spy(new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

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
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);

        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionDDD courseEditionDouble1 = mock(CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock(CourseEditionDDD.class);

            // Spy
        ICourseEditionRepositoryDDD courseEditionRepository = spy(new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

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
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        CourseEditionDDD courseEditionDouble1 = mock(CourseEditionDDD.class);

            // Spy
        ICourseEditionRepositoryDDD courseEditionRepository = spy(new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble));

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
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

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
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);

        // Act
        CourseEditionDDD result = repository.save(courseEditionDouble);

        // Assert
        verify(courseEditionsDouble).add(courseEditionDouble);
        assertEquals(courseEditionDouble, result);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIsNull_SaveMethod() {
        // Arrange
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        // Act & Assert
        Exception thrown = assertThrows(Exception.class, () -> repository.save(null));
        assertEquals("Course edition cannot be null", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNull_SaveMethod() {
        // Arrange
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        when(courseEditionDouble.identity()).thenReturn(null);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> repository.save(courseEditionDouble));
        assertEquals("Course edition ID cannot be null", thrown.getMessage());
    }

    @Test
    void shouldReturnAllCourseEditions_FindAllMethod() {
        // Arrange
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        // Act
        Iterable<CourseEditionDDD> result = repository.findAll();

        // Assert
        assertEquals(courseEditionsDouble, result);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDIsNull_ofIdentityMethod() {
        // Arrange
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> repository.ofIdentity(null));
        assertEquals("Course edition ID cannot be null", thrown.getMessage());
    }

    @Test
    void shouldReturnEmptyIfNotFound_ofIdentityMethod() {
        // Arrange
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        Iterator<CourseEditionDDD> iteratorDouble = mock(Iterator.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);


        when(courseEditionsDouble.iterator()).thenReturn(iteratorDouble);
        when(iteratorDouble.hasNext()).thenReturn(false);

        // Act
        Optional<CourseEditionDDD> result = repository.ofIdentity(courseEditionIDDouble);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueIfResultContainsCourseEditionWithCourseEditionID_ofIdentityMethod() {
        // Arrange
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);
        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionDouble.identity()).thenReturn(courseEditionIDDouble);
        when(courseEditionsDouble.stream()).thenReturn(List.of(courseEditionDouble).stream());
        when(courseEditionsDouble.iterator()).thenReturn(List.of(courseEditionDouble).iterator());

        // Act
        Optional<CourseEditionDDD> result = repository.ofIdentity(courseEditionIDDouble);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(courseEditionDouble, result.get());
    }

    @Test
    void shouldReturnTrueIfRepositoryContainsCourseEditionWithCourseEditionID_containsOfIdentityMethod() {
        // Arrange
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

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
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);

        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

        Iterator<CourseEditionDDD> iteratorDouble = mock(Iterator.class);
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
        List<CourseEditionDDD> courseEditionsDouble = mock(List.class);
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        when(courseEditionListFactoryDouble.newList()).thenReturn(courseEditionsDouble);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);

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
        ICourseEditionFactoryDDD doubleCourseEditionFactory = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImplDDD = new CourseEditionRepositoryDDDImpl(doubleCourseEditionFactory, courseEditionListFactoryDouble);

        CourseEditionDDD doubleCourseEdition1 = mock(CourseEditionDDD.class);
        CourseEditionDDD doubleCourseEdition2 = mock(CourseEditionDDD.class);
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

        courseEditionRepositoryImplDDD.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        courseEditionRepositoryImplDDD.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1);

        // Act
        List<CourseEditionID> result = courseEditionRepositoryImplDDD.findCourseEditionsByProgrammeEdition(programmeEditionIDDouble1);

        // Assert
        assertEquals(2, result.size());

    }


    @Test
    void shouldReturnCorrectCourseEditionsInList() throws Exception {
        // Arrange
        ICourseEditionFactoryDDD doubleCourseEditionFactory = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionRepositoryDDDImpl courseEditionRepositoryImplDDD = new CourseEditionRepositoryDDDImpl(doubleCourseEditionFactory, courseEditionListFactoryDouble);

        CourseEditionDDD doubleCourseEdition1 = mock(CourseEditionDDD.class);
        CourseEditionDDD doubleCourseEdition2 = mock(CourseEditionDDD.class);
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

        courseEditionRepositoryImplDDD.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        courseEditionRepositoryImplDDD.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1);

        // Act
        List<CourseEditionID> result = courseEditionRepositoryImplDDD.findCourseEditionsByProgrammeEdition(programmeEditionIDDouble1);

        // Assert
        assertTrue(result.contains(doubleCourseEditionId1));
        assertTrue(result.contains(doubleCourseEditionId2));

    }


    @Test
    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
        // Arrange

        CourseEditionDDD doubleCourseEdition = mock(CourseEditionDDD.class);
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
    @Test
    void shouldReturnOptionalWithCourseEditionID() throws Exception{
        //arrange
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);
        List<CourseEditionDDD> courseEdition2s = List.of(courseEditionDouble);
        courseEditionListFactoryDouble = () -> courseEdition2s;
        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(courseEditionDouble.identity()).thenReturn(courseEditionID);

        //act
        Optional<CourseEditionID> result = repository.findIdByCourseEdition(courseEditionDouble);
        //assert
        assertTrue(result.isPresent());

    }

    @Test
    void shouldReturnOptionalEmptyWhenDoNotFindCourseEdition() throws Exception{
        //arrange
        ICourseEditionFactoryDDD courseEditionFactoryDouble = mock(ICourseEditionFactoryDDD.class);
        ICourseEditionListFactoryDDD courseEditionListFactoryDouble = mock(ICourseEditionListFactoryDDD.class);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock(CourseEditionDDD.class);
        List<CourseEditionDDD> courseEdition2s = List.of(courseEditionDouble);
        courseEditionListFactoryDouble = () -> courseEdition2s;
        CourseEditionRepositoryDDDImpl repository = new CourseEditionRepositoryDDDImpl(courseEditionFactoryDouble, courseEditionListFactoryDouble);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(courseEditionDouble.identity()).thenReturn(courseEditionID);

        //act
        Optional<CourseEditionID> result = repository.findIdByCourseEdition(courseEditionDouble2);
        //assert
        assertTrue(result.isEmpty());
    }
}