package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionControllerTest {

    @Test
    void shouldCreateControllerWhenRepositoriesAreValid(){
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        ProgrammeEditionFactory programmeEditionFactory = mock(ProgrammeEditionFactory.class);
        ProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactory.class);
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);


        // Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrollmentRepo);

        // Assert
        assertNotNull(controller);

    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrollmentRepositoryIsNull(){
        // Arrange
        ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory = mock(ProgrammeEditionEnrollmentFactory.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo(programmeEditionEnrollmentFactory);
        ProgrammeEditionFactory programmeEditionFactory = mock(ProgrammeEditionFactory.class);
        ProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactory.class);
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null));

    }

    @Test
    void shouldReturnCorrectNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception{
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
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controlador1 = new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(repository);

        repository.enrollStudentInProgrammeEdition(student1Double, edition1Double, currentDate);
        repository.enrollStudentInProgrammeEdition(student2Double, edition1Double, currentDate);

        int result = controlador1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(edition1Double);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }
}