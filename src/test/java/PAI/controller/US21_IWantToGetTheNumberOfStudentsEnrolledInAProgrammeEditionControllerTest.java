package PAI.controller;

import PAI.VOs.*;

import PAI.factory.*;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import PAI.repository.ProgrammeEditionEnrolmentRepositoryImpl;

import org.junit.jupiter.api.Test;

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
        IProgrammeEditionEnrolmentRepository repo = mock(IProgrammeEditionEnrolmentRepository.class);
        //Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(repo);
        //Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryIsNull() {
        //SUT Controller
        //Arrange
        //Act + Assert
        assertThrows(Exception.class, () -> new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(null));
    }

    @Test
    void shouldCreateIWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController_Integration_Test() {
        // Arrange
        //Controlador recebia programmmeEdition Repo
//        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        IProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        IProgrammeEditionEnrolmentFactory programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);

        // Act
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentRepository);

        // Assert
        assertNotNull(us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController);
    }


//    @Test
//    void shouldReturnSizeOneIfOnlyOneProgrammeEditionInList_IsolatedTest(){
//        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
//        // Arrange
//            //Doubles' instantiation
//        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
//        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepositoryDouble = mock(ProgrammeEditionEnrolmentRepository.class);
//
//        ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
//        programmeEditionList.add(programmeEditionDouble);
//
//            // SUT
//        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
//                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrolmentRepositoryDouble);
//
//            //Instructions
//        when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);
//
//        // Act
//        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();
//
//        // Assert
//        assertEquals(1, result.size());
//    }

//        @Test
//        void shouldReturnProgrammeEditionInList_IsolatedTest(){
//            //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
//            // Arrange
//                //Doubles' instantiation
//            ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
//            ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
//            ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepositoryDouble = mock(ProgrammeEditionEnrolmentRepository.class);
//
//            ArrayList<ProgrammeEdition> programmeEditionList = new ArrayList<>();
//            programmeEditionList.add(programmeEditionDouble);
//
//                // SUT
//            US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
//                    new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepositoryDouble, programmeEditionEnrolmentRepositoryDouble);
//
//                //Instructions
//            when(programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(programmeEditionList);
//
//            // Act
//            List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();
//
//            // Assert
//            assertEquals(1, result.size());
//            assertEquals(programmeEditionDouble, result.get(0));
//        }

    @Test
    void shouldReturnCorrectNumberOfStudentsEnrolledInAProgrammeEdition_IsolatedTest() throws Exception {
        //SUT = IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController -> all else as Double
        // Arrange
        //Doubles' instantiation
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepositoryDouble = mock(IProgrammeEditionEnrolmentRepository.class);
        //ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        //SUT
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentRepositoryDouble);

        //Instructions
        when(programmeEditionEnrolmentRepositoryDouble.getTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionIDDouble)).thenReturn(1);

        // Act
        int result = controller.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionIDDouble);
        // Assert
        assertEquals(1, result);
    }


    @Test
    void shouldGetTheNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception {
        // Arrange
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        UniqueNumber uniqueNumberDouble2= mock(UniqueNumber.class);
        NIF nifDouble2 = mock(NIF.class);
        StudentID studentID2 = new StudentID(uniqueNumberDouble2, nifDouble2);
        UniqueNumber uniqueNumberDouble3= mock(UniqueNumber.class);
        NIF nifDouble3 = mock(NIF.class);
        StudentID studentID3 = new StudentID(uniqueNumberDouble3, nifDouble3);
        UniqueNumber uniqueNumberDouble4= mock(UniqueNumber.class);
        NIF nifDouble4 = mock(NIF.class);
        StudentID studentID4 = new StudentID(uniqueNumberDouble4, nifDouble4);

        IProgrammeEditionEnrolmentFactory programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactoryImpl);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeName1, programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();
        SchoolYearID schoolYearID2 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentRepository);

        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID, programmeEditionID1);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID2, programmeEditionID1);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID3, programmeEditionID1);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID4, programmeEditionID2);

        // Act
        int result = controller1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1);

        // Assert
        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroIfProgrammeEditionHasZeroStudentsEnrolled() throws Exception {
        //Arrange

        IProgrammeEditionEnrolmentFactory programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactoryImpl);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeName1, programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentRepository);

        // Act
        int result = controller1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1);

        // Assert
        assertEquals(0, result);
    }
