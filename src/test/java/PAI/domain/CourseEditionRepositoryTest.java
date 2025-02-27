package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionRepositoryTest {

    //US19
    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory and CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock (Course.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(courseEditionDouble);

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
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble2, programmeEditionDouble)).thenReturn(courseEditionDouble2);

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
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble2)).thenReturn(courseEditionDouble2);

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
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenReturn(courseEditionDouble1);

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
        CourseEditionFactory courseEditionFactoryDouble = mock (CourseEditionFactory.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        Course courseDouble1 = mock (Course.class);

            //SUT
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when(courseEditionFactoryDouble.newCourseEdition(courseDouble1, programmeEditionDouble1)).thenThrow();

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
        CourseEditionFactory courseEditionFactoryDouble = mock(CourseEditionFactory.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);
        Teacher rucDouble = mock (Teacher.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble,programmeEditionDouble)).thenReturn(courseEditionDouble);
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
        CourseEditionFactory courseEditionFactoryDouble = mock(CourseEditionFactory.class);
        CourseEdition courseEditionDouble = mock (CourseEdition.class);
        Teacher rucDouble = mock (Teacher.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble,programmeEditionDouble)).thenReturn(courseEditionDouble);
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
        CourseEditionFactory courseEditionFactoryDouble = mock(CourseEditionFactory.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble1,programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble2,programmeEditionDouble2)).thenReturn(courseEditionDouble2);

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
        CourseEditionFactory courseEditionFactoryDouble = mock(CourseEditionFactory.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble1,programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble2,programmeEditionDouble2)).thenReturn(courseEditionDouble2);

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
        CourseEditionFactory courseEditionFactoryDouble = mock(CourseEditionFactory.class);
        CourseEdition courseEditionDouble1 = mock (CourseEdition.class);
        CourseEdition courseEditionDouble2 = mock (CourseEdition.class);
        Course courseDouble1 = mock (Course.class);
        Course courseDouble2 = mock (Course.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryDouble);

            //instructions
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble1,programmeEditionDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryDouble.newCourseEdition(courseDouble2,programmeEditionDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseDouble1, programmeEditionDouble1);
        List<CourseEdition> allEditions = courseEditionRepository.getCourseEditions();

        //Act
        boolean result = allEditions.contains(courseEditionDouble2);

        //assert
        assertFalse(result);

    }


    @Test
    void shouldReturnCourseEditionsWithSameProgrammeEdition() throws Exception {
        // Arrange
        Course doubleCourse1 = mock(Course.class);
        Course doubleCourse2 = mock(Course.class);
        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);

        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactory);
        courseEditionRepository.createAndSaveCourseEdition(doubleCourse1, doubleProgrammeEdition1);
        courseEditionRepository.createAndSaveCourseEdition(doubleCourse2, doubleProgrammeEdition1);


        // Act
        List<CourseEdition> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition1);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(new CourseEdition(doubleCourse1, doubleProgrammeEdition1)));
        assertTrue(result.contains(new CourseEdition(doubleCourse2, doubleProgrammeEdition1)));

    }


    @Test
    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
        // Arrange
        Course doubleCourse1 = mock(Course.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionFactory doubleCourseEditionFactory = mock(CourseEditionFactory.class);
        CourseEditionRepository repository = new CourseEditionRepository(doubleCourseEditionFactory);

        when(doubleCourseEditionFactory.newCourseEdition(doubleCourse1, programmeEdition)).thenReturn(courseEdition);

        repository.createAndSaveCourseEdition(doubleCourse1, programmeEdition);

        when(repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition)).thenReturn(programmeEdition);

        // Act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        // Assert
        assertNotNull(result, "The returned ProgrammeEdition should not be null.");
        assertEquals(programmeEdition, result, "The returned ProgrammeEdition should match the one associated with the CourseEdition.");
    }

    //US16
    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListEmpty() throws Exception {
        //arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactory);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);
        });

        // assert
        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListWithElements() throws Exception {
        // arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactory);

        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);
        Course mockCourse1 = mock(Course.class);
        Course mockCourse2 = mock(Course.class);

        CourseEdition existingCourseEditionDouble = mock(CourseEdition.class);
        CourseEdition courseEditionToFindDouble = mock(CourseEdition.class);

        repository.createAndSaveCourseEdition(mockCourse1, mockProgrammeEdition);

        // act
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionToFindDouble);
        });

        // assert
        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    @Test
    void shouldReturnTheProgrammeEditionThatBelongsToACourseEdition() throws Exception {
        //arrange
        CourseEditionFactory courseEditionFactory = mock(CourseEditionFactory.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactory);

        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Course courseDouble = mock(Course.class);

        when(courseEditionDouble.whatProgrammeEditionBelongsThisCourseEdition()).thenReturn(programmeEditionDouble);
        when(courseEditionFactory.newCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(courseEditionDouble);

        repository.createAndSaveCourseEdition(courseDouble, programmeEditionDouble);

        //act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);

        //assert
        assertEquals(programmeEditionDouble, result);
    }

}