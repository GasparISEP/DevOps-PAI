package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.factory.IProgrammeEditionEnrolmentListFactory;
import PAI.factory.ProgrammeEditionEnrolmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentRepositoryTest {

    @Test
    void shouldReturnFalseWhenStudentIsNull() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrolStudentInProgrammeEdition(null, peId1);
        });
        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionIsNull() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        StudentID stId1 = mock(StudentID.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrolStudentInProgrammeEdition(stId1, null);
        });

        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenProgrammeEditionEnrolmentAlreadyExists() throws IllegalArgumentException {
        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);


        ProgrammeEditionEnrolment enrolMock = mock(ProgrammeEditionEnrolment.class);
        when(doubleIPEEF.newProgrammeEditionEnrolment(stId1, peId1))
                .thenReturn(enrolMock);
        repository.enrolStudentInProgrammeEdition(stId1, peId1);

        //act
        boolean result = repository.enrolStudentInProgrammeEdition(stId1, peId1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAValidProgrammeEditionEnrolment() {
        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);


        ProgrammeEditionEnrolment enrolMock = mock(ProgrammeEditionEnrolment.class);
        when(doubleIPEEF.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock);

        //act
        boolean result = repository.enrolStudentInProgrammeEdition(stId1, peId1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnATwoValidProgrammeEditionEnrollments() {
        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        StudentID stId1 = mock(StudentID.class);
        StudentID stId2 = mock(StudentID.class);

        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID peId2 = mock(ProgrammeEditionID.class);



        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(doubleIPEEF.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock1);
        when(doubleIPEEF.newProgrammeEditionEnrolment(stId2, peId2)).thenReturn(enrolMock2);
        //act
        boolean result1 = repository.enrolStudentInProgrammeEdition(stId1, peId2);
        boolean result2 = repository.enrolStudentInProgrammeEdition(stId2, peId2);

        //assert
        assertTrue(result1);
        assertTrue(result2);

    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        StudentID stId1 = mock(StudentID.class);

        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(doubleIPEEF.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock1);

        when(enrolMock1.getStudentID()).thenReturn(stId1);
        when(enrolMock1.hasSameStudent(stId1)).thenReturn(true);
        when(enrolMock1.hasSameProgrammeEdition(peId1)).thenReturn(true);
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(peId1);

        // Act
        repository.enrolStudentInProgrammeEdition(stId1, peId1);

        // Assert
        assertTrue(repository.isStudentEnrolledInThisProgrammeEdition(stId1, peId1));
    }

    @Test
    void shouldReturnFalseIfStudentNullNotEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(enrolMock1.hasSameProgrammeEdition(peId1)).thenReturn(true);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(peId1);
        // Act + Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(null, peId1));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionNull() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        StudentID stId1 = mock(StudentID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(enrolMock1.hasSameStudent(stId1)).thenReturn(true);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(null);

        // Act + Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(stId1, null));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionNullAndStudentNull() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        // Act + Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(null, null));
    }
    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        Student mockStudent = mock(Student.class);
        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID peId2 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(mockStudent.identity()).thenReturn(stId1);

        when(enrolMock1.getStudentID()).thenReturn(stId1);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(peId1);

        when(doubleIPEEF.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock1);

        // Act
        repository.enrolStudentInProgrammeEdition(stId1, peId1);
        // Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(stId1, peId2));
    }


    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() {

        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEditionID editionId1Double = mock(ProgrammeEditionID.class);
        ProgrammeEditionID editionId2Double = mock(ProgrammeEditionID.class);
        ProgrammeEditionID editionId3Double = mock(ProgrammeEditionID.class);

        StudentID studentId1Double = mock(StudentID.class);
        StudentID studentId2Double = mock(StudentID.class);
        StudentID studentId3Double = mock(StudentID.class);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        Student student3Double = mock(Student.class);

        when(student1Double.identity()).thenReturn(studentId1Double);
        when(student2Double.identity()).thenReturn(studentId2Double);
        when(student3Double.identity()).thenReturn(studentId3Double);


        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(ProgrammeEditionEnrolmentListFactoryImpl.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock1.getStudentID()).thenReturn(studentId1Double);
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId1Double, editionId1Double)).thenReturn(enrolMock1);

        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock2.getStudentID()).thenReturn(studentId2Double);
        when(enrolMock2.findProgrammeEditionInEnrolment()).thenReturn(editionId2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId2Double, editionId2Double)).thenReturn(enrolMock2);

        ProgrammeEditionEnrolment enrolMock3 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock3.getStudentID()).thenReturn(studentId3Double);
        when(enrolMock3.findProgrammeEditionInEnrolment()).thenReturn(editionId3Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId3Double, editionId3Double)).thenReturn(enrolMock3);


        repository.enrolStudentInProgrammeEdition(studentId1Double, editionId1Double);
        repository.enrolStudentInProgrammeEdition(studentId2Double, editionId2Double);
        repository.enrolStudentInProgrammeEdition(studentId3Double, editionId3Double);

        when(enrolMock1.isEnrolmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(enrolMock2.isEnrolmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        // act
        int result = repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(2, result);
    }

    @Test
    void shouldReturnZeroWhenNoStudentsAreEnrolledInDepartmentAndSchoolYear() {
        //arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);

        ProgrammeEditionID editionId1Double = mock(ProgrammeEditionID.class);
        ProgrammeEditionID editionId2Double = mock(ProgrammeEditionID.class);

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(false);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(false);

        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        when(student1Double.identity()).thenReturn(mockStudentID1);
        when(student2Double.identity()).thenReturn(mockStudentID2);


        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock1.getStudentID()).thenReturn(mockStudentID1);
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(mockStudentID1, editionId1Double)).thenReturn(enrolMock1);

        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock2.getStudentID()).thenReturn(mockStudentID2);
        when(enrolMock2.findProgrammeEditionInEnrolment()).thenReturn(editionId2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(mockStudentID2, editionId2Double)).thenReturn(enrolMock2);

        repository.enrolStudentInProgrammeEdition(mockStudentID1, editionId1Double);
        repository.enrolStudentInProgrammeEdition(mockStudentID2, editionId2Double);

        // act
        int result = repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(0, result);
    }


    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInMultipleEditions() {
        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);

        ProgrammeEditionID editionId1Double = mock(ProgrammeEditionID.class);
        ProgrammeEditionID editionId2Double = mock(ProgrammeEditionID.class);

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        StudentID mockStudentID = mock(StudentID.class);

        StudentID studentID1Double = mock(StudentID.class);


        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock1.getStudentID()).thenReturn(mockStudentID);
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentID1Double, editionId1Double)).thenReturn(enrolMock1);

        ProgrammeEditionEnrolment enrollMock2 = mock(ProgrammeEditionEnrolment.class);
        when(enrollMock2.getStudentID()).thenReturn(mockStudentID);
        when(enrollMock2.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentID1Double, editionId2Double)).thenReturn(enrollMock2);

        when(enrolMock1.isEnrolmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(enrollMock2.isEnrolmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);


        repository.enrolStudentInProgrammeEdition(studentID1Double, editionId1Double);
        repository.enrolStudentInProgrammeEdition(studentID1Double, editionId2Double);

        // act
        int result = repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(1, result);
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInAProgrammeEdition() {
        //SUT = ProgrammeEditionEnrolmentRepo -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        StudentID studentId1Double = mock(StudentID.class);
        StudentID studentId2Double = mock(StudentID.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment1Double = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment2Double = mock(ProgrammeEditionEnrolment.class);

        ProgrammeEditionID programmeEditionId1Double = mock(ProgrammeEditionID.class); ProgrammeEditionID programmeEditionId2Double = mock(ProgrammeEditionID.class);


        // SUT

        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        //Instructions
        when(programmeEditionEnrolment1Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionId1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId1Double, programmeEditionId1Double)).thenReturn(programmeEditionEnrolment1Double);

        when(programmeEditionEnrolment2Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionId1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId2Double, programmeEditionId1Double)).thenReturn(programmeEditionEnrolment2Double);

        // Act
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentId1Double, programmeEditionId1Double);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentId2Double, programmeEditionId1Double);
        int result = programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionId1Double);

        // Assert
        assertEquals(2, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() {
        //SUT = ProgrammeEditionEnrolmentRepo -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionID programmeEditionIdDouble = mock(ProgrammeEditionID.class);

        // SUT
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        // Act
        int result = programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionIdDouble);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void shouldReturnZeroIfCheckingNumberOfStudentsInDifferentProgrammeEdition() {
        //SUT = ProgrammeEditionEnrolmentRepo -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEdition programmeEdition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2Double = mock(ProgrammeEdition.class);
        StudentID studentId1Double = mock(StudentID.class);
        StudentID studentId2Double = mock(StudentID.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment1Double = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment2Double = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionID programmeEditionId1Double = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionId2Double = mock(ProgrammeEditionID.class);
        // SUT

        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        //Instructions
        when(programmeEditionEnrolment1Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionId2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId1Double, programmeEditionId2Double)).thenReturn(programmeEditionEnrolment1Double);

        when(programmeEditionEnrolment2Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionId2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId2Double, programmeEditionId2Double)).thenReturn(programmeEditionEnrolment2Double);

        // Act
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentId1Double, programmeEditionId2Double);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentId2Double, programmeEditionId2Double);

        int result = programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionId1Double);

        // Assert
        assertEquals(0, result);
    }
    @Test
    void should_return_a_list_of_programmeEditions_that_student_is_enrolled (){
        //arrange
        IProgrammeEditionEnrolmentFactory doubleFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);

        ProgrammeEditionEnrolmentRepository enrolment = new ProgrammeEditionEnrolmentRepository(doubleFactory, doubleIPEELF);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionEnrolment doublePee = mock (ProgrammeEditionEnrolment.class);
        ProgrammeEditionID doublePeId = mock (ProgrammeEditionID.class);

        when (doubleFactory.newProgrammeEditionEnrolment(doubleStudentId, doublePeId)).thenReturn(doublePee);
        enrolment.enrolStudentInProgrammeEdition(doubleStudentId, doublePeId);

        when (doublePee.findStudentInProgrammeEdition()).thenReturn(doubleStudentId);
        when(doublePee.findProgrammeEditionInEnrolment()).thenReturn(doublePeId);

        //act
        List<ProgrammeEditionID> result = enrolment.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals (1, result.size());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionEnrollmentAlreadyExists() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doublePeeFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doublePeeListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doublePeeFactory, doublePeeListFactory);

        StudentID doubleSt1Id = mock(StudentID.class);
        ProgrammeEditionID doublePe1Id = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment pee1 = new ProgrammeEditionEnrolmentDoubleEqualsTrue(doubleSt1Id,doublePe1Id);
        ProgrammeEditionEnrolment pee2 = new ProgrammeEditionEnrolmentDoubleEqualsTrue(doubleSt1Id,doublePe1Id);

        when(doublePeeFactory.newProgrammeEditionEnrolment(doubleSt1Id, doublePe1Id)).thenReturn(pee1);
        repository.enrolStudentInProgrammeEdition(doubleSt1Id, doublePe1Id);

        when(doublePeeFactory.newProgrammeEditionEnrolment(doubleSt1Id, doublePe1Id)).thenReturn(pee2);

        // Act
        boolean result = repository.enrolStudentInProgrammeEdition(doubleSt1Id, doublePe1Id);

        // Assert
        assertFalse(result);
    }


    @Test
    void save_ShouldAddProgrammeEditionEnrolment() {
        // Arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        Set<ProgrammeEditionEnrolment> mockList = new HashSet<>();
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(mockList);

        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(enrolmentFactory, listFactory);

        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        when(enrolment.identity()).thenReturn(mock(ProgrammeEditionEnrolmentID.class));

        // Act
        ProgrammeEditionEnrolment savedEnrolment = repository.save(enrolment);

        // Assert
        assertEquals(enrolment, savedEnrolment);
        assertTrue(mockList.contains(enrolment));
    }

    @Test
    void findAll_ShouldReturnAllSavedEnrolments() {
        // Arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>(Collections.singletonList(enrolment));
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(enrolmentFactory, listFactory);

        // Act
        Iterable<ProgrammeEditionEnrolment> result = repository.findAll();

        // Assert
        assertTrue(result.iterator().hasNext());
        assertEquals(enrolment, result.iterator().next());
    }

    @Test
    void ofIdentity_ShouldReturnEnrolmentIfExists() {
        // Arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        when(enrolment.identity()).thenReturn(enrolmentID);
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>(Collections.singletonList(enrolment));
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(enrolmentFactory, listFactory);

        // Act
        Optional<ProgrammeEditionEnrolment> result = repository.ofIdentity(enrolmentID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(enrolment, result.get());
    }

    @Test
    void containsOfIdentity_ShouldReturnTrueIfEnrolmentExists() {
        // Arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        when(enrolment.identity()).thenReturn(enrolmentID);
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>(Collections.singletonList(enrolment));
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(enrolmentFactory, listFactory);

        // Act
        boolean exists = repository.containsOfIdentity(enrolmentID);

        // Assert
        assertTrue(exists);
    }

    @Test
    void containsOfIdentity_ShouldReturnFalseIfEnrolmentDoesNotExist() {
        // Arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());

        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(enrolmentFactory, listFactory);

        // Act
        boolean exists = repository.containsOfIdentity(enrolmentID);

        // Assert
        assertFalse(exists);
    }

    @Test
    void should_throw_exception_if_identity_is_null() throws IllegalArgumentException {

        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF,doubleIPEELF);

        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        when(programmeEditionEnrolment.identity()).thenReturn(null);

        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            repository.save(null);
        });
        assertEquals(exception.getMessage(),"Entity cannot be null");
    }


    @Test
    void should_return_true_when_ID_exists(){

        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF,doubleIPEELF);

        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);


        when(enrolment.sameAs(enrolment)).thenReturn(false);
        when(enrolment.identity()).thenReturn(enrolmentID);
        repository.save(enrolment);


        //act
        boolean idExists = repository.containsOfIdentity(enrolmentID);

        //assert
        assertTrue(idExists);
    }
}