
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.DegreeTypeDDD.DegreeType_2;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.DegreeTypeRepoDDD.IDegreeTypeRepository_2;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.courseInStudyPlanRepo.ICourseInStudyPlanDDDRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.studyPlanRepo.IStudyPlanDDDRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US19_CreateCourseEditionControllerTest {

    @Test
    void shouldCreateControllerSuccessfully() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble,studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);
        //Assert
        assertNotNull(us19Controller);
    }

    @Test
    void shouldThrowExceptionIfDegreeTypeRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(null, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, null, studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfStudyPlanRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, null, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble,studyPlanRepositoryDouble, null, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, null, courseEditionRepositoryDouble);});
    }

    @Test
    void shouldThrowExceptionIfCourseEditionRepositoryIsNull() {
        //SUT = Controller
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);

        //Act
        //Assert
        assertThrows(Exception.class, () -> {new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, null);});
    }

    @Test
    void shouldReturnListOfDegreeTypes() {
        // Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        DegreeType_2 degreeType1Double = mock(DegreeType_2.class);
        DegreeType_2 degreeType2Double = mock(DegreeType_2.class);
        List<DegreeType_2> expectedList = List.of(degreeType1Double, degreeType2Double);

        when(degreeTypeRepositoryDouble.getAllDegreeTypes()).thenReturn(expectedList);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
                degreeTypeRepositoryDouble,
                programmeRepositoryDouble,
                studyPlanRepositoryDouble,
                courseInStudyPlanRepositoryDouble,
                programmeEditionRepositoryDouble,
                courseEditionRepositoryDouble
        );

        // Act
        List<DegreeType_2> result = controller.getAllDegreeTypes();

        // Assert
        assertEquals(expectedList, result);
        assertTrue(result.contains(degreeType1Double));
        assertTrue(result.contains(degreeType2Double));
        verify(degreeTypeRepositoryDouble).getAllDegreeTypes();
    }

    @Test
    void shouldReturnListOfProgrammesByDegreeTypeID() throws Exception {
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);


        DegreeTypeID degreeTypeIDDouble = mock(DegreeTypeID.class);

        Programme programme = mock(Programme.class);
        when(programme.getDegreeTypeID()).thenReturn(degreeTypeIDDouble);

        List<Programme> ListWithProgramme = Arrays.asList(programme);
        when(programmeRepositoryDouble.getProgrammesByDegreeTypeID(degreeTypeIDDouble)).thenReturn(ListWithProgramme);

        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);

        //Act
        List<Programme> result = us19Controller.getProgrammesByDegreeTypeID(degreeTypeIDDouble);

        //Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(programme));
    }

    @Test
    void shouldReturnListOfCoursesInStudyPLanByProgrammeID() {
        //Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        CourseInStudyPlan courseInStudyPlan = mock(CourseInStudyPlan.class);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        StudyPlanID studyPlanIDDouble = mock(StudyPlanID.class);

        when(studyPlanRepositoryDouble.getLatestStudyPlanIDByProgrammeID(programmeIDDouble)).thenReturn(studyPlanIDDouble);

        List<CourseInStudyPlan> ListOfCourseInStudyPlan = Arrays.asList(courseInStudyPlan);
        when(courseInStudyPlanRepositoryDouble.getCoursesInStudyPlanByStudyPlanID(studyPlanIDDouble)).thenReturn(ListOfCourseInStudyPlan);

        US19_CreateCourseEditionController us19Controller = new US19_CreateCourseEditionController(degreeTypeRepositoryDouble, programmeRepositoryDouble, studyPlanRepositoryDouble, courseInStudyPlanRepositoryDouble, programmeEditionRepositoryDouble, courseEditionRepositoryDouble);

        //Act
        List<CourseInStudyPlan> result = us19Controller.getCoursesInStudyPlanByProgrammeID(programmeIDDouble);

        //Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(courseInStudyPlan));
    }

    @Test
    void shouldReturnListOfProgrammeEditionsByProgrammeID(){
        //SUT = Controller
        // Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        List<ProgrammeEdition> expectedList = List.of(edition1Double, edition2Double);

        when(programmeEditionRepositoryDouble.getProgrammeEditionsByProgrammeID(programmeIDDouble)).thenReturn(expectedList);

        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
                degreeTypeRepositoryDouble,
                programmeRepositoryDouble,
                studyPlanRepositoryDouble,
                courseInStudyPlanRepositoryDouble,
                programmeEditionRepositoryDouble,
                courseEditionRepositoryDouble
        );

        // Act
        List<ProgrammeEdition> result = controller.getProgrammeEditionsByProgrammeID(programmeIDDouble);

        // Assert
        assertEquals(expectedList, result);
        verify(programmeEditionRepositoryDouble).getProgrammeEditionsByProgrammeID(programmeIDDouble);
    }

    @Test
    void shouldReturnTrueIfCourseEditionIsCreatedSuccessfully() {
        // Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        when(courseEditionRepositoryDouble.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble))
                .thenReturn(true);

        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
                degreeTypeRepositoryDouble,
                programmeRepositoryDouble,
                studyPlanRepositoryDouble,
                courseInStudyPlanRepositoryDouble,
                programmeEditionRepositoryDouble,
                courseEditionRepositoryDouble
        );

        // Act
        boolean result = controller.createCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        // Assert
        assertTrue(result);
        verify(courseEditionRepositoryDouble, times(1)).createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreated() {
        // Arrange
        IDegreeTypeRepository_2 degreeTypeRepositoryDouble = mock(IDegreeTypeRepository_2.class);
        IProgrammeDDDRepository programmeRepositoryDouble = mock(IProgrammeDDDRepository.class);
        IStudyPlanDDDRepository studyPlanRepositoryDouble = mock(IStudyPlanDDDRepository.class);
        ICourseInStudyPlanDDDRepository courseInStudyPlanRepositoryDouble = mock(ICourseInStudyPlanDDDRepository.class);
        IProgrammeEditionRepositoryDDD programmeEditionRepositoryDouble = mock(IProgrammeEditionRepositoryDDD.class);
        ICourseEditionRepository courseEditionRepositoryDouble = mock(ICourseEditionRepository.class);

        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        when(courseEditionRepositoryDouble.createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble))
                .thenReturn(false);

        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(
                degreeTypeRepositoryDouble,
                programmeRepositoryDouble,
                studyPlanRepositoryDouble,
                courseInStudyPlanRepositoryDouble,
                programmeEditionRepositoryDouble,
                courseEditionRepositoryDouble
        );

        // Act
        boolean result = controller.createCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);

        // Assert
        assertFalse(result);
        verify(courseEditionRepositoryDouble, times(1)).createAndSaveCourseEdition(courseInStudyPlanIDDouble, programmeEditionIDDouble);
    }

