package PAI.service.courseEdition;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DefineRucServiceImplTest {

    //testing the constructor
    @Test
    void shouldCreateInstanceWhenDependenciesAreValid() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        // Act
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);
        // Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionRepositoryIsNull() {
        // Arrange
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DefineRucServiceImpl(null, teacherRepository)
        );
        assertEquals("CourseEditionRepository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTeacherRepositoryIsNull() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DefineRucServiceImpl(courseEditionRepository, null)
        );
        assertEquals("TeacherRepository cannot be null", exception.getMessage());
    }

    //testing findALl method
    @Test
    void shouldReturnAllCourseEditions() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEdition ce2 = mock(CourseEdition.class);
        Iterable<CourseEdition> listOfCourseEditions = List.of(ce1, ce2);

        when(courseEditionRepository.findAll()).thenReturn(listOfCourseEditions);
        // Act
        Iterable<CourseEdition> result = service.findAll();
        // Assert
        assertEquals(listOfCourseEditions, result);
    }

    //testing findAllTeachers method
    @Test
    void shouldReturnAllTeachers() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        Teacher t1 = mock(Teacher.class);
        Teacher t2 = mock(Teacher.class);
        Iterable<Teacher> expected = List.of(t1, t2);

        when(teacherRepository.findAll()).thenReturn(expected);
        // Act
        Iterable<Teacher> result = service.findAllTeachers();
        // Assert
        assertEquals(expected, result);
    }

    //testing assignRucToCourseEdition method
    @Test
    void shouldReturnTrueIfRucSuccessfullyAssignedToCourseEdition() throws Exception {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);
        when(courseEdition.setRuc(teacherID)).thenReturn(true);
        //act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherDoesNotExist() throws Exception {
        //arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);


        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(false);
        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);
        when(courseEdition.setRuc(teacherID)).thenReturn(true);
        //act & assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.assignRucToCourseEdition(teacherID, courseEditionID)
        );

        assertEquals("Teacher with given ID does not exist.", exception.getMessage());
    }

    @Test
    void shouldReturnFalseWhenCourseEditionDoesNotExist() throws Exception {
        //arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.empty());
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);
        when(courseEdition.setRuc(teacherID)).thenReturn(true);
        //act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSetRucFails() throws Exception {
        //arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(courseEditionRepository.save(courseEdition)).thenReturn(courseEdition);
        when(courseEdition.setRuc(teacherID)).thenReturn(false);
        //act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenSaveFails() throws Exception {
        //arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherRepository.containsOfIdentity(teacherID)).thenReturn(true);
        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(courseEditionRepository.save(courseEdition)).thenThrow(new RuntimeException("DB failure"));
        when(courseEdition.setRuc(teacherID)).thenReturn(true);
        //act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.assignRucToCourseEdition(teacherID, courseEditionID)
        );
        assertTrue(exception.getMessage().contains("Error when persisting CourseEdition with new RUC"));
    }

    //testing containsOfIdentity method
    @Test
    void shouldReturnFalseWhenCourseEditionIDIsNull() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);
        //act
        boolean result = service.containsOfIdentity(null);
        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfRepositoryReturnsTrue() {
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);
        CourseEditionID courseEditionID= mock(CourseEditionID.class);
        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(true);
        // Act
        boolean result = service.containsOfIdentity(courseEditionID);
        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfRepositoryReturnsFalse() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherRepository);
        CourseEditionID courseEditionID= mock(CourseEditionID.class);

        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(false);
        // Act
        boolean result = service.containsOfIdentity(courseEditionID);
        // Assert
        assertFalse(result);
    }
}