package PAI.repository;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.*;
import PAI.factory.ICourseEditionFactory;
import PAI.factory.CourseEditionFactoryImpl;
import PAI.factory.ICourseEditionListFactory;
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
        //SUT = CourseEditionRepository
        //Arrange
            //Doubles' instantiation
        ICourseEditionFactory ICourseEditionFactoryDouble = mock (ICourseEditionFactory.class);
        ICourseEditionListFactory ICourseEditionListFactoryDouble = mock (ICourseEditionListFactory.class);

        CourseEditionDDD courseEditionDouble = mock (CourseEditionDDD.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ICourseEditionFactoryDouble, ICourseEditionListFactoryDouble);

            //instructions
        when(ICourseEditionFactoryDouble.newCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble)).thenReturn(courseEditionDouble);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble,programmeEditionIDDouble);

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
        CourseEditionDDD courseEditionDouble1 = mock (CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock (CourseEditionDDD.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble)).thenReturn(courseEditionDouble2);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble2,programmeEditionIDDouble);

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
        CourseEditionDDD courseEditionDouble1 = mock (CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock (CourseEditionDDD.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(courseEditionDouble1);
        when(courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble2)).thenReturn(courseEditionDouble2);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble2);

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
        CourseEditionDDD courseEditionDouble1 = mock (CourseEditionDDD.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(courseEditionDouble1);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble1);

        //Act
        boolean result = courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble1);

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
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when(courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenThrow();

        //Act
        boolean result = courseEditionRepository1.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        //Assert
        assertFalse(result);
    }

