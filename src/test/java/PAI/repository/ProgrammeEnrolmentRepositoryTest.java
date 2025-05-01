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
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);

        //act
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(listFactoryDouble);

        //assert
        assertNotNull(programmeEnrolmentRepository);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEnrolmentFactoryIsNull() {
        //arrange
        IProgrammeEnrolmentListFactory listFactoryDouble = null;

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepositoryImpl(listFactoryDouble));
    }

    //US17
    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgramme() {
        // Arrange
        StudentID studentIdDouble = mock(StudentID.class);
        ProgrammeID programmeIdDouble = mock(ProgrammeID.class);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(listFactoryDouble);

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
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(listFactoryDouble);

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
        IProgrammeEnrolmentListFactory listFactoryDouble = mock(IProgrammeEnrolmentListFactory.class);
        ArrayList<ProgrammeEnrolment> listDouble = mock(ArrayList.class);

        when(listFactoryDouble.newArrayList()).thenReturn(listDouble);

        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(listFactoryDouble);

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
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);
        ProgrammeEnrolment programmeEnrolmentDouble = mock(ProgrammeEnrolment.class);

        // Act
        ProgrammeEnrolment result = programmeEnrolmentRepository.save(programmeEnrolmentDouble);

        // Assert
        assertEquals(result, programmeEnrolmentDouble);
    }

    @Test
    void shouldReturnProgrammeEnrolmentList() {
        // Arrange
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);
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
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> programmeEnrolmentRepository.findAll());
    }

    @Test
    void shouldReturnOptionalWhenMatchingIdentity() {
        // Arrange
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);
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
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);
        ProgrammeEnrolmentID programmeEnrolmentID = mock(ProgrammeEnrolmentID.class);

        // Act
        Optional<ProgrammeEnrolment> result = programmeEnrolmentRepository.ofIdentity(programmeEnrolmentID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueWhenContainsID() {
        // Arrange
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);
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
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = mock(IProgrammeEnrolmentListFactory.class);
        ProgrammeEnrolmentRepositoryImpl programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentListFactory);
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
        // Arrange
        IProgrammeEnrolmentListFactory listFactory = mock(IProgrammeEnrolmentListFactory.class);
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        ProgrammeEnrolmentRepositoryImpl repository = new ProgrammeEnrolmentRepositoryImpl(listFactory);
        ProgrammeEnrolmentID peID = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);
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
        IProgrammeEnrolmentListFactory listFactory = mock(IProgrammeEnrolmentListFactory.class);
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        StudentID studentIDDouble2 = mock(StudentID.class);
        ProgrammeID programmeIDDouble2 = mock(ProgrammeID.class);
        ProgrammeEnrolmentRepositoryImpl repository = new ProgrammeEnrolmentRepositoryImpl(listFactory);
        ProgrammeEnrolmentID peID = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);
        ProgrammeEnrolment programmeEnrolment = mock(ProgrammeEnrolment.class);

        when(programmeEnrolment.identity()).thenReturn(peID);
        repository.save(programmeEnrolment);

        ProgrammeEnrolmentID nonMatchingID = new ProgrammeEnrolmentID(studentIDDouble2, programmeIDDouble2);

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(nonMatchingID);

        // Assert
        assertFalse(result.isPresent());
    }
}


