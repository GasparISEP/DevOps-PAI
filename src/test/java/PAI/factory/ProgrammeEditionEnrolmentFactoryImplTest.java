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
}
