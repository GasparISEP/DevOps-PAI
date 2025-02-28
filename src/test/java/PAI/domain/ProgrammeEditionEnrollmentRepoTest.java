package PAI.domain;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrollmentRepoTest {

    @Test
    void shouldThrowExceptionWhenStudentIsNull() throws IllegalArgumentException {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(null, pe1, currentDate);
        });

        // Assert
        assertEquals("ProgrammeEdition and Student cannot be null.", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIsNull() throws IllegalArgumentException {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        Student st1 = mock(Student.class);
        LocalDate currentDate = LocalDate.now();

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(st1, null, currentDate);
        });

        // Assert
        assertEquals("ProgrammeEdition and Student cannot be null.", thrown.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenProgrammeEditionEnrollmentAlreadyExists() throws IllegalArgumentException {
        //arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollMock = mock(ProgrammeEditionEnrollment.class);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(st1,pe1,currentDate))
                .thenReturn(enrollMock);

        //act
        repository.enrollStudentInProgrammeEdition(st1, pe1, currentDate);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInProgrammeEdition(st1, pe1, currentDate);
        });

        //assert
        assertEquals("This programme edition enrollment is already in the list.", thrown.getMessage());
    }

    @Test
    void shouldReturnAValidProgrammeEditionEnrollment () {
        //arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollMock = mock(ProgrammeEditionEnrollment.class);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(st1,pe1,currentDate)).thenReturn(enrollMock);

        //act
        Optional<ProgrammeEditionEnrollment> result = repository.enrollStudentInProgrammeEdition(st1,pe1,currentDate);

        //assert
        assertTrue(result.isPresent(), "The student was enrolled in a programme edition successfully.");
    }

    @Test
    void shouldReturnATwoValidProgrammeEditionEnrollments () {
        //arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        Student st1 = mock(Student.class);
        Student st2 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pe2 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(st1,pe1,currentDate)).thenReturn(enrollMock1);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(st2,pe2,currentDate)).thenReturn(enrollMock2);
        //act
        Optional<ProgrammeEditionEnrollment> result1 = repository.enrollStudentInProgrammeEdition(st1,pe1,currentDate);
        Optional<ProgrammeEditionEnrollment> result2 = repository.enrollStudentInProgrammeEdition(st2,pe2,currentDate);

        //assert
        assertTrue(result1.isPresent(), "The student was enrolled in a programme edition successfully.");
        assertTrue(result2.isPresent(), "The student was enrolled in a programme edition successfully.");

    }

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgrammeEdition() {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(st1.getUniqueNumber()).thenReturn(1);

        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);

        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(pe1);

        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(st1,pe1,currentDate)).thenReturn(enrollMock1);

        // Act
        repository.enrollStudentInProgrammeEdition(st1, pe1, currentDate);

        // Assert
        assertTrue(repository.isStudentEnrolledInThisProgrammeEdition(st1, pe1));
    }

    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        Student st1 = mock(Student.class);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pe2 = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();
        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);

        when(st1.getUniqueNumber()).thenReturn(1);

        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);

        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(pe1);

        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(st1,pe1,currentDate)).thenReturn(enrollMock1);

        // Act
        repository.enrollStudentInProgrammeEdition(st1, pe1, currentDate);
        // Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(st1, pe2));
    }


    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear(){

        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition3Double = mock(ProgrammeEdition.class);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        Student student3Double = mock(Student.class);

        when(student1Double.getUniqueNumber()).thenReturn(1);
        when(student2Double.getUniqueNumber()).thenReturn(2);
        when(student3Double.getUniqueNumber()).thenReturn(3);

        LocalDate currentDate = LocalDate.now();
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory= mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student1Double,edition1Double,currentDate)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn(2);
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition2Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student2Double,edition2Double,currentDate)).thenReturn(enrollMock2);

        ProgrammeEditionEnrollment enrollMock3 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock3.getStudentUniqueNumber()).thenReturn(3);
        when(enrollMock3.findProgrammeEditionInEnrollment()).thenReturn(edition3Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student3Double,edition3Double,currentDate)).thenReturn(enrollMock3);


        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student2Double, edition2Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student3Double, edition3Double, currentDate);

        when(enrollMock1.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(true);
        when(enrollMock2.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(true);

        // act
        int result =repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(2, result);
    }

    @Test
    void shouldReturnZeroWhenNoStudentsAreEnrolledInDepartmentAndSchoolYear(){
        //arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(false);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(false);


        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        when(student1Double.getUniqueNumber()).thenReturn(1);
        when(student2Double.getUniqueNumber()).thenReturn(2);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student1Double,edition1Double,currentDate)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn(2);
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition2Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student2Double,edition2Double,currentDate)).thenReturn(enrollMock2);

        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student2Double, edition2Double, currentDate);

        // act
        int result =repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(0, result);
    }


    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInMultipleEditions() {
        // arrange
        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        ProgrammeEdition edition1Double = mock (ProgrammeEdition.class);
        ProgrammeEdition edition2Double= mock(ProgrammeEdition.class);

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        Student student1Double = mock(Student.class);

        LocalDate currentDate = LocalDate.now();

        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student1Double,edition1Double,currentDate)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn(1);
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student1Double,edition2Double,currentDate)).thenReturn(enrollMock2);

        when(enrollMock1.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(true);
        when(enrollMock2.isEnrollmentAssociatedToDepartmentAndSchoolYear(department1Double,schoolYear1Double)).thenReturn(true);


        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student1Double, edition2Double, currentDate);

        // act
        int result = repository.countStudentsInProgrammesFromDepartmentInSchoolYear(department1Double, schoolYear1Double);

        // assert
        assertEquals(1, result);
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInAProgrammeEdition()  {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);

        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);
        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);

        LocalDate currentDate = LocalDate.now();

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student1Double, edition1Double,currentDate)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn(2);
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition1Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student2Double, edition1Double,currentDate)).thenReturn(enrollMock2);

        int NumberOfStudentsEnrolledInAProgrammeEdition = 2;

        // Act
        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student2Double, edition1Double, currentDate);
        int result = repository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(edition1Double);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }


    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        ProgrammeEdition pe1 = mock(ProgrammeEdition.class);

        int NumberOfStudentsEnrolledInAProgrammeEdition = 0;

        // Act
        int result = repository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }


    @Test
    void shouldReturnZeroIfCheckingNumberOfStudentsInDifferentProgrammeEdition() {
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo repository = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        LocalDate currentDate = LocalDate.now();

        Department department1Double = mock(Department.class);
        SchoolYear schoolYear1Double = mock(SchoolYear.class);

        Student student1Double = mock(Student.class);
        Student student2Double = mock(Student.class);

        when(edition1Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);
        when(edition2Double.isEditionAssociatedToDepartmentAndSchoolYear(department1Double, schoolYear1Double)).thenReturn(true);

        ProgrammeEditionEnrollment enrollMock1 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock1.getStudentUniqueNumber()).thenReturn(1);
        when(enrollMock1.findProgrammeEditionInEnrollment()).thenReturn(edition2Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student1Double, edition2Double,currentDate)).thenReturn(enrollMock1);

        ProgrammeEditionEnrollment enrollMock2 = mock(ProgrammeEditionEnrollment.class);
        when(enrollMock2.getStudentUniqueNumber()).thenReturn(2);
        when(enrollMock2.findProgrammeEditionInEnrollment()).thenReturn(edition2Double);
        when(programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student2Double, edition2Double,currentDate)).thenReturn(enrollMock2);

        int NumberOfStudentsEnrolledInAProgrammeEdition1 = 0;

        // Act
        repository.enrollStudentInProgrammeEdition(student1Double, edition2Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student2Double, edition2Double, currentDate);

        int result = repository.getTheNumberOfStudentsEnrolledInAProgrammeEdition(edition1Double);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition1, result);
    }
}