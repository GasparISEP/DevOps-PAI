package PAI.service.student;

import PAI.VOs.Date;
import PAI.VOs.EnrolmentStatus;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeAndCoursesEnrolmentServiceImplTest {
    @Test
    void shouldReturnConstructor() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        //assert
        assertNotNull(service);

    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrolmentIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = null;
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrolmentRepositoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = null;
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionEnrolmentFactoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = null;
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionEnrolmentRepositoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = null;
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenAvailableServiceIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = null;
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionRepositoryIsNull() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = null;

        //assert
        assertThrows(IllegalArgumentException.class,() -> new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository));
    }

    @Test
    void shouldReturnProgrammeEditionEnrolment() {

        //arrange
        IProgrammeEditionEnrolmentFactory enrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentRepository enrolmentRepository = mock(IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentFactory courseEditionEnrolmentFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        IAvailableCoursesService availableCoursesService = mock(IAvailableCoursesService.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);

        ProgrammeAndCoursesEnrolmentServiceImpl service = new ProgrammeAndCoursesEnrolmentServiceImpl(enrolmentFactory, enrolmentRepository, courseEditionEnrolmentFactory,
                courseEditionEnrolmentRepository, availableCoursesService, courseEditionRepository);

        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        Date date = mock(Date.class);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);

        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(enrolmentFactory.createWithEnrolmentDate(studentID,programmeEditionID,date,enrolmentStatus)).thenReturn(programmeEditionEnrolment);

        //act

        ProgrammeEditionEnrolment result = service.createProgrammeEditionEnrolment(studentID,programmeEditionID,date,enrolmentStatus);

        //assert
        assertNotNull(result);

    }

}