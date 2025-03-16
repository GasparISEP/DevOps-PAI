package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockConstruction;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class ProgrammeEditionEnrolmentFactoryImplTest {

    @Test
    void whenNewProgrammeEditionEnrollmentInvoked_ThenMockObjectShouldBeCreated() {
        // arrange
        Student mockStudent = mock(Student.class);
        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);

        try (MockedConstruction<ProgrammeEditionEnrolment> enrollmentDouble =
                     mockConstruction(ProgrammeEditionEnrolment.class, (mock, context) -> {
                         when(mock.findStudentInProgrammeEdition()).thenReturn((Student) context.arguments().get(0));
                         when(mock.findProgrammeEditionInEnrollment()).thenReturn((ProgrammeEdition) context.arguments().get(1));
                     })) {

            ProgrammeEditionEnrolmentFactoryImpl factory = new ProgrammeEditionEnrolmentFactoryImpl();

            // act
            ProgrammeEditionEnrolment enrollment =
                    factory.newProgrammeEditionEnrollment(mockStudent, mockProgrammeEdition);

            // assert
            assertEquals(1, enrollmentDouble.constructed().size());
            assertEquals(mockStudent, enrollment.findStudentInProgrammeEdition());
            assertEquals(mockProgrammeEdition, enrollment.findProgrammeEditionInEnrollment());
        }
    }

    @Test
    void whenStudentIsNull_thenThrowIllegalArgumentException() {
        // Arrange:
        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolmentFactoryImpl factory = new ProgrammeEditionEnrolmentFactoryImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.newProgrammeEditionEnrollment(null, mockProgrammeEdition);
        });

        assertEquals("Student cannot be null.", exception.getMessage());
    }

    @Test
    void whenProgrammeEditionIsNull_thenThrowIllegalArgumentException() {
        // Arrange:
        Student mockStudent = mock(Student.class);

        ProgrammeEditionEnrolmentFactoryImpl factory = new ProgrammeEditionEnrolmentFactoryImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.newProgrammeEditionEnrollment(mockStudent, null);
        });

        assertEquals("ProgrammeEdition cannot be null.", exception.getMessage());
    }
}


