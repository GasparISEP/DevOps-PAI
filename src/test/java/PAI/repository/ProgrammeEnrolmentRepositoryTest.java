package PAI.repository;

import PAI.VOs.*;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEnrolment;
import PAI.domain.Student;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.factory.IProgrammeEnrolmentListFactory;
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
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);

        //act
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, listFactoryDouble);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEnrolmentFactoryIsNull() {
        //arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = null;

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, listFactoryDouble));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEnrolmentRepositoryFactoryIsNull() {
        //arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = null;
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, listFactoryDouble));
    }

    @Test
    void shouldReturnTrueIfTheEnrolmentInTheProgrammeIsSuccessful() throws Exception {
        //arrange
        StudentID studentDouble1 = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);
        ProgrammeEnrolment programmeEnrolmentDouble1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment programmeEnrolmentDouble2 = mock(ProgrammeEnrolment.class);
        Date dateDouble = mock(Date.class);

        //create programmeEnrolmentRepository
        IProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator da lista
        Iterator<ProgrammeEnrolment> it = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(it);

        when(it.hasNext()).thenReturn(true, false);

        when(it.next()).thenReturn(programmeEnrolmentDouble1);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble1, accessMethodDouble, programmeDouble, dateDouble))
                .thenReturn(programmeEnrolmentDouble2);

        when(programmeEnrolmentDouble1.hasSameEnrolment(programmeEnrolmentDouble2)).thenReturn(false);

        //act
        boolean result = programmeEnrolmentRepository.enrolStudents(studentDouble1, accessMethodDouble, programmeDouble, dateDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionIfEnrolmentIsNotSuccessfulBecauseStudentIsAlreadyEnrolledInTheProgramme() throws Exception {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        IProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolment programmeEnrolmentDouble1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment programmeEnrolmentDouble2 = mock(ProgrammeEnrolment.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepository enrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactoryDouble, listFactoryDouble);

        //Iterator
        Iterator<ProgrammeEnrolment> itDouble = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(itDouble);

        when(itDouble.hasNext()).thenReturn(false, true);

        when(itDouble.next()).thenReturn(programmeEnrolmentDouble1);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble))
                .thenReturn(programmeEnrolmentDouble1);

        enrolmentRepository.enrolStudents(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(programmeEnrolmentFactoryDouble.createProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble))
                .thenReturn(programmeEnrolmentDouble2);

        when(programmeEnrolmentDouble1.hasSameEnrolment(programmeEnrolmentDouble2)).thenReturn(true);

        //act + assert
        assertThrows(Exception.class, () -> enrolmentRepository.enrolStudents(studentDouble, accessMethodDouble, programmeDouble, dateDouble));
    }

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() {
        // Arrange
        Student studentDouble = mock(Student.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        IProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
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
        IProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
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
        IProgrammeEnrolmentFactory programmeEnrolmentFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
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