package PAI.service.courseEdition;
import PAI.VOs.CourseEditionGeneratedID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.service.teacher.ITeacherService;
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
        ITeacherService teacherService = mock(ITeacherService.class);
        // Act
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);
        // Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionRepositoryIsNull() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DefineRucServiceImpl(null, teacherService)
        );
        assertEquals("CourseEditionRepository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExpectionWhenTeacherServiceIsNull() {

        // arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        // act & assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DefineRucServiceImpl(courseEditionRepository, null)
        );
        assertEquals("TeacherService cannot be null.", exception.getMessage());
    }

    //testing findALl method
    @Test
    void shouldReturnAllCourseEditions() {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        Teacher t1 = mock(Teacher.class);
        Teacher t2 = mock(Teacher.class);
        Iterable<Teacher> expected = List.of(t1, t2);

        when(teacherService.getAllTeachers()).thenReturn(expected);
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherService.existsById(teacherID)).thenReturn(true);
        when(courseEditionRepository.findCourseEditionByGeneratedId(courseEditionID)).thenReturn(Optional.of(courseEdition));
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);


        when(teacherService.existsById(teacherID)).thenReturn(false);
        when(courseEditionRepository.findCourseEditionByGeneratedId(courseEditionID)).thenReturn(Optional.of(courseEdition));
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherService.existsById(teacherID)).thenReturn(true);
        when(courseEditionRepository.findCourseEditionByGeneratedId(courseEditionID)).thenReturn(Optional.empty());
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherService.existsById(teacherID)).thenReturn(true);
        when(courseEditionRepository.findCourseEditionByGeneratedId(courseEditionID)).thenReturn(Optional.of(courseEdition));
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherService.existsById(teacherID)).thenReturn(true);
        when(courseEditionRepository.findCourseEditionByGeneratedId(courseEditionID)).thenReturn(Optional.of(courseEdition));
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);
        //act
        boolean result = service.containsOfIdentity(null);
        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfRepositoryReturnsTrue() {
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
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
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionRepository.containsOfIdentity(courseEditionID)).thenReturn(false);
        // Act
        boolean result = service.containsOfIdentity(courseEditionID);
        // Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionWhenTeacherIsTheSameAsThePreviousRUC() throws Exception {
        // Arrange
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherService teacherService = mock(ITeacherService.class);
        DefineRucServiceImpl service = new DefineRucServiceImpl(courseEditionRepository, teacherService);

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(teacherService.existsById(teacherID)).thenReturn(true);
        when(courseEditionRepository.findCourseEditionByGeneratedId(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(courseEdition.getRuc()).thenReturn(teacherID);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.assignRucToCourseEdition(teacherID, courseEditionID)
        );

        assertEquals("This teacher is already assigned as the RUC for this course edition.", exception.getMessage());
    }
}