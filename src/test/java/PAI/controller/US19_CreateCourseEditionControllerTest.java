package PAI.controller;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.CourseEditionRepository;
import PAI.repository.CourseRepository;
import PAI.repository.ProgrammeEditionRepository;
import PAI.repository.ProgrammeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US19_CreateCourseEditionControllerTest {


    @Test
    void shouldReturnTrueIfCourseEditionIsCreated_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock (ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (courseEditionRepositoryDouble.createAndSaveCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(true);

        // Act
        boolean result = controller.createCourseEdition(courseDouble, programmeEditionDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreated_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock (ProgrammeEdition.class);
        Course course = mock (Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (courseEditionRepositoryDouble.createAndSaveCourseEdition(course, programmeEditionDouble)).thenReturn(false);

        // Act
        boolean result = controller.createCourseEdition(course, programmeEditionDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        List<ProgrammeEdition> allEditionsDouble = mock (List.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(allEditionsDouble);

        //Act
        controller.getAllProgrammeEditions();
        //Assert
        assertNotNull(allEditionsDouble);
    }


    @Test
    void ShouldReturnSizeOfListOfProgrammeEditionsForMethodGetAllProgrammeEditions_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

        //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(List.of(programmeEditionDouble1));

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertEquals(1, controller.getAllProgrammeEditions().size());
    }


    @Test
    void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(List.of(programmeEditionDouble));

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertTrue(controller.getAllProgrammeEditions().contains(programmeEditionDouble));
    }


    @Test
    void shouldReturnFalseIfListOfProgrammeEditionsDoesNotContainProgrammeEdition_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEditionDouble2 = mock(ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(List.of(programmeEditionDouble2));

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertFalse(controller.getAllProgrammeEditions().contains(programmeEditionDouble));
    }


    @Test
    void shouldReturnSizeOfCourseListInProgrammeForGetCoursesInProgrammeMethod_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

        //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(List.of(courseDouble1, courseDouble2));

        // Act
        controller.getCoursesInProgramme(programmeEditionDouble);

        // Assert
        assertEquals(2, controller.getCoursesInProgramme(programmeEditionDouble).size());
    }

    @Test
    void shouldReturnNotNullEvenIfCourseListIsEmptyInProgrammeForGetCoursesInProgrammeMethod_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        List <Course> courseListDouble= mock(List.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(courseListDouble);

        // Act + Assert
        assertNotNull(controller.getCoursesInProgramme(programmeEditionDouble));
    }

    @Test
    void shouldReturnTrueIfCourseListHasCourseInProgrammeForGetCoursesInProgrammeMethod_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(List.of(courseDouble1, courseDouble2));

        // Act+Assert
        assertTrue(controller.getCoursesInProgramme(programmeEditionDouble).contains(courseDouble1));
    }

    @Test
    void shouldReturnFalseIfCourseListNotHaveCourseInProgrammeForGetCoursesInProgrammeMethod_isolated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeRepository programmeListDouble = mock (ProgrammeRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble1 = mock(Course.class);
        Course courseDouble2 = mock(Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.findProgrammeInProgrammeEdition(programmeEditionDouble)).thenReturn(programmeDouble);
        when (programmeListDouble.getCourseList(programmeDouble)).thenReturn(List.of(courseDouble1));


        // Act
        controller.getCoursesInProgramme(programmeEditionDouble);

        // Assert
        assertFalse(controller.getCoursesInProgramme(programmeEditionDouble).contains(courseDouble2));
    }

    //INTEGRATION TESTS
    //setUp
    private IProgrammeEditionFactory programmeEditionFactory;
    private IProgrammeEditionListFactory programmeEditionListFactory;
    private ProgrammeEditionRepository programmeEditionRepository;

    private ICourseEditionFactory ICourseEditionFactory;
    private ICourseEditionListFactory ICourseEditionListFactory;
    private CourseEditionRepository courseEditionRepository;

    private IProgrammeFactory IProgrammeFactory;
    private ProgrammeCourseListFactoryImpl programmeCourseListFactory;
    private IProgrammeRepositoryListFactory programmeListFactory;
    private ProgrammeRepository programmeRepository;

    private ICourseInStudyPlanFactory ICourseInStudyPlanFactory;
    private IStudyPlanListFactory IStudyPlanListFactory;
    private IStudyPlanFactory IStudyPlanFactory;
    private ICourseFactory ICourseFactory;

    private US19_CreateCourseEditionController controller;

    private Course course;
    private IAddressFactory addressFactory;

    private TeacherCareerProgressionListFactoryImpl teacherCareerListFactory;
    private TeacherCareerProgressionFactoryImpl teacherCareerFactory;
    private TeacherCategory teacherCategory;

    private ProgrammeEdition programmeEdition;
    private Programme programme;
    private SchoolYear schoolYear;



    @BeforeEach
    void setUp() throws Exception {
        programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);

        ICourseEditionFactory = new CourseEditionFactoryImpl();
        ICourseEditionListFactory = new CourseEditionListFactoryImpl();
        courseEditionRepository = new CourseEditionRepository(ICourseEditionFactory, ICourseEditionListFactory);

        IProgrammeFactory = new ProgrammeFactoryImpl();
        programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        programmeListFactory = new ProgrammeRepositoryListFactoryImpl();
        programmeRepository = new ProgrammeRepository(IProgrammeFactory, programmeListFactory);

        ICourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        IStudyPlanListFactory = new StudyPlanListFactoryImpl();
        IStudyPlanFactory = new StudyPlanFactoryImpl();
        ICourseFactory = new CourseFactoryImpl();

        controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeRepository);

        course = new Course("Informatics", "INF", 6, 1);
        addressFactory = new AddressFactoryImpl();

        teacherCareerListFactory = new TeacherCareerProgressionListFactoryImpl();
        teacherCareerFactory = new TeacherCareerProgressionFactoryImpl();
        teacherCategory = new TeacherCategory("Professor Adjunto");

        programme = new Programme("Computer Engineering", "CE", 20, 6,
                        new DegreeType("Master", 240),
                        new Department("CSE", "Computer Science Engineer"),
                        new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                                "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                                teacherCategory, 70,
                                new Department("CSE", "Computer Science Engineer"),
                                teacherCareerFactory, teacherCareerListFactory),
                        programmeCourseListFactory,
                ICourseInStudyPlanFactory,
                IStudyPlanListFactory,
                IStudyPlanFactory,
                ICourseFactory);

        schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        programmeEdition = new ProgrammeEdition(programme, schoolYear);
    }

    @Test
    void shouldReturnTrueIfCourseEditionIsCreated_integration() throws Exception {
        // Arrange
        programme.addCourseToAProgramme(course);
        // Act
        boolean result = controller.createCourseEdition(course, programmeEdition);

        // Assert
        assertTrue(result);
    }

        @Test
        void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseCourseIsNull_integration() {
            // Arrange
            // Act
            boolean result = controller.createCourseEdition(null, programmeEdition);

            // Assert
            assertFalse(result);
        }

        @Test
        void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseProgrammeEditionIsNull_integration() {
            // Arrange
            // Act
            boolean result = controller.createCourseEdition(course, null);

            // Assert
            assertFalse(result);
        }

        @Test
        void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseProgrammeEditionAndCourseAreNull_integration() {
            // Arrange
            // Act
            boolean result = controller.createCourseEdition(null, null);

            // Assert
            assertFalse(result);
        }

        @Test
        void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty_integration() {
            // Act + Assert
            assertNotNull(controller.getAllProgrammeEditions());
        }

        @Test
        void ShouldReturnSizeOfListOfProgrammeEditionsForMethodGetAllProgrammeEditions_integration() throws Exception {
            // Arrange
            Programme programme2 = new Programme("Computer Science", "CC", 20, 6,
                    new DegreeType("Master", 240),
                    new Department("CSE", "Computer Science Engineer"),
                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                            teacherCategory, 70,
                            new Department("CSE", "Computer Science Engineer"),
                            teacherCareerFactory, teacherCareerListFactory),
                    programmeCourseListFactory,
                    ICourseInStudyPlanFactory,
                    IStudyPlanListFactory,
                    IStudyPlanFactory,
                    ICourseFactory);

            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
            programmeEditionRepository.createProgrammeEdition(programme2,schoolYear);

            // Act
            controller.getAllProgrammeEditions();

            // Assert
            assertEquals(2, controller.getAllProgrammeEditions().size());
        }


        @Test
        void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition_integration() throws Exception {
            // Arrange
            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

            // Act
            List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();

            // Assert
            assertTrue(allEditions.contains (programmeEdition));
        }

        @Test
        void shouldReturnFalseIfListOfProgrammeEditionsNotContainsProgrammeEdition_integration() throws Exception {
            // Arrange
            Programme programme2 = new Programme("Computer Science", "CC", 20, 6,
                    new DegreeType("Master", 240),
                    new Department("CSE", "Computer Science Engineer"),
                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                            teacherCategory, 70,
                            new Department("CSE", "Computer Science Engineer"),
                            teacherCareerFactory, teacherCareerListFactory),
                    programmeCourseListFactory,
                    ICourseInStudyPlanFactory,
                    IStudyPlanListFactory,
                    IStudyPlanFactory,
                    ICourseFactory);

            ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme2,schoolYear);
            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

            // Act
            List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();
            // Assert
            assertFalse(allEditions.contains (programmeEdition2));
        }



        @Test
        void shouldReturnSizeOfCourseListInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
            // Arrange
            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
            courseRepository.registerCourse("Informatica", "INF", 6, 1);
            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
            Course c1 = new Course ("Informatica", "INF", 6, 1);
            Course c2 = new Course("Matemática", "MAT", 4, 1);
            Acronym acronym = new Acronym("CC");
            QuantEcts quantEcts = new QuantEcts(20);
            QuantSemesters quantSemesters = new QuantSemesters(6);
            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
                    new DegreeType("Master", 240),
                    new Department("CSE", "Computer Science Engineer"),
                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                            teacherCategory, 70,
                            new Department("CSE", "Computer Science Engineer"),
                            teacherCareerFactory, teacherCareerListFactory),
                    programmeCourseListFactory,
                    ICourseInStudyPlanFactory,
                    IStudyPlanListFactory,
                    IStudyPlanFactory,
                    ICourseFactory);

            programme.addCourseToAProgramme(c1);
            programme.addCourseToAProgramme(c2);

            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

            // Act + Assert
            assertEquals(2, controller.getCoursesInProgramme(programmeEdition).size());
        }

        @Test
        void shouldReturnNotNullEvenIfCourseListIsEmptyInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
            // Arrange
            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
            courseRepository.registerCourse("Informatica", "INF", 6, 1);
            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
            Acronym acronym = new Acronym("CC");
            QuantEcts quantEcts = new QuantEcts(20);
            QuantSemesters quantSemesters = new QuantSemesters(6);
            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
                    new DegreeType("Master", 240),
                    new Department("CSE", "Computer Science Engineer"),
                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                            teacherCategory, 70,
                            new Department("CSE", "Computer Science Engineer"),
                            teacherCareerFactory, teacherCareerListFactory),
                    programmeCourseListFactory,
                    ICourseInStudyPlanFactory,
                    IStudyPlanListFactory,
                    IStudyPlanFactory,
                    ICourseFactory);

            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);


            // Act + Assert
            assertNotNull(controller.getCoursesInProgramme(programmeEdition));
        }

        @Test
        void shouldReturnTrueIfCourseListHasCourseInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
            // Arrange
            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
            courseRepository.registerCourse("Informatica", "INF", 6, 1);
            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
            Acronym acronym = new Acronym("CC");
            QuantEcts quantEcts = new QuantEcts(20);
            QuantSemesters quantSemesters = new QuantSemesters(6);
            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
                    new DegreeType("Master", 240),
                    new Department("CSE", "Computer Science Engineer"),
                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                            teacherCategory, 70,
                            new Department("CSE", "Computer Science Engineer"),
                            teacherCareerFactory, teacherCareerListFactory),
                    programmeCourseListFactory,
                    ICourseInStudyPlanFactory,
                    IStudyPlanListFactory,
                    IStudyPlanFactory,
                    ICourseFactory);

            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

            Course c1 = new Course ("Informatica", "INF", 6, 1);
            Course c2 = new Course("Matemática", "MAT", 4, 1);

            programme.addCourseToAProgramme(c1);
            programme.addCourseToAProgramme(c2);

            // Act + Assert
            assertTrue(controller.getCoursesInProgramme(programmeEdition).contains(c1));
        }

        @Test
        void shouldReturnFalseIfCourseListNotHaveCourseInProgrammeForGetCoursesInProgrammeMethod_integration() throws Exception {
            // Arrange
            CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();
            CourseRepository courseRepository = new CourseRepository(ICourseFactory, courseListFactoryImpl);
            courseRepository.registerCourse("Informatica", "INF", 6, 1);
            courseRepository.registerCourse("Matemática", "MAT", 4, 1);
            Acronym acronym = new Acronym("CC");
            QuantEcts quantEcts = new QuantEcts(20);
            QuantSemesters quantSemesters = new QuantSemesters(6);
            NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");

            programmeRepository.registerProgramme(name, acronym, quantEcts, quantSemesters,
                    new DegreeType("Master", 240),
                    new Department("CSE", "Computer Science Engineer"),
                    new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                            "Doutoramento em Engenharia Informatica, 2005, ISEP",
                            "Rua das Flores", "4444-098", "Porto", "Portugal", addressFactory, "15-04-2005",
                            teacherCategory, 70,
                            new Department("CSE", "Computer Science Engineer"),
                            teacherCareerFactory, teacherCareerListFactory),
                    programmeCourseListFactory,
                    ICourseInStudyPlanFactory,
                    IStudyPlanListFactory,
                    IStudyPlanFactory,
                    ICourseFactory);

            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

            Course c1 = new Course ("Informatica", "INF", 6, 1);
            Course c2 = new Course("Matemática", "MAT", 4, 1);

            programme.addCourseToAProgramme(c1);

            ProgrammeEdition programmeEdition = new ProgrammeEdition(programme,schoolYear);
            programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

            // Act + Assert
            assertFalse(controller.getCoursesInProgramme(programmeEdition).contains(c2));
        }

    }