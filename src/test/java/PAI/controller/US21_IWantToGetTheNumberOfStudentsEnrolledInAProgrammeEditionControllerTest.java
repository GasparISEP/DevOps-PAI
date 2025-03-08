package PAI.controller;

import PAI.domain.*;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import PAI.repository.ProgrammeEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionControllerTest {

    @Test
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryNull = null;
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryNull, programmeEditionEnrollmentRepoDouble));

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionEnrollmentRepositoryIsNull(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoNull = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble,programmeEditionEnrollmentRepoNull));

    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeEditionInList(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEditionDouble);

            // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrollmentRepoDouble);

            //Instructions
        when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());

    }

    @Test
    void shouldReturnProgrammeEditionInList(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
        //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEditionDouble);

        // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrollmentRepoDouble);

        //Instructions
        when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());
        assertEquals(programmeEditionDouble, result.get(0));
    }

    @Test
    void shouldReturnAllProgrammeEdition(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
        //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEdition programmeEdition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2Double = mock(ProgrammeEdition.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEdition1Double);
        programmeEditionList.add(programmeEdition2Double);

        // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrollmentRepoDouble);

        //Instructions
        when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(2, result.size());
        assertEquals(programmeEditionList.get(0), result.get(0));
        assertEquals(programmeEditionList.get(1), result.get(1));
    }

    @Test
    void shouldReturnCorrectNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception{
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble ,programmeEditionEnrollmentRepoDouble);

            //Instructions
        when(programmeEditionEnrollmentRepoDouble.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble)).thenReturn(1);

        // Act
        int result = controller.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull() {
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoDouble = mock(ProgrammeEditionEnrollmentRepo.class);
        ProgrammeEdition programmeEditionNull = null;

            // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrollmentRepoDouble);

        // Act + Assert
        assertThrows(Exception.class, () -> {
            us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionNull);
        });
    }
}