//
    @Test
    void shouldReturnZeroIfCheckingNumberOfStudentsInDifferentProgrammeEdition() throws Exception {
        // Arrange
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        UniqueNumber uniqueNumberDouble2= mock(UniqueNumber.class);
        NIF nifDouble2 = mock(NIF.class);
        StudentID studentID2 = new StudentID(uniqueNumberDouble2, nifDouble2);
        UniqueNumber uniqueNumberDouble3= mock(UniqueNumber.class);
        NIF nifDouble3 = mock(NIF.class);
        StudentID studentID3 = new StudentID(uniqueNumberDouble3, nifDouble3);
        UniqueNumber uniqueNumberDouble4= mock(UniqueNumber.class);
        NIF nifDouble4 = mock(NIF.class);
        StudentID studentID4 = new StudentID(uniqueNumberDouble4, nifDouble4);

        IProgrammeEditionEnrolmentFactory programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactoryImpl);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeName1, programmeAcronym1);

        SchoolYearID schoolYearID1 = new SchoolYearID();
        SchoolYearID schoolYearID2 = new SchoolYearID();

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController controller1 =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentRepository);

        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID, programmeEditionID1);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID2, programmeEditionID1);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID3, programmeEditionID1);
        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID4, programmeEditionID1);

        // Act
        int result = controller1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID2);

        // Assert
        assertEquals(0, result);
        assertEquals(4, controller1.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionID1));
    }

    @Test
    void shouldThrowExceptionIfGivenProgrammeEditionIDIsNull() throws Exception {
        // Arrange
        //Quando o controller implementar a lista de programmeEdition
        //IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        //ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
        //ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
        IProgrammeEditionEnrolmentFactory programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
        IProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactoryImpl = new ProgrammeEditionEnrolmentListFactoryImpl();
        IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepositoryImpl(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactoryImpl);
        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionEnrolmentRepository);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.iWantToGetTheNumberOfStudentsEnrolledInAProgrammeEdition(null);
        });
    }

//Quando o controller implementar a lista de programmeEdition
//    @Test
//    void shouldThrowExceptionIfProgrammeEditionEnrollmentRepositoryIsNull() throws Exception {
//        // Arrange
//        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//
//        // Act + Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, null);
//        });
//    }


//Testes para a listaProgrammeEdition
//    @Test
//    void shouldReturnSizeOneIfOnlyOneProgrammeEditionInList() throws Exception {
//        // Arrange
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date("20-01-2024");
//        Date endDate = new Date("23-02-2024");
//        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
//        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
//                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
//                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
//        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
//        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
//        CourseFactoryImpl courseFactory = new CourseFactoryImpl();
//
//        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
//                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
//        programmeEditionRepository.createProgrammeEdition(p1, sy1);
//
//        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
//                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);
//
//        // Act
//        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();
//
//        // Assert
//        assertEquals(1, result.size());
//    }

//    @Test
//    void shouldReturnProgrammeEditionInList() throws Exception {
//        // Arrange
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date("20-01-2024");
//        Date endDate = new Date("23-02-2024");
//        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
//        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
//                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
//                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
//        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
//        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
//        CourseFactoryImpl courseFactory = new CourseFactoryImpl();
//
//        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
//                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
//        programmeEditionRepository.createProgrammeEdition(p1, sy1);
//        ProgrammeEdition programmeEdition = programmeEditionRepository.getAllProgrammeEditions().get(0);
//
//        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
//                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);
//
//        // Act
//        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();
//
//        // Assert
//        assertEquals(1, result.size());
//        assertEquals(programmeEdition, result.get(0));
//    }

//    @Test
//    void shouldReturnAllProgrammeEdition() throws Exception {
//        // Arrange
//        IAddressFactory addressFactory = new AddressFactoryImpl();
//        Description description = new Description("School Year 24/25");
//        Date startDate = new Date("20-01-2024");
//        Date endDate = new Date("23-01-2024");
//        SchoolYear sy1 = new SchoolYear(description, startDate, endDate);
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
//        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
//        Date date = new Date("20-12-2010");
//        TeacherCategoryID tcID = new TeacherCategoryID();
//        WorkingPercentage wp = new WorkingPercentage(100);
//        TeacherID teacherID = TeacherID.createNew();
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
//                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
//                "4249-015", "Porto", "Portugal", addressFactory, date, tcID,
//                wp, teacherID, CSE, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
//        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
//        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
//        CourseFactoryImpl courseFactory = new CourseFactoryImpl();
//
//        ProgrammeEditionFactoryImpl programmeEditionFactory = new ProgrammeEditionFactoryImpl();
//        ProgrammeEditionListFactoryImpl programmeEditionListFactory = new ProgrammeEditionListFactoryImpl();
//        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory, programmeEditionListFactory);
//        ProgrammeEditionEnrolmentFactoryImpl programmeEditionEnrollmentFactoryImpl = new ProgrammeEditionEnrolmentFactoryImpl();
//        ProgrammeEditionEnrolmentListFactoryImpl programmeEditionEnrolmentListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
//        ProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository = new ProgrammeEditionEnrolmentRepository(programmeEditionEnrollmentFactoryImpl, programmeEditionEnrolmentListFactory);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher,
//                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
//        programmeEditionRepository.createProgrammeEdition(p1, sy1);
//        Programme p2 = new Programme("Physics", "Phy", 20, 6, master, CSE, teacher,
//                programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
//        programmeEditionRepository.createProgrammeEdition(p2, sy1);
//
//        US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController =
//                new US21_IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController(programmeEditionRepository, programmeEditionEnrolmentRepository);
//
//        // Act
//        List<ProgrammeEdition> result = us21IWantToGetTheNumberOfStudentsEnrolledInAProgrammeEditionController.getAllProgrammeEditions();
//
//        // Assert
//        assertEquals(2, result.size());
//        assertEquals(programmeEditionRepository.getAllProgrammeEditions().get(0), result.get(0));
//        assertEquals(programmeEditionRepository.getAllProgrammeEditions().get(1), result.get(1));
//    }
}