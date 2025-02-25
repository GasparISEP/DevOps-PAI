package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class US03AddCourseToProgrammeControllerTest {
    
    
    
    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        CourseRepository courseRepository = mock(CourseRepository.class);
        ProgrammeList programmeList = mock(ProgrammeList.class);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //assert
        assertNotNull(US03AddCourseToProgrammeController);
    }


    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        when(programmeDouble.addCourseToAProgramme(courseDouble)).thenThrow(new Exception("Course is already added to the programme."));
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
        });
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldThrowExceptionIfProgrammeListIsNull() {
        // arrange
        ProgrammeList nullProgrammeList = null;
        CourseRepository courseRepository = mock(CourseRepository.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(nullProgrammeList, courseRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseIsNull() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble = mock(Programme.class);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        //act + assert
        assertThrows(Exception.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, null);
        });
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneCourseInList() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Course courseDouble = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);

        // act
        List<Course> allCourses = US03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(1, allCourses.size());
    }

    @Test
    void shouldReturnCourseInList() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Course courseDouble = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);

        // act
        List<Course> allCourses = US03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(courseDouble, allCourses.get(0));
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeInList() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble = mock(Programme.class);

        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programmeDouble);

        when(programmeListDouble.getAllProgrammes()).thenReturn(programmeList);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);

        // act
        List<Programme> allProgrammes = US03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(1, allProgrammes.size());
    }

    @Test
    void shouldReturnProgrammeInList() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble = mock(Programme.class);

        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programmeDouble);

        when(programmeListDouble.getAllProgrammes()).thenReturn(programmeList);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        // act
        List<Programme> allProgrammes = US03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(programmeDouble, allProgrammes.get(0));
    }

    @Test
    void shouldReturnAllProgrammes() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Programme programmeDouble1 = mock(Programme.class);
        Programme programmeDouble2 = mock(Programme.class);

        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programmeDouble1);
        programmeList.add(programmeDouble2);

        when(programmeListDouble.getAllProgrammes()).thenReturn(programmeList);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        // act
        List<Programme> allProgrammes = US03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(2, allProgrammes.size());
        assertEquals(programmeList.get(0), allProgrammes.get(0));
        assertEquals(programmeList.get(1), allProgrammes.get(1));
    }

    @Test
    void shouldReturnAllCourses() throws Exception {
        // arrange
        ProgrammeList programmeListDouble = mock(ProgrammeList.class);
        CourseRepository courseRepositoryDouble = mock(CourseRepository.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble1);
        courseList.add(courseDouble2);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
        // act
        List<Course> allCourses = US03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(courseList.size(), allCourses.size());
        assertEquals(courseList.get(0), allCourses.get(0));
        assertEquals(courseList.get(1), allCourses.get(1));
    }

    @Test
    void shouldThrowExceptionIfCourseRepositoryIsNull() {
        // arrange
        ProgrammeList programmeList = mock(ProgrammeList.class);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeList, null);
        });
    }
}
