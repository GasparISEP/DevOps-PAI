package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockConstruction;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollment;
import PAI.domain.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.time.LocalDate;

class ProgrammeEditionEnrollmentFactoryTest {

    @Test
    void whenNewProgrammeEditionEnrollmentInvoked_ThenMockObjectShouldBeCreated() {
        // arrange
        Student mockStudent = mock(Student.class);
        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);

        try (MockedConstruction<ProgrammeEditionEnrollment> enrollmentDouble =
                     mockConstruction(ProgrammeEditionEnrollment.class, (mock, context) -> {
                         when(mock.findStudentInProgrammeEdition()).thenReturn((Student) context.arguments().get(0));
                         when(mock.findProgrammeEditionInEnrollment()).thenReturn((ProgrammeEdition) context.arguments().get(1));
                     })) {

            ProgrammeEditionEnrollmentFactory factory = new ProgrammeEditionEnrollmentFactory();

            // act
            ProgrammeEditionEnrollment enrollment =
                    factory.newProgrammeEditionEnrollment(mockStudent, mockProgrammeEdition);

            // assert
            assertEquals(1, enrollmentDouble.constructed().size());
            assertEquals(mockStudent, enrollment.findStudentInProgrammeEdition());
            assertEquals(mockProgrammeEdition, enrollment.findProgrammeEditionInEnrollment());
        }
    }
}
