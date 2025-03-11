package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.CourseEditionEnrollmentRepository;
import PAI.repository.CourseEditionRepository;
import PAI.repository.ProgrammeEditionEnrollmentRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrollAStudentInACourseEditionControllerTest {

    //testing constructor of US16 controller
    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepoIsNull (){
        //arrange
        CourseEditionEnrollmentRepository doubleCeeRepository = mock (CourseEditionEnrollmentRepository.class);
        CourseEditionRepository doubleCourseEditionRepository = mock (CourseEditionRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(doubleCeeRepository, null, doubleCourseEditionRepository);
        });

        //assert
        assertEquals("Programme edition enrollment repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        CourseEditionEnrollmentRepository doubleCeeRepository = mock (CourseEditionEnrollmentRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock (ProgrammeEditionEnrollmentRepo.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(doubleCeeRepository, doublePeeRepository, null);
        });

        //assert
        assertEquals("Course edition repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        CourseEditionRepository doubleCeRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock (ProgrammeEditionEnrollmentRepo.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrollAStudentInACourseEditionController(null, doublePeeRepository, doubleCeRepository);
        });

        //assert
        assertEquals("Course edition enrollment repository cannot be null!", exception.getMessage());
    }

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnOptionalEmptyWhenStudentIsNull(){
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
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
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
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
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
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
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository , doublePeeRepository, doubleCourseEditionRepository);

        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);

        when (doubleCeeRepository .enrollStudentInACourseEdition(doubleSt1,doubleCe1)).thenReturn (true);

        //act
        boolean result = controller.enrollStudentInCourseEdition(doubleSt1,doubleCe1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIsAlreadyEnrolledInCourseEdition () {
        //arrange
        CourseEditionRepository doubleCourseEditionRepository = mock(CourseEditionRepository.class);
        ProgrammeEditionEnrollmentRepo doublePeeRepository = mock(ProgrammeEditionEnrollmentRepo.class);
        CourseEditionEnrollmentRepository doubleCeeRepository = mock(CourseEditionEnrollmentRepository.class);

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(
                doubleCeeRepository, doublePeeRepository, doubleCourseEditionRepository);

        Student doubleStudent = mock (Student.class);
        CourseEdition doubleCe = mock (CourseEdition.class);

        when (doubleCeeRepository.enrollStudentInACourseEdition(doubleStudent,doubleCe)).thenReturn(false);

        //act
        boolean result = controller.enrollStudentInCourseEdition (doubleStudent, doubleCe);

        //assert
        assertFalse(result);
    }

    //integration tests

    //auxiliary methods
    private ProgrammeEditionEnrollmentRepo setUpProgrammeEditionEnrollmentRepo(){
        ProgrammeEditionEnrollmentFactory peeFactory = new ProgrammeEditionEnrollmentFactory();
        ProgrammeEditionEnrolmentListFactory peeListFactory = new ProgrammeEditionEnrolmentListFactory();
        return new ProgrammeEditionEnrollmentRepo(peeFactory, peeListFactory);
    }

    private CourseEditionEnrollmentRepository setUpCourseEditionEnrollmentRepository () {
        CourseEditionEnrollmentFactory ceeFactory = new CourseEditionEnrollmentFactory();
        CourseEditionEnrollmentListFactory ceeListFactory = new CourseEditionEnrollmentListFactory();
        return new CourseEditionEnrollmentRepository(ceeFactory, ceeListFactory);
    }

    private CourseEditionRepository setUpCourseEditionRepository() {
        CourseEditionFactoryImpl ceFactory = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl ceListFactory = new CourseEditionListFactoryImpl();
        return new CourseEditionRepository(ceFactory, ceListFactory);
    }

    private Programme setUpProgramme () throws Exception {
        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        AddressFactory addressFactory = new AddressFactory();
        TeacherCategory tc = new TeacherCategory("Assistant Teacher");
        TeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory tcpLF = new TeacherCareerProgressionListFactory();

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
        CourseFactory courseFactory = new CourseFactory();

        return new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, dpt1, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactory
        );

    }
    private ProgrammeEdition setUpProgrammeEdition() throws Exception {
        Programme programme1 = setUpProgramme();

        SchoolYear schoolYear1 = new SchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        return new ProgrammeEdition(programme1, schoolYear1);
    }

    private Student setUpStudent () {
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        return new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);
    }


    //testing find Programme Editions that Student is Enrolled Method

    @Test
    void shouldReturnOptionalEmptyWhenStudentIsNull_IntegrationTest(){
        //arrange
        ProgrammeEditionEnrollmentRepo peeRepository = setUpProgrammeEditionEnrollmentRepo();
        CourseEditionEnrollmentRepository ceeRepository = setUpCourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = setUpCourseEditionRepository();

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        //act
        Optional<List<ProgrammeEdition>> result = controller.findProgrammeEditionsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrollmentRepo peeRepository = setUpProgrammeEditionEnrollmentRepo();
        CourseEditionEnrollmentRepository ceeRepository = setUpCourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = setUpCourseEditionRepository();

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        Student st1 = setUpStudent();

        ProgrammeEdition programmeEdition1 = setUpProgrammeEdition();

        Programme programme1 = setUpProgramme();

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
        ProgrammeEditionEnrollmentRepo peeRepository = setUpProgrammeEditionEnrollmentRepo();
        CourseEditionEnrollmentRepository ceeRepository = setUpCourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = setUpCourseEditionRepository();

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        ProgrammeEdition programmeEdition1 = setUpProgrammeEdition();

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
        ProgrammeEditionEnrollmentRepo peeRepository = setUpProgrammeEditionEnrollmentRepo();
        CourseEditionEnrollmentRepository ceeRepository = setUpCourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = setUpCourseEditionRepository();

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        Student st1 = setUpStudent();

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student st2 = new Student("1234367", "Joana", "123556789", "963741369", "joana@gmail.com", address1);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        ProgrammeEdition programmeEdition1 = setUpProgrammeEdition();

        CourseEdition ce1 = new CourseEdition(course1, programmeEdition1);

        courseEditionRepository.createAndSaveCourseEdition(course1,programmeEdition1);
        ceeRepository.enrollStudentInACourseEdition(st2,ce1);

        //act
        boolean result = controller.enrollStudentInCourseEdition(st1,ce1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsAlreadyEnrolledInCourseEdition_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrollmentRepo peeRepository = setUpProgrammeEditionEnrollmentRepo();
        CourseEditionEnrollmentRepository ceeRepository = setUpCourseEditionEnrollmentRepository();
        CourseEditionRepository courseEditionRepository = setUpCourseEditionRepository();

        US16_EnrollAStudentInACourseEditionController controller = new US16_EnrollAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        Student st1 = setUpStudent();

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        ProgrammeEdition programmeEdition1 = setUpProgrammeEdition();

        CourseEdition ce1 = new CourseEdition(course1, programmeEdition1);

        courseEditionRepository.createAndSaveCourseEdition(course1,programmeEdition1);
        ceeRepository.enrollStudentInACourseEdition(st1,ce1);

        //act
        boolean result = controller.enrollStudentInCourseEdition(st1,ce1);

        //assert
        assertFalse(result);
    }
}