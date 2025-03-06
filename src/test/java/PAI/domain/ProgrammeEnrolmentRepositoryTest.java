package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentRepositoryTest {

    @Test
    void shouldCreateObject() {
        //arrange
        ProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(ProgrammeEnrolmentFactory.class);

        //act
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory);
    }

    @Test
    void shouldReturnTrueIfTheEnrolmentInTheProgrammeIsSuccessful() throws Exception {
        //arrange
        Student studentDouble1 = mock(Student.class);
        Student studentDouble2 = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);

        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble);

        //enrolment of studentDouble1 to not have an empty list
        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble1, accessMethodDouble, programmeDouble, "12-12-2025"))
                .thenReturn(programmeEnrolmentDouble);

        programmeEnrolmentRepository.enrolStudents(studentDouble1, accessMethodDouble, programmeDouble, "12-12-2025");

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble, "12-12-2025"))
                .thenReturn(programmeEnrolmentDouble);

        //act
        boolean result = programmeEnrolmentRepository.enrolStudents(studentDouble2, accessMethodDouble, programmeDouble, "12-12-2025");

        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionIfEnrolmentIsNotSuccessfulBecauseStudentIsAlreadyEnrolledInTheProgramme() throws Exception {
        //arrange
        Student studentDouble = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolment programmeEnrolmentDouble1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment programmeEnrolmentDouble2 = mock(ProgrammeEnrolment.class);

        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "12-12-2025"))
                .thenReturn(programmeEnrolmentDouble1);

        enrolmentRepository.enrolStudents(studentDouble, accessMethodDouble, programmeDouble, "12-12-2025");

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "12-12-2025"))
                .thenReturn(programmeEnrolmentDouble2);

        when(programmeEnrolmentDouble1.hasSameEnrolment(programmeEnrolmentDouble2)).thenReturn(true);

        //act + assert
        assertThrows(Exception.class, () -> enrolmentRepository.enrolStudents(studentDouble, accessMethodDouble, programmeDouble, "12-12-2025"));
    }

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() throws Exception {
        // Arrange
        Student studentDouble = mock(Student.class);
        Programme programmeDouble = mock(Programme.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, "12-04-2020"))
                .thenReturn(programmeEnrolmentDouble);

        programmeEnrolmentRepository.enrolStudents(studentDouble, accessMethodDouble, programmeDouble, "12-04-2020");

        when(programmeEnrolmentDouble.hasSameStudent(studentDouble)).thenReturn(true);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeDouble)).thenReturn(true);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentDouble, programmeDouble);

        // Assert
        assertTrue(result);
    }

    //US17
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() throws Exception {
        // Arrange
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble);
        Student studentDouble1 = mock(Student.class);
        Student studentDouble2 = mock(Student.class);
        Programme programmeDouble = mock(Programme.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble1, accessMethodDouble, programmeDouble, "12-04-2020"))
                .thenReturn(programmeEnrolmentDouble);

        programmeEnrolmentRepository.enrolStudents(studentDouble1, accessMethodDouble, programmeDouble, "12-04-2020");

        when(programmeEnrolmentDouble.hasSameStudent(studentDouble2)).thenReturn(false);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeDouble)).thenReturn(true);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentDouble2, programmeDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTheSameStudentIsEnrolledInADifferentProgramme() throws Exception {
        // Arrange
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble);
        Student studentDouble = mock(Student.class);
        Programme programmeDouble1 = mock(Programme.class);
        Programme programmeDouble2 = mock(Programme.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble1, "12-04-2020"))
                .thenReturn(programmeEnrolmentDouble);

        programmeEnrolmentRepository.enrolStudents(studentDouble, accessMethodDouble, programmeDouble1, "12-04-2020");

        when(programmeEnrolmentDouble.hasSameStudent(studentDouble)).thenReturn(true);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeDouble2)).thenReturn(false);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentDouble, programmeDouble2);

        // Assert
        assertFalse(result);
    }
}