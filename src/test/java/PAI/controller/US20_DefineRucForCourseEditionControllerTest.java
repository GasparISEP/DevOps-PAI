package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.ITeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    private ICourseEditionService courseService;
    private ITeacherService       teacherService;
    private US20_DefineRucForCourseEditionController controller;

    @BeforeEach
    void setUp() {
        courseService  = mock(ICourseEditionService.class);
        teacherService = mock(ITeacherService.class);
        controller     = new US20_DefineRucForCourseEditionController(courseService, teacherService);
    }

    @Test
    void shouldReturnAllTeachersRegisteredInTheService() {
        // Arrange
        List<Teacher> teachers = List.of(mock(Teacher.class), mock(Teacher.class));
        when(teacherService.getAllTeachers()).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = controller.getAllTeachers();

        // Assert
        assertIterableEquals(teachers, result);
        verify(teacherService).getAllTeachers();
        verifyNoInteractions(courseService);
    }

    @Test
    void shouldReturnAllCourseEditionsRegisteredInTheService() {
        // Arrange
        List<CourseEdition> editions = List.of(mock(CourseEdition.class), mock(CourseEdition.class));
        when(courseService.findAll()).thenReturn(editions);

        // Act
        Iterable<CourseEdition> result = controller.getAllCourseEditions();

        // Assert
        assertIterableEquals(editions, result);
        verify(courseService).findAll();
        verifyNoInteractions(teacherService);
    }

    @Test
    void shouldDefineRucForCourseEditionWhenIdsExistAndServiceReturnsTrue() {
        // Arrange
        CourseEditionID ceId = mock(CourseEditionID.class);
        TeacherID       tId  = mock(TeacherID.class);
        when(courseService.containsOfIdentity(ceId)).thenReturn(true);
        when(teacherService.existsById(tId)).thenReturn(true);
        when(courseService.assignRucToCourseEdition(tId, ceId)).thenReturn(true);

        // Act
        boolean result = controller.defineRucForCourseEdition(ceId, tId);

        // Assert
        assertTrue(result);
        verify(courseService).containsOfIdentity(ceId);
        verify(teacherService).existsById(tId);
        verify(courseService).assignRucToCourseEdition(tId, ceId);
    }

    @Test
    void shouldReturnFalseWhenServiceFailsToDefineRuc() {
        // Arrange
        CourseEditionID ceId = mock(CourseEditionID.class);
        TeacherID       tId  = mock(TeacherID.class);
        when(courseService.containsOfIdentity(ceId)).thenReturn(true);
        when(teacherService.existsById(tId)).thenReturn(true);
        when(courseService.assignRucToCourseEdition(tId, ceId)).thenReturn(false);

        // Act
        boolean result = controller.defineRucForCourseEdition(ceId, tId);

        // Assert
        assertFalse(result);
        verify(courseService).assignRucToCourseEdition(tId, ceId);
    }

    @Test
    void shouldThrowIfCourseEditionIdDoesNotExist() {
        // Arrange
        CourseEditionID ceId = mock(CourseEditionID.class);
        TeacherID       tId  = mock(TeacherID.class);
        when(courseService.containsOfIdentity(ceId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> controller.defineRucForCourseEdition(ceId, tId)
        );
        assertTrue(ex.getMessage().contains("Edição de curso não encontrada"));
        verify(courseService).containsOfIdentity(ceId);
        verifyNoMoreInteractions(courseService, teacherService);
    }

    @Test
    void shouldThrowIfTeacherIdDoesNotExist() {
        // Arrange
        CourseEditionID ceId = mock(CourseEditionID.class);
        TeacherID       tId  = mock(TeacherID.class);
        when(courseService.containsOfIdentity(ceId)).thenReturn(true);
        when(teacherService.existsById(tId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> controller.defineRucForCourseEdition(ceId, tId)
        );
        assertTrue(ex.getMessage().contains("Professor não encontrado"));
        verify(courseService).containsOfIdentity(ceId);
        verify(teacherService).existsById(tId);
        verify(courseService, never()).assignRucToCourseEdition(any(), any());
    }

    @Test
    void shouldThrowNullPointerWhenArgumentsAreNull() {
        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> controller.defineRucForCourseEdition(null, mock(TeacherID.class))
        );
        assertThrows(NullPointerException.class,
                () -> controller.defineRucForCourseEdition(mock(CourseEditionID.class), null)
        );
    }
}
