package PAI.repository;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.domain.Student;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.factory.IProgrammeEnrolmentListFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

        //assert
        assertNotNull(programmeEnrolmentRepository);
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
        StudentID studentIdDouble = mock(StudentID.class);
        ProgrammeID programmeIdDouble = mock(ProgrammeID.class);
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

        when(programmeEnrolmentDouble.hasSameStudent(studentIdDouble)).thenReturn(true);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeIdDouble)).thenReturn(true);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentIdDouble, programmeIdDouble);

        // Assert
        assertTrue(result);
    }

    //US17
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgramme() {
        // Arrange
        StudentID studentIdDouble = mock(StudentID.class);
        ProgrammeID programmeIdDouble = mock(ProgrammeID.class);
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

        when(programmeEnrolmentDouble.hasSameStudent(studentIdDouble)).thenReturn(false);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeIdDouble)).thenReturn(true);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentIdDouble, programmeIdDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTheSameStudentIsEnrolledInADifferentProgramme() {
        // Arrange
        Student studentDouble = mock(Student.class);
        StudentID studentIdDouble = mock(StudentID.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeID programmeIdDouble = mock(ProgrammeID.class);
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

        when(programmeEnrolmentDouble.hasSameStudent(studentIdDouble)).thenReturn(true);

        when(programmeEnrolmentDouble.hasSameProgramme(programmeIdDouble)).thenReturn(false);

        // Act
        boolean result = programmeEnrolmentRepository.isStudentEnrolled(studentIdDouble, programmeIdDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldSaveEnrolment() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);

        // Act
        ProgrammeEnrolment result = programmeEnrolmentRepository.save(programmeEnrolmentDouble);

        // Assert
        assertEquals(result, programmeEnrolmentDouble);
    }

    @Test
    void shouldReturnProgrammeEnrolmentList() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        programmeEnrolmentRepository.save(programmeEnrolmentDouble);

        // Act
        Iterable<ProgrammeEnrolment> result = programmeEnrolmentRepository.findAll();

        // Assert
        assertIterableEquals(List.of(programmeEnrolmentDouble), result);
    }

    @Test
    void shouldThrowExceptionWhenEmptyProgrammeEnrolmentList() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> programmeEnrolmentRepository.findAll());
    }

    @Test
    void shouldReturnOptionalWhenMatchingIdentity() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentID programmeEnrolmentID = mock(ProgrammeEnrolmentID.class);
        when(programmeEnrolmentDouble.identity()).thenReturn(programmeEnrolmentID);

        // Act
        programmeEnrolmentRepository.save(programmeEnrolmentDouble);
        Optional<ProgrammeEnrolment> result = programmeEnrolmentRepository.ofIdentity(programmeEnrolmentID);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoMatchingIdentity() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);
        ProgrammeEnrolmentID programmeEnrolmentID = mock(ProgrammeEnrolmentID.class);

        // Act
        Optional<ProgrammeEnrolment> result = programmeEnrolmentRepository.ofIdentity(programmeEnrolmentID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueWhenContainsID() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentID programmeEnrolmentID = mock(ProgrammeEnrolmentID.class);

        programmeEnrolmentRepository.save(programmeEnrolment);
        when(programmeEnrolment.identity()).thenReturn(programmeEnrolmentID);

        // Act
        boolean result = programmeEnrolmentRepository.containsOfIdentity(programmeEnrolmentID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDoesNotContainID() {
        // Arrange
        IProgrammeEnrolmentFactory programmeEnrolmentFactory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepository(programmeEnrolmentFactory, programmeEnrolmentListFactory);
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentID programmeEnrolmentID = mock(ProgrammeEnrolmentID.class);

        when(programmeEnrolment.identity()).thenReturn(programmeEnrolmentID);

        // Act
        boolean result = programmeEnrolmentRepository.containsOfIdentity(programmeEnrolmentID);

        // Assert
        assertFalse(result);
    }


    @Test
    void shouldReturnOptionalWhenMatchingOfIdentity() {

        IProgrammeEnrolmentFactory factory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository repository = new ProgrammeEnrolmentRepository(factory, listFactory);
        ProgrammeEnrolmentID peID = new ProgrammeEnrolmentID();
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);

        when(programmeEnrolment.identity()).thenReturn(peID);
        repository.save(programmeEnrolment);

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(peID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(programmeEnrolment, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoMatchingOfIdentity() {
        // Arrange
        IProgrammeEnrolmentFactory factory = mock(IProgrammeEnrolmentFactory.class);
        IProgrammeEnrolmentListFactory listFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepository repository = new ProgrammeEnrolmentRepository(factory, listFactory);
        ProgrammeEnrolmentID peID = new ProgrammeEnrolmentID();
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);

        when(programmeEnrolment.identity()).thenReturn(peID);
        repository.save(programmeEnrolment);

        ProgrammeEnrolmentID nonMatchingID = new ProgrammeEnrolmentID();

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(nonMatchingID);

        // Assert
        assertFalse(result.isPresent());
    }
}


