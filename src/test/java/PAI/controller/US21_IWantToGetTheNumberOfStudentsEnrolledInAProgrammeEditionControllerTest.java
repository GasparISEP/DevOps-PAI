package PAI.controller;

import PAI.domain.*;
import PAI.factory.ProgrammeEditionEnrollmentFactory;
import PAI.factory.ProgrammeEditionFactory;
import PAI.factory.ProgrammeEditionListFactory;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import PAI.repository.ProgrammeEditionRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionControllerTest {

    @Test
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull(){
        // Arrange
        ProgrammeEditionRepository programmeEditionRepositoryNull = null;
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryNull,programmeEditionEnrollmentRepo));

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionEnrollmentRepositoryIsNull(){
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepoNull = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository,programmeEditionEnrollmentRepoNull));

    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeEditionInList(){
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepository.class);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = mock(ProgrammeEditionEnrollmentRepo.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEdition);

        // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrollmentRepo);

        //Instructions
        when(programmeEditionRepository.getAllProgrammeEditions()).thenReturn(programmeEditionList);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());

    }

    @Test
    void shouldReturnCorrectNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception{
        // Arrange
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
}