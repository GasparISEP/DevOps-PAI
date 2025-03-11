package PAI.repository;

import PAI.domain.*;
import PAI.factory.CourseEditionFactoryImpl;
import PAI.factory.CourseEditionListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionRepositoryTest {

    //US19
    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(courseEditionDouble);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble,programmeEditionDouble);

        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasDifferentCourseButTheSameProgrammeEdition() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble1, programmeEditionDouble)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble2, programmeEditionDouble)).thenReturn(courseEditionDouble2);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble2,programmeEditionDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasSameCourseButDifferentProgrammeEdition() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble1, programmeEditionDouble2)).thenReturn(courseEditionDouble2);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble2);

        //Assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfTheCourseEditionIsAlreadyRegistered() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenReturn(courseEditionDouble1);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble1);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseDouble1,programmeEditionDouble1);

        //Assert
        assertFalse(result);

    }


    @Test
    void shouldReturnFalseIfCourseOrProgrammeEditionAreNull() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);

            //SUT
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenThrow();

        //Act
        boolean result = courseEditionRepository1.createAndSaveCourseEdition(courseDouble1, programmeEditionDouble1);

        //Assert
        assertFalse(result);
    }


    //US20
    @Test
    void shouldReturnTrueIfCourseEditionContainsRuc() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition and RUC as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);
        Teacher rucDouble = mock (Teacher.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble,programmeEditionDouble)).thenReturn(courseEditionDouble);
        when (courseEditionDouble.setRuc(rucDouble)).thenReturn(true);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseDouble, programmeEditionDouble);

        //Act
        boolean result = courseEditionRepository.setRucInACourseEdition(courseEditionDouble, rucDouble);

        //Assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfCourseEditionDoesNotContainsRuc() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition and RUC as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);
        Teacher rucDouble = mock (Teacher.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble,programmeEditionDouble)).thenReturn(courseEditionDouble);
        when (courseEditionDouble.setRuc(rucDouble)).thenReturn(false);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseDouble, programmeEditionDouble);

        //Act
        boolean result = courseEditionRepository.setRucInACourseEdition(courseEditionDouble, rucDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnSizeOfListOfCourseEditions() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble1,programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble2,programmeEditionDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseDouble1, programmeEditionDouble1);
        courseEditionRepository.createAndSaveCourseEdition(courseDouble2, programmeEditionDouble2);

        //Act
        List<CourseEdition> result = courseEditionRepository.getCourseEditions();

        //assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnTrueIfCourseEditionListContainsCourseEdition() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble1,programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble2,programmeEditionDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseDouble1, programmeEditionDouble1);
        courseEditionRepository.createAndSaveCourseEdition(courseDouble2, programmeEditionDouble2);
        List<CourseEdition> allEditions = courseEditionRepository.getCourseEditions();

        //Act
        boolean result = allEditions.contains(courseEditionDouble1);

        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnFalseIfCourseEditionListNOTContainsCourseEdition() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble1,programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryImplDouble.newCourseEdition(courseDouble2,programmeEditionDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseDouble1, programmeEditionDouble1);
        List<CourseEdition> allEditions = courseEditionRepository.getCourseEditions();

        //Act
        boolean result = allEditions.contains(courseEditionDouble2);

        //assert
        assertFalse(result);

    }


    @Test
    void shouldReturnCorrectNumberOfCourseEditionsInProgrammeEdition() throws Exception {
        // Arrange
        CourseEditionFactoryImpl doubleCourseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(doubleCourseEditionFactoryImpl, courseEditionListFactoryImplDouble);
        Course doubleCourse1 = mock(Course.class);
        Course doubleCourse2 = mock(Course.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock(CourseEdition.class);
        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);

       when(doubleCourseEditionFactoryImpl.newCourseEdition(doubleCourse1,doubleProgrammeEdition1)).thenReturn(doubleCourseEdition1);
       when(doubleCourseEditionFactoryImpl.newCourseEdition(doubleCourse2,doubleProgrammeEdition1)).thenReturn(doubleCourseEdition2);
       when(doubleCourseEdition1.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(doubleProgrammeEdition1);
       when(doubleCourseEdition2.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(doubleProgrammeEdition1);

        courseEditionRepository.createAndSaveCourseEdition(doubleCourse1, doubleProgrammeEdition1);
        courseEditionRepository.createAndSaveCourseEdition(doubleCourse2, doubleProgrammeEdition1);

        // Act
        List<CourseEdition> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition1);

        // Assert
        assertEquals(2, result.size());

    }

    @Test
    void shouldReturnCorrectCourseEditionsInList() throws Exception {
        // Arrange
        CourseEditionFactoryImpl doubleCourseEditionFactory = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryDouble = mock(CourseEditionListFactoryImpl.class);
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(doubleCourseEditionFactory, courseEditionListFactoryDouble);
        Course doubleCourse1 = mock(Course.class);
        Course doubleCourse2 = mock(Course.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock(CourseEdition.class);
        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);

        when(doubleCourseEditionFactory.newCourseEdition(doubleCourse1, doubleProgrammeEdition1)).thenReturn(doubleCourseEdition1);
        when(doubleCourseEditionFactory.newCourseEdition(doubleCourse2, doubleProgrammeEdition1)).thenReturn(doubleCourseEdition2);
        when(doubleCourseEdition1.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(doubleProgrammeEdition1);
        when(doubleCourseEdition2.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(doubleProgrammeEdition1);

        courseEditionRepository.createAndSaveCourseEdition(doubleCourse1, doubleProgrammeEdition1);
        courseEditionRepository.createAndSaveCourseEdition(doubleCourse2, doubleProgrammeEdition1);

        // Act
        List<CourseEdition> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition1);

        // Assert
        assertTrue(result.contains(doubleCourseEdition1));
        assertTrue(result.contains(doubleCourseEdition2));
    }


    @Test
    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
        // Arrange
        Course doubleCourse1 = mock(Course.class);
        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
        CourseEdition doubleCourseEdition = mock(CourseEdition.class);
        CourseEditionFactoryImpl doubleCourseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);

        CourseEditionRepository repository = new CourseEditionRepository(doubleCourseEditionFactoryImpl, courseEditionListFactoryImplDouble);

        when(doubleCourseEditionFactoryImpl.newCourseEdition(doubleCourse1, doubleProgrammeEdition)).thenReturn(doubleCourseEdition);

        repository.createAndSaveCourseEdition(doubleCourse1, doubleProgrammeEdition);

        when(repository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCourseEdition)).thenReturn(doubleProgrammeEdition);

        // Act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCourseEdition);

        // Assert
        assertEquals(doubleProgrammeEdition, result);
    }

    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListEmpty() {
        //arrange
        CourseEditionFactoryImpl courseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactoryImpl, courseEditionListFactoryImplDouble);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);
        });

        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListWithElements() throws Exception {
        // arrange
        CourseEditionFactoryImpl courseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactoryImpl, courseEditionListFactoryImplDouble);
        ProgrammeEdition ProgrammeEditionDouble = mock(ProgrammeEdition.class);
        Course CourseDouble = mock(Course.class);
        CourseEdition courseEditionToFindDouble = mock(CourseEdition.class);

        when(courseEditionFactoryImpl.newCourseEdition(any(), any())).thenReturn(mock(CourseEdition.class));
        repository.createAndSaveCourseEdition(CourseDouble, ProgrammeEditionDouble);

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionToFindDouble);
        });

        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    @Test
    void shouldReturnTheProgrammeEditionThatBelongsToACourseEdition() throws Exception {
        //arrange
        CourseEditionFactoryImpl courseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactoryImpl, courseEditionListFactoryImplDouble);

        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock(Course.class);

        when(courseEditionDouble.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(programmeEditionDouble);
        when(courseEditionFactoryImpl.newCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(courseEditionDouble);

        repository.createAndSaveCourseEdition(courseDouble, programmeEditionDouble);

        //act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);

        //assert
        assertEquals(programmeEditionDouble, result);
    }

}