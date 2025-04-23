package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.service.ICourseEditionApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    private ICourseEditionApplicationService service;
    private US20_DefineRucForCourseEditionController controller;

    @BeforeEach
    void setUp() {
        service = mock(ICourseEditionApplicationService.class);
        controller = new US20_DefineRucForCourseEditionController(service);
    }

    @Test
    void shouldReturnAllTeachersRegisteredInTheService() {
        // Arrange
        List<Teacher> teachers = List.of(mock(Teacher.class), mock(Teacher.class));
        when(service.getAllTeachers()).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = controller.getAllTeachers();

        // Assert
        assertIterableEquals(teachers, result);
        verify(service).getAllTeachers();
    }

    @Test
    void shouldReturnAllCourseEditionsRegisteredInTheService() {
        // Arrange
        List<CourseEdition> courseEditions = List.of(mock(CourseEdition.class), mock(CourseEdition.class));
        when(service.getAllCourseEditions()).thenReturn(courseEditions);

        // Act
        Iterable<CourseEdition> result = controller.getAllCourseEditions();

        // Assert
        assertIterableEquals(courseEditions, result);
        verify(service).getAllCourseEditions();
    }

    @Test
    void shouldDefineRucForCourseEdition() throws Exception {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(service.assignRucToCourseEdition(teacherID, courseEditionID)).thenReturn(true);

        // Act
        boolean result = controller.defineRucForCourseEdition(courseEditionID, teacherID);

        // Assert
        assertTrue(result);
        verify(service).assignRucToCourseEdition(teacherID, courseEditionID);
    }

    @Test
    void shouldReturnFalseIfServiceFailsToDefineRuc() throws Exception {
        // Arrange
        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(service.assignRucToCourseEdition(teacherID, courseEditionID)).thenReturn(false);

        // Act
        boolean result = controller.defineRucForCourseEdition(courseEditionID, teacherID);

        // Assert
        assertFalse(result);
        verify(service).assignRucToCourseEdition(teacherID, courseEditionID);
    }
}
