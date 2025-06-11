package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionServiceAssembler;
import PAI.assembler.programmeEdition.ProgrammeEditionServiceAssemblerImpl;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.courseEditionEnrolment.ICourseEditionEnrolmentListFactory;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.persistence.mem.programmeEditionEnrolment.IProgrammeEditionEnrolmentListFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolmentFactoryImpl;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentListFactoryImpl;
import PAI.persistence.mem.programmeEnrolment.IProgrammeEnrolmentListFactory;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentListFactoryImpl;
import PAI.domain.courseEdition.CourseEditionFactoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionListFactoryImpl;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.persistence.mem.courseEdition.ICourseEditionListFactory;
import PAI.persistence.mem.courseEditionEnrolment.CourseEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.programmeEditionEnrolment.ProgrammeEditionEnrolmentRepositoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.persistence.mem.courseEdition.CourseEditionRepositoryImpl;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.programmeEdition.IProgrammeEditionListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionListFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentRepositoryImpl;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.ProgrammeEditionEnrolmentServiceImpl;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("programme-edition-enrolment")
class US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionControllerTest {

    @Autowired private IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    @Autowired private IProgrammeEditionRepository programmeEditionRepository;
    @Autowired private ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository;
    @Autowired private ICourseEditionRepository courseEditionRepository;
    @Autowired private ISchoolYearRepository schoolYearRepository;
    @Autowired private ISchoolYearService schoolYearService;
    @Autowired private IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    @Autowired private IProgrammeRepository programmeRepository;
    @Autowired private IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory;
    @Autowired private IProgrammeEditionFactory programmeEditionFactory;
    @Autowired private IProgrammeEditionServiceAssembler programmeEditionAssembler;
    @Autowired private IProgrammeService programmeService;

    private IProgrammeEditionEnrolmentService programmeEditionEnrolmentService;
    private IProgrammeEditionService programmeEditionService;

    private US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller;

    @BeforeEach
    void setUp() {
        programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
            programmeEditionEnrolmentRepository,
            programmeEditionRepository,
            courseEditionEnrolmentRepository,
            courseEditionRepository,
            schoolYearRepository,
            programmeEnrolmentRepository,
            programmeRepository,
            programmeEditionEnrolmentFactory
        );
        programmeEditionService = new ProgrammeEditionService(
                programmeEditionFactory,
                programmeEditionRepository,
                programmeService,
                programmeEditionAssembler,
                schoolYearService,
                programmeEditionEnrolmentService
        );