//
//    //INTEGRATION TESTS
//    //setUp
//    private IProgrammeEditionFactory programmeEditionFactory;
//    private IProgrammeEditionListFactory programmeEditionListFactory;
//    private ProgrammeEditionRepository programmeEditionRepository;
//
//    private ICourseEditionFactory ICourseEditionFactory;
//    private ICourseEditionListFactory ICourseEditionListFactory;
//    private CourseEditionRepository courseEditionRepository;
//
//    private IProgrammeFactory IProgrammeFactory;
//    private ProgrammeCourseListFactoryImpl programmeCourseListFactory;
//    private IProgrammeRepositoryListFactory programmeListFactory;
//    private ProgrammeRepository programmeRepository;
//
//    private ICourseInStudyPlanFactory ICourseInStudyPlanFactory;
//    private IStudyPlanListFactory IStudyPlanListFactory;
//    private IStudyPlanFactory IStudyPlanFactory;
//    private ICourseFactory ICourseFactory;
//
//    private US19_CreateCourseEditionController controller;
//
//    private Course course;
//
//    private TeacherCareerProgressionListFactoryImpl teacherCareerListFactory;
//    private TeacherCareerProgressionFactoryImpl teacherCareerFactory;
//    private TeacherCategory teacherCategory;
//
//    private ProgrammeEdition programmeEdition;
//    private Programme programme;
//    private SchoolYear schoolYear;
//    private Date date;
//    private TeacherCategoryID tcID;
//    private WorkingPercentage wp;
//    private TeacherID teacherID;
//
//
//

