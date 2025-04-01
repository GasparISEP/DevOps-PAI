package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockConstruction;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class ProgrammeEditionEnrolmentFactoryImplTest {

    @Test
    void whenNewProgrammeEditionEnrolmentInvoked_ThenMockObjectShouldBeCreated() {
        // arrange
        StudentID mockStudentId = mock(StudentID.class);
        ProgrammeEditionID mockProgrammeEditionId = mock(ProgrammeEditionID.class);

        try (MockedConstruction<ProgrammeEditionEnrolment> enrollmentDouble =
                     mockConstruction(ProgrammeEditionEnrolment.class, (mock, context) -> {
                         when(mock.findStudentInProgrammeEdition()).thenReturn((StudentID) context.arguments().get(0));
                         when(mock.findProgrammeEditionInEnrolment()).thenReturn((ProgrammeEditionID) context.arguments().get(1));
                     })) {

            ProgrammeEditionEnrolmentFactoryImpl factory = new ProgrammeEditionEnrolmentFactoryImpl();

            // act
            ProgrammeEditionEnrolment enrolment =
                    factory.newProgrammeEditionEnrolment(mockStudentId, mockProgrammeEditionId);

            // assert
            assertEquals(1, enrollmentDouble.constructed().size());
            assertEquals(mockStudentId, enrolment.findStudentInProgrammeEdition());
            assertEquals(mockProgrammeEditionId, enrolment.findProgrammeEditionInEnrolment());
        }
    }

    @Test
    void whenStudentIsNull_thenThrowIllegalArgumentException() {
        // Arrange:
        ProgrammeEditionID mockProgrammeEditionId = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentFactoryImpl factory = new ProgrammeEditionEnrolmentFactoryImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.newProgrammeEditionEnrolment(null, mockProgrammeEditionId);
        });

        assertEquals("Student cannot be null.", exception.getMessage());
    }

    @Test
    void whenProgrammeEditionIsNull_thenThrowIllegalArgumentException() {
        // Arrange:
        StudentID mockStudentId = mock(StudentID.class);

        ProgrammeEditionEnrolmentFactoryImpl factory = new ProgrammeEditionEnrolmentFactoryImpl();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.newProgrammeEditionEnrolment(mockStudentId, null);
        });

        assertEquals("ProgrammeEdition cannot be null.", exception.getMessage());
    }
}


