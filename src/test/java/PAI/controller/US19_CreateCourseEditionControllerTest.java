package PAI.controller;

import PAI.domain.*;
import PAI.repository.CourseEditionRepository;
import PAI.repository.ProgrammeEditionRepository;
import PAI.repository.ProgrammeList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US19_CreateCourseEditionControllerTest {


    @Test
    void shouldReturnTrueIfCourseEditionIsCreated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock (ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (courseEditionRepositoryDouble.createAndSaveCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(true);

        // Act
        boolean result = controller.createCourseEdition(courseDouble, programmeEditionDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock (ProgrammeEdition.class);
        Course course = mock (Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (courseEditionRepositoryDouble.createAndSaveCourseEdition(course, programmeEditionDouble)).thenReturn(false);

        // Act
        boolean result = controller.createCourseEdition(course, programmeEditionDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        List<ProgrammeEdition> allEditionsDouble = mock (List.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(allEditionsDouble);

        //Act
        controller.getAllProgrammeEditions();
        //Assert
        assertNotNull(allEditionsDouble);
    }


    @Test
    void ShouldReturnSizeOfListOfProgrammeEditionsForMethodGetAllProgrammeEditions() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

        //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(List.of(programmeEditionDouble1));

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertEquals(1, controller.getAllProgrammeEditions().size());
    }


    @Test
    void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(List.of(programmeEditionDouble));

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertTrue(controller.getAllProgrammeEditions().contains(programmeEditionDouble));
    }


    @Test
    void shouldReturnFalseIfListOfProgrammeEditionsDoesNotContainProgrammeEdition() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(List.of(programmeEditionDouble2));

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertFalse(controller.getAllProgrammeEditions().contains(programmeEditionDouble));
    }


    @Test
    void shouldReturnSizeOfCourseListInProgrammeForGetCoursesInProgrammeMethod() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

        //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(List.of(courseDouble1, courseDouble2));

        // Act
        controller.getCoursesInProgramme(programmeEditionDouble);

        // Assert
        assertEquals(2, controller.getCoursesInProgramme(programmeEditionDouble).size());
    }

    @Test
    void shouldReturnNotNullEvenIfCourseListIsEmptyInProgrammeForGetCoursesInProgrammeMethod() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        List <Course> courseListDouble= mock(List.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(courseListDouble);

        // Act + Assert
        assertNotNull(controller.getCoursesInProgramme(programmeEditionDouble));
    }

    @Test
    void shouldReturnTrueIfCourseListHasCourseInProgrammeForGetCoursesInProgrammeMethod() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(List.of(courseDouble1, courseDouble2));

        // Act+Assert
        assertTrue(controller.getCoursesInProgramme(programmeEditionDouble).contains(courseDouble1));
    }

    @Test
    void shouldReturnFalseIfCourseListNotHaveCourseInProgrammeForGetCoursesInProgrammeMethod() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(List.of(courseDouble1));


        // Act
        controller.getCoursesInProgramme(programmeEditionDouble);

        // Assert
        assertFalse(controller.getCoursesInProgramme(programmeEditionDouble).contains(courseDouble2));
    }

}