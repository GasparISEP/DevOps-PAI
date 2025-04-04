package PAI.controller;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ProgrammeEditionEnrolmentRepositoryImpl;
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
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull_IsolatedTest(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImplDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null, programmeEditionEnrolmentRepositoryImplDouble));

    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionEnrollmentRepositoryIsNull_IsolatedTest(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble,null));

    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeEditionInList_IsolatedTest(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImplDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEditionDouble);

            // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrolmentRepositoryImplDouble);

            //Instructions
        when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnProgrammeEditionInList_IsolatedTest(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImplDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEditionDouble);

            // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrolmentRepositoryImplDouble);

            //Instructions
        when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());
        assertEquals(programmeEditionDouble, result.get(0));
    }

    @Test
    void shouldReturnAllProgrammeEdition_IsolatedTest(){
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImplDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ProgrammeEdition programmeEdition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2Double = mock(ProgrammeEdition.class);

        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
        programmeEditionList.add(programmeEdition1Double);
        programmeEditionList.add(programmeEdition2Double);

            // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrolmentRepositoryImplDouble);

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
    void shouldReturnCorrectNumberOfStudentsEnrolledInAProgrammeEdition_IsolatedTest() throws Exception{
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImplDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionID programmeEditionDouble = mock(ProgrammeEditionID.class);

            //SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble , programmeEditionEnrolmentRepositoryImplDouble);

            //Instructions
        when(programmeEditionEnrolmentRepositoryImplDouble.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble)).thenReturn(1);

        // Act
        int result = controller.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionDouble);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull_IsolatedTest() {
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImplDouble = mock(ProgrammeEditionEnrolmentRepositoryImpl.class);

            // SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrolmentRepositoryImplDouble);

        // Act + Assert
        assertThrows(Exception.class, () -> {
            us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(null);
        });
    }

    @Test
    void shouldCreateIWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(){
        // Arrange
        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImpl = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        // Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepositoryImpl);

        // Assert
        assertNotNull(us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController);
    }

    /*
    @Test
    void shouldGetTheNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception{
        // Arrange
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");

        StudentID studentID = new StudentID(1111111);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("123456789", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student st1 = new Student(studentID, name, nif, phone, email, add1, academicEmail);
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("20-01-2024");
        Date endDate = new Date ("23-02-2024");
        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactory = new CourseFactoryImpl();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);

        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        // Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controlador1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(st1, pe1);
        int result = controlador1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(1, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() throws Exception{
        // Arrange
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("20-01-2024");
        Date endDate = new Date ("23-02-2024");
        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactory = new CourseFactoryImpl();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);

        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        // Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controlador1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        int result = controlador1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void shouldReturnZeroIfCheckingNumberOfStudentsInDifferentProgrammeEdition() throws Exception{
        // Arrange
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");

        StudentID studentID = new StudentID(1111111);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("123456789", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student st1 = new Student(studentID, name, nif, phone, email, add1, academicEmail);
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("20-01-2024");
        Date endDate = new Date ("23-02-2024");
        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactory = new CourseFactoryImpl();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        Programme p2 = new Programme("Physics", "Phy", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        ProgrammeEdition pe2 = new ProgrammeEdition(p2, sy1);

        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        // Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controlador1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(st1, pe1);
        int result = controlador1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(pe2);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull() throws Exception {
        // Arrange
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null, programmeEditionEnrolmentRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionEnrollmentRepositoryIsNull() throws Exception {
        // Arrange
        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, null);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIsNull() throws Exception {
        // Arrange
        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(null);
        });
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeEditionInList() throws Exception{
        // Arrange
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("20-01-2024");
        Date endDate = new Date ("23-02-2024");
        SchoolYear sy1 = new SchoolYear( description, startDate, endDate);
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactory = new CourseFactoryImpl();

        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        programmeEditionRepository.createProgrammeEdition(p1, sy1);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnProgrammeEditionInList() throws Exception{
        // Arrange
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("20-01-2024");
        Date endDate = new Date ("23-02-2024");
        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactory = new CourseFactoryImpl();

        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        programmeEditionRepository.createProgrammeEdition(p1, sy1);
        ProgrammeEdition programmeEdition = programmeEditionRepository.getAllProgrammeEditions().get(0);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(1, result.size());
        assertEquals(programmeEdition, result.get(0));
    }

    @Test
    void shouldReturnAllProgrammeEdition() throws Exception{
        // Arrange
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("20-01-2024");
        Date endDate = new Date ("23-01-2024");
        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactory = new CourseFactoryImpl();

        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        programmeEditionRepository.createProgrammeEdition(p1, sy1);
        Programme p2 = new Programme("Physics", "Phy", 20, 6, master, CSE, teacher,
                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        programmeEditionRepository.createProgrammeEdition(p2, sy1);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);

        // Act
        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();

        // Assert
        assertEquals(2, result.size());
        assertEquals(programmeEditionRepository.getAllProgrammeEditions().get(0), result.get(0));
        assertEquals(programmeEditionRepository.getAllProgrammeEditions().get(1), result.get(1));
    }
    */
}