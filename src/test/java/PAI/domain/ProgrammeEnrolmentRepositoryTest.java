package PAI.domain;

import PAI.factory.ProgrammeEnrolmentFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentRepositoryTest {

    @Test
    void shouldCreateObject() {
        //arrange
        ProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);

        //act
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, listFactoryDouble);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEnrolmentFactoryIsNull() {
        //arrange
        ProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentListFactory listFactoryDouble = null;

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, listFactoryDouble));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEnrolmentRepositoryFactoryIsNull() {
        //arrange
        ProgrammeEnrolmentFactory programmeEnrolmentFactory = null;
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, listFactoryDouble));
    }

    @Test
    void shouldReturnTrueIfTheEnrolmentInTheProgrammeIsSuccessful() throws Exception {
        //arrange
        Student studentDouble1 = mock(Student.class);
        AccessMethod accessMethodDouble = mock(AccessMethod.class);
        Programme programmeDouble = mock(Programme.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);
        ProgrammeEnrolment programmeEnrolmentDouble1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment programmeEnrolmentDouble2 = mock(ProgrammeEnrolment.class);

        //create programmeEnrolmentRepository
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator da lista
        Iterator<ProgrammeEnrolment> it = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(it);

        when(it.hasNext()).thenReturn(true, false);

        when(it.next()).thenReturn(programmeEnrolmentDouble1);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble1, accessMethodDouble, programmeDouble, "12-12-2025"))
                .thenReturn(programmeEnrolmentDouble2);

        when(programmeEnrolmentDouble1.hasSameEnrolment(programmeEnrolmentDouble2)).thenReturn(false);

        //act
        boolean result = programmeEnrolmentRepository.enrolStudents(studentDouble1, accessMethodDouble, programmeDouble, "12-12-2025");

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
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolment programmeEnrolmentDouble1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment programmeEnrolmentDouble2 = mock(ProgrammeEnrolment.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator
        Iterator<ProgrammeEnrolment> itDouble = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(false, true, false);

        when(itDouble.next()).thenReturn(programmeEnrolmentDouble1);

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
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() {
        // Arrange
        Student studentDouble = mock(Student.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator
        Iterator<ProgrammeEnrolment> itDouble = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true);

        when(itDouble.next()).thenReturn(programmeEnrolmentDouble);

        when(programmeEnrolmentDouble.hasSameStudent(studentDouble)).thenReturn(true);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeDouble)).thenReturn(true);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentDouble, programmeDouble);

        // Assert
        assertTrue(result);
    }

    //US17
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() {
        // Arrange
        Student studentDouble1 = mock(Student.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator
        Iterator<ProgrammeEnrolment> itDouble = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true, false);

        when(itDouble.next()).thenReturn(programmeEnrolmentDouble);

        when(programmeEnrolmentDouble.hasSameStudent(studentDouble1)).thenReturn(false);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeDouble)).thenReturn(true);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentDouble1, programmeDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTheSameStudentIsEnrolledInADifferentProgramme() {
        // Arrange
        Student studentDouble = mock(Student.class);
        Programme programmeDouble2 = mock(Programme.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactory.class);
        ProgrammeEnrolmentListFactory listFactoryDouble = mock(ProgrammeEnrolmentListFactory.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator
        Iterator<ProgrammeEnrolment> itDouble = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(true, false);

        when(itDouble.next()).thenReturn(programmeEnrolmentDouble);

        when(programmeEnrolmentDouble.hasSameStudent(studentDouble)).thenReturn(true);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeDouble2)).thenReturn(false);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentDouble, programmeDouble2);

        // Assert
        assertFalse(result);
    }
}