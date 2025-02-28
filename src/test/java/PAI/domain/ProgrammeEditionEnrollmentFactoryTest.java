package PAI.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;

public class ProgrammeEditionEnrollmentFactoryTest {

    @Test
    void shouldCreateProgrammeEditionEnrollment()  {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        Student student = mock(Student.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        // Act
        ProgrammeEditionEnrollment enrollment = factory.newProgrammeEditionEnrollment(student, programmeEdition, enrollmentDate);

        // Assert
        assertNotNull(enrollment);
        assertEquals(student, enrollment.findStudentInProgrammeEdition());
        assertEquals(programmeEdition, enrollment.findProgrammeEditionInEnrollment());
    }

    @Test
    void shouldThrowExceptionWithNullStudent() {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        // Act & Assert
        assertThrows(Exception.class, () -> factory.newProgrammeEditionEnrollment(null, programmeEdition, enrollmentDate));
    }

    @Test
    void shouldThrowExceptionWithNullProgrammeEdition() {
        // Arrange
        ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();
        Student student = mock(Student.class);
        LocalDate enrollmentDate = LocalDate.now();

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
