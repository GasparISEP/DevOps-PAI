package PAI.controller;
import PAI.domain.*;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class US26_CountStudentsInProgrammesFromDepartmentInSchoolYearControllerTest {

    //testing the constructor
    //valid constructor
    @Test
    void shouldCreateControllerWhenRepositoriesAreValid() {
        // arrange
        ProgrammeEditionEnrollmentRepo PEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble= mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble= mock(DepartmentRepository.class);

        // Act & Assert
        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);
    }

    //test when ProgramEditionEnrollmentRepo is null
    @Test
    void shouldThrowExceptionWhenPEERepoIsNull(){
        // arrange
        SchoolYearRepository schoolYearRepoDouble= mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble= mock(DepartmentRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(null, schoolYearRepoDouble, departmentRepoDouble)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when SchoolYearRepo is null
    @Test
    void shouldThrowExceptionWhenSchoolYearRepoIsNull(){
        // arrange
        ProgrammeEditionEnrollmentRepo PEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        DepartmentRepository departmentRepoDouble= mock(DepartmentRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, null, departmentRepoDouble)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test when DepartmentRepo is null
    @Test
    void shouldThrowExceptionWhenDepartmentRepoNull(){
        // arrange
        ProgrammeEditionEnrollmentRepo PEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble= mock(SchoolYearRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, null)
        );
        assertEquals("Repositories cannot be null.", exception.getMessage());
    }

    //test that ensures that the method returns a positive int when there are students enrolled in Programmes from specified department and school year
    @Test
    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() {
        // Arrange
        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);
        ProgrammeEditionEnrollmentRepo PEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

        when(schoolYearRepoDouble.schoolYearExists(schoolYearDouble)).thenReturn(true);
        when(departmentRepoDouble.departmentExists(departmentDouble)).thenReturn(true);

        when(PEERepoDouble.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble)).thenReturn(3);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(PEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);

        // Act
        int result = controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);

        // Assert
        assertEquals(3, result);
    }


    //test that ensures that the method throws an exception when School Year is not present in the School Year Repository
   @Test
    void shouldThrowExceptionWhenSchoolYearDoesNotExist() {
        // Arrange
        ProgrammeEditionEnrollmentRepo pEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble= mock(SchoolYear.class);

        when(schoolYearRepoDouble.schoolYearExists(schoolYearDouble)).thenReturn(false);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);
        });

        assertEquals("SchoolYear does not exist.", exception.getMessage());
    }

    //test that ensures that the method throws an exception when Department is not present in the Department Repository
    @Test
    void shouldThrowExceptionWhenDepartmentDoesNotExist() {
        // arrange
        ProgrammeEditionEnrollmentRepo pEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

        Department departmentDouble = mock(Department.class);
        SchoolYear schoolYearDouble= mock(SchoolYear.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);

        when(schoolYearRepoDouble.schoolYearExists(schoolYearDouble)).thenReturn(true);
        when(departmentRepoDouble.departmentExists(departmentDouble)).thenReturn(false);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, schoolYearDouble);
        });

        // Assert
        assertEquals("Department does not exist.", exception.getMessage());
    }

    //test that ensures that the method throws an exception when Department is null
    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        // arrange
        ProgrammeEditionEnrollmentRepo pEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

        SchoolYear schoolYearDouble= mock(SchoolYear.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(null, schoolYearDouble);
        });
        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }

    //test that ensures that the method throws an exception when School Year is null
    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() {
        // arrange
        ProgrammeEditionEnrollmentRepo pEERepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        SchoolYearRepository schoolYearRepoDouble = mock(SchoolYearRepository.class);
        DepartmentRepository departmentRepoDouble = mock(DepartmentRepository.class);

       Department departmentDouble= mock(Department.class);

        US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController controller =
                new US26_CountStudentsInProgrammesFromDepartmentInSchoolYearController(pEERepoDouble, schoolYearRepoDouble, departmentRepoDouble);
        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            controller.countStudentsInProgrammesFromDepartmentInSchoolYear(departmentDouble, null);
        });

        // Assert
        assertEquals("Department or SchoolYear cannot be null", exception.getMessage());
    }
}