package PAI.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;

public class ProgrammeEditionEnrollmentFactoryTest {

    @Test
    void shouldCreateProgrammeEditionEnrollment() throws Exception {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        Student student = mock(Student.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.of(2025, 2, 26);

        // Act
        ProgrammeEditionEnrollment enrollment = factory.newProgrammeEditionEnrollment(student, programmeEdition, enrollmentDate);

        // Assert
        assertNotNull(enrollment); // Checks that ProgrammeEditionEnrollment object is not null
        assertEquals(student, enrollment.findStudentInProgrammeEdition()); // Checks if student matches
        assertEquals(programmeEdition, enrollment.findProgrammeEditionInEnrollment()); // Checks if programmeEdition matches
    }

    @Test
    void shouldThrowExceptionWithNullStudent() {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.of(2025, 2, 26);

        // Act & Assert
        assertThrows(Exception.class, () -> factory.newProgrammeEditionEnrollment(null, programmeEdition, enrollmentDate));
    }

    @Test
    void shouldThrowExceptionWithNullProgrammeEdition() {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        Student student = mock(Student.class);
        LocalDate enrollmentDate = LocalDate.of(2025, 2, 26);

        // Act & Assert
        assertThrows(Exception.class, () -> factory.newProgrammeEditionEnrollment(student, null, enrollmentDate));
    }

    @Test
    void shouldThrowExceptionWithNullEnrollmentDate() {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        Student student = mock(Student.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        // Act & Assert
        assertThrows(Exception.class, () -> factory.newProgrammeEditionEnrollment(student, programmeEdition, null));
    }
}
