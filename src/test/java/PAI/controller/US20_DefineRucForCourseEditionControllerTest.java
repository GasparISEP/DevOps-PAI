package PAI.controller;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.teacher.Teacher;
import PAI.service.courseEdition.IDefineRucService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    private IDefineRucService defineRucService;
    private US20_DefineRucForCourseEditionController controller;

    @BeforeEach
    void setUp() {
        defineRucService  = mock(IDefineRucService.class);
        controller  = new US20_DefineRucForCourseEditionController(defineRucService);
    }

    @Test
    void shouldReturnAllTeachersRegisteredInTheService() {
        // Arrange
        List<Teacher> teachers = List.of(mock(Teacher.class));
        when(defineRucService.findAllTeachers()).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = controller.getAllTeachers();

        // Assert
        assertIterableEquals(teachers, result);
        verify(defineRucService).findAllTeachers();
    }

    @Test
    void shouldReturnAllCourseEditionsRegisteredInTheService() {
        // Arrange
        List<CourseEdition> editions = List.of(mock(CourseEdition.class));
        when(defineRucService.findAll()).thenReturn(editions);

        // Act
        Iterable<CourseEdition> result = controller.getAllCourseEditions();

        // Assert
        assertIterableEquals(editions, result);
        verify(defineRucService).findAll();
    }

    @Test
    void shouldDefineRucForCourseEditionWhenServiceReturnsTrue() {
        // Arrange
        CourseEditionID ceId = mock(CourseEditionID.class);
        TeacherID       tId  = mock(TeacherID.class);
        when(defineRucService.assignRucToCourseEdition(tId, ceId)).thenReturn(true);

        // Act
        boolean result = controller.defineRucForCourseEdition(ceId, tId);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenServiceFailsToDefineRuc() {
        // Arrange
        CourseEditionID ceId = mock(CourseEditionID.class);
        TeacherID       tId  = mock(TeacherID.class);
        when(defineRucService.assignRucToCourseEdition(tId, ceId)).thenReturn(false);

        // Act
        boolean result = controller.defineRucForCourseEdition(ceId, tId);

        // Assert
        assertFalse(result);
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
