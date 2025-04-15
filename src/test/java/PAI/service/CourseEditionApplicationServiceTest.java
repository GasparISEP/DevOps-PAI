package PAI.service;

import PAI.VOs.*;
import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ITeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CourseEditionApplicationServiceTest {

    private ICourseEditionRepository courseRepo;
    private ITeacherRepository teacherRepo;
    private CourseEditionApplicationService service;

    private TeacherID teacherID;
    private CourseEditionID courseEditionID;
    private CourseEdition courseEdition;
    private Teacher teacher;

    @BeforeEach
    void setUp() throws Exception {
        courseRepo = mock(ICourseEditionRepository.class);
        teacherRepo = mock(ITeacherRepository.class);
        service = new CourseEditionApplicationService(courseRepo, teacherRepo);

        teacherID = new TeacherID(new TeacherAcronym("ABC"));

        // ProgrammeEditionID dependencies
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Software Engineering 2024");
        Acronym programmeAcronym = new Acronym("SE24");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        SchoolYearID schoolYearID = new SchoolYearID(); // uses default constructor
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // CourseInStudyPlanID dependencies
        CourseID courseID = new CourseID(); // uses default constructor
        PAI.VOs.Date studyPlanDate = new PAI.VOs.Date(java.time.LocalDate.now());
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        CourseInStudyPlanID coursePlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        courseEditionID = new CourseEditionID(programmeEditionID, coursePlanID);

        courseEdition = mock(CourseEdition.class);
        teacher = mock(Teacher.class);
    }

    @Test
    void assignRuc_shouldAssignTeacherWhenCourseAndTeacherExist() {
        // Arrange
        when(courseRepo.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(teacherRepo.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));
        when(teacher.identity()).thenReturn(teacherID);
        when(courseEdition.setRuc(teacherID)).thenReturn(true);

        // Act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);

        // Assert
        assertTrue(result);
        verify(courseEdition).setRuc(teacherID);
        verify(courseRepo).save(courseEdition);
    }

    @Test
    void assignRuc_shouldFailIfCourseEditionNotFound() {
        // Arrange
        when(courseRepo.ofIdentity(courseEditionID)).thenReturn(Optional.empty());

        // Act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);

        // Assert
        assertFalse(result);
        verify(courseRepo, never()).save(any());
    }

    @Test
    void assignRuc_shouldFailIfTeacherNotFound() {
        // Arrange
        when(courseRepo.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(teacherRepo.ofIdentity(teacherID)).thenReturn(Optional.empty());

        // Act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);

        // Assert
        assertFalse(result);
        verify(courseEdition, never()).setRuc(any());
    }

    @Test
    void assignRuc_shouldNotSaveIfSetRucReturnsFalse() {
        // Arrange
        when(courseRepo.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
        when(teacherRepo.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));
        when(teacher.identity()).thenReturn(teacherID);
        when(courseEdition.setRuc(teacherID)).thenReturn(false);

        // Act
        boolean result = service.assignRucToCourseEdition(teacherID, courseEditionID);

        // Assert
        assertFalse(result);
        verify(courseRepo, never()).save(any());
    }

    @Test
    void assignRuc_shouldReturnFalseIfInputIsNull() {
        // Act & Assert
        assertFalse(service.assignRucToCourseEdition(null, courseEditionID));
        assertFalse(service.assignRucToCourseEdition(teacherID, null));
        assertFalse(service.assignRucToCourseEdition(null, null));
    }
}
