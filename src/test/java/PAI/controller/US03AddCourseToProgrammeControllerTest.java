package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class US03AddCourseToProgrammeControllerTest {

    private US03_AddCourseToProgrammeController us03AddCourseToProgrammeController;
    private ProgrammeList programmeListDouble;
    private CourseRepository courseRepositoryDouble;

    @BeforeEach
    void setUp() throws Exception {
        programmeListDouble = mock(ProgrammeList.class);
        courseRepositoryDouble = mock(CourseRepository.class);
        us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeListDouble, courseRepositoryDouble);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);

        when(programmeDouble.addCourseToAProgramme(courseDouble)).thenThrow(new Exception("Course is already added to the programme."));
        //act + assert
        assertThrows(Exception.class, () -> {
            us03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
        });
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);
        //act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
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
    void shouldThrowExceptionIfCourseIsNull() {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        //act + assert
        assertThrows(Exception.class, () -> {
            us03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, null);
        });
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneCourseInList(){
        // arrange
        Course courseDouble = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);

        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnCourseInList() {
        // arrange
        Course courseDouble = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);

        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(courseDouble, result.get(0));
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeInList() {
        // arrange
        Programme programmeDouble = mock(Programme.class);

        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programmeDouble);

        when(programmeListDouble.getAllProgrammes()).thenReturn(programmeList);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnProgrammeInList() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);

        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programmeDouble);

        when(programmeListDouble.getAllProgrammes()).thenReturn(programmeList);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(programmeDouble, result.get(0));
    }

    @Test
    void shouldReturnAllProgrammes() {
        // arrange
        Programme programmeDouble1 = mock(Programme.class);
        Programme programmeDouble2 = mock(Programme.class);

        List<Programme> programmeList = new ArrayList<>();
        programmeList.add(programmeDouble1);
        programmeList.add(programmeDouble2);

        when(programmeListDouble.getAllProgrammes()).thenReturn(programmeList);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(2, result.size());
        assertEquals(programmeList.get(0), result.get(0));
        assertEquals(programmeList.get(1), result.get(1));
    }

    @Test
    void shouldReturnAllCourses() throws Exception {
        // arrange
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(courseDouble1);
        courseList.add(courseDouble2);

        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseList);

        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(courseList.size(), result.size());
        assertEquals(courseList.get(0), result.get(0));
        assertEquals(courseList.get(1), result.get(1));
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
