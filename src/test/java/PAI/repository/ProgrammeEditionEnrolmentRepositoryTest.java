package PAI.repository;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.StudentID;
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
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrolStudentInProgrammeEdition(null, pe1);
        });
        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionIsNull() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrolStudentInProgrammeEdition(st1, null);
        });

        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenProgrammeEditionEnrolmentAlreadyExists() throws IllegalArgumentException {
        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrolment enrolMock = mock(ProgrammeEditionEnrolment.class);
        when(doubleIPEEF.newProgrammeEditionEnrolment(st1, pe1))
                .thenReturn(enrolMock);
        repository.enrolStudentInProgrammeEdition(st1, pe1);

        //act
        boolean result = repository.enrolStudentInProgrammeEdition(st1, pe1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAValidProgrammeEditionEnrolment() {
        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrolment enrolMock = mock(ProgrammeEditionEnrolment.class);
        when(doubleIPEEF.newProgrammeEditionEnrolment(st1, pe1)).thenReturn(enrolMock);

        //act
        boolean result = repository.enrolStudentInProgrammeEdition(st1, pe1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnATwoValidProgrammeEditionEnrollments() {
        //arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        Student st2 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pe2 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(doubleIPEEF.newProgrammeEditionEnrolment(st1, pe1)).thenReturn(enrolMock1);
        when(doubleIPEEF.newProgrammeEditionEnrolment(st2, pe2)).thenReturn(enrolMock2);
        //act
        boolean result1 = repository.enrolStudentInProgrammeEdition(st1, pe1);
        boolean result2 = repository.enrolStudentInProgrammeEdition(st2, pe2);

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
        StudentID mockStudentID = mock(StudentID.class);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(st1.identity()).thenReturn(mockStudentID);

        when(enrolMock1.getStudentID()).thenReturn(mockStudentID);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(pe1);

        when(doubleIPEEF.newProgrammeEditionEnrolment(st1, pe1)).thenReturn(enrolMock1);

        // Act
        repository.enrolStudentInProgrammeEdition(st1, pe1);

        // Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(st1, pe1));
    }

    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);
        StudentID mockStudentID = mock(StudentID.class);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pe2 = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(st1.identity()).thenReturn(mockStudentID);

        when(enrolMock1.getStudentID()).thenReturn(mockStudentID);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(pe1);

        when(doubleIPEEF.newProgrammeEditionEnrolment(st1, pe1)).thenReturn(enrolMock1);

        // Act
        repository.enrolStudentInProgrammeEdition(st1, pe1);
        // Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(st1, pe2));
    }


    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() {

        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition3Double = mock(ProgrammeEdition.class);

        StudentID mockStudentID1 = mock(StudentID.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        StudentID mockStudentID3 = mock(StudentID.class);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        Student student3Double = mock(Student.class);

        when(student1Double.identity()).thenReturn(mockStudentID1);
        when(student2Double.identity()).thenReturn(mockStudentID2);
        when(student3Double.identity()).thenReturn(mockStudentID3);


        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(ProgrammeEditionEnrolmentListFactoryImpl.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock1.getStudentID()).thenReturn(mockStudentID1);
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student1Double, edition1Double)).thenReturn(enrolMock1);

        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock2.getStudentID()).thenReturn(mockStudentID2);
        when(enrolMock2.findProgrammeEditionInEnrolment()).thenReturn(edition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student2Double, edition2Double)).thenReturn(enrolMock2);

        ProgrammeEditionEnrolment enrolMock3 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock3.getStudentID()).thenReturn(mockStudentID3);
        when(enrolMock3.findProgrammeEditionInEnrolment()).thenReturn(edition3Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student3Double, edition3Double)).thenReturn(enrolMock3);


        repository.enrolStudentInProgrammeEdition(student1Double, edition1Double);
        repository.enrolStudentInProgrammeEdition(student2Double, edition2Double);
        repository.enrolStudentInProgrammeEdition(student3Double, edition3Double);

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
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student1Double, edition1Double)).thenReturn(enrolMock1);

        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock2.getStudentID()).thenReturn(mockStudentID2);
        when(enrolMock2.findProgrammeEditionInEnrolment()).thenReturn(edition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student2Double, edition2Double)).thenReturn(enrolMock2);

        repository.enrolStudentInProgrammeEdition(student1Double, edition1Double);
        repository.enrolStudentInProgrammeEdition(student2Double, edition2Double);

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

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        StudentID mockStudentID = mock(StudentID.class);

        Student student1Double = mock(Student.class);


        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock1.getStudentID()).thenReturn(mockStudentID);
        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student1Double, edition1Double)).thenReturn(enrolMock1);

        ProgrammeEditionEnrolment enrollMock2 = mock(ProgrammeEditionEnrolment.class);
        when(enrollMock2.getStudentID()).thenReturn(mockStudentID);
        when(enrollMock2.findProgrammeEditionInEnrolment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student1Double, edition2Double)).thenReturn(enrollMock2);

        when(enrolMock1.isEnrolmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(enrollMock2.isEnrolmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);


        repository.enrolStudentInProgrammeEdition(student1Double, edition1Double);
        repository.enrolStudentInProgrammeEdition(student1Double, edition2Double);

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
        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment1Double = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment2Double = mock(ProgrammeEditionEnrolment.class);

        // SUT

        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        //Instructions
        when(programmeEditionEnrolment1Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionDouble);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student1Double, programmeEditionDouble)).thenReturn(programmeEditionEnrolment1Double);

        when(programmeEditionEnrolment2Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionDouble);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student2Double, programmeEditionDouble)).thenReturn(programmeEditionEnrolment2Double);

        // Act
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student1Double, programmeEditionDouble);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student2Double, programmeEditionDouble);
        int result = programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble);

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
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

        // SUT
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        // Act
        int result = programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble);

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
        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment1Double = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment2Double = mock(ProgrammeEditionEnrolment.class);
        // SUT

        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(doubleIPEEF, doubleIPEELF);

        //Instructions
        when(programmeEditionEnrolment1Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEdition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student1Double, programmeEdition2Double)).thenReturn(programmeEditionEnrolment1Double);

        when(programmeEditionEnrolment2Double.findProgrammeEditionInEnrolment()).thenReturn(programmeEdition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrolment(student2Double, programmeEdition2Double)).thenReturn(programmeEditionEnrolment2Double);

        // Act
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student1Double, programmeEdition2Double);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(student2Double, programmeEdition2Double);

        int result = programmeEditionEnrolmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition1Double);

        // Assert
        assertEquals(0, result);
    }
    @Test
    void should_return_a_list_of_programmeEditions_that_student_is_enrolled (){
        //arrange
        IProgrammeEditionEnrolmentFactory doubleFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);

        ProgrammeEditionEnrolmentRepository enrolment = new ProgrammeEditionEnrolmentRepository(doubleFactory, doubleIPEELF);

        Student doubleStudent = mock(Student.class);
        ProgrammeEditionEnrolment doublePee = mock (ProgrammeEditionEnrolment.class);
        ProgrammeEdition doublePe = mock (ProgrammeEdition.class);

        when (doubleFactory.newProgrammeEditionEnrolment(doubleStudent, doublePe)).thenReturn(doublePee);
        enrolment.enrolStudentInProgrammeEdition(doubleStudent, doublePe);

        when (doublePee.findStudentInProgrammeEdition()).thenReturn(doubleStudent);
        when(doublePee.findProgrammeEditionInEnrolment()).thenReturn(doublePe);

        //act
        List<ProgrammeEdition> result = enrolment.findProgrammeEditionsThatStudentIsEnrolled(doubleStudent);

        //assert
        assertEquals (1, result.size());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionEnrollmentAlreadyExists() {
        // Arrange
        IProgrammeEditionEnrolmentFactory doublePeeFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doublePeeListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(doublePeeFactory, doublePeeListFactory);

        Student doubleSt1 = mock(Student.class);
        ProgrammeEdition doublePe1 = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrolment pee1 = new ProgrammeEditionEnrolmentDoubleEqualsTrue(doubleSt1,doublePe1);
        ProgrammeEditionEnrolment pee2 = new ProgrammeEditionEnrolmentDoubleEqualsTrue(doubleSt1,doublePe1);

        when(doublePeeFactory.newProgrammeEditionEnrolment(doubleSt1, doublePe1)).thenReturn(pee1);
        repository.enrolStudentInProgrammeEdition(doubleSt1, doublePe1);

        when(doublePeeFactory.newProgrammeEditionEnrolment(doubleSt1, doublePe1)).thenReturn(pee2);

        // Act
        boolean result = repository.enrolStudentInProgrammeEdition(doubleSt1, doublePe1);

        // Assert
        assertFalse(result);
    }


    @Test
    void save_ShouldAddProgrammeEditionEnrolment() {
        // Arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());
        ProgrammeEditionEnrolmentRepository repository = new ProgrammeEditionEnrolmentRepository(enrolmentFactory, listFactory);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        // Act
        ProgrammeEditionEnrolment savedEnrolment = repository.save(enrolment);

        // Assert
        assertEquals(enrolment, savedEnrolment);
        assertTrue(repository.findAll().iterator().hasNext());
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
}