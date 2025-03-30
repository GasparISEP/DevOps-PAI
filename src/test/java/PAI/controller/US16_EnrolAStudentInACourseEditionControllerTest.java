package PAI.controller;

import PAI.VOs.*;
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
        StudentID studentID1 = new StudentID(1234567);
        Name name = new Name("Rita");
        NIF nif = new NIF("123456789");
        PhoneNumber phone = new PhoneNumber("+351","963741258");
        Email email = new Email("rita@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID1);

        Student st1 = new Student(studentID1, name, nif, phone, email, address1, academicEmail);

        Date date = new Date("15-04-2005");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(70);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, date, tcID, wp, teacherID, dpt1, tcpFactory, tcpLF
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

        Description description = new Description("School Year 23/24");
        SchoolYearID schoolYearID = new SchoolYearID();
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");
        SchoolYear schoolYear1 = new SchoolYear(schoolYearID,description, startDate, endDate);

        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme1, schoolYear1);

        SchoolYear schoolYear2 = new SchoolYear(schoolYearID, description, startDate, endDate);
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme1, schoolYear2);

        peeRepository.enrolStudentInProgrammeEdition(st1,programmeEdition1);
        peeRepository.enrolStudentInProgrammeEdition(st1,programmeEdition2);

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

        Date date = new Date("15-04-2005");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(70);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, date, tcID, wp, teacherID, dpt1, tcpFactory, tcpLF
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

        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 23/24");
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");
        SchoolYear schoolYear1 = new SchoolYear(schoolYearID,description, startDate, endDate);

        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme1, schoolYear1);

        Course course1 = new Course ("Informatics", "INF", 6, 1);
        Course course2 = new Course ("Mechanics", "MFN", 6, 1);

        programme1.addCourseToAProgramme(course1);
        programme1.addCourseToAProgramme(course2);
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

        StudentID studentID1 = new StudentID(1234567);

        Name name = new Name("Rita");
        NIF nif = new NIF("123456789");
        PhoneNumber phone = new PhoneNumber("+351","963741258");
        Email email = new Email("rita@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID1);

        Student st1 = new Student(studentID1, name, nif, phone, email, address1, academicEmail);

        StudentID studentID2 = new StudentID(1234367);
        Name name2 = new Name("Rita");
        NIF nif2 = new NIF("123456789");
        PhoneNumber phone2 = new PhoneNumber("+351","963741258");
        Email email2 = new Email("rita@gmail.com");
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID1);

        Student st2 = new Student(studentID2, name2, nif2, phone2, email2, address1, academicEmail2);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        Date date = new Date("15-04-2005");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(70);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, date, tcID, wp, teacherID, dpt1, tcpFactory, tcpLF
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

        programme1.addCourseToAProgramme(course1);

        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 23/24");
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");
        SchoolYear schoolYear1 = new SchoolYear(schoolYearID,description, startDate, endDate);

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
        StudentID studentID = new StudentID(1234567);
        Name name = new Name("Rita");
        NIF nif = new NIF("123456789");
        PhoneNumber phone = new PhoneNumber("+351","963741258");
        Email email = new Email("rita@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);
        Student st1 = new Student(studentID, name, nif, phone, email, address1, academicEmail);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        Date date = new Date("15-04-2005");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(70);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType degreeType = new DegreeType("Bachelor", 25);
        Department dpt1 = new Department("DEI", "Department1");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactoryImpl tcpFactory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactoryImpl tcpLF = new TeacherCareerProgressionListFactoryImpl();

        Teacher t1 = new Teacher(
                "ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores", "4444-098", "Porto", "Portugal",
                addressFactory, date, tcID, wp, teacherID, dpt1, tcpFactory, tcpLF
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

        programme1.addCourseToAProgramme(course1);

        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 23/24");
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");
        SchoolYear schoolYear1 = new SchoolYear(schoolYearID,description, startDate, endDate);

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