        controller = new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(
            programmeEditionEnrolmentService,
                programmeEditionService
        );
    }

    @Test
    void shouldReturnOneWhenOneStudentEnrolledInAProgrammeEdition_Integration_Test_JPA() throws Exception {
        // Arrange
        UUID schoolYearID = UUID.fromString("1c7f0b41-bf0b-41eb-9ac7-e654ee592bf4");
        SchoolYearID schoolYearID1 = new SchoolYearID(schoolYearID);
        ProgrammeID programmeID = new ProgrammeID(
            new Acronym("SWE")
        );
        /*UUID programmeEditionErolmentGeneratedID = UUID.fromString("d5f93a36-8b9a-4de5-b3f8-6e3ac8791fa4");
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedIDID = new ProgrammeEditionEnrolmentGeneratedID(programmeEditionErolmentGeneratedID);*/
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID1);

        // Act
        int result = controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID);
        
        // Assert
        assertEquals(1, result);


    }

    @Test
    void shouldReturnZeroWhenNoStudentsEnrolledInProgrammeEdition_Integration_Test_JPA() throws Exception {
        // Arrange
        UUID schoolYearID = UUID.fromString("1c7f141-bf0b-41eb-9ac7-e654ee592bf4");
        SchoolYearID schoolYearID1 = new SchoolYearID(schoolYearID);
        ProgrammeID programmeID = new ProgrammeID(
            new Acronym("CHY")
        );

        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID1);

        // Act
        int result = controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID);
        
        // Assert
        assertEquals(0, result);
    }

    @Test
    void shouldCreateControllerWhenRepositoryIsValid_Isolation_Test() {
        //SUT Controller
        //Arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionService iProgrammeEditionService = mock(IProgrammeEditionService.class);
        //Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionService);
        //Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenServiceIsNull() {
        //SUT Controller
        IProgrammeEditionService iProgrammeEditionService = mock(IProgrammeEditionService.class);
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null, iProgrammeEditionService));
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
        IProgrammeEditionService iProgrammeEditionService = mock(IProgrammeEditionService.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionService);
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
        IProgrammeEditionService iProgrammeEditionService = mock(IProgrammeEditionService.class);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionService);
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> controller.getTheNumberOfStudentsEnrolledInAProgrammeEdition(null));
    }

    @Test
    void shouldGetTheNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);
        StudentID studentID3 = mock(StudentID.class);
        StudentID studentID4 = mock(StudentID.class);

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();

        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactoryImpl);


        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);

        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = mock(IProgrammeAssembler.class);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(IDegreeTypeRegistrationService.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);

        IProgrammeService programmeService = new ProgrammeServiceImpl(
                programmeFactory,
                programmeRepository,
                programmeAssembler,
                degreeTypeRegistrationService
        );
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);

        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionServiceAssembler programmeEditionAssembler = new ProgrammeEditionServiceAssemblerImpl();
        IProgrammeEditionService iProgrammeEditionService = new ProgrammeEditionService(programmeEditionFactory,programmeEditionRepository,programmeService,programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                programmeEditionEnrolmentRepository,
                programmeEditionRepository,
                courseEditionEnrolmentRepositoryImpl,
                courseEditionRepository,
                schoolYearRepository,
                programmeEnrolmentRepository,
                programmeRepository,
                programmeEditionEnrolmentFactory);

        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();
        SchoolYearID schoolYearID2 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentService, iProgrammeEditionService);

        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID2, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID3, programmeEditionID1);
        programmeEditionEnrolmentService.enrolStudentInProgrammeEdition(studentID4, programmeEditionID2);

        // Act
        int result = controller1.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1);

        // Assert
        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() throws Exception {
        //Arrange

        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrolmentListFactoryImpl);

        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrolmentFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeRepositoryListFactory IProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory);
        IProgrammeAssembler programmeAssembler = mock(IProgrammeAssembler.class);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(IDegreeTypeRegistrationService.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);

        IProgrammeService programmeService = new ProgrammeServiceImpl(
                programmeFactory,
                programmeRepository,
                programmeAssembler,
                degreeTypeRegistrationService
        );
        ICourseEditionFactory courseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();
        ICourseEditionRepository courseEditionRepository = new CourseEditionRepositoryImpl(courseEditionFactory, courseEditionListFactory);
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryImpl = new CourseEditionEnrolmentRepositoryImpl(courseEditionEnrollmentListFactory);
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactoryImpl);
        IProgrammeEnrolmentListFactory programmeEnrolmentList = new ProgrammeEnrolmentListFactoryImpl();
        IProgrammeEnrolmentRepository programmeEnrolmentRepository = new ProgrammeEnrolmentRepositoryImpl(programmeEnrolmentList);
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionServiceAssembler programmeEditionAssembler = new ProgrammeEditionServiceAssemblerImpl();
        IProgrammeEditionService iProgrammeEditionService = new ProgrammeEditionService(programmeEditionFactory,programmeEditionRepository,programmeService,programmeEditionAssembler, schoolYearService, programmeEditionEnrolmentService);
        IProgrammeEditionEnrolmentService programmeEditionEnrolmentService = new ProgrammeEditionEnrolmentServiceImpl(
                programmeEditionEnrolmentRepository,
                programmeEditionRepository,
                courseEditionEnrolmentRepositoryImpl,
                courseEditionRepository,
                schoolYearRepository,
                programmeEnrolmentRepository,
                programmeRepository,
                programmeEditionEnrolmentFactory);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentService, iProgrammeEditionService);

        // Act
        int result = controller1.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1);

        // Assert
        assertEquals(0, result);
    }
//


    @Test
    void shouldGetAllProgrammeEdition() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentService iProgrammeEditionEnrolmentService = mock(IProgrammeEditionEnrolmentService.class);
        IProgrammeEditionService iProgrammeEditionService = mock(IProgrammeEditionService.class);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(iProgrammeEditionEnrolmentService, iProgrammeEditionService);

        ProgrammeIDDTO programmeIDDTO = new ProgrammeIDDTO("CSE");
        String schoolYearId = UUID.randomUUID().toString();

        ProgrammeEditionResponseServiceDTO dto = new ProgrammeEditionResponseServiceDTO(programmeIDDTO, schoolYearId);
        List<ProgrammeEditionResponseServiceDTO> dtoList = List.of(dto);

        when(iProgrammeEditionService.findAllProgrammeEditions()).thenReturn(dtoList);

        // Act
        Iterable<ProgrammeEditionResponseServiceDTO> result = controller.getAllProgrammeEdition();

        // Assert
        assertNotNull(result);
        assertEquals(dtoList, result);
    }
}