/*
    //US20
    @Test
    void shouldReturnTrueIfCourseEditionContainsRuc() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition and RUC as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEdition_2 courseEditionDouble = mock (CourseEdition_2.class);
        Teacher rucDouble = mock (Teacher.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble1)).thenReturn(courseEditionDouble);
        when (courseEditionDouble.setRuc(rucDouble)).thenReturn(true);

            //auxiliary method
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

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
        CourseEdition_2 courseEditionDouble = mock (CourseEdition_2.class);
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
*/
    @Test
    void shouldReturnSizeOfListOfCourseEditions() throws Exception {
        //SUT = CourseEditionRepository -> CourseEditionFactory, CourseEdition as Doubles
        //Arrange
            //Doubles' instantiation
        CourseEditionFactoryImpl courseEditionFactoryImplDouble = mock (CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionDDD courseEditionDouble1 = mock (CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock (CourseEditionDDD.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble2,programmeEditionIDDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble2);

        //Act
        List<CourseEditionDDD> result = courseEditionRepository.getCourseEditions();

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
        CourseEditionDDD courseEditionDouble1 = mock (CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock (CourseEditionDDD.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble2,programmeEditionIDDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble2);
        List<CourseEditionDDD> allEditions = courseEditionRepository.getCourseEditions();

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
        CourseEditionDDD courseEditionDouble1 = mock (CourseEditionDDD.class);
        CourseEditionDDD courseEditionDouble2 = mock (CourseEditionDDD.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble2 = mock(ProgrammeEditionID.class);

            //SUT
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(courseEditionFactoryImplDouble, courseEditionListFactoryImplDouble);

            //instructions
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble1,programmeEditionIDDouble1)).thenReturn(courseEditionDouble1);
        when (courseEditionFactoryImplDouble.newCourseEdition(courseInStudyPlanIDDouble2,programmeEditionIDDouble2)).thenReturn(courseEditionDouble2);

            //auxiliary methods
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
        List<CourseEditionDDD> allEditions = courseEditionRepository.getCourseEditions();

        //Act
        boolean result = allEditions.contains(courseEditionDouble2);

        //assert
        assertFalse(result);

    }

//
//    @Test
//    void shouldReturnCorrectNumberOfCourseEditionsInProgrammeEdition() throws Exception {
//        // Arrange
//        CourseEditionFactoryImpl doubleCourseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
//        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(doubleCourseEditionFactoryImpl, courseEditionListFactoryImplDouble);
//
//        CourseEdition_2 doubleCourseEdition1 = mock(CourseEdition_2.class);
//        CourseEdition_2 doubleCourseEdition2 = mock(CourseEdition_2.class);
//        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);
//        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
//        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
//        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
//
//
//       when(doubleCourseEditionFactoryImpl.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition1);
//       when(doubleCourseEditionFactoryImpl.newCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition2);
//       when(doubleCourseEdition1.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
//       when(doubleCourseEdition2.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
//
//        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
//        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1);
//
//        // Act
//        List<CourseEdition_2> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition1);
//
//        // Assert
//        assertEquals(2, result.size());
//
//    }
//
//
//    @Test
//    void shouldReturnCorrectCourseEditionsInList() throws Exception {
//        // Arrange
//        CourseEditionFactoryImpl doubleCourseEditionFactory = mock(CourseEditionFactoryImpl.class);
//        CourseEditionListFactoryImpl courseEditionListFactoryDouble = mock(CourseEditionListFactoryImpl.class);
//        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(doubleCourseEditionFactory, courseEditionListFactoryDouble);
//
//        CourseEdition_2 doubleCourseEdition1 = mock(CourseEdition_2.class);
//        CourseEdition_2 doubleCourseEdition2 = mock(CourseEdition_2.class);
//        ProgrammeEdition doubleProgrammeEdition1 = mock(ProgrammeEdition.class);
//        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
//        CourseInStudyPlanID courseInStudyPlanIDDouble2 = mock(CourseInStudyPlanID.class);
//        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
//
//
//        when(doubleCourseEditionFactory.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition1);
//        when(doubleCourseEditionFactory.newCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition2);
//        when(doubleCourseEdition1.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
//        when(doubleCourseEdition2.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
//
//        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
//        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanIDDouble2, programmeEditionIDDouble1);
//
//        // Act
//        List<CourseEdition_2> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition1);
//
//        // Assert
//        assertTrue(result.contains(doubleCourseEdition1));
//        assertTrue(result.contains(doubleCourseEdition2));
//    }
//
//
//    @Test
//    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
//        // Arrange
//
//        ProgrammeEdition doubleProgrammeEdition = mock(ProgrammeEdition.class);
//        CourseEdition_2 doubleCourseEdition = mock(CourseEdition_2.class);
//        CourseEditionFactoryImpl doubleCourseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
//        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
//        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
//
//        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);
//
//
//        CourseEditionRepository repository = new CourseEditionRepository(doubleCourseEditionFactoryImpl, courseEditionListFactoryImplDouble);
//
//        when(doubleCourseEditionFactoryImpl.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(doubleCourseEdition);
//
//        repository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);
//
//        when(repository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCourseEdition)).thenReturn(programmeEditionIDDouble1);
//
//        // Act
//        ProgrammeEditionID result = repository.findWhichProgrammeEditionBelongsToACourseEdition(doubleCourseEdition);
//
//        // Assert
//        assertEquals(doubleProgrammeEdition, result);
//    }
//

    @Test
    void shouldReturnExceptionBecauseCourseEditionDoesNotExist_ListEmpty() {
        //arrange
        CourseEditionFactoryImpl courseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactoryImpl, courseEditionListFactoryImplDouble);
        CourseEditionDDD courseEditionDouble = mock(CourseEditionDDD.class);

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

        CourseEditionDDD courseEditionToFindDouble = mock(CourseEditionDDD.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);


        when(courseEditionFactoryImpl.newCourseEdition(any(), any())).thenReturn(mock(CourseEditionDDD.class));
        repository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        // act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionToFindDouble);
        });

        assertEquals("The course edition does not belong to the course Edition Repository.", exception.getMessage());
    }

    /*
    @Test
    void shouldReturnTheProgrammeEditionThatBelongsToACourseEdition() throws Exception {
        //arrange
        CourseEditionFactoryImpl courseEditionFactoryImpl = mock(CourseEditionFactoryImpl.class);
        CourseEditionListFactoryImpl courseEditionListFactoryImplDouble = mock (CourseEditionListFactoryImpl.class);
        CourseEditionRepository repository = new CourseEditionRepository(courseEditionFactoryImpl, courseEditionListFactoryImplDouble);

        CourseEdition_2 courseEditionDouble = mock(CourseEdition_2.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble1 = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble1 = mock(ProgrammeEditionID.class);


        when(courseEditionDouble.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble1);
        when(courseEditionFactoryImpl.newCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1)).thenReturn(courseEditionDouble);

        repository.createAndSaveCourseEdition(courseInStudyPlanIDDouble1, programmeEditionIDDouble1);

        //act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEditionDouble);

        //assert
        assertEquals(programmeEditionDouble, result);
    }
*/
}