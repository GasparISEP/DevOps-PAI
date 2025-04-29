package PAI.controller;

import PAI.VOs.*;

import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.factory.IProgrammeEditionEnrolmentListFactory;
import PAI.factory.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.factory.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.persistence.springdata.programmeEdition.IProgrammeEditionRepositorySpringData;
import PAI.persistence.springdata.programmeEdition.ProgrammeEditionRepositorySpringDataImpl;
import PAI.repository.*;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.IProgrammeEditionEnrolmentService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionControllerTest {

    @Test
    void shouldCreateControllerWhenRepositoryIsValid_Isolation_Test() {
        //SUT Controller
        //Arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        //Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        //Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenServiceIsNull() {
        //SUT Controller
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null, iProgrammeEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryIsNull() {
        //SUT Controller
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, null));
    }

    @Test
    void shouldGetTheTotalNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        when(iProgrammeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEditionID)).thenReturn(1);
        // act
        int result = controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID);
        // assert
        assertEquals(1, result);
    }

    @Test
    void shouldReturnIllegalArgumentExceptionIfProgrammeEditionIdNull() throws Exception {
        // arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(null));
    }

    @Test
    void shouldGetAllProgrammeEdition() {
        // arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionRepository);
        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(iProgrammeEditionRepository.findAll()).thenReturn(List.of(programmeEdition));
        // act
        Iterable<ProgrammeEdition> result = controller.getAllProgrammeEdition();
        // assert
        assertEquals(List.of(programmeEdition), result);
    }
}