//    @BeforeEach
//    void setUp() throws Exception {
//        programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//
//        ICourseEditionFactory = new CourseEditionFactoryImpl();
//        ICourseEditionListFactory = new CourseEditionListFactoryImpl();
//        courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);
//
//        IProgrammeFactory = new ProgrammeFactoryImpl();
//        programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
//        programmeListFactory = new ProgrammeRepositoryListFactoryImpl();
//        programmeRepository = new ProgrammeRepository(IProgrammeFactory, programmeListFactory);
//
//        ICourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
//        IStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanFactory = new StudyPlanFactoryImpl();
//        ICourseFactory = new CourseFactoryImpl();
//
//        controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeRepository);
//
//        course = new Course("Informatics", "INF", 6, 1);
//        addressFactory = new AddressFactoryImpl();
//
//        teacherCareerListFactory = new TeacherCareerProgressionListFactoryImpl();
//        teacherCareerFactory = new TeacherCareerProgressionFactoryImpl();
//        teacherCategory = new TeacherCategory("Professor Adjunto");
//        date = new Date("15-04-2005");
//        tcID = new TeacherCategoryID();
//        wp = new WorkingPercentage(70);
//        teacherID = TeacherID.createNew();
//
//        programme = new Programme("Computer Engineering", "CE", 20, 6,
//                        new DegreeType("Master", 240),
//                        new Department("CSE", "Computer Science Engineer"),
//                        new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                                "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                                "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                                tcID, wp, teacherID,
//                                new Department("CSE", "Computer Science Engineer"),
//                                teacherCareerFactory, teacherCareerListFactory),
//                        programmeCourseListFactory,
//                ICourseInStudyPlanFactory,
//                IStudyPlanListFactory,
//                IStudyPlanFactory,
//                ICourseFactory);
//
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date("23-11-2024");
//        Date endDate = new Date ("09-12-2025");
//        schoolYear = new SchoolYear(description, startDate, endDate);
//
//        programmeEdition = new ProgrammeEdition(programme, schoolYear);
//    }
//
//    @Test
//    void shouldReturnTrueIfCourseEditionIsCreated_integration() throws Exception {
//        // Arrange
//        programme.addCourseToAProgramme(course);
//        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID();
//        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();
//        // Act
//        boolean result = controller.createCourseEdition(courseInStudyPlanID, programmeEditionID);
//
//        // Assert
//        assertTrue(result);
//    }
//
//        @Test
//        void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseCourseIsNull_integration() {
//            // Arrange
//            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();
//            // Act
//            boolean result = controller.createCourseEdition(null, programmeEditionID);
//
//            // Assert
//            assertFalse(result);
//        }
//
//        @Test
//        void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseProgrammeEditionIsNull_integration() {
//            // Arrange
//            CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID();
//            // Act
//            boolean result = controller.createCourseEdition(courseInStudyPlanID, null);
//
//            // Assert
//            assertFalse(result);
//        }
//
//        @Test
//        void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseProgrammeEditionAndCourseAreNull_integration() {
//            // Arrange
//            // Act
//            boolean result = controller.createCourseEdition(null, null);
//
//            // Assert
//            assertFalse(result);
//        }
//
//        @Test
//        void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty_integration() {
//            // Act + Assert
//            assertNotNull(controller.getAllProgrammeEditions());
//        }
//
//        @Test
//        void ShouldReturnSizeOfListOfProgrammeEditionsForMethodGetAllProgrammeEditions_integration() throws Exception {
//            // Arrange
//            Programme programme2 = new Programme("Computer Science", "CC", 20, 6,
//                    new DegreeType("Master", 240),
//                    new Department("CSE", "Computer Science Engineer"),
//                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                            tcID, wp, teacherID,
//                            new Department("CSE", "Computer Science Engineer"),
//                            teacherCareerFactory, teacherCareerListFactory),
//                    programmeCourseListFactory,
//                    ICourseInStudyPlanFactory,
//                    IStudyPlanListFactory,
//                    IStudyPlanFactory,
//                    ICourseFactory);
//
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//            programmeEditionRepository.createProgrammeEdition(programme2,schoolYear);
//
//            // Act
//            controller.getAllProgrammeEditions();
//
//            // Assert
//            assertEquals(2, controller.getAllProgrammeEditions().size());
//        }
//
//
//        @Test
//        void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition_integration() throws Exception {
//            // Arrange
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//            // Act
//            List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();
//
//            // Assert
//            assertTrue(allEditions.contains (programmeEdition));
//        }
//
//        @Test
//        void shouldReturnFalseIfListOfProgrammeEditionsNotContainsProgrammeEdition_integration() throws Exception {
//            // Arrange
//            Programme programme2 = new Programme("Computer Science", "CC", 20, 6,
//                    new DegreeType("Master", 240),
//                    new Department("CSE", "Computer Science Engineer"),
//                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                            tcID, wp, teacherID,
//                            new Department("CSE", "Computer Science Engineer"),
//                            teacherCareerFactory, teacherCareerListFactory),
//                    programmeCourseListFactory,
//                    ICourseInStudyPlanFactory,
//                    IStudyPlanListFactory,
//                    IStudyPlanFactory,
//                    ICourseFactory);
//
//            ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme2,schoolYear);
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//            // Act
//            List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();
//            // Assert
//            assertFalse(allEditions.contains (programmeEdition2));
//        }
//
//
//
//        @Test
//        void shouldReturnSizeOfCourseListInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
//            // Arrange
//            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
//            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
//            courseRepository.registerCourse("Informatica", "INF", 6, 1);
//            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
//            Course c1 = new Course ("Informatica", "INF", 6, 1);
//            Course c2 = new Course("Matemática", "MAT", 4, 1);
//            Acronym acronym = new Acronym("CC");
//            QuantEcts quantEcts = new QuantEcts(20);
//            QuantSemesters quantSemesters = new QuantSemesters(6);
//            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
//
//            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
//                    new DegreeType("Master", 240),
//                    new Department("CSE", "Computer Science Engineer"),
//                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                            tcID, wp, teacherID,
//                            new Department("CSE", "Computer Science Engineer"),
//                            teacherCareerFactory, teacherCareerListFactory),
//                    programmeCourseListFactory,
//                    ICourseInStudyPlanFactory,
//                    IStudyPlanListFactory,
//                    IStudyPlanFactory,
//                    ICourseFactory);
//
//            programme.addCourseToAProgramme(c1);
//            programme.addCourseToAProgramme(c2);
//
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//            // Act + Assert
//            assertEquals(2, controller.getCoursesInProgramme(programmeEdition).size());
//        }
//
//        @Test
//        void shouldReturnNotNullEvenIfCourseListIsEmptyInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
//            // Arrange
//            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
//            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
//            courseRepository.registerCourse("Informatica", "INF", 6, 1);
//            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
//            Acronym acronym = new Acronym("CC");
//            QuantEcts quantEcts = new QuantEcts(20);
//            QuantSemesters quantSemesters = new QuantSemesters(6);
//            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
//
//            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
//                    new DegreeType("Master", 240),
//                    new Department("CSE", "Computer Science Engineer"),
//                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                            tcID, wp, teacherID,
//                            new Department("CSE", "Computer Science Engineer"),
//                            teacherCareerFactory, teacherCareerListFactory),
//                    programmeCourseListFactory,
//                    ICourseInStudyPlanFactory,
//                    IStudyPlanListFactory,
//                    IStudyPlanFactory,
//                    ICourseFactory);
//
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//
//            // Act + Assert
//            assertNotNull(controller.getCoursesInProgramme(programmeEdition));
//        }
//
//        @Test
//        void shouldReturnTrueIfCourseListHasCourseInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
//            // Arrange
//            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
//            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
//            courseRepository.registerCourse("Informatica", "INF", 6, 1);
//            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
//            Acronym acronym = new Acronym("CC");
//            QuantEcts quantEcts = new QuantEcts(20);
//            QuantSemesters quantSemesters = new QuantSemesters(6);
//            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
//
//            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
//                    new DegreeType("Master", 240),
//                    new Department("CSE", "Computer Science Engineer"),
//                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                            tcID, wp, teacherID,
//                            new Department("CSE", "Computer Science Engineer"),
//                            teacherCareerFactory, teacherCareerListFactory),
//                    programmeCourseListFactory,
//                    ICourseInStudyPlanFactory,
//                    IStudyPlanListFactory,
//                    IStudyPlanFactory,
//                    ICourseFactory);
//
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//            Course c1 = new Course ("Informatica", "INF", 6, 1);
//            Course c2 = new Course("Matemática", "MAT", 4, 1);
//
//            programme.addCourseToAProgramme(c1);
//            programme.addCourseToAProgramme(c2);
//
//            // Act + Assert
//            assertTrue(controller.getCoursesInProgramme(programmeEdition).contains(c1));
//        }
//
//        @Test
//        void shouldReturnFalseIfCourseListNotHaveCourseInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
//            // Arrange
//            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
//            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
//            courseRepository.registerCourse("Informatica", "INF", 6, 1);
//            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
//            Acronym acronym = new Acronym("CC");
//            QuantEcts quantEcts = new QuantEcts(20);
//            QuantSemesters quantSemesters = new QuantSemesters(6);
//            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
//
//            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
//                    new DegreeType("Master", 240),
//                    new Department("CSE", "Computer Science Engineer"),
//                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, date,
//                            tcID, wp, teacherID,
//                            new Department("CSE", "Computer Science Engineer"),
//                            teacherCareerFactory, teacherCareerListFactory),
//                    programmeCourseListFactory,
//                    ICourseInStudyPlanFactory,
//                    IStudyPlanListFactory,
//                    IStudyPlanFactory,
//                    ICourseFactory);
//
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//            Course c1 = new Course ("Informatica", "INF", 6, 1);
//            Course c2 = new Course("Matemática", "MAT", 4, 1);
//
//            programme.addCourseToAProgramme(c1);
//
//            ProgrammeEdition programmeEdition = new ProgrammeEdition(programme,schoolYear);
//            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
//
//            // Act + Assert
//            assertFalse(controller.getCoursesInProgramme(programmeEdition).contains(c2));
//        }


    }
