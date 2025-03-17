package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.CourseEditionEnrolmentRepository;
import PAI.repository.CourseEditionRepository;
import PAI.repository.ProgrammeEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrolAStudentInACourseEditionControllerTest {

    //testing constructor of US16 controller
    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        CourseEditionEnrolmentRepository doubleCeeRepository = mock (CourseEditionEnrolmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock (CourseEditionRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(doubleCeeRepository, null, doubleCourseEditionRepository);
        });

        //assert
        assertEquals("Programme edition enrolment repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        CourseEditionEnrolmentRepository doubleCeeRepository = mock (CourseEditionEnrolmentRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock (ProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(doubleCeeRepository, doublePeeRepository, null);
        });

        //assert
        assertEquals("Course edition repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        CourseEditionRepository doubleCeRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock (ProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(null, doublePeeRepository, doubleCeRepository);
        });

        //assert
        assertEquals("Course edition enrolment repository cannot be null!", exception.getMessage());
    }

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnOptionalEmptyWhenStudentIsNull(){
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        CourseEditionEnrolmentRepository doubleCeeRepository = mock(CourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        //act
        Optional<List<ProgrammeEdition>> result = controller.findProgrammeEditionsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled (){
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        CourseEditionEnrolmentRepository doubleCeeRepository = mock(CourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        Student doubleStudent = mock(Student.class);
        ProgrammeEdition doublePee1 = mock (ProgrammeEdition.class);
        ProgrammeEdition doublePee2 = mock (ProgrammeEdition.class);
        ProgrammeEdition doublePee3 = mock (ProgrammeEdition.class);


        when (doublePeeRepository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudent)).
                thenReturn(List.of(doublePee1,doublePee2,doublePee3));

        //act
        Optional<List<ProgrammeEdition>> result = controller.findProgrammeEditionsThatStudentIsEnrolled(doubleStudent);

        //assert
        assertEquals(3, result.get().size());
    }

    //testing find Course Editions by Programme Edition Method
    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition(){
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        CourseEditionEnrolmentRepository doubleCeeRepository = mock(CourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        ProgrammeEdition doubleProgrammeEdition = mock (ProgrammeEdition.class);
        CourseEdition doubleCourseEdition1 = mock (CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock (CourseEdition.class);

        when (doubleCourseEditionRepository.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition)).thenReturn(List.of(doubleCourseEdition1, doubleCourseEdition2));

        //act
        List<CourseEdition> result = controller.findCourseEditionsByProgrammeEdition(doubleProgrammeEdition);

        //assert
        assertEquals (2, result.size());
    }

    //testing enroll a student in a course edition method
    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment () {

        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        CourseEditionEnrolmentRepository doubleCeeRepository = mock(CourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepository , doublePeeRepository, doubleCourseEditionRepository);

        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);

        when (doubleCeeRepository .enrolStudentInACourseEdition(doubleSt1,doubleCe1)).thenReturn (true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleSt1,doubleCe1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIsAlreadyEnrolledInCourseEdition () {
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrolmentRepository doublePeeRepository = mock(ProgrammeEditionEnrolmentRepository.class);
        CourseEditionEnrolmentRepository doubleCeeRepository = mock(CourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        Student doubleStudent = mock (Student.class);
        CourseEdition doubleCe = mock (CourseEdition.class);

        when (doubleCeeRepository.enrolStudentInACourseEdition(doubleStudent,doubleCe)).thenReturn(false);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleStudent, doubleCe);

        //assert
        assertFalse(result);
    }

    //integration tests

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnOptionalEmptyWhenStudentIsNull_IntegrationTest(){
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository ceeRepository = new CourseEditionEnrolmentRepository(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        //act
        Optional<List<ProgrammeEdition>> result = controller.findProgrammeEditionsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository ceeRepository = new CourseEditionEnrolmentRepository(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student st1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        AddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, "15-04-2005", tc, 70, dpt1, tcpFactory, tcpLF
        );

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, dpt1, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        SchoolYear schoolYear1 = new SchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme1, schoolYear1);

        SchoolYear schoolYear2 = new SchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme1, schoolYear2);

        peeRepository.enrollStudentInProgrammeEdition(st1,programmeEdition1);
        peeRepository.enrollStudentInProgrammeEdition(st1,programmeEdition2);

        //act
        Optional<List<ProgrammeEdition>> result = controller.findProgrammeEditionsThatStudentIsEnrolled(st1);

        //assert
        assertEquals(2, result.get().size());
    }

    //testing find Course Editions by Programme Edition Method
    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition_IntegrationTest() throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository ceeRepository = new CourseEditionEnrolmentRepository(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        AddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, "15-04-2005", tc, 70, dpt1, tcpFactory, tcpLF
        );

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, dpt1, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        SchoolYear schoolYear1 = new SchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme1, schoolYear1);

        Course course1 = new Course ("Informatics", "INF", 6, 1);
        Course course2 = new Course ("Mechanics", "MFN", 6, 1);

        courseEditionRepository.createAndSaveCourseEdition(course1,programmeEdition1);
        courseEditionRepository.createAndSaveCourseEdition(course2,programmeEdition1);

        //act
        List<CourseEdition> result = controller.findCourseEditionsByProgrammeEdition(programmeEdition1);

        //assert
        assertEquals (2, result.size());
    }

    //testing enroll a student in a course edition
    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository ceeRepository = new CourseEditionEnrolmentRepository(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student st1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Student st2 = new Student("1234367", "Joana", "123556789", "963741369", "joana@gmail.com", address1);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        AddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, "15-04-2005", tc, 70, dpt1, tcpFactory, tcpLF
        );

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, dpt1, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        SchoolYear schoolYear1 = new SchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme1, schoolYear1);

        CourseEdition ce1 = new CourseEdition(course1, programmeEdition1);

        courseEditionRepository.createAndSaveCourseEdition(course1,programmeEdition1);
        ceeRepository.enrolStudentInACourseEdition(st2,ce1);

        //act
        boolean result = controller.enrolStudentInCourseEdition(st1,ce1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsAlreadyEnrolledInCourseEdition_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository ceeRepository = new CourseEditionEnrolmentRepository(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student st1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        AddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, "15-04-2005", tc, 70, dpt1, tcpFactory, tcpLF
        );

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, dpt1, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        SchoolYear schoolYear1 = new SchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme1, schoolYear1);

        CourseEdition ce1 = new CourseEdition(course1, programmeEdition1);

        courseEditionRepository.createAndSaveCourseEdition(course1,programmeEdition1);
        ceeRepository.enrolStudentInACourseEdition(st1,ce1);

        //act
        boolean result = controller.enrolStudentInCourseEdition(st1,ce1);

        //assert
        assertFalse(result);
    }
}