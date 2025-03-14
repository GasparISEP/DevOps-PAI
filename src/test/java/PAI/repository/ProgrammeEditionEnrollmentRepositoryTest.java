package PAI.repository;

import PAI.domain.*;
import PAI.factory.IProgrammeEditionEnrollmentFactory;
import PAI.factory.IProgrammeEditionEnrollmentListFactory;
import PAI.factory.ProgrammeEditionEnrollmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrollmentRepositoryTest {

    @Test
    void shouldReturnFalseWhenStudentIsNull() {
        // Arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(null, pe1);
        });
        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionIsNull() {
        // Arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(st1, null);
        });

        assertEquals("ProgrammeEdition and Student cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenProgrammeEditionEnrollmentAlreadyExists() throws IllegalArgumentException {
        //arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrollment enrollMock = mock(ProgrammeEditionEnrollment.class);
        when(doubleIPEEF.newProgrammeEditionEnrollment(st1, pe1))
                .thenReturn(enrollMock);
        repository.enrollStudentInProgrammeEdition(st1, pe1);

        //act
        boolean result = repository.enrollStudentInProgrammeEdition(st1, pe1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAValidProgrammeEditionEnrollment() {
        //arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrollment enrollMock = mock(ProgrammeEditionEnrollment.class);
        when(doubleIPEEF.newProgrammeEditionEnrollment(st1, pe1)).thenReturn(enrollMock);

        //act
        boolean result = repository.enrollStudentInProgrammeEdition(st1, pe1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnATwoValidProgrammeEditionEnrollments() {
        //arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        Student st2 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pe2 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(doubleIPEEF.newProgrammeEditionEnrollment(st1, pe1)).thenReturn(enrollMock1);
        when(doubleIPEEF.newProgrammeEditionEnrollment(st2, pe2)).thenReturn(enrollMock2);
        //act
        boolean result1 = repository.enrollStudentInProgrammeEdition(st1, pe1);
        boolean result2 = repository.enrollStudentInProgrammeEdition(st2, pe2);

        //assert
        assertTrue(result1);
        assertTrue(result2);

    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);


        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(st1.getUniqueNumber()).thenReturn("1234567");

        when(enrollMock1.getStudentUniqueNumber()).thenReturn("1234567");

        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(pe1);

        when(doubleIPEEF.newProgrammeEditionEnrollment(st1, pe1)).thenReturn(enrollMock1);

        // Act
        repository.enrollStudentInProgrammeEdition(st1, pe1);

        // Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(st1, pe1));
    }

    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pe2 = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);

        when(st1.getUniqueNumber()).thenReturn("1234567");

        when(enrollMock1.getStudentUniqueNumber()).thenReturn("1234567");

        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(pe1);

        when(doubleIPEEF.newProgrammeEditionEnrollment(st1, pe1)).thenReturn(enrollMock1);

        // Act
        repository.enrollStudentInProgrammeEdition(st1, pe1);
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

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        Student student3Double = mock(Student.class);

        when(student1Double.getUniqueNumber()).thenReturn("1234567");
        when(student2Double.getUniqueNumber()).thenReturn("1334568");
        when(student3Double.getUniqueNumber()).thenReturn("1344556");


        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(ProgrammeEditionEnrollmentListFactoryImpl.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn("1234567");
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student1Double, edition1Double)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn("1334568");
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student2Double, edition2Double)).thenReturn(enrollMock2);

        ProgrammeEditionEnrollment enrollMock3 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock3.getStudentUniqueNumber()).thenReturn("1344556");
        when(enrollMock3.findProgrammeEditionInEnrollment()).thenReturn(edition3Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student3Double, edition3Double)).thenReturn(enrollMock3);


        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double);
        repository.enrollStudentInProgrammeEdition(student2Double, edition2Double);
        repository.enrollStudentInProgrammeEdition(student3Double, edition3Double);

        when(enrollMock1.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(enrollMock2.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

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


        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        when(student1Double.getUniqueNumber()).thenReturn("1234567");
        when(student2Double.getUniqueNumber()).thenReturn("1334568");


        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn("1234567");
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student1Double, edition1Double)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn("1334568");
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student2Double, edition2Double)).thenReturn(enrollMock2);

        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double);
        repository.enrollStudentInProgrammeEdition(student2Double, edition2Double);

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

        Student student1Double = mock(Student.class);


        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn("1234567");
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student1Double, edition1Double)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn("1234567");
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student1Double, edition2Double)).thenReturn(enrollMock2);

        when(enrollMock1.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(enrollMock2.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);


        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double);
        repository.enrollStudentInProgrammeEdition(student1Double, edition2Double);

        // act
        int result = repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(1, result);
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInAProgrammeEdition() {
        //SUT = ProgrammeEditionEnrollmentRepo -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        ProgrammeEditionEnrollment programmeEditionEnrollment1Double = mock(ProgrammeEditionEnrollment.class);
        ProgrammeEditionEnrollment programmeEditionEnrollment2Double = mock(ProgrammeEditionEnrollment.class);

        // SUT

        ProgrammeEditionEnrollmentRepository programmeEditionEnrollmentRepository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);

        //Instructions
        when(programmeEditionEnrollment1Double.findProgrammeEditionInEnrollment()).thenReturn(programmeEditionDouble);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student1Double, programmeEditionDouble)).thenReturn(programmeEditionEnrollment1Double);

        when(programmeEditionEnrollment2Double.findProgrammeEditionInEnrollment()).thenReturn(programmeEditionDouble);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student2Double, programmeEditionDouble)).thenReturn(programmeEditionEnrollment2Double);

        // Act
        programmeEditionEnrollmentRepository.enrollStudentInProgrammeEdition(student1Double, programmeEditionDouble);
        programmeEditionEnrollmentRepository.enrollStudentInProgrammeEdition(student2Double, programmeEditionDouble);
        int result = programmeEditionEnrollmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble);

        // Assert
        assertEquals(2, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() {
        //SUT = ProgrammeEditionEnrollmentRepo -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

        // SUT
        ProgrammeEditionEnrollmentRepository programmeEditionEnrollmentRepository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);

        // Act
        int result = programmeEditionEnrollmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void shouldReturnZeroIfCheckingNumberOfStudentsInDifferentProgrammeEdition() {
        //SUT = ProgrammeEditionEnrollmentRepo -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrollmentFactory doubleIPEEF = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEdition programmeEdition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2Double = mock(ProgrammeEdition.class);
        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        ProgrammeEditionEnrollment programmeEditionEnrollment1Double = mock(ProgrammeEditionEnrollment.class);
        ProgrammeEditionEnrollment programmeEditionEnrollment2Double = mock(ProgrammeEditionEnrollment.class);
        // SUT

        ProgrammeEditionEnrollmentRepository programmeEditionEnrollmentRepository = new ProgrammeEditionEnrollmentRepository(doubleIPEEF, doubleIPEELF);

        //Instructions
        when(programmeEditionEnrollment1Double.findProgrammeEditionInEnrollment()).thenReturn(programmeEdition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student1Double, programmeEdition2Double)).thenReturn(programmeEditionEnrollment1Double);

        when(programmeEditionEnrollment2Double.findProgrammeEditionInEnrollment()).thenReturn(programmeEdition2Double);
        when(doubleIPEEF.newProgrammeEditionEnrollment(student2Double, programmeEdition2Double)).thenReturn(programmeEditionEnrollment2Double);

        // Act
        programmeEditionEnrollmentRepository.enrollStudentInProgrammeEdition(student1Double, programmeEdition2Double);
        programmeEditionEnrollmentRepository.enrollStudentInProgrammeEdition(student2Double, programmeEdition2Double);

        int result = programmeEditionEnrollmentRepository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEdition1Double);

        // Assert
        assertEquals(0, result);
    }
    @Test
    void should_return_a_list_of_programmeEditions_that_student_is_enrolled (){
        //arrange
        IProgrammeEditionEnrollmentFactory doubleFactory = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrollmentListFactory.class);

        ProgrammeEditionEnrollmentRepository enrollment = new ProgrammeEditionEnrollmentRepository(doubleFactory, doubleIPEELF);

        Student doubleStudent = mock(Student.class);
        ProgrammeEditionEnrollment doublePee = mock (ProgrammeEditionEnrollment.class);
        ProgrammeEdition doublePe = mock (ProgrammeEdition.class);

        when (doubleFactory.newProgrammeEditionEnrollment(doubleStudent, doublePe)).thenReturn(doublePee);
        enrollment.enrollStudentInProgrammeEdition(doubleStudent, doublePe);

        when (doublePee.findStudentInProgrammeEdition()).thenReturn(doubleStudent);
        when(doublePee.findProgrammeEditionInEnrollment()).thenReturn(doublePe);

        //act
        List<ProgrammeEdition> result = enrollment.findProgrammeEditionsThatStudentIsEnrolled(doubleStudent);

        //assert
        assertEquals (1, result.size());
    }

    @Test
    void shouldReturnFalseWhenProgrammeEditionEnrollmentAlreadyExists() {
        // Arrange
        IProgrammeEditionEnrollmentFactory doublePeeFactory = mock(IProgrammeEditionEnrollmentFactory.class);
        IProgrammeEditionEnrollmentListFactory doublePeeListFactory = mock(IProgrammeEditionEnrollmentListFactory.class);
        ProgrammeEditionEnrollmentRepository repository = new ProgrammeEditionEnrollmentRepository(doublePeeFactory, doublePeeListFactory);

        Student doubleSt1 = mock(Student.class);
        ProgrammeEdition doublePe1 = mock(ProgrammeEdition.class);

        ProgrammeEditionEnrollment pee1 = new ProgrammeEditionEnrollmentDoubleEqualsTrue(doubleSt1,doublePe1);
        ProgrammeEditionEnrollment pee2 = new ProgrammeEditionEnrollmentDoubleEqualsTrue(doubleSt1,doublePe1);

        when(doublePeeFactory.newProgrammeEditionEnrollment(doubleSt1, doublePe1)).thenReturn(pee1);
        repository.enrollStudentInProgrammeEdition(doubleSt1, doublePe1);

        when(doublePeeFactory.newProgrammeEditionEnrollment(doubleSt1, doublePe1)).thenReturn(pee2);

        // Act
        boolean result = repository.enrollStudentInProgrammeEdition(doubleSt1, doublePe1);

        // Assert
        assertFalse(result);
    }

}